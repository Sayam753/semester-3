#include<stdio.h>
void merge(int a[], int l, int m, int r)
{
    int n1 = m-l+1;
    int n2 = r-m;
    int a1[n1], a2[n2];
    for(int i=0;i<n1;i++)
    {
        a1[i] = a[l+i];
    }
    for(int i=0;i<n2;i++)
    {
        a2[i] = a[m+1+i];
    }
    int i=0,j=0,k=l;
    while(i!=n1 && j!=n2)
    {
        if (a1[i] > a2[j])
        {
            a[k] = a2[j];
            j++;
        }
        else{
            a[k] = a1[i];
            i++;
        }
        k++;
    }
    while(i!=n1)
    {
        a[k] = a1[i];
        i++;
        k++;
    }
    while(j!=n2)
    {
        a[k] = a2[j];
        j++;
        k++;
    }
}

void mergeSort(int a[], int l, int r)
{
    if (l<r)
    {
        int m = l + (r-l)/2;
        mergeSort(a, l, m);
        mergeSort(a, m+1, r);
        merge(a, l, m, r);
    }
}

int main()
{
    int n;
    scanf("%d",&n);
    int a[n];
    for(int i=0;i<n;i++)
    {
        scanf("%d",&a[i]);
    }
    mergeSort(a, 0, n-1);
    for(int i=0;i<n;i++)
    {
        printf("%d ",a[i]);
    }
    printf("\n");
}