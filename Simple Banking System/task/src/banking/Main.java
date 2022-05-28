package banking;

import banking.connect.Connect;
import banking.menu.MenuWithCustomerAccountStorage;


public class Main {
    public static void main(String[] args) {
        MenuWithCustomerAccountStorage menuWithCustomerAccountStorage = new MenuWithCustomerAccountStorage();
        Connect connect;
        if(args!=null){
            connect = new Connect(args);
        }

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

