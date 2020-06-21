import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "1111";

        System.out.println("Программа расчета среднего количества покупок курсов в месяц за 2018 год.\n");
        try (ResultSet resultSet = DriverManager.getConnection(url, user, pass)
                .createStatement().executeQuery("SELECT distinct course_name, " +
                        "COUNT(*) over(partition by course_name) / " +
                        "(first_value(MONTH(subscription_date)) OVER (partition by course_name ORDER BY subscription_date DESC) - " +
                        "first_value(MONTH(subscription_date)) OVER (partition by course_name ORDER BY subscription_date) + 1) as avg " +
                        "FROM PurchaseList")) {
            while (resultSet.next()) {
                System.out.printf("%-35s %.2f\n", resultSet.getString("course_name"), Double.valueOf(resultSet.getString("avg")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}