public class Main {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colors = text.split(",?\\s+");
        String temp;
        for (int i = 0; i <= colors.length / 2; i++) {
            temp = colors[i];
            colors[i] = colors[colors.length - 1 - i];
            colors[colors.length - 1 - i] = temp;
        }
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
        }
    }
}
