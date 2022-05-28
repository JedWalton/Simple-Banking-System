package banking;

import banking.account.CustomerAccount;
import banking.connect.Connect;

import java.util.Random;
import java.util.Scanner;

public class Application extends Repository {

    Connect connect;
    boolean loginStatus;
    int currentState;
    public Application(String[] args) {
        this.connect = new Connect(args);
        loginStatus = false;
        currentState = 0;
        menuLoopEntryPoint();
    }

    /* This loop ends only by System.exit */
    public void menuLoopEntryPoint() {
        while (true) {
            if (currentState == 0) {
                currentState = menuOneLogic();
            } else if (currentState == 1) {
                currentState = menuTwoLogic();
            }
        }
    }

    public void displayCustomerAccountLoggedInView() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    public int getMenuOption_ZeroToTwoInclusive() {
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

    /* recursive solution */
    public String generateCustomerAccount() {
        Random rnd = new Random();
        /* Generates pin numbers */
        int pinGen = 1000 + rnd.nextInt(9000 - 1);
        long numberGen = (100000000L + rnd.nextLong(900000000L));
        String bin = "400000";
        String cardNumberGenString = String.valueOf(numberGen);
        String checksum = "1";
        long cardNumberGen = Long.parseLong(bin.concat(cardNumberGenString).concat(checksum));

        while (!passesLuhnsAlgorithm(cardNumberGen)) {
            pinGen = 1000 + rnd.nextInt(9000 - 1);
            numberGen = (100000000L + rnd.nextLong(900000000L));
            bin = "400000";
            cardNumberGenString = String.valueOf(numberGen);
            checksum = "1";
            cardNumberGen = Long.parseLong(bin.concat(cardNumberGenString).concat(checksum));
        }
        System.out.println(cardNumberGen);
        System.out.println(pinGen);

        long finalCardNumberGen = cardNumberGen;

        /* To be replaced with SQLite query */
        if (super.customerAccounts.stream().map(CustomerAccount::getCardNumber).anyMatch(x -> x == finalCardNumberGen)) {
            generateCustomerAccount();
        }
        /* Condition must only be reached if account does not already exist by card number */

        /* To be replaced with SQLite query */
//        super.customerAccounts.add(new CustomerAccount(cardNumberGen, pinGen));
        this.connect.saveNewlyCreatedCard(new CustomerAccount(cardNumberGen, pinGen));


        return "\nYour card has been created\n" +
                "Your card number:\n" +
                finalCardNumberGen +
                "\nYour card PIN:\n" +
                pinGen +
                "\n";
    }

    public boolean passesLuhnsAlgorithm(long cardNumberGen) {
        String ccNumber = String.valueOf(cardNumberGen);
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            long n = Long.parseLong(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public boolean LogIntoAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your card number:");
        System.out.print(">");
        long cardNum = scanner.nextLong();
        System.out.println("Enter your PIN:");
        int pinNum = scanner.nextInt();


        /* To be replaced with SQLite query */

//        if (super.customerAccounts.stream().map(CustomerAccount::getCardNumber).anyMatch(x -> x.equals(cardNum)) &&
//                (super.customerAccounts.stream().map(CustomerAccount::getPinNumber)
//                        .anyMatch(x -> x.equals(pinNum)))) {
        if (connect.isLoginValid(cardNum, pinNum)) {

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


