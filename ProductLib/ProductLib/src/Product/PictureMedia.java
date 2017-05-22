package Product;



import javafx.scene.image.Image;

import java.io.File;

public class PictureMedia implements IDisplayable {

    private File file;
    private int mediaID;

    public PictureMedia(int mediaID, String path) {
        this.image = new MediaTest(new File(path).toURI().toString());
        this.mediaID = mediaID;
    }

    public PictureMedia(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
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
