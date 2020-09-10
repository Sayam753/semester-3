// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign04158.java && java Assign04158
class NumberFactors
{

}

class grid
{
    static void travere(int i, int j, int k, int l)
    {
        if (i<=k && j<=l)
        {
            System.out.println(i + " " + j);
            travere(i+1, j, k, l);
            travere(i, j+1, k, l);
        }
    }
}
public class Assign04158
{
    public static void main(String[] args)
    {
        // -------------------------------------------------------------------------------
        System.out.println("\nProblem - 1");
        

        // -------------------------------------------------------------------------------
        System.out.println("\nProblem - 2");
        
        
        // -------------------------------------------------------------------------------
        System.out.println("\nProblem - 3");
        grid.travere(0, 0, 3, 4);

        // -------------------------------------------------------------------------------
        System.out.println("\nProblem - 4");
        
    }
}