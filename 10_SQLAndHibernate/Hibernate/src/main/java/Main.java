import dataBase.course.Course;
import dataBase.Student;
import dataBase.subscription.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

        sessionFactory.close();
    }
}