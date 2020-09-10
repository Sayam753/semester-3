import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class LabTechnician extends Person
{
    LocalDate start_labtech;
    float salary_labtech;
    static String[] duties = {"Organises work", "Serves as technial resource", "Identifies and communicates abnormal patient conditions", "Maintains quality results", "Maintains patientconfidence", "Contributes to a safe and secure environment for patients"};
    String tech_id;

    LabTechnician(Person p)
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Preserving person
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.dob = p.dob;
        this.phoneNumber = p.phoneNumber;
        this.email = p.email;
        this.qualification = "Technician";
        this.start_date = p.start_date;

        // Setting start_labtech
        this.start_labtech = p.start_date.plusDays(730);

        // Setting salary
        this.salary_labtech = (Math.abs(rand.nextFloat()+1))*50000;

        // Setting tech_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.tech_id = for_id.toString();
    }

    LabTechnician()
    {
        
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of LabTechnician: "+start_labtech);
        System.out.println("ID: "+tech_id); 
    }
} 

class LTSpecialist extends LabTechnician
{
    LocalDate start_lt;
    float salary_lt;

    LTSpecialist(LabTechnician l)
    {
        // Preserving LabTechnician
        this.firstName = l.firstName;
        this.lastName = l.lastName;
        this.dob = l.dob;
        this.phoneNumber = l.phoneNumber;
        this.email = l.email;
        this.qualification = "Technician";
        this.start_date = l.start_date;
        this.start_labtech = l.start_labtech;
        this.salary_labtech = l.salary_labtech;
        this.tech_id = l.tech_id;

        // Setting start_labtech
        this.start_lt = l.start_labtech.plusDays(180);

        // Setting salary
        this.salary_lt = l.salary_labtech+10000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of LTSpecialist: "+start_lt);
        System.out.println("Salary: "+salary_lt); 
    }
}