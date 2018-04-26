package storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class FileWriterBolt extends BaseRichBolt {
    PrintWriter writer;
    int index = 0;
    private OutputCollector _collector;
    private String _filename;

    public FileWriterBolt(String filename){
        this._filename = filename;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
        try {
            writer = new PrintWriter(_filename, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void execute(Tuple tuple) {
        Status tweet = (Status) tuple.getValueByField("tweet");
        String loc = tweet.getUser().getLocation();

        writer.println("TWEET: " + tweet.getText());
        writer.flush();
        // Confirm that this tuple has been treated.
        _collector.emit(tuple, new Values(tweet.getText(), null, null, loc));
        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("tweet", "sentiment", "sentiment_text", "location"));
    }

    @Override
    public void cleanup() {
        writer.close();
        super.cleanup();

    }
}
