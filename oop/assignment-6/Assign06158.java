import java.util.*;
import java.io.*;
// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign06158.java && java Assign06158
class UserProfile
{
    String name;
    String dob;
    float balance;
    int flag; // to check age criteria and balance
    UserProfile()
    {
        // Setting name
        Random rand = new Random();
        String[] names = { "naman","rahul","rajat","sayam","raman","abhishek","abhijit","mridul","sanjeet","honey","mandeep","prem","salmaan","aamir","vicky","rajkumar","vijay","virat","dhoni","yuvraj",  "palak","tanvi","anjali","pawan","simar","simran","vasudha","vanshika","gurlovleen","gauri","dikhsa","disha","alia","sridevi","madhuri","rekha","smriti","jaspinder","preet","priyanka"};
        int numberNames = names.length;
        int index = Math.abs((rand.nextInt() % numberNames));
        this.name = names[index];

        // Setting date
        int year = 1920 + Math.abs((rand.nextInt() % 99));
        int month = 1 + Math.abs((rand.nextInt() % 12));
        int date;
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
        {
            date =  1 + Math.abs((rand.nextInt() % 31));
        }
        else
        {
            date =  1 + Math.abs((rand.nextInt() % 30));
        }
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(month) + "/");
        d.append(Integer.toString(date) + "/");
        d.append(Integer.toString(year));
        this.dob = d.toString();

        // Setting balance
        this.balance = 10 + rand.nextFloat() * 10000;

        // Setting flag
        if ((2019-year >= 18) && balance >=5000 )
        {
            this.flag=1;
        }
        else
        {
            this.flag=0;
        }
    }
}

class Bank
{
    UserProfile user;
    String PassportNumber;
    String PersonalNumber;
    String DrivingLicense;
    String EmployeeID;

    static String generateNumString(int length)
    {
        Random rand = new Random();
        int num;
        StringBuilder s = new StringBuilder();
        for(int i=0;i<length;i++)
        {
            num = Math.abs((rand.nextInt() % 10));
            s.append(Integer.toString(num));
        }
        return s.toString();
    }

    Bank(UserProfile u)
    {
        Random rand = new Random();

        this.user = u;
        int count = Math.abs((rand.nextInt() % 4)) + 1;

        // generating passportNumber
        String charSet = "abcdefghujklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder pass = new StringBuilder();
        int num = Math.abs((rand.nextInt() % 10));
        pass.append(charSet.charAt(Math.abs(rand.nextInt() % charSet.length())));
        pass.append(generateNumString(7));

        // generating personalNumber
        StringBuilder personal = new StringBuilder();
        for(int i=0;i<3;i++)
        {
            personal.append(generateNumString(4) + " ");
        }

        // generating drivinglicense
        StringBuilder drive = new StringBuilder();
        drive.append(generateNumString(6) + "/");
        drive.append(generateNumString(4));

        // generating empID
        StringBuilder empId =  new StringBuilder();
        for(int i=0;i<4;i++)
        {
            empId.append(charSet.charAt(Math.abs(rand.nextInt() % charSet.length())));
        }
        empId.append(generateNumString(7));

        this.PassportNumber = pass.toString();
        if (count >= 2)
        {
            this.PersonalNumber = personal.toString();
            if (count >= 3)
            {
                this.DrivingLicense = drive.toString();
                if (count == 4)
                {
                    this.EmployeeID = empId.toString();
                }
            }
        }
    }
}

public class Assign06158
{
    static void PrintCustomer(List<Bank> c, int n)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println("User Profile: name: " + c.get(i).user.name + " dob:" + c.get(i).user.dob + " balance:" + c.get(i).user.balance);
            System.out.print("Personal details: passport: " + c.get(i).PassportNumber + ", ");
            if (c.get(i).PersonalNumber != null)
            {
                System.out.print("personalNo: " + c.get(i).PersonalNumber + ", ");
                if (c.get(i).DrivingLicense != null)
                {
                    System.out.print("drivingLicense: " + c.get(i).DrivingLicense + ", ");
                    if (c.get(i).EmployeeID != null)
                    {
                        System.out.print("employeeID: " + c.get(i).EmployeeID + " ");  
                    }
                }
            }
            System.out.print("\n\n");
        }
    }

    static void PrintInvalid(ArrayList<UserProfile> invalidUsers)
    {
        for (int i=0;i<invalidUsers.size();i++) 
        {
            UserProfile u = invalidUsers.get(i);
            System.out.println("User Profile: name: " + u.name + ", dob:" + u.dob + ", balance:" + u.balance);
        }
    }

    static void populate(Bank b1, Bank b2)
    {
        if (b1.PassportNumber == null)
            b1.PassportNumber = b2.PassportNumber;
        if (b1.PersonalNumber == null)
            b1.PersonalNumber = b2.PersonalNumber;
        if (b1.DrivingLicense == null)
            b1.DrivingLicense = b2.DrivingLicense;
        if (b1.EmployeeID == null)
            b1.EmployeeID = b2.EmployeeID;
    }

     // Question 2
    static void merge(List<Bank> customerList)
    {
        int flag=0;
        // Checking PassportNumber
        for(int i=0;i<customerList.size();i++)
        {
            for(int j=i+1;j<customerList.size();j++)
            {
                if (customerList.get(i).PassportNumber.equals(customerList.get(j).PassportNumber))
                {
                    flag=1;
                    populate(customerList.get(i), customerList.get(j));
                    customerList.get(i).user.balance += customerList.get(j).user.balance;
                    customerList.remove(j);
                }
            }
        }

        // Checking PersonalNumber
        for(int i=0;i<customerList.size();i++)
        {
            for(int j=i+1;j<customerList.size();j++)
            {
                if (customerList.get(i).PersonalNumber!= null && customerList.get(j).PersonalNumber!= null)
                {
                    if (customerList.get(i).PersonalNumber.equals(customerList.get(j).PersonalNumber))
                    {
                        flag=1;
                        populate(customerList.get(i), customerList.get(j));
                        customerList.get(i).user.balance += customerList.get(j).user.balance;
                        customerList.remove(j);
                    }
                }   
            }
        }

        // Checking DrivingLicense
        for(int i=0;i<customerList.size();i++)
        {
            for(int j=i+1;j<customerList.size();j++)
            {
                if (customerList.get(i).DrivingLicense!= null && customerList.get(j).DrivingLicense!= null)
                {
                    if (customerList.get(i).DrivingLicense.equals(customerList.get(j).DrivingLicense))
                    {
                        flag=1;
                        populate(customerList.get(i), customerList.get(j));
                        customerList.get(i).user.balance += customerList.get(j).user.balance;
                        customerList.remove(j);
                    }
                }
            }
        }
        
        // Checking EmployeeID
        for(int i=0;i<customerList.size();i++)
        {
            for(int j=i+1;j<customerList.size();j++)
            {
                if (customerList.get(i).EmployeeID!= null && customerList.get(j).EmployeeID!= null)
                {
                    if (customerList.get(i).EmployeeID.equals(customerList.get(j).EmployeeID))
                    {
                        flag=1;
                        populate(customerList.get(i), customerList.get(j));
                        customerList.get(i).user.balance += customerList.get(j).user.balance;
                        customerList.remove(j);
                    }
                }
            }
        }
        if (flag==0)
        {
            System.out.println("No duplicate entity found.");
        }
    }

     // Question 3
    static void transactions(List<Bank> customerList)
    {
        Random rand = new Random();
        for(int i=0;i<customerList.size();i++)
        {
            int isAbove = 0;
            for(int j=0;j<10;j++)
            {
                int check = Math.abs((rand.nextInt() % 2));
                int amount = Math.abs((rand.nextInt() % 99901)) + 100;
                if (amount > 50000)
                {
                    isAbove = 1;
                }
                if (check == 0)
                {
                    amount = -1*amount;
                }
                customerList.get(i).user.balance = customerList.get(i).user.balance + amount;
                if (customerList.get(i).user.balance<0)
                {
                    customerList.get(i).user.balance = 0;
                }
            }
            if (isAbove == 1)
            {
                System.out.println("Transaction with amount>50000 for user with passport number: " + customerList.get(i).PassportNumber);
            }
        }
    }

    // Question 4
    static void interest(List<Bank> customerList)
    {
        Random rand = new Random();
        for(int i=0;i<customerList.size();i++)
        {
            if (customerList.get(i).user.balance <= 10000)
            {
                customerList.get(i).user.balance = customerList.get(i).user.balance * 1.035f;
            }
            else if ((customerList.get(i).user.balance > 10000) && (customerList.get(i).user.balance <= 50000))
            {
                customerList.get(i).user.balance = customerList.get(i).user.balance * 1.0425f;
            }
            else
            {
                customerList.get(i).user.balance = customerList.get(i).user.balance * 1.05f;
            }
        }
        PrintCustomer(customerList, customerList.size());
    }

    public static void main(String[] args)
    {
        Random rand = new Random();

        System.out.println("\nProblem - 1");
        int n = 20;
        // n = Math.abs((rand.nextInt() % 10));
        System.out.println("Generating " + n + " customers: ");
        ArrayList<UserProfile> invalidUsers = new ArrayList<UserProfile>();
        List<Bank> customerList = new ArrayList<Bank>();
        int count = 0;
        while(count<n)
        {
            UserProfile user = new UserProfile();
            if (user.flag == 1)
            {
                Bank newCustomer = new Bank(user);
                customerList.add(newCustomer);
                count = count + 1;
            }
            else
            {
                invalidUsers.add(user);   
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Printing valid customers: ");
        PrintCustomer(customerList, customerList.size());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Printing invalid customers: ");
        PrintInvalid(invalidUsers);   

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 2");
        System.out.println("Checking duplicate pairs: ");
        merge(customerList);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 3");
        transactions(customerList);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 4");
        System.out.println("Printing all customers with updated transactions by giving rate of interest: ");
        interest(customerList);
    }
}