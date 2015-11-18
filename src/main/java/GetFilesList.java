import java.io.IOException;
 
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


 
public class GetFilesList {
 
	
    public static void main(String[] args) {
    	
        String server = "ftp.stat.duke.edu";
        String userId = "user";
        String pass = "password";
 
        FTPClient ftpClient = new FTPClient();
 try{
	 ftpClient.connect(server);
        if(!ftpClient.login(userId, pass))
        {
        	ftpClient.logout();
        }
        int reply = ftpClient.getReplyCode();
        //FTPReply stores a set of constants for FTP reply codes. 
        if (!FTPReply.isPositiveCompletion(reply))
        {
        	ftpClient.disconnect();
        }

        //enter passive mode
        ftpClient.enterLocalPassiveMode();
     /*   
        try {
        	ftpClient.enterLocalPassiveMode(); 
            ftpClient.connect(server);
 
            boolean sec = ftpClient.login(user, pass);
 
            ftpClient.enterLocalPassiveMode();
 
     //       String dirToSearch = "pub";
 
           
 
            //FTPFile[] result = ftpClient.listFiles(dirToSearch);
 
           // if (result != null && result.length > 0) {
            //    System.out.println("SEARCH RESULT:");
            //    for (FTPFile aFile : result) {
            //        System.out.println(aFile.getName());
             //   }
          //  }*/
 
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}