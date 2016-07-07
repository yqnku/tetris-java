#!/usr/bin/python3
# -*- coding: <utf-8> -*-

#************************************************************************
# File Name: count_lines.py
# Author: quicy
# Email: xiqian013@live.com 
# Created Time: 2016-04-09 20:05:04
#************************************************************************
 
import os
import os.path
pwd = os.getcwd()
count = 0
d = []
for parent,dirnames,filenames in os.walk(pwd):
    for filename in filenames:
        if (parent.find(".git") != -1 or parent.find(".metadata") != -1):
            continue
        f = open(os.path.join(parent,filename))
        try:
            tmp = len(f.readlines())
            d.append([os.path.join(parent,filename),str(tmp)])
            count += tmp
        except:
            pass
        f.close()
maxLength = len(d[0][0])
for i in range(1,len(d)):
    if len(d[i][0]) > maxLength:
        maxLength = len(d[i][0])
for i in range(len(d)):
    print(d[i][0]+' '*(maxLength+4-len(d[i][0]))+d[i][1])
print("Total Lines:"+str(count))
