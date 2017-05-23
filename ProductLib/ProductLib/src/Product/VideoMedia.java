package Product;


import java.io.File;

public class VideoMedia implements IDisplayable {

    private String url;
    private String embedString;

    public VideoMedia(String url) {
        this.url = url;
    }


    @Override
    public File getMedia() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException();
    }
}
