import java.util.*;
import java.io.*;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut6 problem 1
// Just compile and run: javac Sol_Tut06_01_18_158.java && java Sol_Tut06_01_18_158
// Every input is randomly generated

public class Sol_Tut06_01_18_158
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        int[][] a = new int[10][20];
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<20;j++)
            {
                a[i][j] = (rand.nextInt() % 50);
            }
        }
        System.out.println("2D array is:");
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<20;j++)
            {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
        for(int i=0;i<10;i++)
        {
            int[] left = new int[20];
            int[] right = new int[20];
            left[0] = a[i][0];
            right[19] = a[i][19];

            // Calculate forward sum
            for(int j=1;j<20;j++)
            {
                left[j] = a[i][j] + left[j-1];
            }

            // Calculate backward sum
            for(int j=18;j>=0;j--)
            {
                right[j] = a[i][j] + right[j+1];
            }
            System.out.println("For Row " + i + ":");
            int k = 1, flag=1, check = 1;
            int index = 8;

            // Checking symmetrically from center index
            while(true)
            {
                if ((index + 1 + k) >= 20 || (index + k)<0)
                {
                    break;
                }
                if (left[index + k] < right[index + 1 + k])
                {
                    check = 0;
                    System.out.println("LeftIndex: "  + (index + k) + " leftSum: " + left[index + k] + " rightSum " + right[index + k +1]);
                    break;
                }
                if (flag == 1)
                {
                    k = -1*k;
                    flag = 0;
                }
                else
                {
                    k = (-1*k) + 1;
                    flag = 1;
                }
            }
            if (check == 1)
            {
                System.out.println("Cannot find any partition");
            }
        }
    }
}