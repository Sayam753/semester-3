#include<stdio.h>

void Swap(int *n1, int *n2)
{
    // swapping the values stored in n1 and n2
    int temp = *n1;
    *n1 = *n2;
    *n2 = temp;
}

void Sort(int A[], int no_of_elements)
{
    for(int i = 0; i < no_of_elements; i++)
    {
        int minIndex = i; // assign i to minIndex
        for(int j = i + 1; j < no_of_elements; j++)
        {
            // find the smallest element
            if (A[j] < A[minIndex])
            {
                minIndex = j;
            }
        }
        // swap with the smallest element
        Swap(&A[i], &A[minIndex]);
    }
}

int main()
{
    int no_of_elements;
    printf("Enter the no of elements: ");
    scanf("%d", &no_of_elements);
    int A[no_of_elements];
    printf("Enter the elements: ");
    for(int i = 0; i < no_of_elements; i++)
    {
        scanf("%d", &A[i]);
    }
    Sort(A, no_of_elements);
    printf("Sorted array is ");
    for(int i = 0; i < no_of_elements; i++)
    {
        printf("%d ", A[i]);
    }
    printf("\n");
}