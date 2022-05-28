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
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    public static int getMenuOption_ZeroToTwoInclusive() {
        Scanner scanner = new Scanner(System.in);

        int userin = scanner.nextInt();
        while (userin != 0 && userin != 1 && userin != 2) {
            userin = scanner.nextInt();
        }
        return userin;
    }

}
