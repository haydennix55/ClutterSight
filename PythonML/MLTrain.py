from csvParser import Parse
import numpy as np


def train(y_train, text_train):

    """
    Learn the vocabularly, class_counts, and feature counts from the training data
    """
    #change text sentiments to numbers
    for ii in range(len(y_train)):
        if(y_train[ii] == "positive"):
            y_train[ii] = 1
        elif(y_train[ii] == "negative"):
            y_train[ii] = -1
        elif(y_train[ii] == "neutral"):
            y_train[ii] = 0
        else:
            y_train[ii] = 2

    # get number of classes
    num_classes = len(set(y_train))

    # initialize vocab to feature map
    vocab = dict()

    # initialize class counts
    class_counts = np.zeros(num_classes, dtype=int)


    #create vocabularly
    VCount = 0
    for sentence in text_train:
        for word in sentence:
            if(word not in vocab):
                vocab[word] = VCount
                VCount +=1

    #Create class counts array
    for label in y_train:
        if(label == -1):
            class_counts[0] +=1
        elif(label == 1):
            class_counts[1] +=1
        elif(label == 0):
            class_counts[2] +=1
        else:
            class_counts[3] +=1
    #Create feature counts matrix
    feature_counts = np.zeros((num_classes, len(vocab)), dtype=int)
    for ii in range(text_train.shape[0]):
        for word in text_train[ii]:
            feature_counts[y_train[ii],vocab[word]] +=1

    return feature_counts,class_counts,vocab
#train()
