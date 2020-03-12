import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDate birthday = LocalDate.of(1996, 2, 2);
        LocalDate now = LocalDate.now();
        int i = 0;
        while (now.isAfter(birthday)) {
            System.out.println(i + " - " + birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - eeee", new Locale("ru"))));
            birthday = birthday.plusYears(1);
            i++;
        }
    }
}
