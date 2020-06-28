package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PurchaseList")
public class Purchase {
    @EmbeddedId
    private PurchasePK id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return price == purchase.price &&
                id.equals(purchase.id) &&
                studentName.equals(purchase.studentName) &&
                courseName.equals(purchase.courseName) &&
                subscriptionDate.equals(purchase.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, courseName, price, subscriptionDate);
    }

    @Embeddable
    public static class PurchasePK implements Serializable {
        @Column(name = "student_name")
        protected String studentName;

        @Column(name = "course_name")
        protected String courseName;

        public PurchasePK() {
        }

        public PurchasePK(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }
    }
}
