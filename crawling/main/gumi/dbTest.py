import pymysql

user = 'backEnd'
password = 'backEnd'
db = 'corona_gumi'

# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user=user, password=password,
                       db=db, charset='utf8')

# Connection 으로부터 Cursor 생성
curs = conn.cursor()

# SQL문 실행
sql = "select * from test"
curs.execute(sql)

# 데이타 Fetch
rows = curs.fetchall()
print(rows)     # 전체 rows
print(rows[0])  # 첫번째 row: ('data1', 1, 1)
print(rows[1])  # 두번째 row: ('data2', 2, 2)

# Connection 닫기
conn.close()
