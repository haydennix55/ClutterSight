import csv
import io
import re

ifile = open('FullCorpus.csv', "rt")
#ifile = open('FullCorpus.csv', "rt", encoding = "utf8")

reader = csv.reader(ifile)

sentiments = []
tweets = []

rownum = 0
for row in reader:
    if(rownum == 0):
        header = row
    else:
        colnum = 0
        for col in row:
            if(header[colnum] == "Sentiment"):
                sentiments.append(col)
            if(header[colnum] == "TweetText"):
                URLlessTweet = re.sub(r'https?:\/\/.*\s|\r', '', col)#, flags=re.MULTILINE)
                tweets.append(URLlessTweet)
            colnum += 1
    rownum += 1
ifile.close()

for ii in range(100):
    print"Senitment: ",sentiments[ii],", Tweet: ",tweets[ii]

print tweets[6]
URLlessTweet = re.sub(r'https?:\/\/.*\s', '', tweets[6], flags=re.MULTILINE)
print URLlessTweet
#print tweets[6].split()
