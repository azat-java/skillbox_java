package client;

import java.math.BigDecimal;

public class Individual extends Client {
    private final static float PUT_COMMISSION_PERCENT_1 = 1;
    private final static float PUT_COMMISSION_PERCENT_2 = 0.5f;
    private final static float COMMISSION_CONDITION = 1000;

    public void putMoney(BigDecimal money) {
        float commission;
        if (money.compareTo(BigDecimal.valueOf(COMMISSION_CONDITION)) < 0) {
            commission = PUT_COMMISSION_PERCENT_1;
        } else {
            commission = PUT_COMMISSION_PERCENT_2;
        }
        BigDecimal fee = money.multiply(BigDecimal.valueOf(commission)).divide(new BigDecimal(100));
        money = money.subtract(fee);
        super.putMoney(money);
    }
}
