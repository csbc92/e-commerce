package Networking;

import Product.IDisplayable;

import java.io.Serializable;

/**
 * Created by danie on 25-05-2017.
 */
public class DisplayableFrame implements Serializable {
    private StatusCode statusCode;
    private IDisplayable media;

    public DisplayableFrame(StatusCode statusCode, IDisplayable media) {
        this.statusCode = statusCode;
        this.media = media;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public IDisplayable getMedia() {
        return media;
    }
}
