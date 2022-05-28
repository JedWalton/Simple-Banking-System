package banking.menu;


import banking.account.CustomerAccount;
import java.util.ArrayList;

public class Repository {
    ArrayList<CustomerAccount> customerAccounts;

    public Repository() {
        this.customerAccounts = new ArrayList<CustomerAccount>();
    }

}
