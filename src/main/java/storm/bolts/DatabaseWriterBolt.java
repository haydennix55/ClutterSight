package storm.bolts;

//storm
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

//sql and other java
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

//mediator and connector classes
import storm.db.SQLMediator;
import storm.db.SQLConnector;

public class DatabaseWriterBolt implements IBasicBolt {
    private static transient SQLConnector connector_ = new SQLConnector();
    private static transient Connection connection_ = null;
    private static transient SQLMediator mediator_ = null;
    private String tableName_ = null, dbURL_ = null, pass_ = null, user_ = null;
    private ArrayList<String> columnNames_ = new ArrayList<String>();
    private ArrayList<String> columnTypes_ = new ArrayList<String>();
    private ArrayList<Object> fieldValues_ = new ArrayList<Object>();

    public DatabaseWriterBolt(String tableName, ArrayList<String> columnNames,
            ArrayList<String> columnTypes, String dbURL, String user, String pass) throws SQLException {
        super();
        this.tableName_ = tableName;
        this.columnNames_ = columnNames;
        this.columnTypes_ = columnTypes;
        this.dbURL_ = dbURL;
        this.user_ = user_;
        this.pass_ = pass_;

        //create connection to db
        try {
            connection_ = connector_.makeConnection(dbURL, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //create mediator to interact with db via above connection
        mediator_ = new SQLMediator(connection_, tableName, columnNames, columnTypes);
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        fieldValues_ = new ArrayList<Object>();
        Object fieldValueObject;

        //add all the tuple values to a list
        for (int i = 0; i < columnNames_.size(); i++) {
            fieldValueObject = input.getValue(i);
            fieldValues_.add(fieldValueObject);
        }

        //list passed to the insertRow funtion
        try {
            mediator_.insertRow(fieldValues_);
        } catch (SQLException e) {
            System.out.println("Exception occurred in adding a row ");
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    //required for IBasicBolt
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    //required for IBasicBolt
    @Override
    public void cleanup() {

    }


}
