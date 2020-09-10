import java.util.Random;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut3 problem 1 
public class Sol_Tut03_01_18_158
{
    static void printArray(int array[], int n)
    {
        for(int i=0;i<n;i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    static int generateNoOfElements()
    {
        Random rand = new Random();
        int n = Math.abs((rand.nextInt() % 50)) + 1;
        if(n%2 == 1)
        {
            n = n + 1;
        }
        return n;
    }
    static int[] generateArray(int n)
    {
        Random rand = new Random();
        int[] array = new int[n];
        for(int i=0;i<n;i++)
        {
            array[i] = Math.abs((rand.nextInt() % 64)) + 20;
        }
        return array;
    }
    static void addToNext(int array[], int n)
    {
        for(int i=n-1;i>=0;i=i-2)
        {
            array[i] = array[i] + array[i-1];
        }
    }
    public static void main(String[] args)
    {
        int n = generateNoOfElements(); //method 1
        System.out.println("No of elements: " + n);
        int[] array = generateArray(n);
        System.out.println("Original Array: ");
        printArray(array, n);
        addToNext(array, n); // method 2
        System.out.println("Modified Array: ");
        printArray(array, n);
        //Time complexity O(n) to iterate over all elements
    }
}