from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
import json
import requests
import urllib.parse
import numpy as np
import pandas as pd
import random
import warnings
warnings.filterwarnings("ignore")
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.model_selection import GridSearchCV
import sklearn
import pickle
from sklearn.preprocessing import LabelEncoder
from sklearn import preprocessing
from sklearn import model_selection
from sklearn.linear_model import LogisticRegression
from sklearn import metrics
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeClassifier
from numpy import savetxt
from sklearn.metrics import f1_score
import csv
from .models import Greeting

# Create your views here.
def index(request):
    #r = requests.get('http://httpbin.org/status/418')
    parameters = {'identifier_loginEmail': 'mkivory97@gmail.com','identifier_loginName': 'Mark'}
    parameters2 = urllib.parse.urlencode(parameters)
    #PARAMS = {'identifier_loginEmail': 'mkivory97@gmail.com','identifier_loginName': 'Mark'}
    #r = requests.get('https://c16307271.000webhostapp.com/getCountries.php',params = parameters2)
    # sending post request and saving response as response object
    r = requests.post(url = "https://c16307271.000webhostapp.com/getCountries.php", data=parameters)
    #r = requests.post(url='https://c16307271.000webhostapp.com/getCountries.php?identifier_loginEmail=mkivory97@gmail.com&identifier_loginName=Mark')
    print(r.text)
    dataFromPHP=r.text
    data = {
        'country': 'Finland'
    }
    thisdict = {
    }
    splitUp =dataFromPHP.split(", ")
    for x in splitUp:
        thisdict[x] = splitUp[x]
        thisdict[x+1] = splitUp[x+1]


    print(data)
    #return JsonResponse(data)
    #return HttpResponse('<pre>' + r.text + '</pre>')
    return HttpResponse(thisdict)
    #return HttpResponse('Hello from Python!')
    #return render(request, "index.html")


def db(request):

    greeting = Greeting()
    greeting.save()

    greetings = Greeting.objects.all()

    return render(request, "db.html", {"greetings": greetings})

