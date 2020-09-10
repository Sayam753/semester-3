import java.io.*;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut5 problem 2
public class Sol_Tut05_02_18_158
{
    public static void main(String[] args)
    {
        BufferedReader br = null;
        try 
        {                                                                                                                                                                                                                                                                             
            br = new BufferedReader(new FileReader("file.txt"));                                                                                                                                                                                                 
            String line = null;      
            line = br.readLine();
            String[] tokens = line.split("\\s");
            int[][] arr = new int[tokens.length][26];
            for(int i=0;i<tokens.length;i++)
            {
                tokens[i] = tokens[i].toLowerCase();
                int len = tokens[i].length();
                if (len>=5 && len<=10)
                {
                    for(int j=0;j<len;j++)
                    {
                        int index = tokens[i].charAt(j);
                        if (index>=97 && index<=122)
                        {
                            arr[i][index-'a']++;
                        }
                    }
                }
            }
            int[] freq = new int[tokens.length];
            for(int i=0;i<tokens.length;i++)
            {
                int len = tokens[i].length();
                int c=0;
                if (len>=5 && len<=10 && freq[i]!=-1)
                {
                    for(int j=i+1;j<tokens.length;j++)
                    {
                        int l = tokens[j].length();
                        if (l>=5 && l<=10 && freq[j]!=-1)
                        {
                            int flag = 0;
                            for(int k=0;k<26;k++)
                            {
                                if (arr[i][k] != arr[j][k])
                                {
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag==0)
                            {
                                c=1;
                                freq[j]=-1;
                                System.out.print(tokens[j] + " ");
                            }
                        }
                    }
                    if (c==1)
                    {
                        System.out.print(tokens[i]);
                        System.out.print("\n");
                    }
                }
            }
        } 
        catch(FileNotFoundException e) 
        {                                                                                                                                                                                                                                                
            System.err.println("Caught FileNotFoundException: " + e.getMessage());                                                                                                                                                                                                          
            throw new RuntimeException(e);                                                                                                                                                                                                                                                  
        }
        catch(IOException e) 
        {                                                                                                                                                                                                                                                          
            System.err.println("Caught IOException: " + e.getMessage());                                                                                                                                                                                                                    
        }
        finally 
        {                                                                                                                                                                                                                                                                        
            if (null != br) 
            {
                try
                {
                    br.close(); 
                }
                catch(IOException e) {
                    System.err.println("Caught IOException: " + e.getMessage());
                }
            }
        }
    }
}