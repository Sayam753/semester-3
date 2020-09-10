import java.util.Random;
// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign03158.java && java Assign03158
class Factors
{
    static int highestFactor(int left, int right) 
    {
        if (left == 0) 
            return right; 
        return highestFactor(right % left, left); 
    }

    static void computePrimeFactors(int k)
    {
        int isPrime=1;
        for(int i=2;i<=k;i++)
        {
            int flag = 0;
            for(int j=2;j<=i/2;j++)
            {
                if (i%j==0)
                {
                    flag = 1;
                    break;
                }
            }
            if (flag==0) // prime found
            {
                while(k%i==0)
                {
                    isPrime=0;
                    k = k/i;
                    System.out.print(i + " ");
                }
            }
        }
        if (isPrime==1)
        {
            System.out.print(k);
        }
        System.out.print("\n");
    }

    int evenFactors(int num)
    {
        int sum=0;
        System.out.print("Even factors are: ");
        for(int i=2;i<=num/2;i=i+2)
        {
            if (num%i==0)
            {
                sum+=i;
                System.out.print(i + " ");
            }
        }
        if (num%2==0)
        {
            System.out.print(num + " ");
            sum = sum+num;
        }
        return sum;
    }

    void checkCommonFactors(int[] num)
    {
        // take highest common factors and then find its prime factors
        int answer = num[0];
        for(int i=1;i<20;i++)
        {
            answer = highestFactor(answer, num[i]);
        }
        System.out.print("Common prime factors are: ");
        computePrimeFactors(answer);
    }

    void findPrimePalindromes(int k)
    {
        int ifAnyPresent = 0;
        for(int i=2;i<=k;i++)
        {
            int flag = 0;
            for(int j=2;j<=i/2;j++)
            {
                if (i%j==0)
                {
                    flag = 1;
                    break;
                }
            }
            if (flag==0) // prime found
            {
                int isPalindrome = 1;
                String s = Integer.toString(i);
                int len = s.length();
                for(int j=0;j<len/2;j++)
                {
                    if (s.charAt(j) != s.charAt(len-1-j))
                    {
                        isPalindrome = 0;
                        break;
                    }
                }
                if (isPalindrome==1)
                {
                    ifAnyPresent = 1;
                    System.out.print(i + " ");
                }
            }
        }
        if (ifAnyPresent == 0)
        {
            System.out.print("No prime palindrome found within range.");
        }
        System.out.print("\n");
    }

    void findPrimePalindromes(int k, int m)
    {
        int ifAnyPresent = 0;
        for(int i=k;i<=m;i++)
        {
            int flag = 0;
            for(int j=2;j<=i/2;j++)
            {
                if (i%j==0)
                {
                    flag = 1;
                    break;
                }
            }
            if (flag==0) // prime found
            {
                int isPalindrome = 1;
                String s = Integer.toString(i);
                int len = s.length();
                for(int j=0;j<len/2;j++)
                {
                    if (s.charAt(j) != s.charAt(len-1-j))
                    {
                        isPalindrome = 0;
                        break;
                    }
                }
                if (isPalindrome==1)
                {
                    ifAnyPresent = 1;
                    System.out.print(i + " ");
                }
            }
        }
        if (ifAnyPresent == 0)
        {
            System.out.print("No prime palindrome found within range.");
        }
        System.out.print("\n");
    }

    void printKGrams(int k)
    {
        String s = "BAY OF BENGAL IS VERY BEAUTIFUL";
        int len = s.length();
        for(int i=1;i<len-1;i++)
        {
            if (s.charAt(i-1) > s.charAt(i) && s.charAt(i) < s.charAt(i+1))
            {
                System.out.println(s.charAt(i-1) + " " + s.charAt(i) + " " + s.charAt(i+1));
            }
        }
    }
}

public class Assign03158
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 1");
        int number = Math.abs((rand.nextInt() % 200)) + 2;
        System.out.println("Number randomly taken is " + number);
        System.out.print("Prime factors are  ");
        Factors.computePrimeFactors(number);

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 2");
        number = Math.abs((rand.nextInt() % 200)) + 2;
        System.out.println("Number randomly taken is " + number);
        int sum = new Factors().evenFactors(number);
        System.out.println("\nSum of even factors of " + number + " is: " + sum);

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 3");
        int[] array = new int[20];
        for(int i=0;i<20;i++)
        {
            array[i] = Math.abs((rand.nextInt() % 131)) + 20;
            if (array[i]%2==1)
            {
                array[i] = array[i] + 1;
            }
        }
        System.out.print("Array is: ");
        for(int i=0;i<20;i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
        new Factors().checkCommonFactors(array);

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 4");
        // checking whether to call function findPrimePalindromes() with one argument or two arguments
        int check = Math.abs((rand.nextInt() % 2)) + 1; // if check is 1, call with 1 argument
        if (check==1)
        {
            int k = Math.abs((rand.nextInt() % 1000)) + 1;
            System.out.println("k is " + k);
            new Factors().findPrimePalindromes(k);
        }
        else // if check is 2, call with 2 arguments
        {
            int k = Math.abs((rand.nextInt() % 1000)) + 1;
            int m = Math.abs((rand.nextInt() % (999-k))) + k + 1;
            System.out.println("k is " + k + " and m is " + m);
            new Factors().findPrimePalindromes(k, m);
        }

        // --------------------------------------------------------------------
        System.out.println("\nProblem - 5");
        new Factors().printKGrams(3);
    }
}