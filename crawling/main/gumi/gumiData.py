import pymysql
import json

user = 'backEnd'
password = 'backEnd'
db = 'corona_gumi'

# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user=user, password=password,
                       db=db, charset='utf8')

# Connection 으로부터 Cursor 생성
curs = conn.cursor()

# Read json file
json_path = 'data/patientsInfo.json'
json_file = open(json_path, 'r', encoding='utf8')

patientsInfo = json.load(json_file)

table_name = 'patientsInfo'

for patient in patientsInfo:
    # TODO:
    print(patient['idx'])

    # SQL문 실행
    sql = "insert into " + table_name + " values(" + patient['idx'] + ',' + patient['age'] + ', "' + patient['gender'] + '", "' + patient['address'] + '" , "' + patient['lat'] + '" , "' + patient['lng'] + '")'
    print(sql)
    curs.execute(sql)

# commit
conn.commit()

# Connection 닫기
conn.close()
