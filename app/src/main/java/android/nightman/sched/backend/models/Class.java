package android.nightman.sched.backend.models;

import java.util.List;

/**
 * Created by joe on 9/11/16.
 */
public class Class {
    private int id;
    private String name;
    private int badge;
    private String professor;
    private String location;
    private String days;
    private String time;
    private int user_id;
    private List<Assignment> assignments;

    public Class() {
    }

    public Class(int id, String name, int badge, String professor, String location, String days, String time, int user_id, List<Assignment> assignments) {
        this.id = id;
        this.name = name;
        this.badge = badge;
        this.professor = professor;
        this.location = location;
        this.days = days;
        this.time = time;
        this.user_id = user_id;
        this.assignments = assignments;
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

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
