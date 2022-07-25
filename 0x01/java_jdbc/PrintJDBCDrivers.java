import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class PrintJDBCDrivers {
    public static void main(String[] args) {
        System.out.println("List of loaded JDBC drivers");

        for (Enumeration<Driver> driverEnum = DriverManager.getDrivers(); driverEnum.hasMoreElements();) {
            Driver driver = driverEnum.nextElement();
            print(driver);
        }
    }

    public static void print(Driver driver) {
        System.out.println("----------------------------------------");
        System.out.println("Driver name: " + driver.getClass().getName());
        System.out.println("Major version: " + driver.getMajorVersion());
        System.out.println("Minor version: " + driver.getMinorVersion());
        System.out.println("----------------------------------------");
    }
}
