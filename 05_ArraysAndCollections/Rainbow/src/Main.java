public class Main {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colors = text.split(",?\\s+");
        String[] colorsInverted = new String[colors.length];
        for (int i = colors.length - 1; i >= 0; i--) {
            colorsInverted[colors.length - 1 - i] = colors[i];
        }
        colors = colorsInverted;
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
        }
    }
}
