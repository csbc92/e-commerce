package Product;



import javafx.scene.image.Image;

import java.io.File;

public class PictureMedia implements IDisplayable {

    private File file;
    private String path;
    private int mediaID;

    /**
     * initializes Picture Media
     * @param mediaID ID to media
     * @param path Path to media
     */
    public PictureMedia(int mediaID, String path) {
        this.path = path;
        this.file = new File(path);
        this.mediaID = mediaID;
    }

    /**
     *returns path to media
     * @return the media path
     */
    @Override
    public File getMedia() {
        return this.file;
    }

    /**
     * returns media id
     * @return media if
     */
    @Override
    public int getID() {
        return mediaID;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
