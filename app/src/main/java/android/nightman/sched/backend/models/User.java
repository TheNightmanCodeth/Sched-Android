package android.nightman.sched.backend.models;

import java.util.List;

/**
 * Created by joe on 9/11/16.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private List<Class> classes;

    public User() {
    }

    public User(int id, String name, String email, List<Class> classes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
