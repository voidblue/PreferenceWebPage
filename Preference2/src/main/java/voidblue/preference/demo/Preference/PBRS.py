import sys

import MergeAnsemble
import Trainer
import os

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

if (len(sys.argv)>=2):
    # print(sys.argv[1])
    if sys.argv[1] == '--train' or sys.argv[1] == '-t':
        Trainer.train()
    elif sys.argv[1] == '--recommend' or sys.argv[1] == '-r':
        argv = [int(i) for i in sys.argv[2:]]
        MergeAnsemble.recomend(argv)
    elif sys.argv[1] == "--accuracy" or sys.argv[1] == '-a':
        MergeAnsemble.printAccuracy()
    else:
        print("option : train, t, recommend, r, accuracy, a")
else:
    print("option : train, t, recommend, r, accuracy, a")
