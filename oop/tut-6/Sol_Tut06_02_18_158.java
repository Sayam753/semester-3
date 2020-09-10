import java.util.*;
import java.io.*;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut6 problem 2
// Just compile and run: javac Sol_Tut06_02_18_158.java && java Sol_Tut06_02_18_158
// Every input is randomly generated

public class Sol_Tut06_02_18_158
{
    static void checkBalanced(String s, int len)
    {
        int[] a = new int[len];
        int flag = 0;
        for(int i=0;i<len-1;i++)
        {
            if ((s.charAt(i)=='[' && s.charAt(i+1)==']') || (s.charAt(i)=='{' && s.charAt(i+1)=='}') || (s.charAt(i)=='(' && s.charAt(i+1)==')'))
            {
                flag = 1;
                a[i] = 1;
                a[i+1] = 1;
            }
        }
        if (flag == 0)
        {
            if (len == 0)
            {
                System.out.println("Yes, it is a balanced string");
            }
            else
            {
                System.out.println("No, it is not a balanced string");
            }
            return;
        }
        StringBuilder newStr = new StringBuilder();
        for(int i=0;i<len;i++)
        {
            if (a[i] == 0)
            {
                newStr.append(s.charAt(i));
            }
        }
        checkBalanced(newStr.toString(), newStr.toString().length());
    }
    public static void main(String[] args)
    {
        System.out.println("Enter a string: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        checkBalanced(s, s.length());
    }
}