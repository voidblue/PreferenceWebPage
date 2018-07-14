#데이타 선처리모듈
def zerotoone(range, x):
    result = []
    for i, k in enumerate(x):
        xlist = list(k)
        xmax = max(range[i])
        xmin = min(range[i])
        for j, w in enumerate(k):
            xlist[j] = (xlist[j] - xmin) /(xmax - xmin)
        result.append(xlist)
    return result

def transpose(matrix):
    temp_matrix = []
    ret_matrix = []
    x = len(matrix)
    y = len(matrix[0])
    for j in range(y):
        for i in range(x):
            temp_matrix.append(matrix[i][j])
        ret_matrix.append(temp_matrix)
        temp_matrix=[]
    return ret_matrix