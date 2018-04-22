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
        #print "prior: ",class_counts[c]+alpha,np.sum(class_counts)+alpha
        #print "log: ",float(float(class_counts[c]+alpha)/float(np.sum(class_counts)+(alpha*num_classes)))
        class_scores[c] = np.log(float(class_counts[c]+alpha)/float(np.sum(class_counts)+(alpha*num_classes)))
        for word in words:
            if(word in vocab):
                #print "(#",word," in class/Total terms in class): ",feature_counts[c,vocab[word]],np.sum(feature_counts[c])
                class_scores[c] += np.log(float(feature_counts[c,vocab[word]]+alpha)/float(np.sum(feature_counts[c])+(len(vocab)*alpha)))
            else:
                class_scores[c] +=0

    return class_scores


def predict(text_list):
    """
    Predict the class of each example in text_list

    :param text_list: a list or ndarray of text strings to make predictions on
    """
    PredLabels = []
    for ii in range(len(text_list)):
        PredLabels.append(np.argmax(predict_log_score(text_list[ii])))
    return PredLabels
PredLabels = predict(["fuck fuck fuck","I hate this computer","why even buy this"])
print PredLabels
