package dataBase.purchase;

import java.io.Serializable;

public class PurchasePK implements Serializable {
    protected String studentName;

    protected String courseName;

    public PurchasePK(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }
}
