import Exceptions.AmountLessThanOrEqualToZeroException;
import Exceptions.BalanceInsufficientException;

public class Money {
    private final double amount;
    private final Currency currency;

    private Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(double amount) {
        return new Money(amount, Currency.DOLLAR);
    }

    public static Money rupee(double amount) {
        return new Money(amount, Currency.RUPEE);
    }

    public Money add(Money amountToAdd) throws AmountLessThanOrEqualToZeroException {
        if (amountToAdd.amount <= 0) throw new AmountLessThanOrEqualToZeroException();

        double totalAmountInBaseCurrency = convertAmountToBaseCurrency(this) + convertAmountToBaseCurrency(amountToAdd);

        return new Money(currency.getConvertedAmountToSpecificCurrency(totalAmountInBaseCurrency, this.currency), this.currency);
    }

    public Money subtract(Money amountToWithdraw) throws BalanceInsufficientException {
        double totalAmountInBaseCurrency = convertAmountToBaseCurrency(this) - convertAmountToBaseCurrency(amountToWithdraw);

        if (totalAmountInBaseCurrency < 0) throw new BalanceInsufficientException();

        return new Money(currency.getConvertedAmountToSpecificCurrency(totalAmountInBaseCurrency, this.currency), this.currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj.getClass() != getClass()) return false;

        Money object = (Money) obj;
        return convertAmountToBaseCurrency(this) == convertAmountToBaseCurrency(object);
    }

    private double convertAmountToBaseCurrency(Money ToConvert) {
        return ToConvert.currency.getConvertedAmountToBase(ToConvert.amount);
    }

    public Money toSpecificCurrency(Currency currency) {
        double amountInBaseCurrency = convertAmountToBaseCurrency(this);

        return new Money(currency.getConvertedAmountToSpecificCurrency(amountInBaseCurrency, currency), currency);
    }
}


