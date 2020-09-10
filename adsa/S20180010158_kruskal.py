# Kruskal algo implementation by Sayam Kumar - S20180010158
# Updated test cases
# Find utility
def find(value, tree):
	count = 0
	while tree[value] != value:
		count = count+1
		value = tree[value]
	return (value, count)

# Union utility "Joins by height"
def union(tree, source, target):
	tree[source] = target

# Kruskal utility function
def Kruskal(graph, tree):
	result = [] # To store the end result
	for i in graph.keys():
		for j in graph[i]:
			location_1 = find(j[0], tree)
			location_2 = find(j[1], tree)
			if (location_1[0] != location_2[0]):
				if (location_1[1] > location_2[1]):
					union(tree, location_2[0], location_1[0])
				else:
					union(tree, location_1[0], location_2[0])
				result.append((j[0], j[1]))
	return result

graph = dict()
print("Enter the number of nodes and edges")
vertices = int(input())
edges = int(input())

# Initialisation
tree = list() # tree stores heirarchial structure in list
for i in range(vertices + 1):
	tree.append(i)

print("Enter", edges, "edges in format: edge1 edge2 weight")
# Building the graph
for i in range(edges):
    [a, b, w] = list(map(int, input().split()))
    if w in graph:
        graph[w].append((a, b))
    else:
        graph.update({w:[(a, b), ]})

# Sorting the grah
new_dict = dict()
for i in sorted(graph):
	new_dict.update({i:graph[i]})

# Applying the kruskal Algorithm
result = Kruskal(new_dict, tree)
print("The resultant edges are:")
print(result)

"""
sample test case
Test case 1
5
7 
1 2 5
1 4 7
1 3 9
2 4 2
2 5 1
4 3 3
5 4 4

Test case 2
6
9
1 2 1
1 3 4
1 6 4
2 3 3
1 5 7
3 4 2
2 4 5
6 5 6
4 5 9
"""