package Product;

import java.io.File;
import java.io.Serializable;

/**
 * Created by JV on 24-04-2017.
 */
public interface IDisplayable extends Serializable{

    /**
     * returns path to media
     * @return path  to  media
     */
    public String getPath();
    public File getMedia();

    /**
     * returns id of media
     * @return id of media
     */
    public int getID();
}
