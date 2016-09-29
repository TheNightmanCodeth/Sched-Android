package android.nightman.sched.backend.data.assignments;

import android.nightman.sched.backend.models.Assignment;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by joe on 9/11/16.
 */
public interface AssignmentInterface {
    // Add a new assignment
    @POST("api/users/{user_id}/{class_id}/assignments")
    Observable<Response> addAssignment(@Header("Authorization") String auth, @Path("user_id") String user_id, @Path("class_id") String class_id);

    // Remove an assignment
    @DELETE("api/users/{user_id}/{class_id}/{assignment_id}")
    Observable<Response> deleteAssignment(@Header("Authorization") String auth, @Path("user_id") String user_id, @Path("class_id") String class_id, @Path("assignment_id") String ass_id);

    // Get a list of the class' assignments
    @GET("api/users/{user_id}/{class_id}/assignments")
    Observable<List<Assignment>> getAssignments(@Header("Authorization") String auth, @Path("user_id") String user_id, @Path("class_id") String class_id);

    // Get an assignment by id
    @GET("api/user/{user_id}/{class_id}/{assignment_id}")
    Observable<Assignment> getAssignmentById(@Header("Authorization") String auth, @Path("user_id") String user_id, @Path("class_id") String class_id, @Path("assignment_id") String ass_id);
}
