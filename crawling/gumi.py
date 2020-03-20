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
from bs4 import BeautifulSoup
from selenium import webdriver

# chrome option object - headless setting
chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('headless')

driver = webdriver.Chrome('./chromedriver/chromedriver', chrome_options=chrome_options)

gumi = "http://www.gumi.go.kr/"
driver.get(gumi)

## 지역 정보
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
patientsRoute = soup.select('table.tbl_st1')

patientsInfoDictList = list()
# paritentsRouteDict = dict()
for i in range(0, len(patientsInfo)):
    pInfo = patientsInfo[i].text
    if not pInfo[0].isdigit():
        continue
    # print(pInfo)

    # pInfo 변환 - idx, age, gender, address - 집주소
    tempDict = dict()
    tempDict['idx'] = pInfo.split('번째')[0]
    tempDict['age'] = pInfo.split('(')[1].split('대')[0]
    tempDict['gender'] = pInfo.split('/')[1]
    tempDict['address'] = pInfo.split('/')[2].strip(')')
    patientsInfoDictList.append(tempDict)

    pRoute = patientsRoute[i].text.strip().split("\n")
    # for j in range(1, len(pRoute)):
    #     if pRoute[j] == "":
    #         continue
    #     print(pRoute[j])
    # print('--------------------------')

print(patientsInfoDictList)

import json

with open("patientsInfo.json", "w", encoding='UTF-8') as json_file:
    json.dump(patientsInfoDictList, json_file, ensure_ascii=False)
