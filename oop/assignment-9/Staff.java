import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Staff extends Person
{
    LocalDate start_staff;
    float salary_staff;
    String staff_id;

    Staff(Person p)
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Preserving person
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.dob = p.dob;
        this.phoneNumber = p.phoneNumber;
        this.email = p.email;
        this.qualification = "Staff";
        this.start_date = p.start_date;

        // Setting start_staff
        this.start_staff = p.start_date.plusDays(180);

        // Setting salary
        this.salary_staff = (Math.abs(rand.nextFloat()+1))*5000;

        // Setting staff_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.staff_id = for_id.toString();
    }

    Staff()
    {
        
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of LabStaff: "+start_staff);
        System.out.println("ID: "+staff_id); 
    }
}

class Pharmacist extends Staff
{
    LocalDate start_pharma;
    float salary_pharma;

    Pharmacist(Staff s)
    {
        // Preserving staff
        this.firstName = s.firstName;
        this.lastName = s.lastName;
        this.dob = s.dob;
        this.phoneNumber = s.phoneNumber;
        this.email = s.email;
        this.qualification = "Staff";
        this.start_date = s.start_date;
        this.start_staff = s.start_staff;
        this.salary_staff = s.salary_staff;
        this.staff_id = s.staff_id;

        // Setting start_pharma
        this.start_pharma = s.start_staff.plusDays(365);

        // Setting salary
        this.salary_pharma = s.salary_staff+10000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of Pharmacist: "+start_pharma);
        System.out.println("Salary: "+salary_pharma); 
    }
}

class CanteenSupervisor extends Staff
{
    LocalDate start_canteen;
    float salary_canteen;
    static String[] food={"chips","coke","biscuits","pizza","tea","coffee","burger","namkeen","sandwich"};

    CanteenSupervisor(Staff s)
    {
        // Preserving staff
        this.firstName = s.firstName;
        this.lastName = s.lastName;
        this.dob = s.dob;
        this.phoneNumber = s.phoneNumber;
        this.email = s.email;
        this.qualification = "Staff";
        this.start_date = s.start_date;
        this.start_staff = s.start_staff;
        this.salary_staff = s.salary_staff;
        this.staff_id = s.staff_id;

        // Setting start_pharma
        this.start_canteen = s.start_staff.plusDays(180);

        // Setting salary
        this.salary_canteen = s.salary_staff+5000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of CanteenSupervisor: "+start_canteen);
        System.out.println("Salary: "+salary_canteen); 
    }
}

class Accountant extends Staff
{
    LocalDate start_accountant;
    float salary_accountant;
    static String[] duties={"Maintain all records","Create a budget","Announce areas of improvement"};

    Accountant(Staff s)
    {
        // Preserving staff
        this.firstName = s.firstName;
        this.lastName = s.lastName;
        this.dob = s.dob;
        this.phoneNumber = s.phoneNumber;
        this.email = s.email;
        this.qualification = "Staff";
        this.start_date = s.start_date;
        this.start_staff = s.start_staff;
        this.salary_staff = s.salary_staff;
        this.staff_id = s.staff_id;

        // Setting start_pharma
        this.start_accountant = s.start_staff.plusDays(365);

        // Setting salary
        this.salary_accountant = s.salary_staff+10000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of Accountant: "+start_accountant);
        System.out.println("Salary: "+salary_accountant); 
    }
}
