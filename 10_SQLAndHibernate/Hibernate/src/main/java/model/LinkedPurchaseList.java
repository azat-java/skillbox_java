package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListPK id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;


    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(int studentId, int courseId) {
        id = new LinkedPurchaseListPK(studentId, courseId);
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Embeddable
    public static class LinkedPurchaseListPK implements Serializable {
        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public LinkedPurchaseListPK(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }
    }
}
