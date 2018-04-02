import csv

ifile = open(‘full_corpus.csv’, “rb”)
reader = csv.reader(ifile)

Sentiments = [None] * 5113
Tweets = [None] * 5113


rownum = 0
for row in reader:
    if (rownum == 0):
        header = row
    else:
        colnum = 0
        for col in row:
            if(header[colnum] == "Sentiment"):
                Sentiments[rownum] = col
            if(header[colnum] == "TweetText"):
                Tweets[rownum] = col
            colnum + = 1

    rownum + = 1


ifile.close()

for ii in range(10):
    print("Sentiment: ",Sentiments[ii],", Tweet: ",Tweets[ii])
