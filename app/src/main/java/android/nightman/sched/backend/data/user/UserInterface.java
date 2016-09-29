package android.nightman.sched.backend.data.user;

import android.nightman.sched.backend.models.AuthToken;
import android.nightman.sched.backend.models.User;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by joe on 9/11/16.
 */
public interface UserInterface {
    // Register new user
    @POST("api/users")
    Response register(@Body User user);

    // Get user auth token
    @GET("api/authtoken")
    Observable<AuthToken> getToken(@Header("Authorization") String auth);

    // Get current user data
    @GET("api/user")
    Observable<User> getUser(@Header("Authorization") String auth);
}
