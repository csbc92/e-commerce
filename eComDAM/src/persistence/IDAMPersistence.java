package persistence;

import Product.IDisplayable;

import java.util.Set;

/**
 * This interface defines the common CRUD methods in the DAM system.
 */
public interface IDAMPersistence {

    /**
     * fetches media by media id
     *
     * @param mediaID id of desired media
     * @return media
     */
    IDisplayable fetchMedia(Integer mediaID);

    /**
     * fetches overview of media
     *
     * @return a set of IDisplayable
     */
    Set<IDisplayable> fetchMediaOverview();

    /**
     * Fetches the IDisplayables from the given Media IDs
     *
     * @param mediaIDs
     * @return
     */
    Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs);
}
