import Exceptions.AmountLessThanOrEqualToZeroException;
import Exceptions.BalanceInsufficientException;

public class Wallet {

    private Money amount;

    public Wallet(Money money) {
        amount = money;
    }

    public void add(Money amountToAdd) throws AmountLessThanOrEqualToZeroException {
        amount = amount.add(amountToAdd);
    }

    public Money checkBalance() {
        return amount;
    }

    public void withdraw(Money amountToWithdraw) throws BalanceInsufficientException {
        amount = amount.subtract(amountToWithdraw);
    }

    public Money checkBalanceInSpecificCurrency(Currency currency) {
        return amount.toSpecificCurrency(currency);
    }
}
