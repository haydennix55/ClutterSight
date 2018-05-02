from csvParser import Parse
from MLTrain import train
import numpy as np


def predict_log_score(text_str,feature_counts,class_counts,vocab,num_classes,alpha):
    """
    Get the log-probability score for each class
    for a query string

    :param text_str: a single string of text to compute the log_score for
    """

    class_scores = np.zeros(num_classes)
    words = text_str.split()
    #Naive bayes algorithm for calculating score
    for c in range(num_classes):
        class_scores[c] = np.log(float(class_counts[c]+alpha)/float(np.sum(class_counts)+(alpha*num_classes)))
        for word in words:
            if(word in vocab):
                class_scores[c] += np.log(float(feature_counts[c,vocab[word]]+alpha)/float(np.sum(feature_counts[c])+(len(vocab)*alpha)))
            else:
                class_scores[c] +=0

    return class_scores


def predict(tweet_arr):
    """
    Predict the class of each example in text_list
    :param text_list: a list or ndarray of text strings to make predictions on
    """
    y_train, text_train = Parse()
    feature_counts,class_counts,vocab = train(y_train,text_train)
    num_classes = len(class_counts)
    alpha = 1
    #take in the tweet tuple and predict then intert predictions in tuple
    for ii in range(len(tweet_arr)):
        PredLabel = np.argmax(predict_log_score(tweet_arr[ii][1],feature_counts,class_counts,vocab,num_classes,alpha))
        if(PredLabel == 0):
            tweet_arr[ii][2] = -1
            tweet_arr[ii][3] = "negative"
        elif(PredLabel == 1):
            tweet_arr[ii][2] = 1
            tweet_arr[ii][3] = "positive"
        elif(PredLabel == 2):
            tweet_arr[ii][2] = 0
            tweet_arr[ii][3] = "neutral"
        else:
            tweet_arr[ii][2] = 2
            tweet_arr[ii][3] = "irrelevant"
    return tweet_arr




#PredLabels = predict(["this phone is so amazing please get it","I fuck hate this is a peice of junk","why even buy this"])
#print PredLabels
