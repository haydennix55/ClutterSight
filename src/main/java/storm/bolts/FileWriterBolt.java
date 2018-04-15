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
        writer.println("TWEET: " + tuple.getString(0));
        writer.flush();
        // Confirm that this tuple has been treated.
        _collector.emit(tuple, new Values(tuple.getString(0), 1, "test"));
        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("tweet", "sentiment", "sentiment_text"));
    }

    @Override
    public void cleanup() {
        writer.close();
        super.cleanup();

    }
}
