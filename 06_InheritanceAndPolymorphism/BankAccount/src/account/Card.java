package account;

public class Card extends Account {
    public float balance;

    public void getMoney(float money) {
        if (balance >= money) {
            float withFee = money + money / 100;
            if (balance >= withFee) {
                balance -= money;
            } else {
                System.out.println("Недостаточно средств для комиссии");
            }
        } else {
            System.out.println("Недостаточно средств");
        }
    }

}
