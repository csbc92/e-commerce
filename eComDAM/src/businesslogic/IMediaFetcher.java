package businesslogic;

import Product.IDisplayable;

import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public interface IMediaFetcher {

    /**
     * fetches media from persistence
     * @param mediaID id of desired media
     * @return media
     */
    IDisplayable fetchMedia(String mediaID);

    /**
     * fetches media overview
     * @return a set of iDisplayable
     */
    Set<IDisplayable> fetchMediaOverview();
}



