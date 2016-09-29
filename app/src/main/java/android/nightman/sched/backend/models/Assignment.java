package android.nightman.sched.backend.models;

/**
 * Created by joe on 9/11/16.
 */
public class Assignment {

    private enum AssignmentType {
        HOMEWORK, TEST, QUIZ, READING, PROJECT
    }

    private int id;
    private int asstype;
    private String desc;
    private String due;
    private int classs_id;

    public Assignment() {
    }

    public Assignment(int id, int assignmentType, String description, String due, int class_id) {
        this.id = id;
        this.asstype = assignmentType;
        this.desc = description;
        this.due = due;
        this.classs_id = class_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsstype() {
        return asstype;
    }

    public void setAsstype(int asstype) {
        this.asstype = asstype;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public int getClasss_id() {
        return classs_id;
    }

    public void setClasss_id(int classs_id) {
        this.classs_id = classs_id;
    }
}
