package banking;

import banking.menu.MenuWithCustomerAccountStorage;

public class Main {
    public static void main(String[] args) {
        MenuWithCustomerAccountStorage menuWithCustomerAccountStorage = new MenuWithCustomerAccountStorage();
        while (true) {
            menuWithCustomerAccountStorage.startMenu();
        }
    }
}

