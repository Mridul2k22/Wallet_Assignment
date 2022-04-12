import Exceptions.AmountLessThanOrEqualToZeroException;
import Exceptions.BalanceInsufficientException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletTest {

    @Test
    void shouldBeAbleToAddMoneyInWalletWhenTheAmountIsGreaterThanZero() throws AmountLessThanOrEqualToZeroException {
        Wallet wallet = new Wallet(Money.rupee(0));

        wallet.add(Money.rupee(5));

        assertThat(wallet.checkBalance(), is(equalTo(Money.rupee(0).add(Money.rupee(5)))));
    }

    @Test
    void shouldNotBeAbleToAddMoneyInWalletWhenTheAmountIsLessThanOrEqualToZero() {
        Wallet wallet = new Wallet(Money.rupee(0));

        assertThrows(AmountLessThanOrEqualToZeroException.class, () -> wallet.add(Money.rupee(-1)));
    }

    @Test
    void shouldBeAbleToTakeOutMoneyFromWalletWhenTheBalanceIsSufficientAndInSameCurrency() throws BalanceInsufficientException {
        Wallet wallet = new Wallet(Money.rupee(70));

        wallet.withdraw(Money.rupee(5));

        assertThat(wallet.checkBalance(), is(equalTo(Money.rupee(65))));
    }

    @Test
    void shouldBeAbleToTakeOutMoneyFromWalletWhenTheBalanceIsSufficientAndInDifferentCurrency() throws BalanceInsufficientException {
        Wallet wallet = new Wallet(Money.dollar(2));

        wallet.withdraw(Money.rupee(75));

        assertThat(wallet.checkBalance(), is(equalTo(Money.rupee(75))));
    }

    @Test
    void shouldNotBeAbleToTakeOutMoneyWhenTheBalanceIsInSufficient() {
        Wallet wallet = new Wallet(Money.rupee(1));

        assertThrows(BalanceInsufficientException.class, () -> wallet.withdraw(Money.rupee(5)));
    }

    @Test
    void ShouldShowBalanceWhenThePreferredCurrencyAndTheCurrencyInWhichBalanceShouldBeDisplayedIsSame() {
        Wallet wallet = new Wallet(Money.rupee(10));

        assertThat(wallet.checkBalanceInSpecificCurrency(Currency.RUPEE), is(equalTo(Money.rupee(10))));
    }

    @Test
    void ShouldShowBalanceWhenThePreferredCurrencyAndTheCurrencyInWhichBalanceShouldBeDisplayedIsDifferent() {
        Wallet wallet = new Wallet(Money.rupee(75));

        assertThat(wallet.checkBalanceInSpecificCurrency(Currency.DOLLAR), is(equalTo(Money.dollar(1))));
    }

}