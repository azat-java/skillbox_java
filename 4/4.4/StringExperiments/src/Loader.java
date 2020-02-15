
public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String vasyaSalary = text.substring(14, text.indexOf("рублей")).trim();
        String mashaSalary = text.substring(text.indexOf("Маша") + 7, text.lastIndexOf("рублей")).trim();

        int sum = Integer.parseInt(vasyaSalary) + Integer.parseInt(mashaSalary);
        System.out.println("Сумма заработка Васи и Маши: " + sum);
    }
}