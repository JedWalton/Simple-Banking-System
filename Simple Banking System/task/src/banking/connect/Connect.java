package banking.connect;


import banking.account.CustomerAccount;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

    String url;
    SQLiteDataSource dataSource;
    Connection conn;
    String[] args;

    public Connect() {
        /* db parameters and config */
        this.url = "jdbc:sqlite:".concat(args[1]);
        this.dataSource = new SQLiteDataSource();
        this.dataSource.setUrl(url);

        initTable_card();
    }

    public void initTable_card() {
        try (Connection con = this.dataSource.getConnection()) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNewlyCreatedCard(CustomerAccount customerAccount) {
        try (Connection con = this.dataSource.getConnection()) {
//            Statement creation
            try (Statement statement = con.createStatement()) {
//                Statement execution
                statement.executeUpdate("SELECT TABLE IF NOT EXISTS card (" +
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /* RETURN TO AFTER ADDING NEW CARDS */

//    public boolean doesCardAlreadyExist(Long cardNumber) {
//
//        try (Connection con = this.dataSource.getConnection()) {
//             Statement creation
//            try (Statement statement = con.createStatement()) {
//                 Statement execution
//                statement.executeUpdate("SELECT TABLE IF NOT EXISTS card (" +
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        if (/*Query for card exist*/) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}