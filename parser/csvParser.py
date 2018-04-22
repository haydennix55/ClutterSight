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

    sentiments = np.empty([2193],dtype = object)
    tweets = np.empty([2193],dtype = object)
    iter = 0
    neutralCount = 0
    irrelevantCount = 0
    TweetFlag = True


    rownum = 0
    for row in reader:
        if(rownum == 0):
            header = row
        else:
            colnum = 0
            for col in row:
                if(header[colnum] == "Sentiment"):
                    if(col == "neutral"):
                        #Keep neutral counts from out-weighing others
                        if(neutralCount < 551):
                            sentiments[iter] = col
                            neutralCount +=1
                            TweetFlag = False
                    elif(col == "irrelevant"):
                        #Keep irrelevant counts from out-weighing others
                        if(irrelevantCount < 551):
                            sentiments[iter] = col
                            irrelevantCount +=1
                            TweetFlag = False
                    else:
                        sentiments[iter] = col
                        TweetFlag = False
                if(header[colnum] == "TweetText" and TweetFlag == False):
                    CleanTweet = p.clean(col).encode('utf-8')

                    tweets[iter] = CleanTweet
                    iter +=1
                    TweetFlag = True
                colnum += 1
        rownum += 1
    ifile.close()
    #print neutralCount,irrelevantCount
    #print len(sentiments)
    return sentiments,tweets

#sentiments,tweets = Parse()

#for ii in range(100):
#    print"Senitment: ",sentiments[ii],", Tweet: ",tweets[ii]


#print tweets[6].split()
