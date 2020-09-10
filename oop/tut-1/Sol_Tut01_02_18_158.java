import java.util.Scanner; 
public class Sol_Tut01_02_18_158
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter a string:");
        String str = sc.nextLine();
        int num, count=0;
        System.out.println("Enter value of n for n-grams:");
        num = sc.nextInt();
        if (num>str.length())
        {
            System.out.println("Wrong n entered");
        }
        else
        {
            for(int i=0;i<str.length()-num+1;i++)
            {
                for(int j=i;j<i+num;j++)
                {
                    System.out.print(str.charAt(j));
                }
                System.out.print(" ");
                count = count+1;
            }
            System.out.println("\nThe no of n-grams is " + count);
        }
        sc.close();
    }
}