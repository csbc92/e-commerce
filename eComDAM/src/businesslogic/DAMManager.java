package businesslogic;import Product.IDisplayable;import persistence.DAMDBPersistence;import persistence.IDAMPersistence;import networking.Server;import networking.ServerHandler;import java.io.IOException;import java.util.Set;/** * Created by Vedsted on 18-05-2017. */public class DAMManager implements IMediaFetcher {    private IDAMPersistence persistence;    /**     * initializes DAMManageer     * Establishes connection to database     */    public DAMManager() {        String url = "jdbc:postgresql://localhost:5432/eComDam";        String user = "postgres";        String password = "1234";        persistence = new DAMDBPersistence(url, user, password);        try {            Thread t = new Thread(new ServerHandler(this, 5678));            t.setDaemon(true);            t.start();        } catch (IOException e) {            e.printStackTrace();        }    }    /**     * fetches media from persistence     * @param mediaID id of desired media     * @return media     */    @Override    public IDisplayable fetchMedia(String mediaID) {        return persistence.fetchMedia(mediaID);    }    /**     * fetches media overview     * @return a set of iDisplayable     */    @Override    public Set<IDisplayable> fetchMediaOverview() {        return persistence.fetchMediaOverview();    }}