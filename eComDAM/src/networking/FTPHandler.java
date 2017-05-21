/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @author JV
 */
public class FTPHandler {

    FTPClient ftpClient;
    String username = "b13_20119711";
    String password = "eCom1234";
    String hostname = "ftp.byethost13.com";

    public FTPHandler(String user, String password) {

        ftpClient = new FTPClient();
    }

    private void login() {
        try {
            ftpClient.connect(this.hostname);
            ftpClient.login(this.username, this.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FTPFile> getDirectory(){
        ftpClient.connect();
    }
}
