package banking;

import banking.menu.MenuWithCustomerAccountStorage;

public class Main {
    public static void main(String[] args) {
        MenuWithCustomerAccountStorage menuWithCustomerAccountStorage = new MenuWithCustomerAccountStorage();
        boolean loginStatus = false;
        int currentState = 0;
        while (true) {
            if (currentState == 0) {
                currentState = menuWithCustomerAccountStorage.menuOneLogic();
            } else if (currentState == 1) {
                currentState = menuWithCustomerAccountStorage.menuTwoLogic();
            }
        }
    }
}

