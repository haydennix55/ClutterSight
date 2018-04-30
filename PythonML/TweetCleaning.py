import nltk
from nltk.tokenize import word_tokenize
import string
import preprocessor as p
from nltk.corpus import stopwords

def TweetCleaning(tweet):
    p.set_options(p.OPT.URL, p.OPT.EMOJI, p.OPT.MENTION, p.OPT.RESERVED,p.OPT.SMILEY,p.OPT.HASHTAG)
    CleanTweet = p.clean(tweet).encode('utf-8')
    tokens = CleanTweet.split()
    # convert to lower case
    tokens = [w.lower() for w in tokens]
    # remove punctuation from each word
    table = string.maketrans('                                ', string.punctuation)
    stripped = [w.translate(table) for w in tokens]
    # remove remaining tokens that are not alphabetic
    words = [word for word in stripped if word.isalpha()]
    # filter out stop words
    stop_words = set(stopwords.words('english'))
    words = [w for w in words if not w in stop_words]
    return words
