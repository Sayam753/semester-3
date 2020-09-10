# Unbounded knapsack implementation by Sayam Kumar - S20180010158
def unbounded_knapsack(n, weights, values, capacity):
	k = [0]*(capacity+1)
	for i in range(capacity+1):
		for j in range(n):
			if weights[j]<=i:
				pre = k[i]
				k[i] = max(k[i], k[i-weights[j]]+values[j])
	print("Maximum value of knapsack", k[capacity])


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
5 10 15
10 30 20
100
"""