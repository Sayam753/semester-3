# Bellman Ford implementation by Sayam Kumar - S20180010158
def BellmanFord(graph, source, distance, v):
    distance[source] = 0
    # Relaxing v-1 times
    for i in range(v - 1): 
        for a, b, w in graph: 
            if distance[a] != float("Inf") and (distance[a] + w < distance[b]): 
                distance[b] = distance[a] + w 
  
    # Check if Graph contains negative cycle
    for a, b, w in graph: 
        if distance[a] != float("Inf") and (distance[a] + w < distance[b]):
            print("Graph contains negative weight cycle")
            return

    print("Distance from source", source)
    for i in range(len(distance)):
        print(i, distance[i])

graph = list()
print("Enter the number of nodes and edges")
vertices = int(input())
edges = int(input())

print("Enter", edges, "edges in format: edge1 edge2 weight")
# Building the graph
for _ in range(edges):
    [a, b, w] = list(map(int, input().split()))
    graph.append([a, b, w])

distance = [float("Inf")] * (vertices)
BellmanFord(graph, graph[0][0], distance, vertices)

"""
sample test case
Test case 1
5
7
1 2 5
1 4 7
1 3 9
2 4 2
2 0 1
4 3 3
0 4 4

Test case 2
5
8
0 1 -1
0 2 4
1 2 3
1 3 2
1 4 2
3 2 5
3 1 1
4 3 -3
"""