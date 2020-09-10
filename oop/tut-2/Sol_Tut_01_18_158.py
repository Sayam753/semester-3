para = str(input())
max = 0
answer = ""
for i in range(0, len(para)):
    for j in range(i, len(para)):
        s = para[i:j+1]
        toFind = para.count(str(s))
        if (toFind>=2):
            if (j-i+1 > max):
                max = j-i+1 
                answer = para[i:j+1]
print(answer)
