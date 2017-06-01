/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import Product.IDisplayable;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class DAMDBPersistenceTest {
    DAMDBPersistence dbPersistence;
    public DAMDBPersistenceTest() {
        String url = "jdbc:postgresql://localhost:5432/eComDAM";
        String user = "postgres";
        String password = "1234";
        dbPersistence = new DAMDBPersistence(url, user, password);
    }

    @Test
    public void testFetchMedia_Integer() {
        IDisplayable media = dbPersistence.fetchMedia(1);
        assertEquals(1, media.getID());
    }

    @Test
    public void testFetchMediaOverview() {
         Set<IDisplayable> media = dbPersistence.fetchMediaOverview();
        assertTrue(media.size() <= 50);
    }

    @Test
    public void testFetchMedia_Set() {
         Set<IDisplayable> media = null;
        Set<Integer> mediaIDs = new HashSet<>();

        // Existing IDs
        mediaIDs.add(1);
        mediaIDs.add(2);
        mediaIDs.add(3);
        media = dbPersistence.fetchMedia(mediaIDs);
        assertEquals(3, media.size());

        // Non-existing IDs
        mediaIDs.clear();
        mediaIDs.add(-1);
        mediaIDs.add(-2);
        mediaIDs.add(-3);
        media = dbPersistence.fetchMedia(mediaIDs);
        assertTrue(countAmountOfMissingMedia(media) == 3);


        // Mixed IDs
        mediaIDs.clear();
        mediaIDs.add(-1);
        mediaIDs.add(2);
        mediaIDs.add(3);
        media = dbPersistence.fetchMedia(mediaIDs);
        assertTrue(countAmountOfMissingMedia(media) == 1);
    }
    private int countAmountOfMissingMedia(Set<IDisplayable> media) {
        int counter = 0;

        for (IDisplayable m : media) {
            if (m.getID() == 0) {
                counter++;
            }
        }

        return counter;
    }

    
}
