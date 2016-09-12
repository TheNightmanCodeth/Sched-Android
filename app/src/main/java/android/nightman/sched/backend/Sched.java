package android.nightman.sched.backend;

import android.nightman.sched.backend.data.assignments.AssignmentInterface;
import android.nightman.sched.backend.data.classes.ClassInterface;
import android.nightman.sched.backend.data.user.UserInterface;
import android.nightman.sched.backend.models.Assignment;
import android.nightman.sched.backend.models.User;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joe on 9/10/16.
 */
public class Sched {
    public static final String API_BASE_URL = "http://sched.thenightmancodeth.me/";

    private Retrofit retrofit;

    private AssignmentInterface assignmentAPI;
    private ClassInterface      classAPI;
    private UserInterface       authAPI;

    public Sched() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        assignmentAPI = retrofit.create(AssignmentInterface.class);
        classAPI      = retrofit.create(ClassInterface.class);
        authAPI       = retrofit.create(UserInterface.class);
    }

    public AssignmentInterface getAssignmentAPI() {
        return assignmentAPI;
    }

    public ClassInterface getClassAPI() {
        return classAPI;
    }

    public UserInterface getAuthAPI() {
        return authAPI;
    }
}
