/*
Submitted By - Sayam Kumar
Roll No - S20180010158
Section - A
The logic to convert binary to decimal is simple. First we validate the given string if 
it comprises of only 0’s and 1’s. And then we keep on multiplying 
the bits with higher powers of two, to get decimal representation. 
The most optimal way to get higher powers of 2, is by implementing 
left shift binary operator.
The C program for this problem is given below - 
 */
#include<stdio.h>
#include<string.h>
void BinaryToDecimal(char s[], int length)
{
    unsigned long long int sum=0, base=1;
    for(int i=length-1;i>=0;i--)
    {
        sum = sum + (s[i]-'0')*base;
        base = base<<1; // multiplying base with 2 by left shift.
    }
    printf("Decimal number is %lld.\n", sum);
}
int main()
{
    char str[70];
    printf("Enter the binary number ");
    scanf("%s", str);
    int d=strlen(str), flag=0;
    for(int i=0;i<d;i++)
    {
        if (str[i]!='0' && str[i]!='1')
        {
            flag=1;
            break;
        }
    }
    if (flag==1)
    {
        printf("Wrong format entered!\n");
        return 0;
    }
    BinaryToDecimal(str, strlen(str));
}
