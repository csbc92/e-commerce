/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.network;

import java.io.*;

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

    /**
     * Tool to communicate with a FTP server.
     * @param user the username
     * @param password the password
     */
    public FTPTool(String user, String password) {

        ftpClient = new FTPClient();

    }

//    public static void main(String[] args) {
//        FTPTool tool = new FTPTool("", "");
////        tool.retrieveist();
//        tool.retrieveFile("htdocs/files/victor.jpg");
//
//    }

    /**
     * Sign-in with the given username and password.
     * Must be done before interacting with the server.
     */
    private void login() {
        try {
            ftpClient.connect(this.hostname);
            ftpClient.login(this.username, this.password);
            System.out.println("login - successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logout after interacting with the server
     */
    private void logout() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the parent of the current working directory, and combines it with the given path.
     * @param path The path which is combined with the current working directory.
     * @return a file with the resulting path.
     */
    private File getParentDir(String path) {
        File currentDir;
        File newDir;
        currentDir = new File(System.getProperty("user.dir"));
        newDir = new File(currentDir.getParentFile(), path);
        return newDir;
    }

    /**
     * Retrieve a specific file from the FTP server.
     * @param path The path of the file to be downloaded.
     * @return the path where the file has been downloaded to.
     */
    public String retrieveFile(String path) {
        try {
            login();
            // Important to set the fileType and the mode before downloading files.
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            //
            if (ftpClient.isConnected()) {
                String directory = getParentDir(path).getAbsolutePath();
                FileOutputStream fos = new FileOutputStream(directory);
                if (ftpClient.retrieveFile(path, fos)) {
                    return directory;
                }
                throw new FileNotFoundException("Filen kunne ikke downloades");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logout();
        }
        return null;
    }

}
