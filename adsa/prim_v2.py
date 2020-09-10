def delete(priority_queue, index_list):


def update(priority_queue, node, index_list):


def prim(graph, vertices, source):
	priority_queue = list()
	index_list = [0]*(vertices+1)
	priority_queue.append([source, 0])
	print("Starting from node", source)
	while priority_queue:
		[min_vertex, weight] = priority_queue[0]
		print("Choosing vertex", min_vertex, "with weight", weight)
		delete(priority_queue, index_list)
		for i in graph[min_vertex]:
			update(priority_queue, i, index_list)

graph = dict()
# print("Enter the number of vertices")
vertices = int(input())
# print("Print the number of edges")
edges = int(input())
for i in range(edges):
	[a, b, w] = list(map(int, input().split()))
	if a not in graph:
		graph[a] = list()
	if b not in graph:
		graph[b] = list()
	graph[a].append([b, w])
	graph[b].append([a, w])

prim(graph, vertices, list(graph)[0])