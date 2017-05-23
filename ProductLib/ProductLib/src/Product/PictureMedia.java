package Product;



import javafx.scene.image.Image;

import java.io.File;

public class PictureMedia implements IDisplayable {

    private String path;
    private int mediaID;

    /**
     * initializes Picture Media
     * @param mediaID ID to media
     * @param path Path to media
     */
    public PictureMedia(int mediaID, String path) {
        this.path = path;
        this.mediaID = mediaID;
    }

    /**
     *returns path to media
     * @return the media path
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * returns media id
     * @return media if
     */
    @Override
    public int getID() {
        return mediaID;
    }
}
