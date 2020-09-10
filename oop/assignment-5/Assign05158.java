import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign05158.java && java Assign05158
class NumberFactors
{
    int data;
    ArrayList<Integer> factors = new ArrayList<Integer>();

    NumberFactors(int num)
    {
        int isPrime = 1;
        this.data = num;
        for(int i=2;i<this.data/2;i++)
        {
            int flag = 0;
            // finding prime
            for(int j=2;j<i;j++)
            {
                if (i%j == 0)
                {
                    flag = 1;
                    break;
                }
            }
            if (flag==0)
            {
                int count = 0;
                while(num%i == 0)
                {
                    isPrime = 0;
                    // ensuring uniqueness constraint
                    if (count == 0)
                    {
                        this.factors.add(i);
                        count = 1;
                    }
                    num = num/i;
                }
            }
        }
        if (isPrime == 1)
        {
            this.factors.add(num);
        }
    }
    static void findFactors(int num, int position)
    {
        NumberFactors element = new NumberFactors(num);
        System.out.print("Num: " + element.data + " ");
        System.out.println("Factors:" + element.factors);
    }

    static void findFactors(int[] arr)
    {
        NumberFactors[] list = new NumberFactors[arr.length];
        System.out.println("Printing unique factors: ");
        for(int i=0;i<arr.length;i++)
        {
            NumberFactors element = new NumberFactors(arr[i]);
            list[i] = element;
            System.out.print("Arr[" + i + "]: " + list[i].data + ",  ");
            System.out.println("factors:" + list[i].factors);
        }
    }
}

class shape
{
    public static void startWork()
    {
        // generating points on a circle using R*cos(theta) and R*sin(theta)
        Random rand = new Random();
        DecimalFormat numFormat = new DecimalFormat("#.0000");
        int k = Math.abs((rand.nextInt() % 7)) + 4;
        System.out.println("No of line segments randomly chosen: " + k);
        double radius = rand.nextFloat()*4 + 0.5;
        double degrees = rand.nextDouble()*30 + 0.5;
        double radians = Math.toRadians(degrees);
        for(int i=0;i<k;i++)
        {
            double sinValue = Math.sin(i*radians);
            double cosValue = Math.cos(i*radians);
            System.out.println("x: " + numFormat.format(radius*cosValue) +  ", y: " + numFormat.format(radius*sinValue));
        }
    }
} 

class grid
{
    int x, y;
    static int count;
    public void travere(int i, int j, int k, int l, ArrayList<grid> list)
    {
        if (i<=k && j<=l)
        {
            if (i==k && j==l)
            {
                count++;
                for (int index=0; index<list.size(); index++)
                {
                    System.out.print("(" + list.get(index).x + "," + list.get(index).y  + "),  ");
                }
                System.out.print("\n");
            }

            ArrayList<grid> right = new ArrayList<grid>();
            for (int index=0; index<list.size(); index++) 
                    right.add(list.get(index));
            grid new_right = new grid();
            new_right.x = list.get(list.size()-1).x + 1;
            new_right.y = list.get(list.size()-1).y;
            right.add(new_right);
            travere(i+1, j, k, l, right); // go right

            ArrayList<grid> up = new ArrayList<grid>();
            for (int index=0; index<list.size(); index++) 
                    up.add(list.get(index));
            grid new_up = new grid();
            new_up.x = list.get(list.size()-1).x;
            new_up.y = list.get(list.size()-1).y + 1;
            up.add(new_up);
            travere(i, j+1, k, l, up);
        }
    }
    public static void main(String[] args)
    {
        System.out.println("Of assignment 5");
    }
}

class TrainTicket
{
    int tickid;
    String trainname;
    String date;
    int category;
    String pasname;
    int age;
    int seat;
    String code;
    int stype;
    float fare;
    public TrainTicket()
    {
        Random rand = new Random();
        String[] names = {"naman", "rahul", "rajat", "sayam", "raman", "abhishek", "vicky", "rajkumar", "vijay", "virat", "dhoni", "yuvraj", "palak", "tanvi", "anjali", "pawan", "simar", "simran", "vasudha", "vanshika", "gurlovleen", "gauri", "dikhsa", "disha", "alia", "sridevi", "madhuri", "rekha", "smriti", "jaspinder", "preet", "priyanka"};
        String[] trainNames = {"Abida Begum Express", "Agniveena Express", "Ahilyanagari Express",  "Ahimsa Express", "Ajanta Express", "Akal Takht Express", "Ala Hazrat Express", "Amaravati Express", "Amarkantak Express", "Amarnath Express", "Amrapali Express", "Amrita Express", "Ananthapuri Express", "Ananya Express", "Andaman Expess", "Andhra Kesari Express", "Aradhana Express", "Aranyak Express", "Aravalli Express", "Archana Express", "Arunachal Express"};
        int index = Math.abs((rand.nextInt() % 21));
        this.trainname = trainNames[index];

        int year = 1950 + Math.abs((rand.nextInt() % 69));
        int month = 1 + Math.abs((rand.nextInt() % 12));
        int date = 1 + Math.abs((rand.nextInt() % 30));
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            date =  1 + Math.abs((rand.nextInt() % 31));
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(date) + "/");
        d.append(Integer.toString(month) + "/");
        d.append(Integer.toString(year));
        this.date = d.toString();

        this.category = Math.abs((rand.nextInt() % 5));
        index = Math.abs((rand.nextInt() % 32));
        this.pasname = names[index];
        this.age = Math.abs((rand.nextInt() % 100)) + 1;
        this.seat = Math.abs((rand.nextInt() % 72)) + 1;
        
        index = Math.abs((rand.nextInt() % 90)) + 10;
        char alphabet = (char)(Math.abs(rand.nextInt() % 26) + 'A');
        StringBuilder coachID = new StringBuilder();
        coachID.append(alphabet);
        coachID.append(Integer.toString(index));
        this.code = coachID.toString();

        this.stype = Math.abs((rand.nextInt() % 4));
        this.fare = Math.abs((rand.nextFloat() * 1950)) + 50;
    }
    int genBookingDataset(int n)
    {
        Random rand = new Random();

        // assuring uniqueness constraint
        Set<Integer> uniqueID = new HashSet<Integer>();
        int number;
        while(uniqueID.size()<n)
        {
            number = Math.abs((rand.nextInt() % 1000000)) + 1000;
            uniqueID.add(number);
        }
        TrainTicket[] list = new TrainTicket[n];
        for(int i=0;i<n;i++)
        {
            TrainTicket newTicket = new TrainTicket();
            list[i] = newTicket;
        }
        int i = 0;
        for(int data : uniqueID)
        {
            list[i].tickid = data;
            i++;
        }
        return 1;
    }
    void genBookingDataset(int n, TrainTicket[] list)
    {
        Random rand = new Random();

        // assuring uniqueness constraint
        Set<Integer> uniqueID = new HashSet<Integer>();
        int number;
        while(uniqueID.size()<n)
        {
            number = Math.abs((rand.nextInt() % 1000000)) + 1000;
            uniqueID.add(number);
        }
        for(int i=0;i<n;i++)
        {
            TrainTicket newTicket = new TrainTicket();
            list[i] = newTicket;
        }
        int i = 0;
        for(int data : uniqueID)
        {
            list[i].tickid = data;
            i++;
        }
        return;
    }
    int printTickets(int category)
    {
        Random rand = new Random();
        int n = Math.abs((rand.nextInt() % 51)) + 50; // n between 50 and 100
        System.out.println("Number of tickets randomly chosen: " + n);
        System.out.println("Using genBookingDataset to generate " + n + " tickets.");
        System.out.println("Printing tickets of category " + category);
        TrainTicket[] list = new TrainTicket[n];
        genBookingDataset(n, list);
        for(int i=0;i<n;i++)
        {
            if (list[i].category == category)
            {
                System.out.println("TickId: " + list[i].tickid + ", Name: " + list[i].pasname +  ", Fare: " + list[i].fare);
            }
        }
        return 1;
    }
}

public class Assign05158
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        // -----------------------------------------------------------------------------------
        System.out.println("\nProblem - 1");
        int[] arr = new int[]{414, 15, 21, 252, 40, 41, 115, 101, 151, 139};
        int index = Math.abs((rand.nextInt() % 10));
        System.out.println("Index randomly chosen: " + index);
        NumberFactors.findFactors(arr[index], index);
        System.out.println("\nCalling findFactors with array as parameter.");
        NumberFactors.findFactors(arr);

        // -----------------------------------------------------------------------------------
        System.out.println("\nProblem - 2");
        shape.startWork();
        
        // -----------------------------------------------------------------------------------
        System.out.println("\nProblem - 3");
        int n, m;
        n = Math.abs((rand.nextInt() % 4)) + 3; // x between 3 and 6
        m = Math.abs((rand.nextInt() % 4)) + 3; // y between 3 and 6
        grid new_node = new grid();
        new_node.x = 0;
        new_node.y = 0;
        ArrayList<grid> list = new ArrayList<grid>();
        list.add(new_node);
        System.out.println("For n * m grid, n randomly chosen: " + n);
        System.out.println("m randomly chosen: " + m);
        System.out.println("Printing all possible paths from (0,0) to ("+(n-1)+","+(m-1)+"):");
        grid new_grid = new grid();
        new_grid.travere(0, 0, 1, 2, list);
        System.out.println("No of possible paths: " + new_grid.count);

        // -----------------------------------------------------------------------------------
        System.out.println("\nProblem - 4");
        int category = Math.abs((rand.nextInt() % 5));
        new TrainTicket().printTickets(category);
    }
}