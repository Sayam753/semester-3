# Floyd Warshal implementation by Sayam Kumar - S20180010158
def floyd(dist, vertices):
	for k in range(vertices+1):
		for i in range(vertices+1):
			for j in range(vertices+1):
				dist[i][j] = min(dist[i][j], (dist[i][k]+dist[k][j]))
	print("Printing the solution")
	for i in range(vertices+1):
		for j in range(vertices+1):
			print(dist[i][j], end="\t")
		print("")

print("Enter the number of vertices and edges")
vertices = int(input())
edges = int(input())

print("Enter the", edges, "edges")
dist = list()
for i in range(vertices+1):
	dist.append([float('inf')]*(vertices+1))
for i in range(vertices+1):
	dist[i][i] = 0

for _ in range(edges):
	(a, b, w) = list(map(int, input().split()))
	dist[a][b] = w

floyd(dist, vertices)

"""
Test Case:
3
4
0 3 10
0 1 5
1 2 3 
2 3 1
"""