package account;

import java.math.BigDecimal;

public class Card extends Account {

    final static float CASH_COMISSION_PERCENT = 1;

    public void getMoney(BigDecimal money) {
        BigDecimal fee = money.multiply(BigDecimal.valueOf(CASH_COMISSION_PERCENT)).divide(new BigDecimal(100));
        BigDecimal withFee = money.add(fee);
        super.getMoney(withFee);
    }
}
