def dfs(graph, visited, v):
	visited[v] = True
	print(v, end=" ")
	for i in graph[v]:
		if visited[i] == False:
			dfs(graph, visited, i)

def bfs(graph, visited, v):
	visited[v] = True
	count = [0]*(len(visited))
	count[v] = 0
	bfs_list = list()
	bfs_list.append(v)
	while(bfs_list):
		for i in graph[bfs_list[0]]:
			if visited[i] == False:
				count[i] = count[bfs_list[0]] + 1
				visited[i] = True
				bfs_list.append(i)
		num = bfs_list.pop(0)
		print(num, end=" ")
	print(count)

graph = dict()
vertices = int(input())
edges = int(input())
for _ in range(edges):
	(a, b) = list(map(int, input().split()))
	if a not in graph:
		graph[a] = list()
	graph[a].append(b)
	if b not in graph:
		graph[b] = list()
	graph[b].append(a)

visited = [False]*(vertices+1)
dfs(graph, visited, list(graph)[0])
print("")

visited = [False]*(vertices+1)
bfs(graph, visited, list(graph)[0])