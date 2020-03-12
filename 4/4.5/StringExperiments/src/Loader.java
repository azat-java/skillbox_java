import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        Matcher m = Pattern.compile("\\d+").matcher(text);
        int sum = 0;
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        System.out.println("Общая сумма заработка: " + sum);
    }
}