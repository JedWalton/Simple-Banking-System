package banking.menu;

import banking.account.CustomerAccount;
import banking.util.Pair;
import java.util.Random;
import java.util.Scanner;

public class MenuWithCustomerAccountStorage extends Repository {

    public MenuWithCustomerAccountStorage() {
    }

    public static int getMenuOption_ZeroToTwoInclusive() {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);

        int userin = scanner.nextInt();
        while (userin != 0 && userin != 1 && userin != 2) {
            userin = scanner.nextInt();
        }
        return userin;
    }

    public static void display_createAccount_Login_Menu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    public static Pair<String, String> getCardNumberAndPin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        System.out.print(">");
        int cardNum = scanner.nextInt();
        System.out.println("Enter your PIN:");
        System.out.print(">");
        int pinNum = scanner.nextInt();

        return new Pair(cardNum, pinNum);
    }

    public String generateCustomerAccount() {
        Random rnd = new Random();
        /* Generates pin numbers */
        int pinGen = 1000 + rnd.nextInt(9000);
        long cardNumberGen = (1000000000000000L + rnd.nextLong(9000000000000000L));

        if (!super.customerAccounts.stream().map(CustomerAccount::getCardNumber).anyMatch(x -> x == cardNumberGen)) {
            super.customerAccounts.add(new CustomerAccount(cardNumberGen, pinGen));
        }
        return "\nYour card has been created\n" +
                "Your card number:\n" +
                cardNumberGen +
                "\nYour card PIN:\n" +
                pinGen +
                "\n";
    }

    public void startMenu() {
        display_createAccount_Login_Menu();
        int input = getMenuOption_ZeroToTwoInclusive();
        if (input == 1) {
            String out = generateCustomerAccount();
            System.out.println(out);
        } else if (input == 2) {
            Pair cardNumAndPinPair = getCardNumberAndPin();
            if (customerAccounts.stream().map(CustomerAccount::getCardNumber).anyMatch(x -> x == cardNumAndPinPair.t) &&
                    (customerAccounts.stream().map(CustomerAccount::getPinNumber)
                            .anyMatch(x -> x == cardNumAndPinPair.u))) {
                System.out.println("You have successfully logged in!");

            } else {
                System.out.println("Wrong card number or PIN!");
            }
        } else if (input == 0) {
            System.out.println("Bye!");
            System.exit(1);
        }
    }
}


