import java.util.Random;
class Customer
{
    String firstName;
    String lastName;
    String dob;
    int gender; // 1 for male and 0 for female
    int isAdult; // 1 for adult and 0 for minor
    String address;
    String city;
    float salary;
    void setFirstName(String s)
    {
        firstName = s;
    }
    void setLastName(String s)
    {
        lastName = s;
    }
    void setDOB(int date, int month, int year)
    {
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(date) + "/");
        d.append(Integer.toString(month) + "/");
        d.append(Integer.toString(year));
        dob = d.toString();
    }
    void setGender()
    {
        Random rand = new Random();
        gender = Math.abs((rand.nextInt() % 2));
    }
    void setAdult(String inputDate)
    {
        String yearString = "";  
        yearString = inputDate.substring(inputDate.length() - 4);
        int year = Integer.parseInt(yearString);
        if (2019-year>=18)
        {
            isAdult = 1;
        }
        else
        {
            isAdult = 0;
        }
    }
    void setAddress(String s)
    {
        Random rand = new Random();
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(Math.abs((rand.nextInt() % 100)) + 1) + ", ");
        d.append(s + " street, ");
        d.append("India");
        address = d.toString();
    }
    void setCity(String c)
    {
        city=c;
    }
    void setSalary(float s)
    {
        salary = s;
    }
    String getFirstName()
    {
        return this.firstName;
    }
    String getLastName()
    {
        return this.lastName;
    }
    String getDOB()
    {
        return this.dob;
    }
    int getGender()
    {
        return this.gender;
    }
    int getAdult()
    {
        return this.isAdult;
    }
    String getAddress()
    {
        return this.address;
    }
    String getCity()
    {
        return this.city;
    }
    float getSalary()
    {
        return this.salary;
    }
}

public class Assign02158
{

    static void printCustomerData(Customer customer)
    {
        System.out.println("First Name: " + customer.getFirstName());
        System.out.println("Last Name: " + customer.getLastName());
        System.out.println("Date Of Birth: " + customer.getDOB());
        System.out.println("Gender: " + customer.getGender());
        System.out.println("Adult: " + customer.getAdult());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("City: " + customer.getCity());
        System.out.println("Salary: " + customer.getSalary() + "\n\n");
    }

    static void printAge(int n, Customer[] customers)
    {
        for(int i=0;i<n;i++)
        {
            String yearString = customers[i].getDOB().substring(customers[i].getDOB().length()-4);
            int year = Integer.parseInt(yearString);
            int age = 2019-year;
            if (age>60)
            {
                printCustomerData(customers[i]);
            }
        }
    }

    static void dearnessAllowance(int n, Customer[] customers)
    {
        for(int i=0;i<n;i++)
        {
            String yearString = customers[i].getDOB().substring(customers[i].getDOB().length()-4);
            int year = Integer.parseInt(yearString);
            int age = 2019-year;
            if (age>=35 && age<=55)
            {
                float newSalary = customers[i].getSalary() * 1.05f;
                customers[i].setSalary(newSalary);
            }
            else if (age>55)
            {
                float newSalary = customers[i].getSalary() * 1.08f;
                customers[i].setSalary(newSalary);
            }
        }
    }

    
    static void higherSalary(int n, Customer[] customers)
    {
        for(int i=0;i<n;i++)
        {
            if(customers[i].getSalary() > 50000.0f)
            {
                printCustomerData(customers[i]);
            }
        }
    }

    static void tdsDeduction(int n, Customer[] customers)
    {
        for(int i=0;i<n;i++)
        {
            System.out.print("Salary: " + customers[i].getSalary());
            System.out.print(" TDS deducted: " + (customers[i].getSalary()*0.35f));
            System.out.print(" Net Amount: " + (customers[i].getSalary()*0.65f) + "\n\n");
        }
    }

    static void findCustomers(int n, Customer[] customers, String cityName)
    {
        for(int i=0;i<n;i++)
        {
            if (customers[i].getCity().equals(cityName))
            {
                printCustomerData(customers[i]);
            }
        }
    }
    public static void main(String[] args)
    {
        Random rand = new Random();
        int n=20;
        Customer[] customers = new Customer[n];
        String[] cityList = { "chennai","mumbai","delhi","kolkata","pune","banglore","ludhiana","chandigarh","agra","shimla","srinagar","hyderabad","jaipur","varanasi","haridwar","surat","kochi","amritsar","bhopal","nagpur"}; 
        String[] boyFirstName = { "naman","rahul","rajat","sayam","raman","abhishek","abhijit","mridul","sanjeet","honey","mandeep","prem","salmaan","aamir","vicky","rajkumar","vijay","virat","dhoni","yuvraj"}; 
        String[] girlFirstName = { "palak","tanvi","anjali","pawan","simar","simran","vasudha","vanshika","gurlovleen","gauri","dikhsa","disha","alia","sridevi","madhuri","rekha","smriti","jaspinder","preet","priyanka"}; 
        String[] surname = { "kumar","bansal","goyal","tiwari","mishra","malhotra","anand","khare","talari","patil","pandey","nad","singla","singh","kaur","arora","saurav","roy","khan","samrat"}; 
        String[] streetList = { "Nehru","Gandhi","Bhagat Singh","Lajpat Rai","Lakshmi Bai","Azad","Tilak","Chandra","Sultan","Grand Trunk","Model Town","Rajeev Chowk","Sadar Bazaar","Clock Tower","Khan","Pandey","Hyder Ali","Sham Nagar","Lal Nagar","Dwarka"}; 
        for(int i=0;i<n;i++)
        {
            customers[i] = new Customer();
            customers[i].setGender();
            int index;
            if (customers[i].getGender()==1)
            {
                index = Math.abs((rand.nextInt() % 20));
                customers[i].setFirstName(boyFirstName[index]);
            }
            else
            {
                index = Math.abs((rand.nextInt() % 20));
                customers[i].setFirstName(girlFirstName[index]);
            }
            customers[i].setLastName(surname[index]);

            int year = 1950 + Math.abs((rand.nextInt() % 69));
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
            customers[i].setDOB(date, month, year);
            
            customers[i].setAdult(customers[i].getDOB());

            int streetIndex = Math.abs((rand.nextInt() % 20));
            customers[i].setAddress(streetList[streetIndex]);
            customers[i].setCity(cityList[Math.abs(rand.nextInt() % 20)]);

            customers[i].setSalary((995000*rand.nextFloat()) + 5000);
        } 
        System.out.println("Printing details of customers having age>60: ");
        // time complexity = O(n)
        printAge(n, customers);

        // time complexity = O(n)
        dearnessAllowance(n, customers);

        System.out.println("Printing details of customers having salary>50000: ");
        // time complexity = O(n)
        higherSalary(n, customers);

        System.out.println("Deducting TDS of customers: ");
        // time complexity = O(n)
        tdsDeduction(n, customers);

        System.out.println("Finding customers by city name: ");
        int index;
        index = Math.abs((rand.nextInt() % 20));
        System.out.println("City chosen: " + cityList[index]);
        // time complexity = O(n)
        findCustomers(n, customers, cityList[index]);
    }
}