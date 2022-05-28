package banking;

import java.util.Scanner;

public class Application {

    Database database;
    boolean loginStatus;
    int currentState;

    long currentlyActiveCardNumber;
    int currentlyActivePinNumber;

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

        /* Recurse to prevent collisions */
        if (database.doesCardExist(cardNumberGen)) {
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
            currentlyActiveCardNumber = cardNum;
            currentlyActivePinNumber = pinNum;
            return true;
        } else {
            System.out.println("\nWrong card number or PIN!\n");
            return false;
        }
    }

    private void LogOutOfAccount() {
        currentlyActiveCardNumber = 0;
        currentlyActivePinNumber = 0;
        System.out.println("\nYou have successfully logged out!\n");
    }

    public int menuOneLogic() {
        Menu.display_createAccount_Login_Menu();
        int menuOption = Menu.getInput_createAccount_Login_Menu();
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
        int menuOption = Menu.getInput_displayCustomerAccountLoggedInView();
        if (menuOption == 1) {
            getBalance();
            /* Stay on login page */
            return 1;
        } else if (menuOption == 2) {
            addIncome();
            return 1;
        } else if (menuOption == 3) {
            doTransfer();
            return 1;
        } else if (menuOption == 4) {
            return 1;
        } else if (menuOption == 5) {
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

    private void doTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nTransfer");
        System.out.println("Enter card number:");
        long cardNumToTransferTo = scanner.nextLong();
        if (!Util.passesLuhnsAlgorithm(cardNumToTransferTo))  {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        } else if (!database.doesCardExist(cardNumToTransferTo)) {
            System.out.println("Such a card does not exist.");
        } else {
            System.out.println("\nEnter how much money you want to transfer:");
            long amountToTransfer = scanner.nextLong();
            if(database.getBalance(currentlyActiveCardNumber, currentlyActivePinNumber) > amountToTransfer) {
                database.transfer(amountToTransfer, cardNumToTransferTo);
            } else {
                System.out.println("Not enough money!");
            }


        }
        /* Do the transfer */
    }

    private void addIncome() {
        /* get user input income as long */
        Scanner scanner = new Scanner(System.in);
        long income = scanner.nextLong();
        /* call database function */
        if (database.addIncomeToAccount(currentlyActiveCardNumber, currentlyActivePinNumber, income)) {
            System.out.println("\nIncome was added!");
        }
    }

    private void getBalance() {
        System.out.println("\nBalance: 0\n");
    }
}


