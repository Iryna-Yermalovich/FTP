import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Utilities {
	
	private static FTPClient client;
	private static final String host = "ftp.mccme.ru";
	
	public static synchronized FTPClient getInstance() throws IOException {
		if (client == null) {
			client = new FTPClient();
			client.enterLocalPassiveMode();
			client.connect(host);
			boolean loginSuccess = client.login("anonymous", "anonymous");
			client.enterLocalPassiveMode();

			if (!loginSuccess) {
				System.out.println("Exception in connecting to FTP Server.");
			}
		}
		return client;
	}
	
	public void listDirectory() throws Exception {
		FTPFile[] subFiles = client.listFiles();
		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();

				if (aFile.isDirectory()) {
					System.out.println("[" + currentFileName + "]");
				} else {
					System.out.println(currentFileName + "\t" + aFile.getSize());
				}
			}
		}
	}

	public void downloadFile(String nameFile) throws IOException {
		File folder = new File("Download");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File downloadFile1 = new File(folder + "/" + nameFile + "/");
		//if (downloadFile1.exists()) { downloadFile1 = new File(folder + "/" + nameFile + "/"); }
		 
		OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		boolean success = client.retrieveFile(nameFile, outputStream1);
		outputStream1.close();
		if (!success) {
			System.out.println("The file " + nameFile + " is not downloaded!");
		} else {
			System.out.println("File " + nameFile + " has been downloaded successfully!");
		}
	}
}
