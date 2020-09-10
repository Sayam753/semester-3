import java.util.Scanner; 
public class Sol_Tut02_02_18_158
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter a string:");
        String str = sc.nextLine();
        StringBuilder vowels = new StringBuilder();

        //building a string of vowels
        for(int i=str.length()-1;i>=0;i--)
        {
            if (str.charAt(i)=='A' || str.charAt(i)=='E' || str.charAt(i)=='I' || str.charAt(i)=='O' || str.charAt(i)=='U' || str.charAt(i)=='a' || str.charAt(i)=='e' || str.charAt(i)=='i' || str.charAt(i)=='o' || str.charAt(i)=='u')
            {
                vowels.append(str.charAt(i));
            }
        }
        StringBuilder finalStr = new StringBuilder();
        int index=0;

        //replacing initial vowels
        for(int i=0;i<str.length();i++)
        {
            if (str.charAt(i)=='A' || str.charAt(i)=='E' || str.charAt(i)=='I' || str.charAt(i)=='O' || str.charAt(i)=='U' || str.charAt(i)=='a' || str.charAt(i)=='e' || str.charAt(i)=='i' || str.charAt(i)=='o' || str.charAt(i)=='u')
            {
                finalStr.append(vowels.charAt(index));
                index = index+1;
            }
            else
            {
                finalStr.append(str.charAt(i));
            }
        }
        System.out.println(finalStr);
        sc.close();
    }
}