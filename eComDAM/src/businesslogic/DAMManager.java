package businesslogic;

import Product.IDisplayable;
import persistence.DAMDBPersistence;
import persistence.IDAMPersistence;

import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public class DAMManager implements IMediaFetcher {

    private IDAMPersistence persistence;

    public DAMManager() {
        persistence = DAMDBPersistence();
    }
    

    @Override
    public IDisplayable fetchMedia(String mediaID) {
        return persistence.fetchMedia(mediaID);
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        return persistence.fetchMediaOverview();
    }
}
