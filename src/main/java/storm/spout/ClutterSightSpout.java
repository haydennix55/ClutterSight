package storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class ClutterSightSpout extends BaseRichSpout {

    public static String MESSAGE = "";
    private String _apiKey;
    private String _apiSecret;
    private String _token;
    private String _tokenSecret;

    private TwitterStream _tweetStream;
    private FilterQuery _tweetFilterQuery;
    private SpoutOutputCollector _collector;
    private LinkedBlockingQueue _tweets;

    public ClutterSightSpout(String apiKey, String apiSecret, String token, String tokenSecret) {
        _apiKey = apiKey;
        _apiSecret = apiSecret;
        _token = token;
        _tokenSecret = tokenSecret;
    }

    public ClutterSightSpout(String apiKey, String apiSecret, String token, String tokenSecret, FilterQuery filterQuery) {
        this(apiKey, apiSecret, token, tokenSecret);
        _tweetFilterQuery = filterQuery;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(MESSAGE));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        _tweets = new LinkedBlockingQueue();
        _collector = spoutOutputCollector;

        ConfigurationBuilder _configurationBuilder = new ConfigurationBuilder();
        _configurationBuilder.setOAuthConsumerKey(_apiKey)
                .setOAuthConsumerSecret(_apiSecret)
                .setOAuthAccessToken(_token)
                .setOAuthAccessTokenSecret(_tokenSecret);
        _tweetStream = new TwitterStreamFactory(_configurationBuilder.build()).getInstance();
        _tweetStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                _tweets.offer(status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception ex) {
                
            }
        });
        if (_tweetFilterQuery == null) {
            _tweetStream.sample();
        }
        else {
            _tweetStream.filter(_tweetFilterQuery);
        }

    }

    /**
     * When requested for next tuple, reads message from queue and emits the message.
     */
    @Override
    public void nextTuple() {
        Object s = _tweets.poll();
        if (s == null) {
            Utils.sleep(500);
        } else {
            _collector.emit(new Values(s));

        }
    }

    @Override
    public void close() {
        _tweetStream.shutdown();
        super.close();
    }

}
