# -*- coding: utf-8 -*-

"""
    Homework for Team 1
    :copyright: Jang JeeHyun, Jung Hyejin, Lee Jaehan
"""
from __future__ import with_statement
import time
from sqlite3 import dbapi2 as sqlite3
from hashlib import md5
from datetime import datetime
from contextlib import closing
from flask import Flask, request, session, url_for, redirect, \
     render_template, abort, g, flash
from werkzeug.security import check_password_hash, generate_password_hash

from bs4 import BeautifulSoup
import requests

from pip._vendor.urllib3.util import url
from urllib import request as url
from collections import Counter
import re


import nltk
from nltk.corpus import stopwords
from nltk.sentiment.vader import SentimentIntensityAnalyzer
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

import pandas as pd
from builtins import int



# configuration
DATABASE = 'youtube.db'
PER_PAGE = 30
DEBUG = True
SECRET_KEY = 'development key'

# create our little application :)
app = Flask(__name__)
app.config.from_object(__name__)

def connect_db():
    """Returns a new connection to the database."""
    return sqlite3.connect(app.config['DATABASE'])


def init_db():
    """Creates the database tables."""
    with closing(connect_db()) as db:
        with app.open_resource('schema.sql', mode='r') as f:
            db.cursor().executescript(f.read())
        db.commit()


def query_db(query, args=(), one=False):
    """Queries the database and returns a list of dictionaries."""
    cur = g.db.execute(query, args)
    rv = [dict((cur.description[idx][0], value)
               for idx, value in enumerate(row)) for row in cur.fetchall()]
    return (rv[0] if rv else None) if one else rv

@app.before_request
def before_request():
    """Make sure we are connected to the database each request and look
    up the current user so that we know he's there.
    """
    g.db = connect_db()

@app.teardown_request
def teardown_request(exception):
    """Closes the database again at the end of the request."""
    if hasattr(g, 'db'):
        g.db.close()

@app.route('/')
def root():
    return render_template("home.html")





@app.route('/sentiment',methods=['POST'])
def sentiment():
    url = request.form['url1']
    
    options = webdriver.ChromeOptions()
    options.add_argument('headless')
    
    driver = webdriver.Chrome("C:\\Users\\user\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe",chrome_options=options)
    print('크롤링 시작')
    driver.get(url)
    
    body = driver.find_element_by_tag_name("body")
    num_of_pagedowns = 40
    while num_of_pagedowns:
        
            body.send_keys(Keys.PAGE_DOWN)
            time.sleep(0.2)
            num_of_pagedowns -= 1
            
    html = driver.page_source
        
    result = BeautifulSoup(html,'html.parser')
    body = result.find("body")
    
    title = body.find_all('yt-formatted-string', attrs={'class':'style-scope ytd-video-primary-info-renderer'})
    title1=title[0].get_text()
    
    thread=body.find_all('ytd-comment-renderer', attrs={'class':'style-scope ytd-comment-thread-renderer'})
        
    cmtlist=[]
    totalnum=0
    count =0
    eval = ''
    for items in thread:
        div = items.find_all('yt-formatted-string', attrs={'id':'content-text'})
        for lists in div:
            if lists != None:
                try:
                    cmt = lists.string
                    only_english = re.sub('[^a-zA-Z]',' ',cmt)
                    sid = SentimentIntensityAnalyzer()
                    a = sid.polarity_scores(cmt)
                    count=count+1
                    totalnum=totalnum+a['compound']
                except TypeError as e:
                    pass
    
    if count!=0:
        totalnum=totalnum/count
        totalnum=totalnum*100
        print(totalnum)
        if totalnum>5:
            eval = '긍정'
        elif totalnum<-5:
            eval = '부정'
        else:
            eval = '중립'
    
    print(eval)
    g.db.execute('''insert into 
                    sentiment (url_addr1, title, sent, percentage)
                    values (?, ?, ?, ?)''', 
                    (url, title1, eval, totalnum))
    g.db.commit()
    
    return render_template('view.html',words=query_db('''select sentiment.* from sentiment'''))




if __name__ == '__main__':
    init_db()
    app.run()
    