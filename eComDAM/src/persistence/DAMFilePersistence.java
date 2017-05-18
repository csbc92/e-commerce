package persistence;

import Product.IDisplayable;

import java.io.File;
import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public class DAMFilePersistence implements IDAMPersistence {

    private File mediaFolder;

    public DAMFilePersistence(String mediaFolderPath) {
        this.mediaFolder = new File(mediaFolderPath);
    }

    @Override
    public IDisplayable fetchMedia(String mediaID) {
        return null;
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        return null;
    }
}
