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

    /**
     * Unsupported Operation
     * @param mediaID id of desired media
     * @return
     */
    @Override
    public IDisplayable fetchMedia(Integer mediaID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs) {
        throw new UnsupportedOperationException();
    }
}
