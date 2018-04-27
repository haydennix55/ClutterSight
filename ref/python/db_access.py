#!/usr/bin/python
import mysql.connector

# SQL auth
hostname = 'cluttersightdb.cxh7qnwh9vpl.us-west-2.rds.amazonaws.com'
username = 'root'
password = 'CUBigData18'
database = 'ClutterSight'

# Read in id, tweet, sentiment, sentiment_text for unlabeled tweets
# @param conn - mysql.connector connection
# @return - list of tuples (id, tweet, None, None)
def tweetsIn( conn ) :
    cur_ = conn.cursor()

    cur_.execute( "SELECT id, tweet FROM tweets where sentiment is NULL" )

    result = cur_.fetchall()
    tweet_arr = []

    for row in result:
        tweet_arr.append([row[0],row[1].replace("\n"," ").encode('utf-8') + "\n", None, None])

    return tweet_arr

# Update the tweets with the current content of tweet_arr (labeled)
# @param conn - mysql.connector connection
# @param tweet_arr - list of tuples (id, tweet, sentiment, sentiment_text)
def tweetsOut( conn, tweet_arr ):
    cur_ = conn.cursor()

    for row in tweet_arr:
        if (row[2] != None and row[3] != None):
            cur_.execute ("""
                UPDATE tweets
                SET sentiment=%s, sentiment_text=%s
                WHERE id=%s
            """, (row[2],row[3],row[0]))

    conn.commit()

# Example
conn_ = mysql.connector.connect( host=hostname, user=username, passwd=password, db=database )
tweet_arr = tweetsIn( conn_ )

# Replace with ML sentiment analysis assignment
for row in tweet_arr:
    # assess sentiment
    row[2] = 1
    row[3] = "analyzed"

tweetsOut(conn_, tweet_arr)
conn_.close()
