package android.nightman.sched.backend.models;

/**
 * Created by joe on 9/11/16.
 */
public class AuthToken {
    private String token;
    private String created_at;

    public AuthToken() {
    }

    public AuthToken(String token, String created_at) {
        this.token = token;
        this.created_at = created_at;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
