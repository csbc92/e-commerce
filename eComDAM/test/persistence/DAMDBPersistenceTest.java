package persistence;

import Product.IDisplayable;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DAMDBPersistenceTest {

    private IDAMPersistence dbPersistence;

    @BeforeEach
    void setUp() {
        String url = "jdbc:postgresql://localhost:5432/eComDAM";
        String user = "postgres";
        String password = "1234";
        dbPersistence = new DAMDBPersistence(url, user, password);
    }

    /**
     * Fetch one media object.
     */
    @Test
    void fetchMedia() {
        IDisplayable media = dbPersistence.fetchMedia(1);
        assertEquals(1, media.getID());
    }

    /**
     * Fetch multiple media objects.
     */
    @Test
    void fetchMedias() {
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

    @Test
    void fetchMediaOverview() {
        Set<IDisplayable> media = dbPersistence.fetchMediaOverview();
        assertTrue(media.size() <= 50);
    }

    /**
     * Returns the amount of media where the ID is 0.
     * This indicates that the media was NOT found.
     * @param media
     * @return
     */
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