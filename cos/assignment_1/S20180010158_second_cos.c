/*
Submitted By - Sayam Kumar
Roll No - S20180010158
Section - A
The logic is simple. To convert any integer into binary format, we need to take the 
remainder by 2 and divide the number by 2 again. This process is repeatedly done. 
If we want to convert any float into binary format, we separate the integer and 
fractional part and then convert separately into binary format and then combine. The C program for this problem is 
given below - 
 */

#include<stdio.h>
#include<math.h>
void DecimalToBinary(unsigned long long int num)
{
    if (num==0)
    {
        printf("0");
        return;
    }
    int length = ceil(log(num)/log(2)) + 1, i=0;
    char BinaryString[length+1];
    while(num>0)
    {
        BinaryString[i] = num%2 + '0';
        i++;
        num = num>>1; //dividing by two
    }
    for(int j=i-1;j>=0;j--)
    {
        printf("%c",BinaryString[j]);
    }
    
}

void FloatToBinary(double num)
{
    unsigned long long int integer_part = (int)num;
    double fractional_part = num-integer_part;
    DecimalToBinary(integer_part);
    printf(".");
    if (num==0)
    {
        printf("0");
        return;
    }
    while(fractional_part!=0 && fractional_part!=1)
    {
        fractional_part *=2.0;
        printf("%d", (int)fractional_part);
        fractional_part = fractional_part - (int)fractional_part;
    }
}

unsigned long long int power(int p) //calculating power in log(n) time
{
    unsigned long long int answer = 1;
    int x = 10;
    while (p > 0) { 
        if (p & 1) 
            answer = answer * x;  
        p = p >> 1; 
        x = x * x;
    } 
    return answer; 
}

int main()
{
    int choice;
    printf("Enter your choice\n1. Integer Value\n2. Float Value\n3. Power of ten\n");
    scanf("%d", &choice);
    switch (choice)
    {
        case 1:
            printf("Enter integer number: ");
            unsigned long long int num;
            scanf("%lld", &num);
            printf("Binary representation: ");
            DecimalToBinary(num);
            printf("\n");
            break;

        case 2:
            printf("Enter float value: ");
            double float_num;
            scanf("%lf", &float_num);
            printf("Binary representation: ");
            FloatToBinary(float_num);
            printf("\n");
            break;

        case 3:
            printf("Enter a number: ");
            double base;
            scanf("%lf", &base);
            printf("Enter a power of 10: ");
            int p;
            scanf("%d", &p);
            double answer = base*power(p);
            printf("Binary representation: ");
            FloatToBinary(answer);
            printf("\n");
            break;

        default:
            printf("Invalid choice entered\n");
    }
}

