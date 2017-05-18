package businesslogic;

import Product.IDisplayable;

import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public class DAMManager implements IMediaFetcher {

    @Override
    public IDisplayable fetchMedia(String mediaID) {
        return null;
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        return null;
    }
}
