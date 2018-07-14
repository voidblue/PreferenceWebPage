import random
import sklearn.ensemble
import preprocess
from Preference import Pref
from DBGetter import DBGetter



def train():
    dbgetter = DBGetter()
    dbgetter.connect()
    print("DB연결")
    origin_x_data = list(dbgetter.getInput())
    print("입력데이터 불러옴", len(origin_x_data))
    origin_y_data = list(dbgetter.getOutput())
    print("출력데이터 불러옴", len(origin_y_data))
    origin_x_data = preprocess.transpose(origin_x_data)

    less_input_data = []
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

    origin_x_data = preprocess.transpose(origin_x_data)
    less_input_data = preprocess.transpose(less_input_data)


    print(len(less_input_data))
    print(len(less_input_data[0]))
    dbgetter.close()
    inxdata = []
    inydata = []
    test_x_data = []
    test_y_data = []

    inputfrag2 = []
    for j in range(len(origin_y_data)):
        if sum(origin_y_data[j]) == 5:
            inputfrag2.append(less_input_data[j][13])
            test_x_data.append(list(less_input_data[j]))
            test_y_data.append(list(origin_y_data[j]))
    for i in range(len(less_input_data)):
        if (sum(origin_y_data[i]) !=5):
            if((sum(origin_y_data[i]) != 0)):
                inxdata.append(less_input_data[i])
                inydata.append(origin_y_data[i])
    print("학습셋 개수",len(inxdata))
    print("테스트셋 개수",len(test_x_data))
    inxdata = preprocess.transpose(inxdata)
    inxdata = preprocess.zerotoone(inxdata, inxdata)
    inxdata = preprocess.transpose(inxdata)

    for i in range(0,9):
        pref = Pref(inxdata, inydata)
        layers = []
        layers.append(inxdata)

        layers.append(pref.create_layer(layers[-1], 14, 30))
        #레이어 삽입
        for y in range(3):
            layers.append(pref.create_layer(layers[-1], 30, 30))

        pref.set_cost(layers[-1], 30)

        # 학습하는 부분
        pref.training(learning_rate=0.3, show_training_data=False, step=5000, cost_limit=21, mode='adadelta')

        pref.save_weight('weight'+str(i)  ,'bias'+str(i))
