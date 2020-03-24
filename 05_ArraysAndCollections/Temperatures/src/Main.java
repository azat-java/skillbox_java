import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        final int PATIENTS = 30;
        final double MAX_TEMP = 40.0;
        final double MIN_TEMP = 32.0;
        final double MIN_TEMP_WELL = 36.2;
        final double MAX_TEMP_WELL = 36.9;
        double[] temperatures = new double[PATIENTS];
        double sum = 0.;
        int healthyPatients = 0;
        System.out.println("Температура пациентов: ");
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = ThreadLocalRandom.current().nextDouble(MIN_TEMP, MAX_TEMP);
            DecimalFormat formatter = new DecimalFormat("#0.00°C");
            System.out.println(formatter.format(temperatures[i]));
            sum += temperatures[i];
            if (temperatures[i] >= MIN_TEMP_WELL && temperatures[i] <= MAX_TEMP_WELL) {
                healthyPatients++;
            }
        }
        double averageTemperature = sum / PATIENTS;

        System.out.printf("Средняя температура = %.02f°C \n", averageTemperature);
        System.out.println("Количество здоровых пациентов: " + healthyPatients);
    }
}
