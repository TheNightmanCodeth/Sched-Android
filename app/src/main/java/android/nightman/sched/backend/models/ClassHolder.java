package android.nightman.sched.backend.models;

import java.util.List;

/**
 * Created by joe on 9/11/16.
 */
public class ClassHolder {
    List<Class> classes;

    public ClassHolder(List<Class> classes) {
        this.classes = classes;
    }

    public ClassHolder() {

    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
