package storm.db;

//sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

    private Connection connection_ = null;
    private String dbURL_ = null;
    private String dbClass = "com.mysql.jdbc.Driver";

    //create a new connection to a sql databse
    public Connection makeConnection(String dbURL, String user, String pass) throws ClassNotFoundException, SQLException {
        dbURL_ = dbURL + "?user=" + user + "&password=" + pass;
        Class.forName(dbClass);
        connection_ = DriverManager.getConnection(dbURL_);
        System.out.println("connection created!");
        return connection_;
    }
}
