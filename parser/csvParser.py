import csv

ifile = open('/Users/oz346/Desktop/ClutterSight/parser/FullCorpus.csv', "rt", encoding = "utf8")
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
                tweets.append(col)
            colnum += 1
    rownum += 1
ifile.close()

for ii in range(10):
    print("Senitment: ",sentiments[ii],", Tweet: ",tweets[ii])
