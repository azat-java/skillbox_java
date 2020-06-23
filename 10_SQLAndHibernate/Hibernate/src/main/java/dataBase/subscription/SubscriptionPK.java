package dataBase.subscription;

import java.io.Serializable;

public class SubscriptionPK implements Serializable {
    protected int studentId;

    protected int courseId;


    public SubscriptionPK() {
    }

    public SubscriptionPK(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
