package Product;



import javafx.scene.image.Image;

import java.io.File;

public class PictureMedia implements IDisplayable {

    private File file;
    private int mediaID;
    public PictureMedia(int mediaID, String path) {
        this.file = new File(path);
        this.mediaID = mediaID;
    }
    @Override
    public File getMedia() {
        return this.file;
    }
    @Override
    public int getID() {
        return mediaID;
    }
}
