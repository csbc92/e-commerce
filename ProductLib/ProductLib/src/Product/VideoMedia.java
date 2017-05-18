package Product;


import javafx.scene.image.Image;

public class VideoMedia implements IDisplayable {

    private String url;
    private String embedString;

    public VideoMedia(String url) {
        this.url = url;
    }


    @Override
    public Image getMedia() {
        return null;
    }
}
