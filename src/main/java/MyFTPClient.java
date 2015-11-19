import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;

public class MyFTPClient {

	public static void main(String[] args) throws Exception {
		try {
			Utilities.getHelp();
			FTPClient client = Utilities.getInstance();
			Utilities.getListDirectory();
			while (true) {
				System.out.println("------------------------------\n> ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String str = in.readLine();

				String[] mycomm = str.split(" ");
				switch (mycomm[0]) {

				case "help": {
					Utilities.getHelp();
					break;
				}
				case "cd": {
					try {
						client.changeWorkingDirectory(mycomm[1].toString());
						Utilities.getListDirectory();
					} catch (Exception ex) {
						System.err.println("Enter file name or directory name!");
					}
					break;
				}
				case "pd": {
					client.changeToParentDirectory();
					Utilities.getListDirectory();
					break;
				}
				case "d": {
					try {
						Utilities.downloadFile(mycomm[1].toString());
					} catch (Exception ex) {
						System.err.println("Enter file name!");
					}
					break;
				}
				case "q": {
					System.out.println("exit");
					System.exit(0);
					break;
				}
				default: {
					System.err.println("Unknown command");
					break;
				}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
