import csv
import numpy as np
import io
import re as regex
import nltk
from nltk.tokenize import word_tokenize
import string
import preprocessor as p
from nltk.corpus import stopwords
#nltk.download('punkt')
#nltk.download('stopwords')

def Parse():
    ifile = open('FullCorpus.csv', "rt")
    #ifile = open('FullCorpus.csv', "rt", encoding = "utf8")
    p.set_options(p.OPT.URL, p.OPT.EMOJI, p.OPT.MENTION, p.OPT.RESERVED,p.OPT.SMILEY,p.OPT.HASHTAG)

    reader = csv.reader(ifile)

    sentiments = np.empty([5113],dtype = object)
    tweets = np.empty([5113],dtype = object)
    iter = 0

    rownum = 0
    for row in reader:
        if(rownum == 0):
            header = row
        else:
            colnum = 0
            for col in row:
                if(header[colnum] == "Sentiment"):
                    sentiments[iter] = col
                if(header[colnum] == "TweetText"):
                    CleanTweet = p.clean(col).encode('utf-8')


                    tweets[iter] = CleanTweet
                    iter +=1
                colnum += 1
        rownum += 1
    ifile.close()
    return sentiments,tweets

sentiments,tweets = Parse()

#for ii in range(100):
#    print"Senitment: ",sentiments[ii],", Tweet: ",tweets[ii]


#print tweets[6].split()
