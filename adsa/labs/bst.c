#include<stdio.h>
#include<stdlib.h>
#define COUNT 10
struct node
{
    int data;
    struct node *left;
    struct node *right;
};

typedef struct node node;
#define allot_memory (node *)malloc(sizeof(node))
node *root=NULL;

node* insert(node *travel, node *new)
{
    if (travel==NULL)
    {
        return new;
    }
    else if (travel->data > new->data)
    {
        travel->left = insert(travel->left, new);
    }
    else
    {
        travel->right = insert(travel->right, new);
    }
    return travel;
}

node* delete(node *travel, int data)
{
    if (travel==NULL)
    {
        printf("Nothing found\n");
    }
    else if (travel->data > data)
    {
        travel->left =  delete(travel->left, data);
    }
    else if (travel->data < data)
    {
        travel->right =  delete(travel->right, data);
    }
    else
    {
        // travel found
        if (travel->left==NULL)
        {
            if (travel->right==NULL)
            {
                return NULL;
            }
            return travel->right;
        }
        else if (travel->right==NULL)
        {
            return travel->left;
        }
        else
        {
            node *temp = travel->right, *parent = travel->right;
            int d;
            while(temp->left!=NULL)
            {
                parent = temp;
                temp = temp->left;
            }
            d = temp->data;
            travel->data = d;
            if (temp->right == NULL)
            {
                if (parent == travel->right)
                {
                    travel->right=NULL;
                }
                else
                    parent->left = NULL;
            }
            else
            {
                parent->left = temp->right;
            } 
        }
    }
    return travel;
}

void print2DUtil(node *root, int space)  
{  
    if (root == NULL)  
        return;  

    space += COUNT;  
    
    print2DUtil(root->right, space);  
    printf("\n");
    for (int i = COUNT; i < space; i++)  
        printf(" ");
    printf("%d\n", root->data);
    print2DUtil(root->left, space);  
}  

int main()
{
    int n;
    scanf("%d", &n);
    while(n--)
    {
        node *new = allot_memory;
        scanf("%d", &new->data);
        new->left = NULL;
        new->right = NULL;
        root = insert(root, new);
        print2DUtil(root, 0);
    }
    printf("Enter data to be deleted:\n");
    int data;
    scanf("%d", &data);
    root = delete(root, data);
    print2DUtil(root, 0);
}