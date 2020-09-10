//import java.util.Random;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut3 problem 2
// converting to decimal and adding - method 1
// actually adding the binary string - method 2
import java.util.Scanner; 
public class Sol_Tut03_02_18_158
{
    static void method1(String b1, String b2)
    {
        long d1=0L; 
        long d2=0L; 
        long base=1L;
        for(int i=b1.length()-1;i>=0;i--)
        {
            d1 = d1 + (b1.charAt(i)-'0')*base;
            base = base*2;
        }
        base = 1;
        for(int i=b2.length()-1;i>=0;i--)
        {
            d2 = d2 + (b2.charAt(i)-'0')*base;
            base = base*2;
        }
        StringBuilder answer=new StringBuilder();  
        long sum = d1+d2;
        while(sum>0)
        {
            if (sum%2==0)
            {
                answer.append("0");
            }
            else
            {
                answer.append("1");
            }
            sum = sum/2;
        }
        String sumAnswer = answer.toString();
        System.out.println("Sum using method 1 is ");
        for(int i=sumAnswer.length()-1;i>=0;i--)
        {
            System.out.print(sumAnswer.charAt(i));
        }
        System.out.println(" ");
    }
    static void method2(String b1, String b2)
    {
        int l1 = b1.length();
        int l2 = b2.length();
        int max = l1;
        if (l1>l2)
        {
            StringBuilder extra=new StringBuilder();
            for(int i=1;i<=l1-l2;i++)
            {
                extra.append("0");
            }
            b2 = extra.toString() + b2;
        }
        else if(l2>l1)
        {
            max = l2;
            StringBuilder extra=new StringBuilder();
            for(int i=1;i<=l2-l1;i++)
            {
                extra.append("0");
            }
            b1 = extra.toString() + b1;
        }
        int c=0;
        StringBuilder answer=new StringBuilder();
        for(int i=max-1;i>=0;i--)
        {
            int sum = b1.charAt(i)-'0' + b2.charAt(i)-'0' + c;
            if (sum==2)
            {
                answer.append("0");
                c=1;
            }
            else if (sum==3)
            {
                answer.append("1");
                c=1;
            }
            else
            {
                if (sum==0)
                {
                    answer.append("0");
                }
                else
                {
                    answer.append("1");
                }
                c=0;
            }
        }
        if (c==1)
        {
            answer.append("1");
        }
        String sumAnswer = answer.toString();
        System.out.println("Sum using method 2 is ");
        for(int i=sumAnswer.length()-1;i>=0;i--)
        {
            System.out.print(sumAnswer.charAt(i));
        }
        System.out.println(" ");
    }
    public static void main(String[] args)
    {   
        String b1, b2;
        System.out.println("Enter two strings:");
		Scanner sc = new Scanner(System.in);
        b1 = sc.nextLine();
        b2 = sc.nextLine();
        // converting to decimal and adding
        method1(b1, b2);
        // actually adding the binary string
        method2(b1, b2);
        sc.close();
    }
}