import numpy
import pymysql
gender = {'남자':1, '여자':2}
education = {'고졸이하' :1 , '대학생(휴학생포함)':2, '대학졸업':3, '대학원졸업 이상':4}
job = {'관리자':1, '전문가 및 관련 종사자':2, '사무종사자':3, '서비스종사자':4,
       '판매종사자':5, '농림어업숙련 종사자':6, '기능원 및 관련 기능종사자':7,
       '장치기계조작 및 조립종사자':8, '단순노무자':9, '군인/공무원':10,
       '학생':11, '주부':12, '무직(은퇴자 포함)':13}

class DBGetter:

    db = None
    def connect(self):
        self.db = pymysql.connect(host="220.149.42.125",  # your host, usually localhost
                             user="root",  # your username
                             passwd="456111",  # your password
                             db="preference",
                             port=3306,
                             charset='utf8')  # name of the data base

    # you must create a Cursor object. It will let
    #  you execute all the queries you need


    def getInput(self):
        cur = self.db.cursor()

        cur.execute("select * from trainingInput")
        result = []
        data = cur.fetchall()
        for row in data:
            result.append(list(row[1:]))

        print("공공데이터(",len(data),")개 로드됨")

        cur.execute("select * from INPUTS a inner join USERS b on a.USER_ID = b.id")
        data = cur.fetchall()
        for row in data:
            tempList = list(row[1:26])
            tempList.append(int(row[36]))  #region

            try:
                tempList.append(int(row[31]))
            except:
                tempList.append(gender[row[31]])

            try:
                tempList.append(int(row[33]))
            except:
                tempList.append(education[row[33]])
            tempList.append(int(row[32])) #birthYear
            tempList.append(row[26])
            try:
                tempList.append(int(row[34]))
            except:
                tempList.append(job[row[34].split(' : ')[0]])
            result.append(tuple(tempList))
        print("수집한데이터(",len(data),")개 로드됨")
        result = numpy.asarray(result, 'float32')
        cur.close()
        return result
    def getOutput(self):
        cur = self.db.cursor()

        cur.execute("""select * from trainingOutput""")
        result = []
        data = cur.fetchall()
        for row in data:
            result.append(row[1:])
        print("공공데이터(",len(data),")개 로드됨")

        cur.execute("select * from OUTPUTS a inner join USERS b on a.USER_ID = b.id")
        data = cur.fetchall()
        for row in data:
            result.append(row[1:57])
        print("수집한데이터(",len(data),")개 로드됨")

        result = numpy.asarray(result, 'float32')
        cur.close()
        return result
    def close(self):
        self.db.close()

if __name__ == '__main__':
    dbgetter = DBGetter()
    dbgetter.connect()
    dbgetter.getInput()