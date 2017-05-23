package persistence;

import Product.IDisplayable;

import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public interface IDAMPersistence {

    /**
     * fetches media by media id
     * @param mediaID id of desired media
     * @return media
     */
    IDisplayable fetchMedia(String mediaID);

    /**
     * fetches overview of media
     * @return a set of IDisplayable
     */
    Set<IDisplayable> fetchMediaOverview();
}
