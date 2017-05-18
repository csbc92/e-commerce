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
        String url = "jdbc:postgresql://localhost:5432/eComDAM";
        String user = "postgres";
        String password = "1234";
        persistence = new DAMDBPersistence(url,user,password);
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
