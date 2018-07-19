package edu.rice.pliny.apitrans.examples;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import org.apache.commons.mail.EmailException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import javax.mail.MessagingException;
import java.io.IOException;

import org.junit.Test;

/**
 * Connect to a FTP Server and then list the files in the default directory
 */
public class FTPClientTest {

    @Test
    public void list() throws IOException, EmailException {
        String username = "username";
        String password = "password";
        String host = "host";

        FTPClient f = new FTPClient();
        f.connect(host);
        f.login(username, password);
        FTPFile[] files = f.listFiles();
        f.disconnect();
    }

    @Test
    public void send2() throws IOException, EmailException, MessagingException {
        String username = "username";
        String password = "password";
        String host = "host";

        SSHClient ssh = new SSHClient();
        ssh.authPassword(username, password);
        ssh.connect(host);
        SFTPClient ftp = ssh.newSFTPClient();
        ftp.ls(".");
        ftp.close();
        ssh.close();
    }
}
