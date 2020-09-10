import java.util.Random;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut4 problems
public class Sol_Tut04_01_18_158
{
    static void armStrongNumbers(int num)
    {
        int pre = num;
        int sum = 0;
        while(num>0)
        {
            int rem = num%10;
            sum = sum + (rem*rem*rem);
            num = num/10;
        }
        if(pre == sum)
        {
            System.out.println(pre + " is an armstrong number");
        }
        else
        {
            System.out.println(pre + " is not an armstrong number");
        }
    }

    static void floydTriangle(int rows)
    {
        int count=1;
        for(int i=1;i<=rows;i++)
        {
            for(int j=1;j<=i;j++)
            {
                System.out.print(count + " ");
                count++;
            }
            System.out.print("\n");
        }
    }

    static void doReverse(String ourString, int len)
    {
        StringBuilder reverseBuilder = new StringBuilder(len);
        for(int i=len-1;i>=0;i--)
        {
            reverseBuilder.append(ourString.charAt(i));
        }
        System.out.println("The reversed string: " + reverseBuilder.toString());
    }

    static void swap(int num1, int num2) // without a third variable
    {
        num1 = num1 + num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
        System.out.println("Swapped numbers -  num1=" + num1 + ", num2=" + num2);
    }

    public static void main(String[] args)
    {
        Random rand = new Random();

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 1");
        int number = Math.abs((rand.nextInt() % 1000)) + 2;
        System.out.println("Number randomly taken is " + number);
        armStrongNumbers(number);

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 2");
        number = Math.abs((rand.nextInt() % 15)) + 5;
        System.out.println("Number of rows randomly taken is " + number);
        System.out.println("Floyd Triangle is: ");
        floydTriangle(number);

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 3");
        int len = 5 + Math.abs((rand.nextInt() % 15));
        String charSet="ABCDEFGHIJKLMNOPQRSTUVWXVZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder forString = new StringBuilder(len);
        for(int i=0;i<len;i++)
        {
            int index = (int) Math.abs((rand.nextInt()) % charSet.length());
            forString.append(charSet.charAt(index));
        }
        String ourString=forString.toString();
        System.out.println("Our randomly generated string: " + ourString);
        doReverse(ourString, ourString.length());

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 4");
        int num1 = Math.abs((rand.nextInt() % 1000)) + 100;
        int num2 = Math.abs((rand.nextInt() % 1000)) + 100;
        System.out.println("Randomly generated numbers -  num1=" + num1 + ", num2=" + num2);
        swap(num1, num2);
    }
}