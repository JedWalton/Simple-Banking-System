package banking.menu;

import banking.account.CustomerAccount;

import java.util.Random;
import java.util.Scanner;

public class MenuWithCustomerAccountStorage extends Repository {

    public MenuWithCustomerAccountStorage() {
    }

    public void displayCustomerAccountLoggedInView() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    public int getMenuOption_ZeroToTwoInclusive() {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);

        int userin = scanner.nextInt();
        while (userin != 0 && userin != 1 && userin != 2) {
            userin = scanner.nextInt();
        }
        return userin;
    }

    public void display_createAccount_Login_Menu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    public String generateCustomerAccount() {
        Random rnd = new Random();
        /* Generates pin numbers */
        int pinGen = 1000 + rnd.nextInt(9000 - 1);
        long numberGen = (100000000L + rnd.nextLong(900000000L));
        String bin = "400000";
        String cardNumberGenString = String.valueOf(numberGen);
        String checksum = "1";
        long cardNumberGen = Long.parseLong(bin.concat(cardNumberGenString).concat(checksum));
        System.out.println(cardNumberGen);
        System.out.println(pinGen);



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

    public boolean LogIntoAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your card number:");
        System.out.print(">");
        long cardNum = scanner.nextLong();
        System.out.println("Enter your PIN:");
//        System.out.print(">");
        int pinNum = scanner.nextInt();


        if (super.customerAccounts.stream().map(CustomerAccount::getCardNumber).anyMatch(x -> x.equals(cardNum)) &&
                (super.customerAccounts.stream().map(CustomerAccount::getPinNumber)
                        .anyMatch(x -> x.equals(pinNum)))) {
            System.out.println("\nYou have successfully logged in!\n");
            return true;
        } else {
            System.out.println("\nWrong card number or PIN!\n");
            return false;
        }
    }
    private void LogOutOfAccount() {
        System.out.println("\nYou have successfully logged out!\n");
    }

    public int menuOneLogic() {
        display_createAccount_Login_Menu();
        int menuOption = getMenuOption_ZeroToTwoInclusive();
        boolean loginStatus = false;
        if (menuOption == 1) {
            generateCustomerAccount();
            return 0;
        } else if (menuOption == 2) {
            loginStatus = LogIntoAccount();
            if (loginStatus) {
                return 1;
            }
        } else if (menuOption == 0) {
            System.out.println("Bye!");
            System.exit(1);
        }
        /* stay on same menu */
        return 0;
    }

    public int menuTwoLogic() {
        displayCustomerAccountLoggedInView();
        int menuOption = getMenuOption_ZeroToTwoInclusive();
        if (menuOption == 1) {
            getBalance();
            /* Stay on login page */
            return 1;
        } else if (menuOption == 2) {
            LogOutOfAccount();
            /* Back to main menu */
            return 0;
        } else if (menuOption == 0) {
            System.out.println("Bye!");
            System.exit(1);
        }
        /* stay on same menu */
        return 1;
    }

    private void getBalance() {
        System.out.println("\nBalance: 0\n");
    }
}


