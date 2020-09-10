import java.util.*;
import java.io.*;
import java.text.*;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut8 problem 1
// Just compile and run: javac Sol_Tut08_03_18_158.java && java Sol_Tut08_03_18_158
// Every input is randomly generated

public class Sol_Tut08_03_18_158
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        int n = Math.abs((rand.nextInt() % 5)) + 1;
        for(int i=0;i<n;i++)
        {
        	int no_of_elements = Math.abs((rand.nextInt() % 6)) + 5;
        	System.out.println("No of elements are: " + no_of_elements);
        	int[] array1 = new int[no_of_elements];
        	int[] array2 = new int[no_of_elements];
        	System.out.print("Array1: ");
        	for(int j=0;j<no_of_elements;j++)
        	{
        		array1[j] = rand.nextInt() % 30;
        		System.out.print(array1[j] + " ");
        	}
        	System.out.print("\nArray2: ");
        	for(int j=0;j<no_of_elements;j++)
        	{
        		array2[j] = rand.nextInt() % 30;
        		System.out.print(array2[j] + " ");
        	}
        	int weight = rand.nextInt() % 20;
        	int index = Math.abs((rand.nextInt() % no_of_elements));
			System.out.print("\nWeight and index is: " + weight + " " + index);

        	if (array1[index] > 0)
    		{
    			array1[index] += (array1[index]*weight);
    		}
    		else
    		{
    			array1[index] += (array1[index]*weight);
    		}

    		int total = 0;
        	for(int j=0;j<no_of_elements;j++)
        	{
        		total += (array1[j]*array2[j]);
        	}
        	System.out.println("\nSum: " + total + "\n");
        }
    }  
}
