import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        final LocalDate birthday = LocalDate.of(1996, 2, 2);
        LocalDate nextBirthday = birthday;
        LocalDate now = LocalDate.now();
        int years = Period.between(birthday, now).getYears();
        Locale localeRu = new Locale("ru", "RU");
        for (int i = 0; i <= years; i++) {
            System.out.println(i + " - " + nextBirthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + nextBirthday.getDayOfWeek().getDisplayName(TextStyle.FULL, localeRu));
            nextBirthday = nextBirthday.plusYears(1);
        }
        int y = 9;
        int o = y++;
        System.out.println(y + " " + o);
    }
}
