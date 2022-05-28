package banking.connect;


import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

    String url;
    SQLiteDataSource dataSource;
    Connection conn;

    public Connect(String[] args) {
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
}