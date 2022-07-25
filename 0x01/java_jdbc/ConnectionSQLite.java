import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLite implements AutoCloseable {
    private static Connection connection;

    public static void initConnection() {
        try{
            String url = "jdbc:sqlite:sqlite_database_2022.db";

            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        initConnection();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            System.out.println("Connection to SQLite has been closed.");
        }
    }
}
