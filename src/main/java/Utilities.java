import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Utilities {

	private static FTPClient client;
	private static String host = "ftp.mccme.ru";
	private static final String NAME_USER = "anonymous";
	private static final String PASS_USER = "anonymous";

	public static void setHost() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter FTP server: ");
		try {
			host = sc.nextLine();
		} catch (Exception ex) {
			System.err.println("Exception in connecting to FTP Server.");
		}
	}

	public static void getHelp() throws IOException {
		StringBuilder printHelp = new StringBuilder();
		printHelp.append("---------------------------------------------------------------------\n");
		printHelp.append("| help   				 |  Get help		\n");
		printHelp.append("| cd <name file>/<name directory>	 |  Go to the directory	 \n");
		printHelp.append("| pd					 |  Go to the pattern directory \n");
		printHelp.append("| d <name file>				 |  Download file				\n");
		printHelp.append("| q					 |  Quit						\n");
		printHelp.append("---------------------------------------------------------------------\n\n");
		System.out.print(printHelp);
	}

	public static FTPClient getInstance() throws IOException {
		if (client == null) {
			setHost();
			client = new FTPClient();
			client.enterLocalPassiveMode();
			client.connect(host);
			boolean loginSuccess = client.login(NAME_USER, PASS_USER);
			client.enterLocalPassiveMode();

			if (!loginSuccess) {
				System.out.println("Exception in connecting to FTP Server.");
			}
		}
		return client;
	}

	public static void getListDirectory() throws Exception {
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

	public static void downloadFile(String nameFile) throws IOException {
		File folder = new File("Download");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File downloadFile1 = new File(folder + "/" + nameFile + "/");
		OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		boolean success = client.retrieveFile(nameFile, outputStream1);
		outputStream1.close();
		if (!success) {
			System.err.println("The file " + nameFile + " is not downloaded!");
		} else {
			System.err.println("File " + nameFile + " has been downloaded successfully!");
		}
	}
}
