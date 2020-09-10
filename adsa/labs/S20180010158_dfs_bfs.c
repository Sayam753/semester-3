/*
By - Sayam Kumar
Roll No - S20180010158
Implementation of BFS and DFS of graphs
*/

#include<stdio.h>
#include<stdlib.h>
struct downlist
{
    int data;
    int flag;
    struct downlist *down;
    struct neighbour *neighbours;
};
struct neighbour
{
    int data;
    struct neighbour *next;
    struct downlist *location;
};
struct queue
{
    struct downlist* node;
    struct queue* next;
};
typedef struct downlist downlist;
typedef struct neighbour neighbour;
typedef struct queue queue;

#define downlist_allot_memory (downlist *)malloc(sizeof(downlist))
#define neighbour_allot_memory (neighbour *)malloc(sizeof(neighbour))
#define queue_allot_memory (queue *)malloc(sizeof(queue))

neighbour* create_new(int data)
{
    neighbour* new_node = neighbour_allot_memory;
    new_node->data = data;
    new_node->next = NULL;
    new_node->location = NULL;
    return new_node;
}

downlist* new_down(int data)
{
    downlist* new_node = downlist_allot_memory;
    new_node->data = data;
    new_node->flag = 0;
    new_node->down = NULL;
    new_node->neighbours = NULL;
    return new_node;
}

downlist* insert(downlist *head)
{
    int a, b;
    printf("Enter two values to insert:  ");
    scanf("%d %d", &a, &b);
    downlist* travel_a = head;
    downlist* travel_b = head;
    if (head==NULL)
    {
        downlist *new_node_a = new_down(a);
        downlist *new_node_b = new_down(b);
        neighbour *new_nei_a = create_new(a);
        neighbour *new_nei_b = create_new(b);
        new_node_a->down = new_node_b;
        new_nei_a->location = new_node_a;
        new_nei_b->location = new_node_b;
        new_node_a->neighbours = new_nei_b;
        new_node_b->neighbours = new_nei_a;
        head = new_node_a;
        return head;
    }
    int flag_a = 0;
    while(travel_a->down!=NULL)
    {
        if (travel_a->data == a)
        {
            flag_a = 1;
            break;
        }
        travel_a = travel_a->down;
    }
    int flag_b = 0;
    while(travel_b->down!=NULL)
    {
        if (travel_b->data == b)
        {
            flag_b = 1;
            break;
        }
        travel_b = travel_b->down;
    }
    if (travel_a->data == a)
        flag_a = 1;
    if (travel_b->data == b)
        flag_b = 1;
    if (flag_a == 1)
    {
        if (flag_b == 0)
        {
            // found a but not b
            downlist* new_node_b = new_down(b);
            travel_b->down = new_node_b;
            travel_b = travel_b->down;
        }
    }
    else
    {
        if (flag_b == 1)
        {
            // found b but not a
            downlist* new_node_a = new_down(a);
            travel_a->down = new_node_a;
            travel_a = travel_a->down;
        }
        else
        {
            // both a and b not found
            downlist* new_node_a = new_down(a);
            travel_a->down = new_node_a;
            travel_a = travel_a->down;
            downlist* new_node_b = new_down(b);
            travel_b = travel_a;
            travel_b->down = new_node_b;
            travel_b = travel_b->down;
        }
    }
    // adjusting neighbours
    neighbour* new_a = create_new(a);
    neighbour* new_b = create_new(b);
    new_a->location = travel_a;
    new_b->location = travel_b;
    neighbour* pre_a = travel_a->neighbours;
    travel_a->neighbours = new_b;
    new_b->next = pre_a;
    neighbour* pre_b = travel_b->neighbours;
    travel_b->neighbours = new_a;
    new_a->next = pre_b;
    return head;
}

void print(downlist* head)
{
    downlist* travel = head;
    while(travel!=NULL)
    {
        printf("%d -> ", travel->data);
        neighbour* travel_nei = travel->neighbours;
        while(travel_nei!=NULL)
        {
            printf("%d ", travel_nei->data);
            travel_nei = travel_nei->next;
        }
        printf("\n");
        travel = travel->down;
    }
}

void dfs(downlist* travel)
{
    if (travel==NULL)
        return;
    travel->flag = 1;
    printf("%d ", travel->data);
    neighbour* travel_nei = travel->neighbours;
    while(travel_nei != NULL)
    {
        if (travel_nei->location->flag == 0)
        {
            //dfs on all unvisited neighbours
            dfs(travel_nei->location);
        }
        travel_nei = travel_nei->next;
    }
}

downlist* reset_flag(downlist* head)
{
    downlist* travel = head;
    while(travel!=NULL)
    {
        travel->flag = 0;
        travel = travel->down;
    }
    return head;
}

void bfs(downlist* head)
{
    head->flag = 1;
    queue *travel = queue_allot_memory;
    travel->node = head;
    travel->next = NULL;
    while(travel != NULL)
    {
        neighbour* new_nei = travel->node->neighbours;
        while(new_nei != NULL)
        {
            if (new_nei->location->flag == 0)
            {
                // appending all unvisited neighbours
                new_nei->location->flag = 1;
                queue* new_node = queue_allot_memory;
                new_node->node = new_nei->location;
                new_node->next = NULL;
                queue* go_last = travel;
                while(go_last->next != NULL)
                {
                    go_last = go_last->next;
                }
                go_last->next = new_node;
            }
            new_nei = new_nei->next;
        }
        // removing the first element
        queue* temp = travel;
        travel = travel->next;
        printf("%d ", temp->node->data);
        free(temp);
    }
}

int main()
{
    downlist *head = NULL;
    int choice;
    printf("1. Insert new edge\n2. Print adjacency list\n3. DFS\n4. BFS\n");
    while(1)
    {
        printf("Enter your choice: ");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                head = insert(head);
                break;
            case 2:
                printf("Printing adjacency list:\n");
                print(head);
                break;
            case 3:
                printf("DFS of graph is:\n");
                dfs(head);
                head = reset_flag(head);
                printf("\n");
                break;
            case 4:
                printf("BFS of graph is:\n");
                bfs(head);
                head = reset_flag(head);
                printf("\n");
                break;
        }
        fflush(stdin);
    }
}