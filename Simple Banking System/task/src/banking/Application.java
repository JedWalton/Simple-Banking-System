package banking;

import java.util.Scanner;

public class Application {

    Database database;
    boolean loginStatus;
    int currentState;
    public Application(String[] args) {
        this.database = new Database(args);
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

    /* recursive solution */
    public String generateCustomerAccount() {

        /* Generates pin numbers */
        int pinGen = Util.generatePin();
        long cardNumberGen = Util.generateCardNum();

        while (!Util.passesLuhnsAlgorithm(cardNumberGen)) {
            pinGen = Util.generatePin();
            cardNumberGen = Util.generateCardNum();
        }
        System.out.println(cardNumberGen);
        System.out.println(pinGen);

        /* To prevent collisions */
        if (database.doesCardAlreadyExist(cardNumberGen)) {
            generateCustomerAccount();
        }
        /* Condition must only be reached if account does not already exist by card number */
        this.database.saveNewlyCreatedCard(cardNumberGen, pinGen);

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
        int pinNum = scanner.nextInt();


        if (database.isLoginValid(cardNum, pinNum)) {
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
        Menu.display_createAccount_Login_Menu();
        int menuOption = Menu.getMenuOption_ZeroToTwoInclusive();
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
        Menu.displayCustomerAccountLoggedInView();
        int menuOption = Menu.getMenuOption_ZeroToTwoInclusive();
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


