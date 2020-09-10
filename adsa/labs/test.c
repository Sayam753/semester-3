#include<stdio.h>
#include<stdlib.h>

struct node
{
    int data;
    int color;
    int up;
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
node * RotateRight(node *down)
{
	node *up;
	up=down->left;
	down->left=up->right;
	up->right=down;
	return(up);
}

node * RotateLeft(node *down)
{
    node* up;
    up = down->right;
    up->parent = down->parent;
    down->right = up->left;
    up->left = down;
    down->parent = up;
    up->color = black;
    down->color = red;
    if (down->right != NULL)
    {
        down->right->parent = down->right;
    }
    return up;
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
        travel->left = insert(travel->left, new, travel);
        // if (travel->color == 1) // case 1
        // {
        //     printf("Case 1 active\n");
        //     printf("travel->data = %d\n", travel->data);
        // }
        // else if (travel->color == 0 && travel->parent && travel->data < travel->parent->data)
        // {
        //     if (travel->left!=NULL && travel->left->color==0)
        //     {
        //         if (travel->parent->right ==  NULL)
        //         {
        //             printf("Case 2 active\n");
        //             travel->parent = RotateRight(travel->parent); // case 2
        //             travel->parent->color = black;
        //             travel->parent->right->color = red;
                
        //         }
        //         else if (travel->parent->right->color == 0)
        //         {
        //             printf("Case 3 active\n");
        //             travel->color = 1; // case 3
        //             travel->parent->color = 0;
        //             travel->parent->right->color=1;
                    
        //         }
        //         else if (travel->parent->right->color == 1)
        //         {
        //             printf("Case 4 active\n");
        //             travel->parent = RotateRight(travel->parent); // case 4
        //             travel->parent->color = black;
        //             travel->parent->right->color = red;
        //         }
        //     }
            
        // }
        // else if (travel->color == 0 && travel->parent && travel->data > travel->parent->data)
        // {
        //     if (travel->left!=NULL && travel->left->color==0)
        //     {
        //         if (travel->parent->left == NULL)
        //         {
        //             printf("Case 5 active\n");
        //             travel->parent = RL(travel->parent); // case 5
        //             travel->parent->color = black;
        //             travel->parent->left->color = red;
                    
        //         }
        //         else if (travel->parent->left->color == 0)
        //         {
        //             printf("Case 6 active\n");
        //             travel->color = 1; // case 6
        //             travel->parent->color = 0;
        //             travel->parent->left->color = 1;
                    
        //         }
        //         else if (travel->parent->left->color == 1)
        //         {
        //             printf("Case 7 active\n");
        //             travel->parent = RL(travel->parent); // case 7
        //             travel->parent->color = black;
        //             travel->parent->left->color = red;
                    
        //         }
        //     }
        // }
    }
    else if (travel->data < new->data)
    {
        printf("right = %d\n", travel->data);
        travel->right = insert(travel->right, new, travel);
        if (travel->up==0)
        {
            travel->right=NULL;
        }
        printf("%d %d\n", travel->color, travel->data);
        if (travel->color == 1)
        {
            printf("Case 14 active\n");
        }
        else if(travel->color == 0 && travel->parent && travel->data < travel->parent->data)
        {
            if (travel->right!= NULL && travel->right->color == 0)
            {
                
                if (travel->parent->right==NULL)
                {
                    printf("Case 8 active\n");
                    travel->parent = LR(travel->parent); // case 8
                    travel->parent->color = black;
                    travel->parent->right->color = red;
                    
                }
                else if (travel->parent->right->color == 0)
                {
                    printf("Case 9 active\n");
                    travel->color = 1; // case 9
                    travel->parent->color = 0;
                    travel->parent->right->color = 1;
                }
                else if (travel->parent->right->color == 1)
                {
                    printf("Case 10 active\n");
                    travel->parent = LR(travel->parent); // case 10
                    travel->parent->color = black;
                    travel->parent->right->color = red;
                    
                }
            }
            
        }
        else if (travel->color == 0 && travel->parent && travel->data > travel->parent->data)
        {
            if (travel->right!= NULL && travel->right->color == 0)
            {
                if (travel->parent->left == NULL)
                {
                    printf("Case 11 active\n");
                    travel->parent = RotateLeft(travel->parent); // case 11
                    travel->left->up = 0;
                }
                else if (travel->parent->left->color == 0)
                {
                    printf("Case 12 active\n");
                    travel->color = 1; // case 12
                    travel->parent->color = 0;
                    travel->parent->left->color = 1;
                }
                else if (travel->parent->left->color == 1)
                {
                    printf("Case 13 active\n");
                    travel->parent = RotateLeft(travel->parent); // case 13
                }
            } 
        }
    }
    if (travel->up==0)
    {
        printf("Returning parent as %d\n", travel->parent->data);
        return travel->parent;
    }
    printf("t = %d\n", travel->data);
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
    root->up=1;
    print2DUtil(root, 0); 
    for(int i=1;i<n;i++)
    {
        node *new = allot_memory;
        scanf("%d", &(new->data));
        new->left = NULL;
        new->right = NULL;
        new->parent = NULL;
        new->color = 0;
        new->up=1;
        root = insert(root, new, root);
        root->parent = NULL;
        root->color = 1;
        print2DUtil(root, 0); 
        printf("\n");
        inorder_travel(root);
        printf("\n");
    }
    printf("\n");
}