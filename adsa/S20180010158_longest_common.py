# Longest common subsequence implementation by Sayam Kumar - S20180010158
def longest_common(x, y):
	l1 = len(x)
	l2 = len(y)

	dp_table = list()
	for i in range(l1+1):
		dp_table.append([0]*(l2+1))

	for i in range(l1+1):
		for j in range(l2+1):
			if i==0 or j==0:
				dp_table[i][j] = 0
			elif x[i-1] == y[j-1]: # check if i and j elements are same
				dp_table[i][j] = dp_table[i-1][j-1]+1
			else:
				dp_table[i][j] = max(dp_table[i][j-1], dp_table[i-1][j])

	print("Length of Longest Common Subsequence of", x, "and", y, "is", dp_table[l1][l2])

print("Enter two strings")
x = str(input())
y = str(input())
longest_common(x, y)

"""
Test Case
abcd
abc
"""