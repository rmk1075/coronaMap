import requests
from bs4 import BeautifulSoup

def patientInfo(pInfo):
    # pInfo 변환 - idx, age, gender, address - 집주소
    tempDict = dict()
    tempDict['idx'] = pInfo.split('번째')[0]
    tempDict['age'] = pInfo.split('(')[1].split('대')[0]
    tempDict['gender'] = pInfo.split('/')[1]
    tempDict['address'] = pInfo.split('/')[2].strip(')').strip()

    # geocoding
    url = "https://maps.googleapis.com/maps/api/geocode/xml?address=구미시 " + tempDict['address'] + "&key=AIzaSyAmVnIOsg37a2A31OqbtYrbqadG8y60Dq4"
    location = BeautifulSoup(requests.get(url).text, "lxml")
    tempDict['lat'] = location.select("location > lat")[0].get_text()
    tempDict['lng'] = location.select("location > lng")[0].get_text()

    return tempDict