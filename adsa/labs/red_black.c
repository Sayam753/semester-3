#include<stdio.h>
#include<stdlib.h>

struct node
{
    int data;
    int color;
    int check;
    struct node *parent;
    struct node *left;
    struct node *right;
};

typedef struct node node;
node *root = NULL;
#define allot_memory (node *)malloc(sizeof(node))
#define COUNT 10
#define red 0
#define black 1
node * RotateRight(node *y)
{
    node *x = y->left;  
    node *T2 = x->right;  
  
    // Perform rotation  
    x->right = y;  
    y->left = T2;

    x->parent = y->parent;
    y->parent = x;
    if (y->left != NULL)
    {
        y->left->parent = y;
    }
    return x;
}

node * RotateLeft(node *x)
{
    node *y = x->right;  
    node *T2 = y->left;
  
    // Perform rotation  
    y->left = x;  
    x->right = T2; 

    y->parent = x->parent;
    x->parent = y;
    if (x->left != NULL)
    {
        x->left->parent = x;
    }
    return y;
}

node * LR(node *travel)
{
	travel->left=RotateLeft(travel->left);
	travel=RotateRight(travel);
	return(travel);
}

node * RL(node *travel)
{
	travel->right=RotateRight(travel->right);
	travel=RotateLeft(travel);
	return(travel);
}

node* delete(node *travel, int data)
{
    if (travel->data > data)
    {
        travel->left = delete(travel->left, data);
    }
    else if (travel->data < data)
    {
        travel->right = delete(travel->right, data);
    }
    else
    {
        if (travel->left == NULL)
        {
            if (travel->right == NULL)
            {
                if (travel->color == red)
                {
                    free(travel);
                    return NULL;
                }
                else // black node found to be deleted 3.2
                {
                    
                }
            }
            travel->right->color = black;
            return travel->right;
        }
        if (travel->right==NULL)
        {
            if (travel->color == red)
            {
                free(travel);
                return NULL;
            }
            else // 3.2
            {
                
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
    printf("%d %d\n", root->data, root->color);
    print2DUtil(root->left, space);  
}  

node* insert(node *travel, node *new, node *parent)
{
    if (travel==NULL)
    {
        new->parent = parent;
        travel = new;
        return new;
    }
    else if(travel->data > new->data)
    {
        travel->check = 1;
        travel->left = insert(travel->left, new, travel);
        if (travel->color == 1) // case 1
        {
            printf("Case 1 active\n");
        }
        else if (travel->color == 0 && travel->parent && travel->data < travel->parent->data)
        {
            if (travel->left && travel->left->color==0)
            {
                if (travel->parent->right ==  NULL || travel->parent->right->color == 1)
                {
                    node *temp = travel->right;
                    travel->parent = RotateRight(travel->parent); // case 2 or 4
                    travel->parent->color = black;
                    travel->parent->right->color = red;
                    travel->parent->right->check = 0;
                    return temp;
                }
                else if (travel->parent->right->color == 0)
                {
                    travel->color = 1; // case 3
                    travel->parent->color = 0;
                    travel->parent->right->color = 1; 
                }
            }
        }
        else if (travel->color == 0 && travel->parent && travel->data > travel->parent->data)
        {
            if (travel->left!=NULL && travel->left->color==0)
            {
                if (travel->parent->left == NULL || travel->parent->left->color == 1)
                {
                    node *temp = travel->left->left;
                    travel->parent = RL(travel->parent); // case 5 or 7
                    travel->parent->color = black;
                    travel->parent->left->color = red;
                    travel->parent->left->check = 0;
                    return temp;
                }
                else if (travel->parent->left->color == 0)
                {
                    travel->color = 1; // case 6
                    travel->parent->color = 0;
                    travel->parent->left->color = 1;
                }
            }
        }
    }
    else if (travel->data < new->data)
    {
        travel->check = 1;
        travel->right = insert(travel->right, new, travel);
        if (travel->color == 1)
        {
            printf("Case 14 active\n");
        }
        else if(travel->color == 0 && travel->parent && travel->data < travel->parent->data)
        {
            if (travel->right!= NULL && travel->right->color == 0)
            {
                if (travel->parent->right==NULL  || travel->parent->right->color == 1)
                {
                    node *temp = travel->right->right;
                    travel->parent = LR(travel->parent); // case 8 or 10
                    travel->parent->color = black;
                    travel->parent->right->color = red;
                    travel->parent->right->check = 0;
                    return temp;                    
                }
                else if (travel->parent->right->color == 0)
                {
                    travel->color = 1; // case 9
                    travel->parent->color = 0;
                    travel->parent->right->color = 1;
                }
            }
        }
        else if (travel->color == 0 && travel->parent && travel->data > travel->parent->data)
        {
            if (travel->right && travel->right->color == 0)
            {
                if (travel->parent->left == NULL || travel->parent->left->color == 1)
                {
                    node *temp = travel->left;
                    travel->parent = RotateLeft(travel->parent); // case 11 or 13
                    travel->parent->color = black;
                    travel->parent->left->color = red;
                    travel->parent->left->check = 0;
                    return temp;
                }
                else if (travel->parent->left->color == 0)
                {
                    travel->color = 1; // case 12
                    travel->parent->color = 0;
                    travel->parent->left->color = 1;
                }
            } 
        }
    }
    if (travel->check==0)
        return travel->parent;
    return travel;
}

void inorder_travel(node *root)
{
    if(root==NULL)
    {
        return;
    }
    printf("%d ", root->data);
    inorder_travel(root->left);
    inorder_travel(root->right);
}

int main()
{
    int n;
    scanf("%d", &n);
    root = allot_memory;
    scanf("%d", &(root->data));
    root->left = NULL;
    root->right = NULL;
    root->parent = NULL;
    root->color = 1;
    root->check=1;
    print2DUtil(root, 0); 
    for(int i=1;i<n;i++)
    {
        node *new = allot_memory;
        scanf("%d", &(new->data));
        new->left = NULL;
        new->right = NULL;
        new->parent = NULL;
        new->color = 0;
        new->check=1;
        root = insert(root, new, root);
        root->parent = NULL;
        root->color = 1;
        print2DUtil(root, 0); 
    }
    printf("\n");
}