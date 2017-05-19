package Product;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by Vedsted on 19-05-2017.
 */
public class MediaTest extends Image implements Serializable{


    public MediaTest(){
        super("test.jpg");
    }
    public MediaTest(String url) {
        super(url);
    }

    public MediaTest(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public MediaTest(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public MediaTest(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public MediaTest(InputStream is) {
        super(is);
    }

    public MediaTest(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(is, requestedWidth, requestedHeight, preserveRatio, smooth);
    }
}
