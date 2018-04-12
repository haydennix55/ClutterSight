package storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.bolts.*;
import storm.spout.ClutterSightSpout;
import twitter4j.FilterQuery;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ClutterSightTopology {


    private static String _apiKey = "ykYErLacoKubs75VnjtybnBCl";
    private static String _apiSecret = "6itrY64ut0PUYTRYs1NNcHvYGoH6ForwFS2CPJvLpppDdgIUnm";
    private static String _token = "839065651-AuKJxSuwOJjfePiw0lUsdQPHRLZgsK4uTLqLfCXL";
    private static String _tokenSecret = "uMti4rnmFv9mThyYfP8xM95oXCewOPP4XWA436cPQVaOz";


    public static void main(String[] args) throws Exception {
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.INFO);


        TopologyBuilder builder = new TopologyBuilder();
        FilterQuery tweetFilterQuery = new FilterQuery();

        tweetFilterQuery.track(new String[]{"Kardashian", "Elon", "Nike"});

        builder.setSpout("spout", new ClutterSightSpout(_apiKey, _apiSecret, _token, _tokenSecret, tweetFilterQuery), 1);
        builder.setBolt("write", new FileWriterBolt("tweets.txt"), 1).shuffleGrouping("spout");

        Config conf = new Config();
        conf.setMaxTaskParallelism(3);
        LocalCluster cluster = new LocalCluster();

        cluster.submitTopology("tweet-stream", conf, builder.createTopology());
        Utils.sleep(600000);
        cluster.shutdown();
    }
}
