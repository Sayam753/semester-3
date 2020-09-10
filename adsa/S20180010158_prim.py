# Prim algo implementation by Sayam Kumar - S20180010158
# Insert utility for heap
def insert(edge, heap, index_dict):
    heap.append(edge)
    index = len(heap)-1
    if edge[1] in index_dict:
        index_dict[edge[1]].append(index)
    else:
        index_dict.update({edge[1]: [index, ]})
    while index>0:
        parent = index//2
        if index%2 == 0:
            parent = parent - 1
        if heap[index][0] < heap[parent][0]: # Going up
            index_dict[heap[parent][1]].remove(parent)
            index_dict[heap[index][1]].remove(index)
            index_dict[heap[index][1]].append(parent)
            index_dict[heap[parent][1]].append(index)
            t = heap[parent]
            heap[parent] = heap[index]
            heap[index] = t
            index = parent
        else:
            break

# Delete utility for heap
def delete(index, heap, index_dict):
    length = len(heap) - 1
    heap[index] = heap[length]
    index_dict[heap[length][1]].remove(length)
    index_dict[heap[length][1]].append(index)
    heap.pop()
    while index*2+2 <= length:
        left = index*2 + 1
        right = index*2 + 2
        small = left
        if index*2 + 2 != length and heap[left][0] > heap[right][0]:
            small = right
        if heap[index][0] > heap[small][0]: # Going down
            index_dict[heap[small][1]].remove(small) # To update the indices
            index_dict[heap[index][1]].remove(index)
            index_dict[heap[index][1]].append(small)
            index_dict[heap[small][1]].append(index)
            t = heap[small]
            heap[small] = heap[index]
            heap[index] = t
            index = small
        else:
            break

def Prim(graph, v, visited):
    heap = list()
    index_dict = dict() # To store location of edges present
    for e in graph[v]:
        insert(e, heap, index_dict)
    visited[v] = 1
    print("Starting from", v)
    while True:
        (min_weight, vertex) = heap[0]
        print("Choosing vertex", vertex, "with weight", min_weight)
        index = index_dict[vertex][0]
        while(len(index_dict[vertex])>0): # Deleting the previous connection
            delete(index, heap, index_dict)
            index_dict[vertex].remove(index)
            if (len(index_dict[vertex])>0):
                index = index_dict[vertex][0]
        del index_dict[vertex]
        for e in graph[vertex]: # Adding the new connection
            if visited[e[1]] == 0:
                insert(e, heap, index_dict)
        visited[vertex] = 1
        if (len(heap)<=0):
            break

graph = dict()
print("Enter the number of nodes and edges")
vertices = int(input())
edges = int(input())

print("Enter", edges, "edges in format: edge1 edge2 weight")
# Building the graph
for _ in range(edges):
    [a, b, w] = list(map(int, input().split()))
    if a in graph:
        graph[a].append((w, b))
    else:
        graph.update({a:[(w, b), ]})
    if b in graph:
        graph[b].append((w, a))
    else:
        graph.update({b:[(w, a), ]})

visited = [0] * (vertices+1)
Prim(graph, list(graph)[0], visited)

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

Test case 3
9
14
0 1 4
0 7 8
1 2 8
1 7 11
2 3 7
2 8 2
2 5 4
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
"""