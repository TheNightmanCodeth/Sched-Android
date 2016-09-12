package android.nightman.sched.backend.data.classes;

import android.nightman.sched.backend.models.Class;
import android.nightman.sched.backend.models.ClassHolder;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by joe on 9/11/16.
 */
public interface ClassInterface {
    // Add a new class
    @POST("api/users/{user_id}/classes")
    Response createClass(@Header("Authorization") String auth, @Body Class clazz);

    // Remove a class
    @DELETE("api/users/{user_id}/{class_id}")
    Response deleteClass(@Header("Authorization") String auth, @Path("user_id")int user_id, @Path("class_id") int class_id);

    // Get a list of the user's classes
    @GET("api/users/{user_id}/classes")
    Observable<ClassHolder> getClasses(@Header("Authorization") String auth, @Path("user_id")int user_id);

    // Get a class by id
    @GET("api/users/{user_id}/{class_id}")
    Observable<Class> getClassById(@Header("Authorization") String auth, @Path("user_id") int user_id, @Path("class_id") int class_id);
}
