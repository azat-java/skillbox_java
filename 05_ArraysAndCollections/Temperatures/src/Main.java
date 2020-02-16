import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        double[] temperatures = new double[30];
        double sum = 0.;
        int healthyPatients = 0;
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = Math.round(ThreadLocalRandom.current().nextDouble(32, 40) * 10) / 10.;
            sum += temperatures[i];
            if (temperatures[i] >= 36.2 && temperatures[i] <= 36.9) {
                healthyPatients++;
            }
        }
        System.out.println("Температура пациентов: ");
        for (double temperature : temperatures) {
            System.out.print(temperature + ", ");
        }
        System.out.println();
        System.out.println("Средняя температура по больнице: " + Math.round(sum / 30) * 100 / 100.);
        System.out.println("Количество здоровых пациентов: " + healthyPatients);
    }
}
