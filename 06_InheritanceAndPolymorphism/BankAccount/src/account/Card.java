package account;

import java.math.BigDecimal;

public class Card extends Account {

    private final static float CASH_COMISSION_PERCENT = 1;

    protected void getMoney(BigDecimal money) {
        BigDecimal fee = money.multiply(BigDecimal.valueOf(CASH_COMISSION_PERCENT)).divide(new BigDecimal(100));
        BigDecimal withFee = money.add(fee);
        super.getMoney(withFee);
    }
}
