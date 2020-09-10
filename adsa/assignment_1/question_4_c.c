#include<stdio.h>
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
   int min = A[0];
   for( int i = 1; i < no_of_elements; i++ )
   {
       if ( A[i] < min )
       {
           min = A[i];
        }
   }
   printf("Minimum element of the array is %d.\n", min);
}
