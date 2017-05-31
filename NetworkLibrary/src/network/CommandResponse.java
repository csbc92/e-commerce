package network;

import java.io.Serializable;

public class CommandResponse implements Serializable {
    private Integer responseCode;
    private String responseMessage;
    private Object responseObject;

    /**
     * Instantiate a CommandResponse containing a responseCode and a responseObject
     * @param responseCode
     * @param responseObject
     */
    public CommandResponse(Integer responseCode, Object responseObject) {
        this.responseCode = responseCode;
        this.responseObject = responseObject;
        this.responseMessage = "";
    }

    /**
     * Get the response code.
     * @return
     */
    public Integer getResponseCode() {
        return responseCode;
    }

    /**
     * Get response message
     * @return
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Get response object
     * @return
     */
    public Object getResponseObject() {
        return responseObject;
    }

    /**
     * Set the response message
     * @param responseMessage
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
