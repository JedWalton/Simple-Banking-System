package banking.menu;


import banking.account.CustomerAccount;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Repository {
    ArrayList<CustomerAccount> customerAccounts;


    SQLiteDataSource dataSource;

    String[] args;

    public Repository() {
        this.customerAccounts = new ArrayList<CustomerAccount>();
        if (this.args != null) {
            setDataSource(args[1]);
        }
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    private void setDataSource(String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
