package Product;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Created by Vedsted on 19-05-2017.
 */
public class MediaTest extends Image implements Serializable{


    public MediaTest(String url) {
        super(url);
    }

}
