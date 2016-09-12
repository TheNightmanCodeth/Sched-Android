package android.nightman.sched.backend.models;

/**
 * Created by joe on 9/11/16.
 */
public class Assignment {

    private enum AssignmentType {
        HOMEWORK, TEST, QUIZ, READING, PROJECT
    }

    private int id;
    private int assignmentType;
    private String description;
    private String due;
    private int class_id;

    public Assignment() {
    }

    public Assignment(int id, int assignmentType, String description, String due, int class_id) {
        this.id = id;
        this.assignmentType = assignmentType;
        this.description = description;
        this.due = due;
        this.class_id = class_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(int assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}
