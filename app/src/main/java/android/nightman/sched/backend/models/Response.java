package android.nightman.sched.backend.models;

/**
 * Created by joe on 9/11/16.
 */
public class Response {
    String response;

    public Response(String response) {
        this.response = response;
    }

    public Response() {

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
