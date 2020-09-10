# Ford Fulkerson's algorithm implementation by Sayam Kumar - S20180010158
def BFS(graph, vertices, source, sink, parent):
    visited = [False]*(vertices+1)
    bfs_list = list()
    bfs_list.append(source)
    visited[source] = True
    while bfs_list:
        p = bfs_list[0]
        for node, weight in enumerate(graph[p]):
            if visited[node] == False and weight>0:
                visited[node] = True
                bfs_list.append(node)
                parent[node] = p
        bfs_list.pop(0)
    return visited[sink]
            
def FordFulkerson(graph, vertices, source, sink):
    parent = [-1]*(vertices+1)
    max_flow = 0
    while BFS(graph, vertices, source, sink, parent):

        # Capturing the min flow of the augmenting apth found
        min_flow_path = float('Inf')
        s = sink
        while(s!=source):
            min_flow_path = min(min_flow_path, graph[parent[s]][s])
            s = parent[s]
        
        max_flow += min_flow_path

        # Updating the flow in the back direction
        s = sink
        while(s!=source):
            p = parent[s]
            graph[p][s] -= min_flow_path
            graph[s][p] += min_flow_path
            s = parent[s]
    
    return max_flow

print("Enter the number of vertices and edges")
vertices = int(input())
edges = int(input())
graph = list()
for i in range(vertices+1):
    graph.append([0]*(vertices+1))

print("Enter an edge by giving two vertices and a weight")
for i in range(edges):
    [a, b, w] = list(map(int, input().split()))
    graph[a][b] = w

print("Enter the source and destination")
source = int(input())
sink = int(input())
max_flow = FordFulkerson(graph, vertices, source, sink)
print("Max Flow =", max_flow)

""" Test Case 1
5
8
0 1 10
0 2 8
1 2 2
1 3 5
2 4 10
3 5 7
4 3 8
4 5 10
0
5
"""

""" Test Case 2
5
10
0 1 16
0 2 13
1 2 10
1 3 12
2 1 4
2 4 14
3 2 9
3 5 20
4 3 7
4 5 4
0
5
"""