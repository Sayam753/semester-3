import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut9 problem 1
// Just compile and run: javac Sol_Tut09_01_18_158.java && java Sol_Tut09_01_18_158
// Every input is randomly generated

class Person
{
    String firstName;
    String lastName;
    String dob;
    String phoneNumber;
    String qualification;
    String email;
    LocalDate start_date;

    Person()
    {
        Random rand = new Random();
        String[] names = {"naman", "rahul", "rajat", "sayam", "raman", "abhishek", "vicky", "rajkumar", "vijay", "virat", "dhoni", "yuvraj", "palak", "tanvi", "anjali", "pawan", "simar", "simran", "vasudha", "vanshika", "gurlovleen", "gauri", "dikhsa", "disha", "alia", "sridevi", "madhuri", "rekha", "smriti", "jaspinder", "preet", "priyanka"};
        String[] surnames = { "kumar","bansal","goyal","tiwari","mishra","malhotra","anand","khare","talari","patil","pandey","nad","singla","singh","kaur","arora","saurav","roy","khan","samrat"}; 
        
        // Setting firstName
        int number_of_names = names.length;
        this.firstName = names[Math.abs((rand.nextInt() % number_of_names))];

        // Setting lastName
        int number_of_surnames = surnames.length;
        this.lastName = surnames[Math.abs((rand.nextInt() % number_of_surnames))];
        
        // Setting date
        int year = 1950 + Math.abs((rand.nextInt() % 51));
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

        // Setting phone_number
        StringBuilder for_phone = new StringBuilder();
        for_phone.append(Integer.toString(1 + Math.abs((rand.nextInt() % 9))));
        for(int i=1;i<=9;i++)
        {
            for_phone.append(Integer.toString(Math.abs((rand.nextInt() % 10))));
        } 
        this.phoneNumber = for_phone.toString();

        // Setting email
        StringBuilder for_email = new StringBuilder();
        for_email.append(this.firstName);
        for_email.append(this.lastName);
        for_email.append(Integer.toString(Math.abs((rand.nextInt() % 10))) + "@gmail.com");
        this.email = for_email.toString(); //  example - sayamkumar4@gmail.com

        // Setting startDate
        StringBuilder for_date = new StringBuilder();
        year = 2010 + Math.abs((rand.nextInt() % 8));
        month = 1 + Math.abs((rand.nextInt() % 12));
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            date =  1 + Math.abs((rand.nextInt() % 31));
        else
            date =  1 + Math.abs((rand.nextInt() % 30));
        for_date.append(Integer.toString(year) + "-");
        if (month/10==0)
            for_date.append("0" + Integer.toString(month) + "-");
        else 
            for_date.append(Integer.toString(month) + "-");
        if (date/10==0)
            for_date.append("0" + Integer.toString(date));
        else 
            for_date.append(Integer.toString(date));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start_date = LocalDate.parse(for_date.toString(), dateFormat);
    }
}

class Doctor extends Person
{
    LocalDate start_Doctor;
    String docID;
    String hospital;
    Doctor(Person p)
    {
        Random rand = new Random();
        String[] hospital_list = {"AMRI Hospitals", "Apollo Hospitals", "Billroth Hospitals", "Care Hospitals", "Command Hospital", "Council of Christian Hospitals", "Currae Hospital", "Dr. Mohan’s Diabetes Specialities Centre", "Dr. Agarwal's Eye Hospital", "Fortis Healthcare"};
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        
        // Preserving person
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.dob = p.dob;
        this.phoneNumber = p.phoneNumber;
        this.email = p.email;
        this.qualification = "MBBS";

        // Setting start_Doctor
        this.start_Doctor = p.start_date.plusDays(420); // adding 14 months

        // Setting docID
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.docID = for_id.toString();

        // Setting hospital
        int index = Math.abs(rand.nextInt()%hospital_list.length);
        this.hospital = hospital_list[index];
    }

    Doctor() // default constructor
    {

    }
}

class Surgeon extends Doctor
{
    LocalDate start_Surgeon;
    String mastersDegree;
    Surgeon(Doctor d)
    {
        Random rand = new Random();

        // Preserving person
        this.firstName = d.firstName;
        this.lastName = d.lastName;
        this.dob = d.dob;
        this.phoneNumber = d.phoneNumber;
        this.qualification = d.qualification;
        this.start_date = d.start_date;

        // Preserving doctor
        this.start_Doctor = d.start_Doctor;
        this.docID = d.docID;
        this.hospital = d.hospital;

        // Setting start_Surgeon
        this.start_Surgeon = d.start_Doctor.plusDays(720); // 24 months ahead of doctor

        // Setting masterdegree
        String[] degrees = {"MCM", "MMSc", "MMedSc", "MM", "MMed", "MPhil", "MS", "MSurg", "MChir", "MCh", "MSc"};
        int index = Math.abs(rand.nextInt()%degrees.length);
        this.mastersDegree = degrees[index];
    }

    Surgeon() // default constructor
    {

    }
}

class Specialist extends Surgeon
{
    LocalDate start_Specialist;
    String domain;
    Specialist(Surgeon s, int flag)
    {
        Random rand = new Random();

        // Preserving person
        this.firstName = s.firstName;
        this.lastName = s.lastName;
        this.dob = s.dob;
        this.phoneNumber = s.phoneNumber;
        this.qualification = s.qualification;
        this.start_date = s.start_date;

        // Preserving doctor
        this.start_Doctor = s.start_Doctor;
        this.docID = s.docID;
        this.hospital = s.hospital;
        
        if (flag==1)
        {
            // Preserving surgeon
            this.start_Surgeon = s.start_Surgeon;
            this.mastersDegree = s.mastersDegree;
            this.start_Specialist = s.start_Surgeon.plusDays(365); // 12 months ahead of surgeon
        }
        else
        {
            this.start_Surgeon = null;
            this.mastersDegree = null;
            this.start_Specialist = s.start_Doctor.plusDays(365); // 12 months ahead of doctor
        }

        // Setting domain
        String[] specialist_domain = {"Anesthesiology", "Dermatology", "Radiology", "Genetics", "Neurology", "Gynecology", "Ophthalmology", "Pathology", "Pediatrics", "Nephrology", "Pulmonology", "Rheumatology", "Hepatology", "Psychiatry", "Plastic surgery", "Orthopaedics", "Urology"};
        int index = Math.abs(rand.nextInt()%specialist_domain.length);
        domain = specialist_domain[index];
    }
}

public class Sol_Tut09_01_18_158
{
    static void findDoctor(List<Surgeon> surgeon_list, String name)
    {
        for(int i=0;i<surgeon_list.size();i++)
        {
            Surgeon s = surgeon_list.get(i);
            if (s.firstName.equals(name))
            {
                System.out.println("----------------------------------------------------\nSurgeon Found");
                System.out.println("Name: "+s.firstName+" "+s.lastName);
                System.out.println("DOB: "+s.dob+", id: "+s.docID);
                System.out.println("Start Date as Surgeon: "+s.start_Surgeon);
                System.out.println("Degree Completed: "+s.mastersDegree);
            }
        }
    }

    static void findDoctor(List<Specialist> specialists, String name, int flag)
    {
        for(int i=0;i<specialists.size();i++)
        {
            Specialist s = specialists.get(i);
            if (s.firstName.equals(name))
            {
                System.out.println("----------------------------------------------------\nSpecialist Found");
                System.out.println("Name: "+s.firstName+" "+s.lastName);
                System.out.println("DOB: "+s.dob+", id: "+s.docID);
                System.out.println("Start Date as Specialist: "+s.start_Specialist);
                System.out.println("Domain: "+s.domain);
            }
        }
    }

    static void experience(int diff)
    {
        int year = diff/365;
        int months = (diff-(year*365))/30;
        int days = diff-(months*30)-(year*365);
        System.out.println("Experince: "+year+" years, "+months+" months, "+days+" days");
    }

    public static void main(String[] args)
    {
        Random rand = new Random();
        int n = 100;
        int count = 0;
        List<Person> person_list = new ArrayList<Person>();
        List<Doctor> doctor_list = new ArrayList<Doctor>();
        List<Surgeon> surgeon_list = new ArrayList<Surgeon>();
        List<Specialist> specialists = new ArrayList<Specialist>();

        // Creating 100 persons
        for(int i=0;i<n;i++)
        {
            Person p = new Person();
            person_list.add(p);
        }
        System.out.println(n+" persons created successfully.\n");

        // Creating doctors from persons
        for(int i=0;i<n;i++)
        {
            Person p = person_list.get(i);
            LocalDate todayDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(p.start_date, todayDate); 
            if (diff >= 420) // 14 month criteria
            {
                Doctor d = new Doctor(p);
                doctor_list.add(d);
            }
        }
        System.out.println(doctor_list.size()+" doctors created successfully.\n");

        // Creating surgeons from doctors
        for(int i=0;i<doctor_list.size();i++)
        {
            int flag = Math.abs(rand.nextInt()%2); // Doctor wishes to become surgeon or not
            if (flag == 1)
            {
                Doctor d = doctor_list.get(i);
                LocalDate todayDate = LocalDate.now();
                long diff = ChronoUnit.DAYS.between(d.start_Doctor, todayDate);
                if (diff>=720) // 24 months criteria
                {
                    Surgeon s = new Surgeon(d);
                    surgeon_list.add(s);
                }
            }
        }
        System.out.println(surgeon_list.size()+" surgeons created successfully.\n");

        // Creating specialists from surgeons
        for(int i=0;i<surgeon_list.size();i++)
        {
            int flag = Math.abs(rand.nextInt()%2); // Surgeon wishes to become Specialist or not
            if (flag == 1)
            {
                Surgeon s = surgeon_list.get(i);
                LocalDate todayDate = LocalDate.now();
                long diff;
                int sur_doc = Math.abs(rand.nextInt()%2); // to check whether Specialist is a Surgeon or a doctor
                // sur_doc=1 => Surgeon, sur_doc=0 => Doctor;
                
                if (sur_doc == 1)
                {
                    diff = ChronoUnit.DAYS.between(s.start_Surgeon, todayDate);   
                    if (diff>=365) // 12 months criteria
                    {
                        Specialist sp = new Specialist(s, sur_doc);
                        specialists.add(sp);
                    }
                }
                else
                {
                    diff = ChronoUnit.DAYS.between(s.start_Doctor, todayDate);
                    if (diff>=365) // 12 months criteria
                    {
                        Specialist sp = new Specialist(s, sur_doc);
                        specialists.add(sp);
                    }
                }
            }
        }
        System.out.println(specialists.size()+" specialists created successfully.\n");

        // Question 3 Problem 1
        int flag = Math.abs(rand.nextInt()%2);
        if (flag == 0) // seach for surgeon
        {
            int index = Math.abs(rand.nextInt()%surgeon_list.size());
            Surgeon s = surgeon_list.get(index);
            System.out.println("\nQuestion 3 Problem 1\nSearching surgeons with Name: " + s.firstName);
            findDoctor(surgeon_list, s.firstName);
        }
        if (flag == 1) // seach for specialist
        {
            int index = Math.abs(rand.nextInt()%specialists.size());
            Specialist s = specialists.get(index);
            System.out.println("\nQuestion 3 Problem 1\nSearching specialists with Name: " + s.firstName);
            findDoctor(specialists, s.firstName, flag);
        }

        // Question 3 Problem 2
        flag = Math.abs(rand.nextInt()%3);
        LocalDate todayDate = LocalDate.now();
        if (flag==0) // experience of doctor
        {
            int index = Math.abs(rand.nextInt()%doctor_list.size());
            Doctor d = doctor_list.get(index);
            System.out.println("\n\nQuestion 3 Problem 2\n----------------------------------------------------\nGetting experience of Doctor");
            System.out.println("Name: " + d.firstName+" "+d.lastName+", id: "+d.docID);
            experience((int)(ChronoUnit.DAYS.between(d.start_Doctor, todayDate)));
        }
        else if (flag==1) // experience of surgeon
        {
            int index = Math.abs(rand.nextInt()%surgeon_list.size());
            Surgeon s = surgeon_list.get(index);
            System.out.println("\n\nQuestion 3 Problem 2\n----------------------------------------------------\nGetting experience of Surgeon");
            System.out.println("Name: " + s.firstName+" "+s.lastName+", id: "+s.docID);
            experience((int)(ChronoUnit.DAYS.between(s.start_Surgeon, todayDate)));   
        }
        else // experience of specialistc
        {
            int index = Math.abs(rand.nextInt()%specialists.size());
            Specialist s = specialists.get(index);
            System.out.println("\n\nQuestion 3 Problem 2\n----------------------------------------------------\nGetting experience of Specialist");
            System.out.println("Name: " + s.firstName+" "+s.lastName+", id: "+s.docID);
            experience((int)(ChronoUnit.DAYS.between(s.start_Specialist, todayDate)));  
        }

        // Question 3 Problem 3
        String[] hospital_list = {"AMRI Hospitals", "Apollo Hospitals", "Billroth Hospitals", "Care Hospitals", "Command Hospital", "Council of Christian Hospitals", "Currae Hospital", "Dr. Mohan’s Diabetes Specialities Centre", "Dr. Agarwal's Eye Hospital", "Fortis Healthcare"};
        int index = Math.abs(rand.nextInt()%hospital_list.length);
        String hospital = hospital_list[index];
        System.out.println("\n\nQuestion 3 Problem 3\nDoctors working in "+hospital+" hospital");
        for(int i=0;i<doctor_list.size();i++)
        {
            Doctor d = doctor_list.get(i);
            if (d.hospital.equals(hospital))
            {
                System.out.println("Name: " + d.firstName+" "+d.lastName+", id: "+d.docID);
            }
        }
    }  
}
