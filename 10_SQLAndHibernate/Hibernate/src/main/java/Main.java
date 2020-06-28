import model.LinkedPurchaseList;
import model.Purchase;
import model.Student;
import model.Subscription;
import model.course.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 2);
        System.out.printf("Количество студентов курса \"%s\" - %d человек.\n", course.getName(), course.getStudentsCount());
        System.out.printf("Студент %s учится на курсе \"%s\". Преподаватель - %s\n", course.getStudents().get(1).getName(), course.getName(), course.getTeacher().getName());

        Student student = session.get(Student.class, 7);
        System.out.printf("Данные студента с id = %d:\nФамилия, Имя: %s\nВозраст: %d\n", student.getId(), student.getName(), student.getAge());
        Subscription subscription = student.getSubscriptions().get(1);
        Course course2 = session.get(Course.class, subscription.getCourseId());
        System.out.printf("Данный студент также учится на курсе \"%s\" с %s\n", course2.getName(), subscription.getSubscriptionDate());


        CriteriaBuilder builder = session.getCriteriaBuilder();

        Map<String, Integer> studentsMap = getAllRecords(session, builder, Student.class)
                .stream().collect(Collectors.toMap(Student::getName, Student::getId));

        Map<String, Integer> coursesMap = getAllRecords(session, builder, Course.class)
                .stream().collect(Collectors.toMap(Course::getName, Course::getId));

        List<Purchase> purchaseList = getAllRecords(session, builder, Purchase.class);
        for (Purchase purchase : purchaseList) {
            addRecord(session, studentsMap.get(purchase.getStudentName()), coursesMap.get(purchase.getCourseName()));
        }
        sessionFactory.close();
    }

    static <T> List<T> getAllRecords(Session session, CriteriaBuilder builder, Class<T> t) {
        CriteriaQuery<T> query = builder.createQuery(t);
        Root<T> root = query.from(t);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    static void addRecord(Session session, int studentId, int courseId) {
        Transaction transaction = session.beginTransaction();
        LinkedPurchaseList element = new LinkedPurchaseList(studentId, courseId);
        session.save(element);
        transaction.commit();
    }
}