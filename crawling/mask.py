import requests
import json

## 약국 정보
url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page="
req = requests.get(url + "1")

## js 파일
f = open("./pharmacyLoc.js", "w", encoding="UTF-8")
f.write('pharmacyLoc = ')

pharmacyList = list()
totlaPages = req.json()['totalPages']
for pageIdx in range(totlaPages):
    pageUrl = url + str(pageIdx)
    page = requests.get(pageUrl)
    jsonPage = page.json()

    for pharmacy in jsonPage['storeInfos']:
        if "구미시" in pharmacy['addr']:
            pharmacyList.append(pharmacy)
json.dump(pharmacyList, f, ensure_ascii=False)