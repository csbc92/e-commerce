package network;

import java.io.Serializable;

public class CommandResponse implements Serializable {
    private Integer responseCode;
    private String responseMessage;
    private Object responseObject;

    public CommandResponse(Integer responseCode, Object responseObject) {
        this.responseCode = responseCode;
        this.responseObject = responseObject;
        this.responseMessage = "";
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
