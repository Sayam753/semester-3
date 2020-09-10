import java.util.Random;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut5 problem 1
class Value
{
    int data;
    int count;
    Value(int a, int b)
    {
        this.data = a;
        this.count = b;
    }
}
public class Sol_Tut05_01_18_158
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        int n = 100;
        int[] array = new int[n];
        int[] freq = new int[n];
        for(int i=0;i<n;i++)
        {
            array[i] = Math.abs((rand.nextInt() % 31)) + 20;
            freq[i] = 0;
        }
        for(int i=0;i<n;i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
        
        // counting freq method 1
        for(int i=0;i<n;i++)
        {
            int c=1;
            if (freq[i]!=-1)
            {
                for(int j=i+1;j<n;j++)
                {
                    if (array[i] == array[j])
                    {
                        freq[j] = -1;
                        c++;
                    }
                }
            }
            if (freq[i]!=-1)
            {
                freq[i] = c;
            }
        }
        int distinct = 0;
        for(int i=0;i<n;i++)
        {
            if (freq[i]!=-1)
            {
                distinct = distinct + 1;
            }
        }
        int index = 0;
        Value[] list = new Value[distinct];
        
        // capturing distinct
        for(int i=0;i<n;i++)
        {
            if (freq[i]!=-1)
            {
                Value v = new Value(array[i], freq[i]);
                list[index] = v;
                index = index+1;
            }
        }

        // sorting wrto count
        for(int i=0;i<distinct;i++)
        {
            for(int j=i+1;j<distinct;j++)
            {
                if (list[i].count < list[j].count)
                {
                    Value t = list[i];
                    list[i] = list[j];
                    list[j] = t;
                }
            }
        }
        for(int i=0;i<distinct;i++)
        {
            for(int j=0;j<list[i].count;j++)
            {
                System.out.print(list[i].data + " ");
            }
        }
        System.out.print("\n");
        
    }
}