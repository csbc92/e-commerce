package ecompim.Product;


public class VideoMedia implements IDisplayable {

    private String url;
    private String embedString;

    public VideoMedia(String url) {
        this.url = url;
    }

    @Override
    public void show() {

    }
}
