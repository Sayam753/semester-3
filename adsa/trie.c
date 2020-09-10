#include<stdio.h>
#include<string.h>
#include<stdlib.h>
/*
    Assignment - 3
    Submitted By - Sayam Kumar
    Roll No - S20180010158
    Submitted To - Dr. Shiv Ram Dubey
*/
struct employ
{
    char name[20];
    struct employ* child;
    struct employ* boss;
    struct employ* next;
};
struct trie_node
{
    int level;
    struct trie_node *next[26];
    struct employ *location;
};
struct queue
{
    struct employ* emp;
    struct queue* next;
};

typedef struct employ employ;
typedef struct trie_node trie_node;
typedef struct queue queue;
employ* employ_head = NULL;
trie_node* trie_head = NULL;

trie_node *create_empty_trie_node()
{
    trie_node *new_trie_node = (trie_node *)malloc(sizeof(trie_node));
    for (int i=0;i<26;i++)
        new_trie_node->next[i] = NULL;
    new_trie_node->level = -1;
    new_trie_node->location = NULL;
    return new_trie_node;
}

trie_node* search(char s[],int length)
{
    int flag = 1;
    trie_node *temp = trie_head;
    for(int i=0;i<length;i++)
    {
        if (temp->next[s[i]-'a'] == NULL)
        {
            flag = 0;
            break;
        }
        temp = temp->next[s[i]-'a'];
    }
    if (flag == 1 && temp->level != -1)
        return temp;
    return NULL;
}

void insert(char e[],int l1, char b[], int l2, int is_boss_present)
{
    trie_node* trie_end = search(b, l2); // search for boss in trie
    int level_of_boss = 0; // capture the level of boss
    if (is_boss_present == 0)
    {
        if (trie_end == NULL)
        {
            printf("Boss %s is not present. Insertion failed!\n", b);
            return;
        }
        level_of_boss = trie_end->level;
    }
    trie_node *travel = trie_head;
    for (int i=0;i<l1;i++)
    {
        if (travel->next[e[i]-'a'] == NULL)
        {
            // printf("Creating new_level for '%c'.\n",e[i]); // creating a new level
            trie_node *new_trie_node = create_empty_trie_node();
            travel->next[e[i]-'a'] = new_trie_node;
            travel = new_trie_node;
        }
        else
        {
            // printf("Character skipped is '%c'.\n",e[i]); // if level is already present
            travel = travel->next[e[i]-'a'];
        }
    }
    if(travel->level != -1)
    {
        // printf("Employ with such name already present!!\n");
        return;
    }
    // printf("Setting level for '%c'.\n",e[l1-1]);
    travel->level = level_of_boss + 1; // adding 1 to level of emp
    employ* new_employ = (employ*)malloc(sizeof(employ));
    travel->location = new_employ;
    strcpy(new_employ->name, e);
    new_employ->child = NULL;
    if (is_boss_present == 1) // to make boss
    {
        new_employ->boss = NULL;
        new_employ->next = NULL;
        employ_head = new_employ;
        return;
    }
    new_employ->boss = trie_end->location; // making new emp child of boss
    employ* p = trie_end->location->child;
    if (p == NULL)
    {
        new_employ->next = NULL;
        trie_end->location->child = new_employ;
    }
    else
    {
        new_employ->next = trie_end->location->child;
        trie_end->location->child = new_employ;
    }
}

void PrintEmployees()
{
    queue* head = NULL; // printing by Breadth First Search using queue
    employ* travel = employ_head;
    while(head || travel)
    {
        while(1)
        {
            if (travel == NULL)
                break;
            printf("%s ", travel->name);
            queue* new_node = (queue*)malloc(sizeof(queue)); // adding travel to queue
            new_node->emp = travel;
            new_node->next = NULL;
            travel = travel->next;
            if (head == NULL)
                head = new_node;
            else
            {
                queue* t = head;
                while(t->next != NULL)
                    t = t->next;
                t->next = new_node;
            }
        }
        if (head != NULL)
        {
            travel = head->emp->child;
            queue* temp = head;
            head = head->next;
            free(temp);
        }
    }
    printf("\n");
}

void DeleteEmployee(char e[], int l1, char n[], int l2)
{
    trie_node* emp_end = search(e, l1);
    trie_node* neighbour_end = search(n, l2);
    if (emp_end == NULL)
    {
        if (neighbour_end == NULL)
            printf("Employee %s and its neighbour %s are not present. Deletion failed!\n", e, n);
        else
            printf("Employee %s is not present. Deletion failed!\n", e);
        return;
    }
    else if (neighbour_end == NULL)
    {
        printf("Neighbour %s is not present. Deletion failed!\n", n);
        return;
    }
    else
    {
        // both Employee and its neighbour exist.
        if (emp_end->level != neighbour_end->level)
        {
            printf("Employee %s and its neighbour %s are not at same level. Deletion failed!\n", e, n);
            return;
        }
        int location = -1, flag = 0, check = 0;
        trie_node *temp = trie_head, *ahead_of_trie = trie_head, *storing_ahead_of_trie;
        for (int i=0;i<l1;i++)
        {
            if (i != l1-1)
            {
                flag = 0;
                for (int j=0;j<26;j++)
                {
                    if ((e[i+1]-'a') != j)
                    {
                        if ((temp->next[e[i]-'a']->next[j]) != NULL)
                        {
                            flag = 1;
                            break;
                        }
                    }
                }
                if(temp->next[e[i]-'a']->level != -1 || flag == 1) // if a level marks the ending of the prefix
                {
                    ahead_of_trie = temp->next[e[i]-'a'];
                    location = i;
                }
            }
            temp = temp->next[e[i]-'a'];
        }
        for (int i=0;i<26;i++) // if emp is prefix of something
        {
            if (temp->next[i] != NULL)
            {
                check = 1;
                temp->level = -1;
                break;
            }
        }
        if (check == 0)
        {
            storing_ahead_of_trie = ahead_of_trie;
            for (int i=location+1;i<l1-1;i++)
            {
                // printf("Deleting level for '%c'.\n",e[i]);
                temp = ahead_of_trie->next[e[i]-'a'];
                free(ahead_of_trie->next[e[i]-'a']);
                ahead_of_trie = temp;
            }
            temp = ahead_of_trie->next[e[l1-1]-'a'];
            storing_ahead_of_trie->next[e[location+1]-'a'] = NULL;
            // printf("Deleting level for '%c'.\n",e[l1-1]);
        }
        employ *list = temp->location->boss->child; // list of children of boss of s
        employ *emp_child = NULL, *prev = list;
        while (list)
        {
            if (strcmp(list->name, e) == 0)
            {
                emp_child = list->child; // adjusting in linked list
                if (list == temp->location->boss->child)
                    temp->location->boss->child = list->next;
                else
                    prev->next = list->next;
                free(list);
                break;
            }
            prev = list;
            list = list->next;
        }
        employ* travel = neighbour_end->location->child; // attach child of s to s'
        if (travel == NULL)
        {
            neighbour_end->location->child = emp_child;
        }
        else
        {
            while(travel->next != NULL)
                travel = travel->next;
            travel->next = emp_child;
        }
        while(emp_child) // updating boss
        {
            emp_child->boss = neighbour_end->location;
            emp_child = emp_child->next;
        }
        free(temp);
        return;
    }
}

void LowestCommonBoss(char emp1[], int l1, char emp2[], int l2)
{
    trie_node* emp1_end = search(emp1, l1);
    trie_node* emp2_end = search(emp2, l2);
    if (emp1_end == NULL)
    {
        if (emp2_end == NULL)
            printf("Employee %s and %s are not present. Process stopped!\n", emp1, emp2);
        else
            printf("Employee %s is not present. Process stopped!\n", emp1);
        return;
    }
    else if (emp2 == NULL)
    {
        printf("Employee %s is not present. Process stopped!\n", emp2);
        return;
    }
    else
    {
        employ *boss1 = emp1_end->location, *boss2 = emp2_end->location;
        // making the level equal
        if (emp1_end->level > emp2_end->level)
        {
            int d = emp1_end->level - emp2_end->level;
            for(int i=0;i<d;i++)
                boss1 = boss1->boss;
        }
        else if (emp1_end->level < emp2_end->level)
        {
            int d = emp2_end->level - emp1_end->level;
            for(int i=0;i<d;i++)
                boss2 = boss2->boss;
        }
        
        // finding the common boss
        while(boss1 != boss2)
        {
            boss1 = boss1->boss;
            boss2 = boss2->boss;
        }
        printf("Lowest Common Boss: %s\n", boss1->name);
    }
}

int main()
{
    char boss[20];
    trie_head = create_empty_trie_node();
    printf("Please enter initial boss of the company:\n");
    scanf("%s", boss);
    insert(boss, strlen(boss), 0, 0, 1);
    int choice;
    char emp1[20], emp2[20];
    while(1)
    {
        printf("---------- Menu ----------\n");
        printf("1. Insert\n2. DeleteEmployee\n3. LowestCommonBoss\n4. printEmployees\n5. exit\n");
        printf("--------------------------\nEnter choice: ");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                printf("Enter the employee and its boss name: ");
                scanf("%s %s", emp1, emp2);
                insert(emp1, strlen(emp1), emp2, strlen(emp2), 0);
                break;
            case 2:
                printf("Enter the employee and its neighbour: ");
                scanf("%s %s", emp1, emp2);
                DeleteEmployee(emp1, strlen(emp1), emp2, strlen(emp2));
                break;
            case 3:
                printf("Enter employee1 and employ2: ");
                scanf("%s %s", emp1, emp2);
                LowestCommonBoss(emp1, strlen(emp1), emp2, strlen(emp2));
                break;
            case 4:
                PrintEmployees();
                break;
            case 5:
                exit(1);
        }
    }
}