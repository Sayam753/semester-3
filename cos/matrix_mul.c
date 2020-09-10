#include<stdio.h>
int main()
{
    int a[3][3], b[3][3];
    int k=10, c=1;
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            a[i][j] = c;
            b[i][j] = k;
            c++;
            k++;
        }
    }
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            printf("%d ", a[i][j]);
        }
        printf("\n");
    }
    int d[3][3];
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            d[i][j] = 0;
        }
    }
    for(int k=0;k<3;k++)
    {
        for(int i=0;i<3;i++)
        {
            int r = a[i][k];
            for(int j=0;j<3;j++)
            {
                d[i][j] += (r*b[k][j]);
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            printf("%d ", d[i][j]);
        }
        printf("\n");
    }
    
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            printf("%d ", b[i][j]);
        }
        printf("\n");
    }
}