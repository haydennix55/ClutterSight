package storm;

//storm
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.bolts.*;
import storm.spout.ClutterSightSpout;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

//twitter4j
import twitter4j.FilterQuery;

//logging, sql, other java
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.net.InetAddress;


public class ClutterSightTopology {

    //Twitter Public API for streaming tweets
    private static String _apiKey = "ykYErLacoKubs75VnjtybnBCl";
    private static String _apiSecret = "6itrY64ut0PUYTRYs1NNcHvYGoH6ForwFS2CPJvLpppDdgIUnm";
    private static String _token = "839065651-AuKJxSuwOJjfePiw0lUsdQPHRLZgsK4uTLqLfCXL";
    private static String _tokenSecret = "uMti4rnmFv9mThyYfP8xM95oXCewOPP4XWA436cPQVaOz";

    //ClutterSight MySQL connection info
    private static String dbURL;
    private static String user = "root";
    private static String pass = "CUBigData18";

    //Required fields to append to table
    private static ArrayList<String> columnNames = new ArrayList<String>();
    private static String tableName = "tweets";

    public static void main(String[] args) throws Exception {
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.INFO);

        if (args[0].equals("prod"))
          dbURL = "jdbc:mysql://cluttersightdb.cxh7qnwh9vpl.us-west-2.rds.amazonaws.com:3306/ClutterSight";
        else
          dbURL = "jdbc:mysql://localhost/ClutterSight";


        columnNames.add("tweet");
        columnNames.add("sentiment");
        columnNames.add("sentiment_text");
        columnNames.add("location");

        TopologyBuilder builder = new TopologyBuilder();

        //Define tweet filter and keywords
        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(new String[]{"Apple", "iPhone", "iPad"});
        tweetFilterQuery.language("en");

        //Define topology structure
        builder.setSpout("spout", new ClutterSightSpout(_apiKey, _apiSecret, _token, _tokenSecret, tweetFilterQuery), 1);
        builder.setBolt("write", new FileWriterBolt("tweets.txt"), 1).shuffleGrouping("spout");
        builder.setBolt("db", new DatabaseWriterBolt(tableName, columnNames, dbURL, user, pass), 1).shuffleGrouping("write");

        //Storm Config
        Config conf = new Config();
        conf.setMaxTaskParallelism(3);
        LocalCluster cluster = new LocalCluster();

        //Submit topology and wait, then kill (otherwise stream will run forever)
        cluster.submitTopology("tweet-stream", conf, builder.createTopology());
        if (args[0].equals("prod")) {
          Utils.sleep(60000);
        } else {
          Utils.sleep(25000);
        }
        cluster.shutdown();
    }
}
