#include<stdio.h>
#include<string.h>
int main()
{
    char s[100001];
    scanf("%s", s);
    int d = strlen(s);
    int flag=0, flag1=0, check=0;
    for(int i=0;i<d-1;i++)
    {
        if (s[i] == 'A' && s[i+1] == 'B')
        {
            if (i+2<d && s[i+2] == 'A')
            {
                check=1;
                printf("NO\n");
                break;
            }
            flag=1;
        }
        if (s[i] == 'B' && s[i+1] == 'A')
        {
            if (i+2<d && s[i+2] == 'B')
            {
                check=1;
                printf("NO\n");
                break;
            }
            flag1=1;
        }
    }
    if (flag==1 && flag1==1)
    {
        printf("YES\n");
    }
    else if (check==0)
    {
        printf("NO\n");
    }
}