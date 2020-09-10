def insert(edge, heap, index_list):
	index = len(heap)
	heap.append(edge)
	if edge[1] in index_list:
		index_list[edge[1]].append(index)
	else:
		index_list[edge[1]] = [index, ]
	while True:
		parent = index//2
		if (index%2==0):
			parent -= 1
		if (heap[index][0] < heap[parent][0]):
			index_list[heap[index][1]].remove(index)
			index_list[heap[parent][1]].remove(parent)
			index_list[heap[index][1]].append(parent)
			index_list[heap[parent][1]].append(index)
			t = heap[index]
			heap[index] = heap[parent]
			heap[parent] = t
		else:
			break


def delete(index, heap, index_list):



def prim(graph, v):
	heap = list()
	index_list = dict()
	visited = [False]*(vertices+1)
	for edge in graph[v]:
		insert(edge, heap, index_list)
	while True:
		(min_weight, vertex) = heap[0]
		while True: # Deleting previous edges
			if len(index_list[vertex]) == 0:
				break
			index = index_list[vertex][0]
			delete(index, heap, index_list)

		for edge in graph[vertex]:
			if visited[edge[1]] == False:
				insert(edge, heap, index_list)
		visited[vertex] = True
		print(vertex, end=" ")
		if (len(heap) == 0):
			break

graph = dict()
vertices = int(input())
edges = int(input())
for _ in range(edges):
	(a, b, w) = list(map(int, input().split()))
	if a in graph:
		graph[a].append((w, b))
	else:
		graph[a] = [(w, b), ]
	if b in graph:
		graph[b].append((w, a))
	else:
		graph[b] = [(w, a), ]

prim(graph, list(graph)[0])
