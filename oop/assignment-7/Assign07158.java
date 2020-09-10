import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign07158.java && java Assign07158
interface Balance
{
	void calculateBalance();
}

interface credit extends Balance
{
	void creditDue(float data);
}

interface debit extends Balance
{
	void debitDue(float data);
}

class Account implements credit, debit
{
	float currentCredit;
	float currentDebit;
	float currentBalance;
	LocalDate interestModified;
    LocalDate last_tds_deducted;
	
	@Override
	public void creditDue(float data)
	{
		currentCredit = data;
	}
	
	@Override
	public void debitDue(float data)
	{
		currentDebit = data;
	}
	
	@Override
	public void calculateBalance()
	{
		currentBalance += currentCredit + currentDebit;
	}
	
	Account()
	{
		Random rand = new Random();
		currentBalance = (rand.nextFloat() * 99900) + 1000; // between 1000 to 1 Lakh
		creditDue(rand.nextFloat() * 10000 - 5000); // between -5k to 5k
		debitDue(rand.nextFloat() * 10000 - 5000); // between -5k to 5k
		calculateBalance();
		
		// setting random date for initial interest applied
        StringBuilder newDate = new StringBuilder();
        int year = 2000 + Math.abs((rand.nextInt() % 20));
        int month = 1 + Math.abs((rand.nextInt() % 12));
        int date;
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            date =  1 + Math.abs((rand.nextInt() % 31));
        else
            date =  1 + Math.abs((rand.nextInt() % 30));
        newDate.append(Integer.toString(year) + "-");
        if (month/10==0)
            newDate.append("0" + Integer.toString(month) + "-");
        else 
            newDate.append(Integer.toString(month) + "-");
        if (date/10==0)
            newDate.append("0" + Integer.toString(date));
        else 
            newDate.append(Integer.toString(date));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        interestModified = LocalDate.parse(newDate.toString(), dateFormat);

        // setting last tds deducted
        StringBuilder new_tds = new StringBuilder();
        year = 2000 + Math.abs((rand.nextInt() % 20));
        month = 1 + Math.abs((rand.nextInt() % 12));
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            date =  1 + Math.abs((rand.nextInt() % 31));
        else
            date =  1 + Math.abs((rand.nextInt() % 30));
        new_tds.append(Integer.toString(year) + "-");
        if (month/10==0)
            new_tds.append("0" + Integer.toString(month) + "-");
        else 
            new_tds.append(Integer.toString(month) + "-");
        if (date/10==0)
            new_tds.append("0" + Integer.toString(date));
        else 
            new_tds.append(Integer.toString(date));
        last_tds_deducted = LocalDate.parse(new_tds.toString(), dateFormat);
	}
}

class SavingsAccount extends Account
{
    static float rate_of_interest = 4.5f;
	SavingsAccount()
	{
		if (currentBalance < 5000)
			currentBalance = 5000;
	}
}

class CurrentAccount extends Account
{
    static float rate_of_interest = 3.5f;
    CurrentAccount()
    {
    	if (currentBalance < 5000)
			currentBalance = 5000;
    }
}

class FixedDepositAccount extends Account
{
    static float rate_of_interest = 6.5f;
    FixedDepositAccount()
    {
    	if (currentBalance < 10000)
			currentBalance = 10000;
    }
}

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
            date =  1 + Math.abs((rand.nextInt() % 31));
        else
            date =  1 + Math.abs((rand.nextInt() % 30));
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(month) + "/");
        d.append(Integer.toString(date) + "/");
        d.append(Integer.toString(year));
        this.dob = d.toString();

        // Setting balance
        this.balance = 10 + rand.nextFloat() * 10000;

        // Setting flag
        this.flag = 0;
        if ((2019-year >= 18) && balance >=5000 )
            this.flag = 1;
    }
}

class Bank
{
    UserProfile user;
    String PassportNumber;
    String PersonalNumber;
    String DrivingLicense;
    String EmployeeID;
    int account_type;
    SavingsAccount save;
    CurrentAccount current;
    FixedDepositAccount fixed;

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
            personal.append(generateNumString(4) + " ");

        // generating drivinglicense
        StringBuilder drive = new StringBuilder();
        drive.append(generateNumString(6) + "/");
        for(int i=0;i<4;i++) // generating last four characters between 1-9
        {
            num = Math.abs((rand.nextInt() % 9)) + 1;
            drive.append(Integer.toString(num));
        }

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
        
        // choosing one case from 6 to generate accounts
        account_type = Math.abs((rand.nextInt() % 7)) + 1;
        user.balance = 0;
        if (account_type == 1 || account_type == 4 || account_type == 6 || account_type == 7)
        {
            save = new SavingsAccount();
            user.balance += save.currentBalance;
        }
        if (account_type == 2 || account_type == 4 || account_type == 5 || account_type == 7)
        {
            current = new CurrentAccount();
            user.balance += current.currentBalance;
        }
        if (account_type == 3 || account_type == 5 || account_type == 6 || account_type == 7)
        {
            fixed = new FixedDepositAccount();
            user.balance += fixed.currentBalance;
        }
	}
}

public class Assign07158
{
    static void PrintCustomer(List<Bank> customerList, int n)
    {
        for(int i=0;i<n;i++)
        {
        	Bank c = customerList.get(i);
            System.out.println("User Profile: name: " + c.user.name + " dob:" + c.user.dob + " balance:" + c.user.balance);
            System.out.print("Personal details: passport: " + c.PassportNumber + ", ");
            if (c.PersonalNumber != null)
            {
                System.out.print("personalNo: " + c.PersonalNumber + ", ");
                if (c.DrivingLicense != null)
                {
                    System.out.print("drivingLicense: " + c.DrivingLicense + ", ");
                    if (c.EmployeeID != null)
                    {
                        System.out.print("employeeID: " + c.EmployeeID + " ");  
                    }
                }
            }
            System.out.print("\nUser has: ");
            if (c.account_type == 1 || c.account_type == 4 || c.account_type == 6 || c.account_type == 7)
            {
            	System.out.print("Savings Account Balance: " + c.save.currentBalance + ", ");
            }
			if (c.account_type == 2 || c.account_type == 4 || c.account_type == 5 || c.account_type == 7)
			{
				System.out.print("Current Account Balance: " + c.current.currentBalance + ", ");
			}
			if (c.account_type == 3 || c.account_type == 5 || c.account_type == 6 || c.account_type == 7)
			{
				System.out.print("Fixed Deposit Balance: " + c.fixed.currentBalance + " ");
			}
            System.out.print("\nTotal Balance " + c.user.balance + "\n\n");
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
    
   // Question 4
	static void interest(List<Bank> customerList)
	{
		LocalDate todayDate = LocalDate.now();
		for (int i=0;i<customerList.size();i++)
		{
			Bank c = customerList.get(i);
			if (c.account_type == 1 || c.account_type == 4 || c.account_type == 6 || c.account_type == 7)
			{
				long d = ChronoUnit.DAYS.between(c.save.interestModified, todayDate);
				if (d>=90) // 3 month criteria
				{
					c.user.balance += ((c.save.rate_of_interest/100)*c.save.currentBalance);
                    c.save.currentBalance += (c.save.rate_of_interest/100)*c.save.currentBalance;
                    c.save.interestModified = todayDate;
				}
			}
			if (c.account_type == 2 || c.account_type == 4 || c.account_type == 5 || c.account_type == 7)
			{
				long d = ChronoUnit.DAYS.between(c.current.interestModified, todayDate);
				if (d>=90) // 3 month criteria
				{
					c.user.balance += ((c.current.rate_of_interest/100)*c.current.currentBalance);
                    c.current.currentBalance += (c.current.rate_of_interest/100)*c.current.currentBalance;
                    c.current.interestModified = todayDate;
				}
			}
			if (c.account_type == 3 || c.account_type == 5 || c.account_type == 6 || c.account_type == 7)
			{
				long d = ChronoUnit.DAYS.between(c.fixed.interestModified, todayDate);
				if (d>=365) // 12 months criteria
				{
					c.user.balance += ((c.fixed.rate_of_interest/100)*c.fixed.currentBalance);
                    c.fixed.currentBalance += (c.fixed.rate_of_interest/100)*c.fixed.currentBalance;
                    c.fixed.interestModified = todayDate;
				}
			}
		}
    }

    static float tds_deduction_rate(float amount)
    {
        if (amount > 1000 && amount <= 5000)
            return 0.1f;
        if (amount > 5000 && amount <= 10000)
            return 0.2f;
        if (amount > 10000 && amount <= 20000)
            return 0.25f;
        if (amount > 20000)
            return 0.5f;
        return 1;
    }

    // Question 5
	static void tds (List<Bank> customerList)
    {
        Random rand = new Random();
        LocalDate todayDate = LocalDate.now();
        for(int i=0;i<customerList.size();i++)
        {
            Bank c = customerList.get(i);
            if (c.account_type == 1 || c.account_type == 4 || c.account_type == 6 || c.account_type == 7)
            {
                long d = ChronoUnit.DAYS.between(c.save.last_tds_deducted, todayDate);
                if (d>=30) // 1 month criteria
                {
                    float rate = tds_deduction_rate(c.save.currentBalance);
                    c.user.balance -= (c.save.currentBalance*rate);
                    c.save.currentBalance -= (c.save.currentBalance*rate);
                    c.save.last_tds_deducted = todayDate;
                }
            }
            if (c.account_type == 2 || c.account_type == 4 || c.account_type == 5 || c.account_type == 7)
            {
                long d = ChronoUnit.DAYS.between(c.current.interestModified, todayDate);
                if (d>=30) // 1 month criteria
                {
                    float rate = tds_deduction_rate(c.current.currentBalance);
                    c.user.balance -= (c.current.currentBalance*rate);
                    c.current.currentBalance -= (c.current.currentBalance*rate);
                    c.current.last_tds_deducted = todayDate;
                }
            }
            if (c.account_type == 3 || c.account_type == 5 || c.account_type == 6 || c.account_type == 7)
            {
                long d = ChronoUnit.DAYS.between(c.fixed.interestModified, todayDate);
                if (d>=30) // 1 months criteria
                {
                    float rate = tds_deduction_rate(c.fixed.currentBalance);
                    c.user.balance -= (c.fixed.currentBalance*rate);
                    c.fixed.currentBalance -= (c.fixed.currentBalance*rate);
                    c.fixed.last_tds_deducted = todayDate;
                }
            }
        }
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
        System.out.println("Making accounts through inheritance\n");

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 3\nCalculating aggregated balance in Bank class\n");

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 4");
        System.out.println("Printing all customers after adding interest: ");
        // Run interest function everyday to calculate interest after extact time duration
        interest(customerList);
        PrintCustomer(customerList, customerList.size());
        
    	System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nProblem - 5");
        System.out.println("Printing all customers after tds: ");
        tds(customerList);
        PrintCustomer(customerList, customerList.size());
    }
}
    