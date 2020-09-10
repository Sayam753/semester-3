def find(tree, value):
	count = 0
	while tree[value]!= value:
		count += 1
		value = tree[value]
	return (value, count)

def union(tree, source, target):
	tree[source] = target

def kruskal(graph, tree):
	result = list()
	for i in graph.keys():
		for j in graph[i]:
			l1 = find(tree, j[0])
			l2 = find(tree, j[1])
			if l1[0]!=l2[0]:
				if l1[1]>l2[1]:
					union(tree, l2[0], l1[0])
				else:
					union(tree, l1[0], l2[0])
				result.append(j)
	return result

graph = dict()
vertices = int(input())
edges = int(input())
for _ in range(edges):
	(a, b, w) = list(map(int, input().split()))
	if w in graph:
		graph[w].append((a, b))
	else:
		graph[w] = [(a, b), ]

tree = list()
for i in range(vertices+1):
	tree.append(i)

new_graph = dict()
for i in sorted(graph):
	new_graph[i] = graph[i]

result = kruskal(new_graph, tree)
print(result)