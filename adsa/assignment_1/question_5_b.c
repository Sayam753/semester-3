#include<stdio.h>
int check(int no_of_elements, int A[][no_of_elements], int x, int y)
{
    // checking if the number is a local min
    int flag1=0, flag2=0, flag3=0, flag4=0;
    if (x-1 >= 0)
        flag1=1;
    if (y-1 >= 0)
        flag2=1;
    if (x+1 < no_of_elements)
        flag3=1;
    if (y+1 < no_of_elements)
        flag4=1;
    if (flag1)
    {
        if (flag2)
        {
            if (flag3)
            {
                if (flag4)
                    return (A[x][y]<A[x-1][y] && A[x][y]<A[x+1][y] && A[x][y]<A[x][y-1] && A[x][y]<A[x][y+1]);
                return (A[x][y]<A[x-1][y] && A[x][y]<A[x+1][y] && A[x][y]<A[x][y-1]);
            }
            if (flag4)
                return (A[x][y]<A[x-1][y] && A[x][y]<A[x][y+1] && A[x][y]<A[x][y-1]);
            return (A[x][y]<A[x-1][y] && A[x][y]<A[x][y-1]);
        }
        if (flag3)
        {
            if (flag4)
                return (A[x][y]<A[x-1][y] && A[x][y]<A[x+1][y] && A[x][y]<A[x][y+1]);
        }
        if (flag4)
            return (A[x][y]<A[x-1][y] && A[x][y]<A[x][y+1]);
    }
    if (flag2)
    {
        if (flag3)
        {
            if (flag4)
                return (A[x][y]<A[x][y-1] && A[x][y]<A[x+1][y] && A[x][y]<A[x][y+1]);
            return (A[x][y]<A[x][y-1] && A[x][y]<A[x+1][y]);
        } 
    }
    if (flag3)
        return (A[x][y]<A[x][y+1] && A[x][y]<A[x+1][y]);
    return 1;
    
}

int findMinimum(int no_of_elements, int A[][no_of_elements], int start, int left, int right)
{
    int min = A[0][0], x = 0, y = 0, flag = 0;
    // first_row
    for(int i=start;i<right;i++)
    {
        if (check(no_of_elements, A, start, i))
            return A[start][i];
        if (A[start][i]<min)
        {
            min = A[start][i];
            x=start;
            y=i;
        }
    }
    // last_row
    int factor = (left+start)%2?1:0;
    for(int i=start;i<right;i++)
    {
        if (check(no_of_elements, A, left-factor, i))
            return A[left-factor][i];
        if (A[left-factor][i]<min)
        {
            min = A[left-factor][i];
            x=left-factor;
            y=i;
        }
    }
    // first_column
    for(int i=start;i<left;i++)
    {
        if (check(no_of_elements, A, i, start))
            return A[i][start];
        if (A[i][start]<min)
        {
            min = A[i][start];
            x=i;
            y=start;
        }
    }
    //last_column
    factor = (right+start)%2?1:0;
    for(int i=start;i<left;i++)
    {
        if (check(no_of_elements, A, i, right-factor))
            return A[i][right-factor];
        if (A[i][right-factor]<min)
        {
            min = A[i][right-factor];
            x=i;
            y=right-factor;
        }
    }
    // middle_row
    int row = (left-start)/2;
    for(int i=start;i<right;i++)
    {
        if (check(no_of_elements, A, row, i))
            return A[row][i];
        if (A[row][i]<min)
        {
            min = A[row][i];
            x = row;
            y = i;
        }
    }
    // middle_column
    int column = (right-start)/2;
    for(int i=start;i<left;i++)
    {
        if (check(no_of_elements, A, i, column))
            return A[i][column];
        if (A[i][column]<min)
        {
            min = A[i][column];
            x=i;
            y=column;
        }
    }
    // global min found in window
    if (x < (left+start)/2)
    {
        if (y < (right+start)/2)
            return findMinimum(no_of_elements, A, 0, (left+start)/2, (right+start)/2); // 1
        return findMinimum(no_of_elements, A, (left+start)/2, left, (right+start)/2); // 2
    }
    else
    {
        if (y < (right+start)/2)
        {
            findMinimum(no_of_elements, A, (right+start)/2, (left+start)/2, right); // 3
        }
        return findMinimum(no_of_elements, A, (left+start)/2, left, right); // 4
    }
}

int main()
{
    int no_of_elements;
    //printf("Enter the no of elements: ");
    scanf("%d", &no_of_elements);
    int A[no_of_elements][no_of_elements];
    //printf("Enter the elements: ");
    for( int i = 0; i < no_of_elements; i++ )
    {
        for( int j = 0; j < no_of_elements; j++ )
            scanf("%d", &A[i][j]);
    }
    int minimum = findMinimum(no_of_elements, A, 0, no_of_elements, no_of_elements);
    printf("Local minimum element of the array is %d.\n", minimum);
}