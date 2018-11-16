package ndingspringboot.BlogSite.utils;

import lombok.Data;

/**
 * This class is an encapsulation for all the service response to unify restful style
 */
@Data
public class Response {

    private boolean success;
    private String  message;
    private Object body;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
