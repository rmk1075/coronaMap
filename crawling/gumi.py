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

# status list
# statusList = driver.find_elements_by_class_name("row status pohang")
# for status in statusList:
#     print(status.text)
#     print()


# patient - tbl_wrap
html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')
patientsInfo = soup.select('div#sub_cont02 > h4')
patientsRoute = soup.select('table.tbl_st1')

for p in patientsRoute:
    print(p.text)
    print('----------------------------------')
