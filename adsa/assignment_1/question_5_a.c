#include<stdio.h>

int findLocalMinimum(int A[], int left, int right)
{
    int mid = (left+right)/2;
    if (left==right)
    {
        return A[left];
    }
    else if (mid==left)
    {
        if (A[mid] < A[mid+1])
            return A[mid];
        else return A[mid+1];
    }
    else if (A[mid+1] <= A[mid] && A[mid+1] <= A[mid-1])
    {
        // mid + 1 is smaller
        return findLocalMinimum(A, mid+1, right);
    }
    else if (A[mid-1] <= A[mid] && A[mid-1] <= A[mid+1])
    {
        // mid - 1 is smaller
        return findLocalMinimum(A, left, mid);
    }
    else if (A[mid] <= A[mid-1] && A[mid]<= A[mid+1])
    {
        // mid is local min
        return A[mid];
    }
    return 0;
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
    int minimum = findLocalMinimum(A, 0, no_of_elements-1);
    printf("Local minimum element of the array is %d.\n", minimum);
}