package persistence;

import Product.IDisplayable;

import java.io.File;
import java.util.Set;

/**
 * This class implements the IDAMPersistence and is responsible for File IO.
 */
public class DAMFilePersistence implements IDAMPersistence {

    private File mediaFolder;

    public DAMFilePersistence(String mediaFolderPath) {
        this.mediaFolder = new File(mediaFolderPath);
    }

    /**
     * Unsupported Operation
     *
     * @param mediaID id of desired media
     * @return
     */
    @Override
    public IDisplayable fetchMedia(Integer mediaID) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation
     *
     * @return
     */
    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation
     *
     * @param mediaIDs
     * @return
     */
    @Override
    public Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs) {
        throw new UnsupportedOperationException();
    }
}
