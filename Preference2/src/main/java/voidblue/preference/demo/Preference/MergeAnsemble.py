import openpyxl

import preprocess
from DBGetter import DBGetter
from LoadData import LoadData
from Preference import Pref

place = {1:"(자연-숲) 한라산", 2:"(자연-숲) 오름", 3: "(자연-숲) 성산일출봉", 4 : "(자연명소) 섬(우도/마라도 등)",
         5:"(기타) 올레길", 6: "(자연명소) 폭포(정방폭포 등)", 7: "(자연명소) 동굴(만장굴 등)", 8: "(자연명소) 해수욕장",
         9:"(자연-숲) 비자림",  10:"(공원) 한라수목원", 11: "(자연-숲) 서귀포자연휴양림", 12: "(자연-숲) 절물자연휴양림",
         13:"(자연명소) 용두암", 14:"(자연명소) 주상절리대", 15: "(공원) 한림공원", 16:"(박물관) 국립제주박물관", 17:"(박물관) 도립미술관", 18:"(박물관) 민속자연사박물관",
         19:"(공원) 제주돌문화공원", 20:"(박물관) 제주세계자연유산센터", 21:"(박물관) 이중섭미술관", 22:"(박물관) 서복전시관", 23:"(공원) 제주4・3평화공원",
         24:"(쇼핑) 동문시장", 25:"(쇼핑) 중앙로지하상가", 26:"(쇼핑) 바오젠거리",27:"(쇼핑) 제주5일장(제주/서귀포)",
         28:"(쇼핑) 서귀포매일올레시장", 29:"(면세점) 신라면세점", 30:"(면세점) 롯데면세점", 31:"(면세점) 제주관광공사 면세점", 32: "(면세점) 공항JDC면세점",
         33:"(문화유적) 제주목관아", 34:"(문화유적) 항몽유적지", 35:"(문화유적) 성읍민속마을",
         36:"(문화유적) 삼양동선사유적", 37:"(문화유적) 제주추사관", 38:"(문화유적) 관덕정", 39:"(문화유적) 이중섭거주지", 40:"하멜기념비",
         41:"(테마파크) 미로공원(김녕/메이지)", 42:"(테마파크) 에코랜드", 43:"(체험) 제주경마공원", 44:"(기타) 불교사찰",
         45:"(체험) 아쿠아플라넷", 46:"(테마파크) 테디베어박물관", 47:"(테마파크) 소인국테마파크", 48:"(체험) 잠수함관광(서귀포 등)",
         49:"(기타) 신비의도로", 50:"(공원) 생각하는정원", 51:"(기타) 드라마촬영지", 52:"(테마파크) 제주별빛누리공원", 53:"(체험) 유람선(요트투어포함)",
        54:"(체험) 제주바다체험장", 55:"(기타) 골프장", 56: "(기타) 카지노"}

def printAccuracy():
    dbGetter = DBGetter()
    dbGetter.connect()
    origin_x_data = dbGetter.getInput()
    y_data = dbGetter.getOutput()
    dbGetter.close()
    recommendvalue, testy_data = merge(origin_x_data, y_data)

    try:
        wb = openpyxl.load_workbook("테스트결과" + '.xlsx')
    except:
        wb = openpyxl.Workbook()
        wb.save("테스트결과" + ".xlsx")

    # 현재 Active Sheet 얻기
    ws = wb.active
    for i, w in enumerate(recommendvalue):
        if i % 5 == 0:
            for j in range(5):
                ws.cell(row=i//5 + 2, column =j + 1).value = place[recommendvalue[i+j] +1]
        if i % 5 == 0:
            k = 0
            for j, test in enumerate(testy_data[i//5]):
                if (test == 1):
                    ws.cell(row=i // 5 + 2, column=k + 7).value = place[j + 1]
                    k+=1

    wb.save("테스트결과" + ".xlsx")
    wb.close()
    Pref(None, None).return_rank_accuracy(testy_data, recommendvalue)

def recomend(x_param):
    print("???뭐죠")
    dbGetter = DBGetter()
    dbGetter.connect()
    origin_x_data = [x_param]
    y_data = [dbGetter.getOutput()[0]]

    less_input_data = []

    origin_x_data = preprocess.transpose(origin_x_data)
    less_input_data.append(list(origin_x_data[0]))
    less_input_data.append(list(origin_x_data[1]))
    less_input_data.append(list(origin_x_data[2]))
    less_input_data.append(list(origin_x_data[3]))
    less_input_data.append(list(origin_x_data[5]))
    less_input_data.append(list(origin_x_data[13]))
    less_input_data.append(list(origin_x_data[23]))
    less_input_data.append(list(origin_x_data[24]))
    less_input_data.append(list(origin_x_data[25]))
    less_input_data.append(list(origin_x_data[26]))
    less_input_data.append(list(origin_x_data[27]))
    less_input_data.append(list(origin_x_data[28]))
    less_input_data.append(list(origin_x_data[29]))
    less_input_data.append(list(origin_x_data[30]))

    less_input_data = preprocess.transpose(less_input_data)

    input = less_input_data
    expected = y_data
    x_data = dbGetter.getInput()
    x_data = preprocess.transpose(x_data)
    input = preprocess.transpose(input)
    input = preprocess.zerotoone(x_data, input)
    input = preprocess.transpose(input)

    preference = Pref(input, expected)
    feat1 = [0] * 56
    result = []
    for i in range(len(input)):
        result.append(list(feat1))
    for i in range(0, 9):
        preference.load_weight(5, 'weight' + str(i), 'bias' + str(i))
        data = preference.predict(list(input))
        rank = preference.process_ranking(data)
        for j, w in enumerate(rank):
            if j % 5 == 0:
                result[j // 5][w[0]] += 5
            if j % 5 == 1:
                result[j // 5][w[0]] += 4
            if j % 5 == 2:
                result[j // 5][w[0]] += 3
            if j % 5 == 3:
                result[j // 5][w[0]] += 2
            if j % 5 == 4:
                result[j // 5][w[0]] += 1

        preference.xlist = []

    finresult = []
    for y in result:
        first = 0
        second = 0
        third = 0
        forth = 0
        fifth = 0

        p1 = 0
        p2 = 0
        p3 = 0
        p4 = 0
        p5 = 0
        for i, item in enumerate(y):

            if item > fifth:
                fifth = item
                p5 = i
            if fifth > forth:
                temp = int(forth)
                forth = int(fifth)
                fifth = temp
                p5 = int(p4)
                p4 = i
            if forth > third:
                temp = int(third)
                third = int(forth)
                forth = temp
                p4 = int(p3)
                p3 = i
            if third > second:
                temp = int(second)
                second = int(third)
                third = temp
                p3 = int(p2)
                p2 = i
            if second > first:
                temp = int(first)
                first = int(second)
                second = temp
                p2 = int(p1)
                p1 = i
        finresult.append(p1)
        finresult.append(p2)
        finresult.append(p3)
        finresult.append(p4)
        finresult.append(p5)
    print("???뭐죠")
    print(end='ESC')
    print(place[finresult[0]+1],end='ESC')
    print(place[finresult[1]+1],end='ESC')
    print(place[finresult[2]+1],end='ESC')
    print(place[finresult[3]+1],end='ESC')
    print(place[finresult[4]+1],end='ESC')
    print("")
    dbGetter.close()
def merge(origin_x_data, y_data):

    less_input_data = []

    origin_x_data = preprocess.transpose(origin_x_data)
    less_input_data.append(list(origin_x_data[0]))
    less_input_data.append(list(origin_x_data[1]))
    less_input_data.append(list(origin_x_data[2]))
    less_input_data.append(list(origin_x_data[3]))
    less_input_data.append(list(origin_x_data[5]))
    less_input_data.append(list(origin_x_data[13]))
    less_input_data.append(list(origin_x_data[23]))
    less_input_data.append(list(origin_x_data[24]))
    less_input_data.append(list(origin_x_data[25]))
    less_input_data.append(list(origin_x_data[26]))
    less_input_data.append(list(origin_x_data[27]))
    less_input_data.append(list(origin_x_data[28]))
    less_input_data.append(list(origin_x_data[29]))
    less_input_data.append(list(origin_x_data[30]))

    less_input_data = preprocess.transpose(less_input_data)
    testx_data = []
    testy_data = []
    for j in range(len(y_data)):
        if sum(y_data[j]) == 5:
            testx_data.append(list(less_input_data[j]))
            testy_data.append(list(y_data[j]))

    x_data = preprocess.transpose(testx_data)
    testx_data = preprocess.transpose(testx_data)
    testx_data = preprocess.zerotoone(x_data, testx_data)
    testx_data = preprocess.transpose(testx_data)
    x_data = preprocess.zerotoone(x_data, x_data)
    x_data = preprocess.transpose(x_data)

    preference = Pref(testx_data, testy_data)
    # layer1 = preference.create_layer(x_data, 31, 31)
    # layer2 = preference.create_layer(layer1, 56, 56)
    # preference.show_cost_graph()
    weight = None
    feat1 = [0] * 56
    result = []
    for i in range(len(testx_data)):
        result.append(list(feat1))
    for i in range(0, 9):
        preference.load_weight(5, 'weight' + str(i), 'bias' + str(i))
        data = preference.predict(list(testx_data))
        rank = preference.process_ranking(data)
        for j, w in enumerate(rank):
            if j % 5 == 0:
                result[j // 5][w[0]] += 5
            if j % 5 == 1:
                result[j // 5][w[0]] += 4
            if j % 5 == 2:
                result[j // 5][w[0]] += 3
            if j % 5 == 3:
                result[j // 5][w[0]] += 2
            if j % 5 == 4:
                result[j // 5][w[0]] += 1

        preference.xlist = []
    # print(result)

    xlist = []
    finresult = []
    for y in result:
        first = 0
        second = 0
        third = 0
        forth = 0
        fifth = 0

        p1 = 0
        p2 = 0
        p3 = 0
        p4 = 0
        p5 = 0
        for i, item in enumerate(y):

            if item > fifth:
                fifth = item
                p5 = i
            if fifth > forth:
                temp = int(forth)
                forth = int(fifth)
                fifth = temp
                p5 = int(p4)
                p4 = i
            if forth > third:
                temp = int(third)
                third = int(forth)
                forth = temp
                p4 = int(p3)
                p3 = i
            if third > second:
                temp = int(second)
                second = int(third)
                third = temp
                p3 = int(p2)
                p2 = i
            if second > first:
                temp = int(first)
                first = int(second)
                second = temp
                p2 = int(p1)
                p1 = i
        finresult.append(p1)
        finresult.append(p2)
        finresult.append(p3)
        finresult.append(p4)
        finresult.append(p5)
    return finresult , testy_data








