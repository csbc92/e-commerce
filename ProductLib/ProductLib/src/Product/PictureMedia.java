package Product;


public class PictureMedia implements IDisplayable {

    private String path;

    public PictureMedia(String path) {
        this.path = path;
    }


    @Override
    public void show() {
        throw new UnsupportedOperationException();
    }
}
