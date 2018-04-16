package storm.db;

//sql and arraylist
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//communicate with sql db via SQLConnector connection
public class SQLMediator {
    private Connection connection_ = null;
    private PreparedStatement prepstmt_ = null;
    private String stmt_ = null, values_ = "", tableName_ = null;
    private int r_ = 0, col_ = 0;
    private ArrayList<String> columnNames_ = new ArrayList<String>();
    private ArrayList<String> columnTypes_ = new ArrayList<String>();

    //cstr
    public SQLMediator(Connection connection, String table, ArrayList<String> columnNames) {
        super();
        this.tableName_ = table;
        this.columnNames_ = columnNames;
        this.connection_ = connection;

    }

    //insert a row in mysql table
    public int insertRow(ArrayList<Object> fieldValues) throws SQLException {
        int r = 0;
        try {
            prepstmt_ = null;
            values_ = "";
            col_ = columnNames_.size();

            //create insert statment
            stmt_ = "insert into " + tableName_ + " (";
            for(int i = 0; i <= col_ - 1; i++) {
                if(i != col_ - 1) {
                    stmt_ = stmt_ + columnNames_.get(i) + ", ";
                    values_ = values_ + "?,";
                }
                else {
                    stmt_ = stmt_ + columnNames_.get(i) + ") ";
                    values_ = values_ + "?";
                }
            }
            stmt_ = stmt_ + " values (" +  values_ + ")";

            //create query
            prepstmt_ = connection_.prepareStatement(stmt_);
            for(int j = 0; j <= col_ - 1; j++) {
                prepstmt_.setObject(j + 1, fieldValues.get(j));
            }

            //run query
            r_ = prepstmt_.executeUpdate();
            if(r_ == 0) {
                return 0;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return r_;
    }




}
