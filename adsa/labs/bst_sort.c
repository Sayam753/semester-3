#include<stdio.h>
#include<stdlib.h>
struct node{
    int data;
    struct node *left;
    struct node *right;
};

typedef struct node node;
#define allot_memory (node*)malloc(sizeof(node))
node *root = NULL;
void insert(node **travel, node *n)
{
    if (*travel==NULL)
    {
        *travel = n;
        return;
    }
    if ((*travel)->data > n->data)
    {
        insert(&((*travel)->left), n);
    }
    else
    {
        insert(&((*travel)->right), n);
    }
}

void inorder_travel(node *travel)
{
    if(travel==NULL)
    {
        return;
    }
    inorder_travel(travel->left);
    printf("%d ", travel->data);
    inorder_travel(travel->right);
}

int main()
{
    int n;
    scanf("%d", &n);
    for(int i=0;i<n;i++)
    {
        node* new_node = allot_memory;
        scanf("%d", &new_node->data);
        new_node->left = NULL;
        new_node->right = NULL;
        insert(&root, new_node);
    }
    inorder_travel(root);
    printf("\n");
}