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
    IDisplayable fetchMedia(Integer mediaID);

    /**
     * fetches media overview
     * @return a set of iDisplayable
     */
    Set<IDisplayable> fetchMediaOverview();

    /**
     * Fetch all the medias from the given set of intergers.
     * @param mediaIDs
     * @return
     */
    Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs);
}



