package banking.menu;


import banking.account.CustomerAccount;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Repository {
    ArrayList<CustomerAccount> customerAccounts;

    public Repository() {
        this.customerAccounts = new ArrayList<CustomerAccount>();
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

//    public void setDataSource(String[] args) {
//        this.dataSource = new SQLiteDataSource();
//        dataSource.setUrl("jdbc:sqlite:".concat(args[1]));
//
//        try (Connection con = dataSource.getConnection()) {
//             Statement creation
//            try (Statement statement = con.createStatement()) {
//                 Statement execution
//                statement.executeUpdate("CREATE DATABASE");
//                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
//                        "id INTEGER PRIMARY KEY," +
//                        "number TEXT," +
//                        "pin TEXT," +
//                        "balance INTEGER DEFAULT 0");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
