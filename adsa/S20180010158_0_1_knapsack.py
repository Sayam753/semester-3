# 0-1 knapsack implementation by Sayam Kumar - S20180010158
def unbounded_knapsack(n, weights, values, capacity):
	k = list()
	weights.insert(0, 0)
	values.insert(0, 0)
	for i in range(capacity+1):
		k.append([0]*(n+1))
	for i in range(1, capacity+1):
		for j in range(1, n+1):
			k[i][j] = k[i][j-1]
			if weights[j]<=i:
				k[i][j] = max(k[i][j], k[i-weights[j]][j-1]+values[j])
	print("Maximum value of knapsack", k[capacity][n])


print("Enter the number of items")
n = int(input())

print("Enter the weights of", n, "items")
weights = list(map(int , input().split()))

print("Enter the values of", n, "items")
values = list(map(int, input().split()))

print("Enter the capacity of knapsack")
capacity = int(input())
unbounded_knapsack(n, weights, values, capacity)

"""
Test Case
3
10 20 30
60 100 120
100
"""