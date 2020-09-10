#include<stdio.h>
int min(int a, int b)
{
    if (a > b)
        return b;
    return a;
}

int findMinimum(int A[], int no_of_elements)
{
    if (no_of_elements == 1)
    {
        return A[0];
    }
    int n1 = (no_of_elements+1)/2;
    int n2 = no_of_elements - n1;
    int A1[n1], A2[n2];
    for(int i = 0; i < n1; i++)
    {
        A1[i] = A[i];
    }
    for(int i = 0; i < n2; i++)
    {
        A2[i] = A[n1 + i];
    }
    return min(findMinimum(A1, n1), findMinimum(A2, n2));
}

int main()
{
    int no_of_elements;
    printf("Enter the no of elements: ");
    scanf("%d", &no_of_elements);
    int A[no_of_elements];
    printf("Enter the elements: ");
    for( int i = 0; i < no_of_elements; i++ )
    {
        scanf("%d", &A[i]);
    }
    int minimum = findMinimum(A, no_of_elements);
    printf("Minimum element of the array is %d.\n", minimum);
}