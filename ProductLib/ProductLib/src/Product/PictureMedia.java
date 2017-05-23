package Product;



import javafx.scene.image.Image;

import java.io.File;

public class PictureMedia implements IDisplayable {

    private File file;
    private String path;
    private int mediaID;

    public PictureMedia(int mediaID, String path) {
        this.path = path;
        this.file = new File(path);
        this.mediaID = mediaID;
    }
    @Override
    public String getPath() {
        return this.path;
    }
    @Override
    public int getID() {
        return mediaID;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
