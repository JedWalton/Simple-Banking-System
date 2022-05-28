package banking;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    String url;
    SQLiteDataSource dataSource;

    public Database(String[] args) {
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
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    public void saveNewlyCreatedCard(long cardNumber, int pinNum) {
        String query = "INSERT INTO card (number, pin) " +
                "VALUES (".concat(String.valueOf(cardNumber))
                        .concat(", ").concat(String.valueOf(pinNum))
                        .concat(");");
        try (Connection con = this.dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean doesCardExist(long cardNum) {
        String query = "SELECT number FROM card WHERE number = ".concat(String.valueOf(cardNum));
        try (Connection con = this.dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    if(!(rs.getString("number").isEmpty())) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLoginValid(Long cardNum, int pinNum) {
        String query = "SELECT number, pin FROM card WHERE number = ".concat(String.valueOf(cardNum)) +
                " AND pin = ".concat(String.valueOf(pinNum)).concat(";");
        try (Connection con = this.dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    if(!(rs.getString("number").isEmpty() ||
                            rs.getString("pin").isEmpty())) {
                       return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addIncomeToAccount(long currentlyActiveCardNumber, int currentlyActivePinNumber, long income) {
        String query = "UPDATE card SET balance = balance + ".concat(String.valueOf(income))
                .concat(" WHERE number = ".concat(String.valueOf(currentlyActiveCardNumber)) +
                        " AND pin = ".concat(String.valueOf(currentlyActivePinNumber)).concat(";"));
        try (Connection con = this.dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeQuery(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Long getBalance(long cardNum, int pinNum) {
        String query = "SELECT balance FROM card WHERE number = ".concat(String.valueOf(cardNum)) +
                " AND pin = ".concat(String.valueOf(pinNum)).concat(";");
        try (Connection con = this.dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                return Long.valueOf(rs.getString("balance"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Balance not found error. Did you enter card and pin correctly?");
    }

    public boolean transfer(long amountToTransfer, long cardNumToTransferTo) {

        return false;
    }
}