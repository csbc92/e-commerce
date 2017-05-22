/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @author JV
 */
public class FTPTool {

    FTPClient ftpClient;
    String username = "b33_20125183";
    String password = "ecom1234";
    String hostname = "ftp.byethost33.com";

    public FTPTool(String user, String password) {

        ftpClient = new FTPClient();

    }

//    public static void main(String[] args) {
//        FTPTool tool = new FTPTool("","");
//        tool.getfile();
//
//    }

    private void login() {
        try {
            ftpClient.connect(this.hostname);
            ftpClient.login(this.username, this.password);
            System.out.println("login - successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream retrieveFile(String path){
        try {
            login();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            if (ftpClient.isConnected()) {
               return ftpClient.retrieveFileStream(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            logout();
        }
        return null;
    }

    public void retrieveist(){
        login();
        ftpClient.enterLocalPassiveMode();
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ftpClient.isConnected()) {
            try {
                for (FTPFile ftpFile : ftpClient.listFiles("/htdocs/Files")) {
                    System.out.println(ftpFile.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
