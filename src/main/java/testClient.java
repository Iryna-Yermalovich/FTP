import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class testClient {

	public static void main(String[] args) throws Exception {
		FTPClient client = Utilities.getInstance();
		Utilities util = new Utilities();
		String patternDir = null;
		String rootDir = client.printWorkingDirectory();
		util.listDirectory();
		String[] mycomm = null;
		while (true) {
			System.out.println("------------------------------\n> ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String str = in.readLine();

			mycomm = str.split(" ");
			/*
			 * if (mycomm[1].equals("")) { System.out.println(
			 * "Uncorrect command"); } else {
			 */
			switch (mycomm[0]) {

			case "cd": {
				patternDir = client.printWorkingDirectory();
				client.changeWorkingDirectory(mycomm[1].toString());
				util.listDirectory();
				break;
			}
			case "pd": {
				client.changeWorkingDirectory(patternDir);
				util.listDirectory();
				break;
			}
			case "d": {
				util.downloadFile(mycomm[1].toString());
				break;
			}
			case "rd": {
				client.changeWorkingDirectory(rootDir);
				util.listDirectory();
				break;
			}
			case "q": {
				System.out.println("exit");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Unknown command");
				break;
			}
			}
		}
	}
}
