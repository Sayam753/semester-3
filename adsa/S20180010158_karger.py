# Karger's algorithm implementation by Sayam Kumar - S20180010158
import random

# Merge Utility
def merge(graph, u, v): # Adding all connections of v to u
    for vertex in graph[v]:
        graph[vertex].remove(v)
        if vertex!=u: # Removing self edges
            graph[u].append(vertex)
            graph[vertex].append(u)
    del graph[v]

# Karger utility
def karger(graph):

    while(len(list(graph))>2):
        # Getting one vertex randomly
        u = random.choice(list(graph))

        # Getting one edge from u randomly
        v = random.choice(graph[u])
        merge(graph, u, v)

    mincut_length = len(graph[list(graph)[0]])
    return mincut_length

graph = dict()
print("Enter the number of edges")
edges = int(input())
print("Enter an edge by giving two vertices")
for _ in range(edges):
    [a, b] = list(map(int, input().split()))
    if a in graph:
        graph[a].append(b)
    else:
        graph[a] = [b, ]
    if b in graph:
        graph[b].append(a)
    else:
        graph[b] = [a, ]

print("Running karger algorithm 10000 times")
result = dict()
for i in range(10000):
    # Creating temp graph
    temp = dict()
    for j in graph:
        temp[j] = graph[j].copy()
    length = karger(temp)
    if length in result:
        result[length] = result[length]+1
    else:
        result[length] = 1

print("Mincut Length:\tNumber of times")
for i in result:
    print("\t", i, "\t", result[i])


"""
Test Case
14
1 2
1 3
1 4
2 3
2 4
3 4
2 5
4 5
5 6
6 7
7 8
8 5
5 7
6 8
"""