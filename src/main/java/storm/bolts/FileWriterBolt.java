package storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class FileWriterBolt extends BaseRichBolt {
    PrintWriter writer;
    int count = 0;
    private OutputCollector _collector;
    private String _filename;

    public FileWriterBolt(String filename){
        _filename = filename;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
        writer = new PrintWriter(_filename, "UTF-8");
    }

    @Override
    public void execute(Tuple tuple) {
        writer.println((count++)+":"+tuple);
        writer.flush();

        _collector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void cleanup() {
        writer.close();
        super.cleanup();

    }
}
