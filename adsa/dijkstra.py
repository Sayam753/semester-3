# Dijkstra algo implementation by Sayam Kumar - S20180010158
# Insert utility for heap
def insert(edge, heap, index_list):
    heap.append(edge)
    index = len(heap)-1
    index_list[edge[1]] = len(heap)-1
    while index>0:
        parent = index//2
        if index%2 == 0:
            parent = parent - 1
        if heap[index][0] < heap[parent][0]:
            index_list[heap[parent][1]] = index
            index_list[heap[index][1]] = parent
            t = heap[parent]
            heap[parent] = heap[index]
            heap[index] = t
            index = parent
        else:
             break

# Delete utility for heap
def delete(index, heap, index_list):
    length = len(heap) - 1
    heap[index] = heap[length]
    index_list[heap[length][1]] = index
    heap.pop()
    while index*2+2 <= length:
        left = index*2 + 1
        right = index*2 + 2
        small = left
        if index*2 + 2 != length and heap[left][0] > heap[right][0]:
            small = right
        if heap[index][0] > heap[small][0]:
            index_list[heap[small][1]] = index
            index_list[heap[index][1]] = small
            t = heap[small]
            heap[small] = heap[index]
            heap[index] = t
            index = small
        else:
            break

def update(index, heap, index_list):
    flag = 0
    while index>0:
        parent = index//2
        if index%2 == 0:
            parent = parent - 1
        if heap[index][0] < heap[parent][0]:
            flag = 1
            index_list[heap[parent][1]] = index
            index_list[heap[index][1]] = parent
            t = heap[parent]
            heap[parent] = heap[index]
            heap[index] = t
            index = parent
        else:
            break
    if flag == 0:
        length = len(heap)-1
        while index*2+2 <= length:
            left = index*2 + 1
            right = index*2 + 2
            small = left
            if index*2 + 2 != length and heap[left][0] > heap[right][0]:
                small = right
            if heap[index][0] > heap[small][0]:
                index_list[heap[small][1]] = index
                index_list[heap[index][1]] = small
                t = heap[small]
                heap[small] = heap[index]
                heap[index] = t
                index = small
            else:
                break


def Dijkstra(graph, v, distance):
    heap = list()
    index_list = [-1] * (vertices)
    for e in graph[v]:
        insert(e, heap, index_list)
        distance[e[1]] = e[0]
    distance[v] = 0
    index_list[v] = -2
    print(distance, heap, index_list)
    while len(heap)!=0:
        (min_weight, vertex) = heap[0]
        print("Min weight has vertex:", vertex)
        for e in graph[vertex]:
            if index_list[e[1]] == -1:
                print("New entry of", e[1])
                e[0] = e[0] + distance[vertex]
                distance[e[1]] = e[0]
                insert(e, heap, index_list)
                print(distance, heap, index_list)
            else:
                print("Already present", e[1])
                if index_list[e[1]] != -2 and distance[e[1]] > min_weight + e[0]:
                    print("Condition met true")
                    index = index_list[e[1]]
                    heap[index] = [min_weight + e[0], e[1]]
                    distance[e[1]] = min_weight + e[0]
                    update(index, heap, index_list)
                    print(distance, heap, index_list)
        index_list[vertex] = -2
        delete(0, heap, index_list)
        print(distance, heap, index_list)

graph = dict()
#print("Enter the number of nodes and edges")
vertices = int(input())
edges = int(input())

#print("Enter", edges, "edges in format: edge1 edge2 weight")
# Building the graph
for _ in range(edges):
    [a, b, w] = list(map(int, input().split()))
    if a in graph:
        graph[a].append([w, b])
    else:
        graph.update({a:[[w, b], ]})
    if b in graph:
        graph[b].append([w, a])
    else:
        graph.update({b:[[w, a], ]})

distance = [float("Inf")] * (vertices)
Dijkstra(graph, list(graph)[0], distance)
print("Starting from", list(graph)[0], "the shortest distances are: ")
for i in distance:
    print(i)

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
7
0 1 -1
0 2 4
1 2 3
1 3 2
1 4 2
3 2 5
4 3 -3
"""