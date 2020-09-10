# By - Sayam Kumar
# Roll No - S20180010158
# Implementation of BFS and DFS of graphs

def dfs(graph, visited, data):
    visited[data] = True
    print(data, end = ' ')
    for i in graph[data]:
        if visited[i] == False:
            dfs(graph, visited, i)

def bfs(graph, visited, data):
    bfs_list = list()
    bfs_list.append(data)
    print(data, end = ' ')
    visited[data] = True
    while bfs_list:
        for i in graph[bfs_list[0]]: # going to all neighbours of first element of list
            if visited[i] == False:
                visited[i] = True
                bfs_list.append(i)  
        num = bfs_list.pop(0)
        print(num, end = ' ')
        

graph = dict()
print("Enter the number of vertices and edges space separated")
[vertices, edges] = list(map(int, input().split()))
for _ in range(edges):
    [a, b] = list(map(int, input().split()))
    if a in graph:
        graph[a].append(b)
    else:
        graph.update({a:[b, ]})
    if b in graph:
        graph[b].append(a)
    else:
        graph.update({b:[a, ]})

# dfs according to the first key
visited = [False] * (vertices+1)
print("DFS of the graph is: ")
dfs(graph, visited, list(graph)[0])

# bfs according to the first key
visited = [False] * (vertices+1)
print("\nBFS of the graph is: ")
bfs(graph, visited, list(graph)[0])

"""
sample test case
10 9
0 3 5
3 6 6
3 2 -1
2 5 3
5 8 10
2 9 11
9 1 12
1 4 2
1 10 20
"""