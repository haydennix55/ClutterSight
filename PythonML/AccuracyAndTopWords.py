from csvParser import Parse
from MLTrain import train
from TweetPrediction import predict
import numpy as np

def TopWords(feature_counts,vocab):
    #sort the position of the array of the feature counts from least to greatest
    Negargsort = feature_counts[0].argsort()
    Posargsort = feature_counts[1].argsort()
    #Take the most counted words for positive and negative class
    NegArgs = Negargsort[-10:]
    PosArgs = Posargsort[-10:]
    NegInd = 0
    #loop through and find the values associated with highest counted words
    for PosInd in range(10):
        for key,value in vocab.iteritems():
            if(value == NegArgs[NegInd]):
                print "Negative top word:",key
            if(value == PosArgs[PosInd]):
                print "Positive top word:",key
        NegInd +=1


y_train, text_train = Parse()
feature_counts,class_counts,vocab = train(y_train,text_train)
TopWords(feature_counts,vocab)
