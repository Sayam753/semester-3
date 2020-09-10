import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut7 problem 1
// Just compile and run: javac Sol_Tut07_01_18_158.java && java Sol_Tut07_01_18_158
// Every input is randomly generated

class Information
{
    String password;
    LocalDate lastReset;
    Information(String p)
    {
        Random rand = new Random();
        this.password = p;
        StringBuilder resetDate = new StringBuilder();
        int year = 2000 + Math.abs((rand.nextInt() % 20));
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
        resetDate.append(Integer.toString(year) + "-");
        if (month/10==0)
            resetDate.append("0" + Integer.toString(month) + "-");
        else 
            resetDate.append(Integer.toString(month) + "-");
        if (date/10==0)
            resetDate.append("0" + Integer.toString(date));
        else 
            resetDate.append(Integer.toString(date));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        lastReset = LocalDate.parse(resetDate.toString(), dateFormat);
    }
    public Information()
    {

    }
}

public class Sol_Tut07_01_18_158
{
    static String generate_password()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbolSet = "!@#$%^&*";
        StringBuilder password = new StringBuilder();
        for(int j=0;j<6;j++)
        {
            password.append(charSet.charAt(Math.abs((rand.nextInt() % charSet.length()))));
        }
        password.append(Integer.toString(Math.abs((rand.nextInt() % 10))));
        password.append(Integer.toString(Math.abs((rand.nextInt() % 10))));
        password.append(symbolSet.charAt(Math.abs((rand.nextInt() % symbolSet.length()))));
        return password.toString();
    }

    public static void main(String[] args)
    {
        // all names, passwords and last date modified are randomly generated
        Random rand = new Random();
        int n = 10 + Math.abs((rand.nextInt() % 41));
        System.out.println("No of customers randomly generated: " + n);
        String[] names = {"shivani","isha","smt shyani devi","divya","mansi","mazida","pooja","kajal","meena","sonam","buity","hina","shakshi sagar","pooja","anita","neetu","anshu","kanika kathuria","manju","shakshi","anita","reena","neha","khushboo","asmin","jyoti","riya","rekha","leela","gulshan","priya jain","pooja","rakhi","versha","sunita","nitu kumari","vandana","roshni","parveen","versa","kavita","pooja","sarojani","nagina","tapas das","priyanka","santna","khushbu","pooja","any bobby","deeya kumari","anjali juneja","anjali babli","champa karketta","anshu","monika","rimmi singh","aanamika misra","chahat","manju","nagma khatoon","pooja","sonam","koshal","laxmi devi","sandhya","poonam","sna","nikita senger","layba noor","iqra","salima","naziya siddiqui","priti","kamni","sandhya","renu rinki","priya","pooja","minakchi","ruby","farhana baigum","sheetal","kalyani patro","anjali","priyanka","palak simran","babita","gurdeep kaur","dhanwanti  devi","fooljhnah","vandana","gyatri devi","shehnaz","kajal","pooja","ranju","vidhi","pooja kashyap","jyoti","meena kumari","savita","reena devi","neha","payal goyala","rakhi","priynka","janki","laxmi  kumari","rinku malav","sohni","basnti","meera","pinki","shivani","kiran devi","sonia","doly","raj kumari","manisha","laxmi devi","swati joshi","aashiya","reena","vimla","suman","karina kavita","pooja jayshwal","pooja prajapati pratibha"};
        Map<String,Information> customerList = new HashMap<String, Information>();
        for(int i=0;i<n;i++)
        {
            int index = Math.abs((rand.nextInt() % names.length));
            Information data = new Information(generate_password()); 
            customerList.put(names[index], data);
            System.out.println("User: " + names[index] + "\nLast password modified on: " + data.lastReset + "\n");
        }
        System.out.println("Checking for 60 days reset time for all customers: ");
        for (Map.Entry<String,Information> customer : customerList.entrySet())
        {
            LocalDate dateAfter = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(customer.getValue().lastReset, dateAfter);
            if (daysBetween>=60)
            {
                Information newData = new Information();
                newData.password = generate_password();
                newData.lastReset = dateAfter;
                System.out.println("Resetting password for user: " + customer.getKey() + ", New Password: " + newData.password);
                customerList.put(customer.getKey(), newData);
            }
        }
    }  
}
