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
patientsRoute = soup.select('table.tbl_st1')

patientsInfoDictList = list()
# paritentsRouteDict = dict()
for i in range(0, len(patientsInfo)):
    pInfo = patientsInfo[i].text
    if not pInfo[0].isdigit():
        continue

    # pInfo 변환 - idx, age, gender, address - 집주소
    tempDict = dict()
    tempDict['idx'] = pInfo.split('번째')[0]
    tempDict['age'] = pInfo.split('(')[1].split('대')[0]
    tempDict['gender'] = pInfo.split('/')[1]
    tempDict['address'] = pInfo.split('/')[2].strip(')').strip()
    patientsInfoDictList.append(tempDict)

    # geocoding
    url = "https://maps.googleapis.com/maps/api/geocode/xml?address=구미시 " + tempDict['address'] + "&key=AIzaSyAmVnIOsg37a2A31OqbtYrbqadG8y60Dq4"
    location = BeautifulSoup(requests.get(url).text, "lxml")
    tempDict['lat'] = location.select("location > lat")[0].get_text()
    tempDict['lng'] = location.select("location > lng")[0].get_text()

    # pRoute 변환
    pRoute = patientsRoute[i].text.strip().split("\n")

    print(tempDict['idx'] + '번째 확진자')
    for j in range(1, len(pRoute)):
        if pRoute[j] == "":
            continue
        # print(pRoute[j])

        if 3 <= len(pRoute[j].split('.')):
            tmp = pRoute[j].split(')')
            print(tmp[0] + ')')
            print(tmp[1])
        else:
            print(pRoute[j])

    print('--------------------------')

# print(patientsInfoDictList)

## TODO: 최적화 필요!! - json, js 파일 생성 과정 한번에
## json 파일
import json

with open("patientsInfo.json", "w", encoding='UTF-8') as json_file:
    json.dump(patientsInfoDictList, json_file, ensure_ascii=False)

## js 파일
f = open("patientsInfo.js", "w", encoding="UTF-8")
f.write('patientsInfo = ')
with open("patientsInfo.json", "r", encoding="UTF-8") as jsonFile:
    for line in jsonFile.readlines():
        f.write(line)


# 행정구역
# 선산읍, 고아읍, 무을면, 옥성면, 도개면, 해평면, 산동면, 장천면, 송정동, 원평1동, 원평2동, 지산동, 도량동, 선주원남, 형곡1동, 형곡2동, 신평1동, 신평2동, 비산동, 공단1동, 공단2동, 광평동, 상모사곡, 임오동, 인동동, 진미동, 양포동