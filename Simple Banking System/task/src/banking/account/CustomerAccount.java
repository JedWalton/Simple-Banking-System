package banking.account;

public class CustomerAccount {
    long cardNumber;
    int pin;

    public long getCardNumber() {
        return cardNumber;
    }

    public int getPinNumber() {
        return pin;
    }

    public CustomerAccount(long cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
}
