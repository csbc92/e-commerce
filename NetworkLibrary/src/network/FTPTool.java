package network;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FTPTool {

    FTPClient ftpClient;

    private String username;
    private String password;
    private String hostname;

    /**
     * Tool to communicate with a FTP server.
     *
     * @param username the username
     * @param hostname the hostname of the file server
     * @param password the password
     */

    public FTPTool(String hostname, String username, String password) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        ftpClient = new FTPClient();
    }

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
     *
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
     *
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
                String directory = new File(System.getProperty("user.dir"), path).getAbsolutePath();
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

    /**
     * Retrieve a set of specific files from the FTP server.
     *
     * @param paths The path of the files to be downloaded.
     * @return the path where the files have been downloaded to.
     */
    public Set<String> retrieveFiles(Set<String> paths) {
        try {
            login();
            // Important to set the fileType and the mode before downloading files.
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            //
            if (ftpClient.isConnected()) {
                Set<String> returnPaths = new HashSet<>();
                for (String path : paths) {
                    String directory = new File(System.getProperty("user.dir"), path).getAbsolutePath();
                    FileOutputStream fos = new FileOutputStream(directory);
                    if (ftpClient.retrieveFile(path, fos)) {
                        returnPaths.add(directory);
                    }
                }
                return returnPaths;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logout();
        }
        return null;
    }

}
