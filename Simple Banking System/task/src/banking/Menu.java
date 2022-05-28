package banking;

import java.util.Scanner;

public class Menu {
    private Menu() {
    }

    public static void display_createAccount_Login_Menu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    public static void displayCustomerAccountLoggedInView() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    public static int getInput_createAccount_Login_Menu() {
        Scanner scanner = new Scanner(System.in);

        int userin = scanner.nextInt();
        while (userin != 0 && userin != 1 && userin != 2) {
            userin = scanner.nextInt();
        }
        return userin;
    }

    public static int getInput_displayCustomerAccountLoggedInView() {
        Scanner scanner = new Scanner(System.in);

        int userin = scanner.nextInt();
        while (userin != 0 && userin != 1 && userin != 2 && userin != 3 && userin != 4 && userin != 5) {
            userin = scanner.nextInt();
        }
        return userin;
    }

}
