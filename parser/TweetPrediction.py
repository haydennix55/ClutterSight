from csvParser import Parse
from MLTrain import train
import numpy as np

feature_counts,class_counts,vocab = train()
num_classes = len(class_counts)
alpha = 1

def predict_log_score(text_str):
    """
    Get the log-probability score for each class
    for a query string

    :param text_str: a single string of text to compute the log_score for
    """

    class_scores = np.zeros(num_classes)
    words = text_str.split()

    for c in range(num_classes):
        print "prior: ",class_counts[c],np.sum(class_counts)
        print "alpha: ",alpha
        class_scores[c] = np.log((class_counts[c]+alpha)/(np.sum(class_counts)+(alpha*num_classes)))
        for word in words:
            if(word in vocab):
                print "(#",word," in class/Total terms in class): ",feature_counts[c,vocab[word]],np.sum(feature_counts[c])
                class_scores[c] += np.log((feature_counts[c,vocab[word]]+alpha)/(np.sum(feature_counts[c])+(len(vocab)*alpha)))
            else:
                class_scores[c] +=0

    return class_scores


def predict(text_list):
    """
    Predict the class of each example in text_list

    :param text_list: a list or ndarray of text strings to make predictions on
    """
    yhat = np.zeros(len(text_list), dtype=int)
    for ii in range(len(text_list)):
        predictions = predict_log_score(text_list[ii])
        if(predictions[0]<predictions[1]):
            yhat[ii]=1
        else:
            yhat[ii]=0
    return yhat
predict(["greatest"])
