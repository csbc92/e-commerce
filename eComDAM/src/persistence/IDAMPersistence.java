package persistence;

import Product.IDisplayable;

import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public interface IDAMPersistence {

    IDisplayable fetchMedia(String mediaID);

    Set<IDisplayable> fetchMediaOverview();
}
