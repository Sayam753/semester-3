#include<stdio.h>
void quickSort(int a[], int l, int r)
{
    int i=l, j=l+1, pivot = a[l];
    if (l>=r)
    {
        return;
    }
    while(j<=r)
    {
        if (a[j]>pivot)
        {
            break;
        }
        j++;
    }    
    i=j;
    while(j<=r)
    {
        if (a[j]<pivot)
        {
            int t=a[i];
            a[i] = a[j];
            a[j]=t;
            i++;
        }
        j++;
    }
    if(i!=l)
    {
        int t=a[i-1];
        a[i-1]=a[l];
        a[l]=t;
    }
    quickSort(a, l, i-2);
    quickSort(a, i, r);
}

int main()
{
    int n;
    scanf("%d", &n);
    int a[n];
    for(int i=0;i<n;i++)
    {
        scanf("%d", &a[i]);
    }
    quickSort(a, 0, n-1);
    for(int i=0;i<n;i++)
    {
        printf("%d ", a[i]);
    }
    printf("\n");
}