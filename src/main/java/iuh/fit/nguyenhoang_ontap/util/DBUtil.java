package iuh.fit.nguyenhoang_ontap.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private DataSource dataSource;
    private static final String URL = "jdbc:mariadb://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sapassword";

    public DBUtil(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        try{
            if (this.dataSource != null) {
                return this.dataSource.getConnection();
            }

            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
