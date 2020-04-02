import client.Client;
import client.Corporate;
import client.Individual;
import client.Physical;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Client client = new Individual();
        client.putMoney(BigDecimal.valueOf(100));
        System.out.println(client.getBalance());
        client.getMoney(BigDecimal.valueOf(70));
        System.out.println(client.getBalance());
    }
}
