import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Nurse extends Person
{
    LocalDate start_nurse;
    float salary_nurse;
    String nurse_id;

    Nurse(Person p)
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Preserving person
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.dob = p.dob;
        this.phoneNumber = p.phoneNumber;
        this.qualification = p.qualification;
        this.email = p.email;
        this.start_date = p.start_date;
        this.qualification = "B.Sc. Nursing";

        // Setting start_nurse
        this.start_nurse = p.start_date.plusDays(365);

        // Setting salary
        this.salary_nurse = Math.abs(rand.nextFloat()*40000)+10000;

        // Setting nurse_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.nurse_id = for_id.toString();
    }

    Nurse()
    {

    }

    void print()
    {
        super.print();
        System.out.println("Starting time of Nurse: "+start_nurse);
        System.out.println("Nurse Id: "+nurse_id); 
    }
}

class ICUNurse extends Nurse
{
    LocalDate start_icu;
    float salary_icu;
    static String dept = "ICU";

    ICUNurse(Nurse n)
    {
        // Preserving nurse
        this.firstName = n.firstName;
        this.lastName = n.lastName;
        this.dob = n.dob;
        this.phoneNumber = n.phoneNumber;
        this.qualification = "B.Sc. Nursing";
        this.email = n.email;
        this.start_date = n.start_date;
        this.start_nurse = n.start_nurse;
        this.salary_nurse = n.salary_nurse;
        this.nurse_id = n.nurse_id;

        // Setting start_icu
        this.start_icu = n.start_date.plusDays(365);

        // Setting salary
        this.salary_icu = n.salary_nurse+10000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of ICUNurse: "+start_icu);
        System.out.println("Salary: "+salary_icu); 
    }
}

class GNNurse extends Nurse
{
    LocalDate start_gn;
    float salary_gn;
    static String dept = "GN Department";

    GNNurse(Nurse n)
    {
        Random rand = new Random();

        // Preserving nurse
        this.firstName = n.firstName;
        this.lastName = n.lastName;
        this.dob = n.dob;
        this.phoneNumber = n.phoneNumber;
        this.qualification = "B.Sc. Nursing";
        this.email = n.email;
        this.start_date = n.start_date;
        this.start_nurse = n.start_nurse;
        this.salary_nurse = n.salary_nurse;
        this.nurse_id = n.nurse_id;

        // Setting start_gn
        this.start_gn = n.start_date.plusDays(180);

        // Setting salary
        this.salary_gn = n.salary_nurse+5000;
    }

    void print()
    {
        super.print();
        System.out.println("Starting time of GNNurse: "+start_gn);
        System.out.println("Salary: "+salary_gn); 
    }

}
