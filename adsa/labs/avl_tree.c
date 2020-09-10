#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<string.h>

struct avl_node
{
    int data;
    int ht;
    struct avl_node* left;
    struct avl_node* right;
};

typedef struct avl_node avl_node;
int height(avl_node *travel);

// balance factor of a node
int BF(avl_node *travel)
{
	int lh,rh;
	if(travel==NULL)
		return(0);

	if(travel->left==NULL)
		lh=0;
	else
		lh=1+travel->left->ht;

	if(travel->right==NULL)
		rh=0;
	else
		rh=1+travel->right->ht;

	return(lh-rh);
}


avl_node* rotateright(avl_node *down)
{
	avl_node *up;
	up = down->left;
	down->left = up->right;
	up->right = down;
	down->ht = height(down);
	up->ht = height(up);
	return(up);
}

avl_node* rotateleft(avl_node *down)
{
	avl_node *up;
	up = down->right;
	down->right = up->left;
	up->left = down;
	down->ht = height(down);
	up->ht = height(up);
	return(up);
}


avl_node* LR(avl_node *travel)
{
	travel->left=rotateleft(travel->left);
	travel=rotateright(travel);
	return(travel);
}

avl_node* RL(avl_node *travel)
{
	travel->right=rotateright(travel->right);
	travel=rotateleft(travel);
	return(travel);
}

int height(avl_node *travel)
{
	int lh,rh;
	if(travel==NULL)
		return(0);

	if(travel->left==NULL)
		lh=0;
	else
		lh=1+travel->left->ht;

	if(travel->right==NULL)
		rh=0;
	else
		rh=1+travel->right->ht;

	if(lh>rh)
		return(lh);

	return(rh);
}

avl_node* Insert_in_avl(avl_node *travel,int value)
{
	if(travel==NULL)
	{
		travel=(avl_node*)malloc(sizeof(avl_node));
		travel->data=value;
		travel->left=NULL;
		travel->right=NULL;
	}
	else
    {
        if(value > travel->data)
		{
			travel->right=Insert_in_avl(travel->right,value);
            printf("BF is %d for %d.\n",BF(travel),travel->data);
			if(BF(travel)==-2)
			{
                if(value>travel->right->data)
                {
                    printf("Pursing left on %d.\n",travel->data);
                    travel=rotateleft(travel);
                }
                else
                {
                    printf("Pursing RL on %d.\n",travel->data);
                    travel=RL(travel);
                }
            }
		}
		else
        {
            if(value<travel->data)
			{
				travel->left=Insert_in_avl(travel->left,value);
                printf("BF is %d for %d.\n",BF(travel),travel->data);
				if(BF(travel)==2)
				{
                    if(value < travel->left->data)
                    {
                        printf("Pursing right on %d.\n",travel->data);
                        travel=rotateright(travel);
                    }
				    else
                    {
                        printf("Pursing LR on %d.\n",travel->data);
                        travel=LR(travel);
                    }
                }
			}
        }
    }	
	travel->ht=height(travel);
    printf("Height of %d is %d.\n",travel->data,travel->ht);
	return(travel);
}

void inorder_print(avl_node *avl_root)
{
    if (avl_root==NULL)
    {
        return;
    }
    inorder_print(avl_root->left);
    printf("%d ",avl_root->data);
    inorder_print(avl_root->right);
}

void preorder_print(avl_node *avl_root)
{
    if (avl_root==NULL)
    {
        return;
    }
    printf("%d ",avl_root->data);
    preorder_print(avl_root->left);
    preorder_print(avl_root->right);
}

int Search_in_avl(avl_node* avl_node, int target) {
    if (avl_node == NULL)
    {
        return(0);
    }
    else
    {
        if (target == avl_node->data) return(1);
        else
        {
            if (target < avl_node->data)
            {
                return(Search_in_avl(avl_node->left, target));
            }
            else
            {
                return(Search_in_avl(avl_node->right, target));
            }
        }
    }
}

avl_node* Delete_in_avl(avl_node *travel,int value)
{
	avl_node *temp;

	if(travel==NULL)
	{
		return NULL;
	}
	else
    {
        if(value > travel->data)
		{
			travel->right=Delete_in_avl(travel->right,value);
			if(BF(travel)==2)
			{
                if(BF(travel->left)>=0)
                    travel=rotateright(travel);
                else
                    travel=LR(travel);
            }
		}
		else
        {
            if(value<travel->data)
			{
				travel->left=Delete_in_avl(travel->left,value);
				if(BF(travel)==-2)
				{
                    if(BF(travel->right)<=0)
                        travel=rotateleft(travel);
                    else
                        travel=RL(travel);
                }
			}
			else
			{
				if(travel->right!=NULL)
				{
					temp=travel->right;

					while(temp->left!= NULL)
						temp=temp->left;

					travel->data=temp->data;
					travel->right=Delete_in_avl(travel->right,temp->data);

					if(BF(travel)==2)
					{
                        if(BF(travel->left)>=0)
                            travel=rotateright(travel);
                        else
                            travel=LR(travel);
                    }
				}
				else
					return(travel->left);
			}
        }
    }
	travel->ht=height(travel);
	return(travel);
}



int main()
{
    avl_node* avl_root = NULL;
    while(1)
    {
        int choice,data;
        printf("Enter\n1. Insert_in_avl\n2. Delete_in_avl\n3. Search_in_avl\n4. Print avl\n");
        scanf("%d",&choice);
        switch(choice)
        {
            case 1:
                printf("Enter data to Insert_in_avl: ");
                scanf("%d",&data);
                avl_root = Insert_in_avl(avl_root,data);
                break;

            case 2:
                printf("Enter data to Delete_in_avl: ");
                scanf("%d",&data);
                if (Search_in_avl(avl_root,data))
                    avl_root = Delete_in_avl(avl_root,data);
                else
                    printf("Data not present!!\n");
                break;

            case 3:
                printf("Enter data to Search_in_avl: ");
                scanf("%d",&data);
                if (Search_in_avl(avl_root,data))
                    printf("Data present!!\n");
                else
                    printf("Data not present!!\n");
                break;

            case 4:
                printf("Inorder traversal is:\n");
                inorder_print(avl_root);
                printf("\nPre-order traversal is:\n");
                preorder_print(avl_root);
                printf("\n");
                break;
        }
    }
}
