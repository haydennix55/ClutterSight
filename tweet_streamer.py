from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import twitter_credentials

class TweetStreamer():

    def stream_tweets(self, output_file, keyword_list):

        listener = PrintListener(output_file)
        auth = OAuthHandler(twitter_credentials.API_KEY, twitter_credentials.API_SECRET)
        auth.set_access_token(twitter_credentials.ACCESS_TOKEN, twitter_credentials.ACCESS_TOKEN_SECRET)
        stream = Stream(auth, listener)

        stream.filter(track=keyword_list)


class PrintListener(StreamListener):

    def __init__(self, output_file):
        self.output_file = output_file

    # overload of abstract class in StreamListener
    def on_data(self, data):
        try:
            print(data)
            with open(self.output_file, 'a') as out:
                out.write(data)
            return True
        except BaseException as err:
            print("Error in on_data method: %s" % str(err))
        return True

    # another abstract class overload
    def on_error(self, status):
        print(status)


if __name__ == '__main__':

    keyword_list = ['elon musk', 'spacex']
    output_file = "tweets.json"

    tweet_streamer = TweetStreamer()
    tweet_streamer.stream_tweets(output_file, keyword_list)
