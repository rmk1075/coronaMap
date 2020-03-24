# {   "idx": 0,
#       "label": "",
#       "title": "",
#       "type": "virus",
#       "location": {
#         "lat": 33.511165,
#         "lng": 126.490914
#       },
#       "address": "제주국제공항 도착<br>제주시호텔 이용",
#       "address2": "제주국제공항 도착<br>제주시호텔 이용",
#       "visitDate": "1/21",
#       "age": "",
#       "nation": "",
#       "gender": "",
#       "date": "1/21",
#       "contact": ""
# }

#-*- coding:utf-8 -*-
import requests
from patientDict import patientInfo
# from patientDict import patientRoute
from bs4 import BeautifulSoup
from selenium import webdriver

# chrome option object - headless setting
chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('headless')

driver = webdriver.Chrome('./chromedriver/chromedriver', chrome_options=chrome_options)

gumi = "http://www.gumi.go.kr/"
driver.get(gumi)

## 지역 별 정보 - 전국, 경상북도, 구미시
# statusList = driver.find_elements_by_class_name("row status pohang")
# for status in statusList:
#     print(status.text)
#     print()

## 환자 정보 - 신상 & 이동 경로
html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')

## 환자 신상 정보 - 나이, 성별
patientsInfo = soup.select('div#sub_cont02 > h4')

## 환자 이동 경로
patientsRoute = soup.select('table.tbl_st1 > tbody')

patientsInfoDictList = list()
patientsRouteDictList = list()
for i in range(0, len(patientsInfo)):
    pInfo = patientsInfo[i].text
    if not pInfo[0].isdigit():
        continue
    
    # pInfo 변환
    patientsInfoDictList.append(patientInfo(pInfo))

    # pRoute 변환
    import re
    pattern = '<th .+>.+</th>'
    pRouteDate = re.findall(pattern, (str)(patientsRoute[i]).strip())
    pRoute = re.split(pattern, (str)(patientsRoute[i]).strip())

    tempDict = dict()
    tempDict['index'] = pInfo.split('번째')[0]
    tempDict['list'] = list()
    for j in range(1, len(pRoute)):
        tempDictListDict = dict()
        tempDictListDict['date'] = BeautifulSoup(
            pRouteDate[j-1], 'html.parser').text

        if pRoute[j].strip() == "":
            continue

        tempDictListDict['route'] = BeautifulSoup(pRoute[j], 'html.parser').text
        tempDict['list'].append(tempDictListDict)

    patientsRouteDictList.append(tempDict)

# print(patientsInfoDictList)

import json

## js 파일 - patientsInfo
f = open("patientsInfo.js", "w", encoding="UTF-8")
f.write('patientsInfo = ')
json.dump(patientsInfoDictList, f, ensure_ascii=False)

## js 파일 - patientsRoute
f = open("patientsRoute.js", "w", encoding="UTF-8")
f.write('patientsRoute = ')
json.dump(patientsRouteDictList, f, ensure_ascii=False)

# 행정구역
# 선산읍, 고아읍, 무을면, 옥성면, 도개면, 해평면, 산동면, 장천면, 송정동, 원평1동, 원평2동, 지산동, 도량동, 선주원남, 형곡1동, 형곡2동, 신평1동, 신평2동, 비산동, 공단1동, 공단2동, 광평동, 상모사곡, 임오동, 인동동, 진미동, 양포동
