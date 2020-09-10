import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    void print()
    {
        super.print();
        System.out.println("Starting time of doctor: "+start_Doctor);
        System.out.println("DocId: "+docID);
        System.out.println("Hospitals: "+hospital);
    }
}

class Surgeon extends Doctor
{
    LocalDate start_Surgeon;
    String mastersDegree;
    Surgeon(Doctor d)
    {
        Random rand = new Random();

        // Preserving doctor
        this.firstName = d.firstName;
        this.lastName = d.lastName;
        this.dob = d.dob;
        this.phoneNumber = d.phoneNumber;
        this.qualification = d.qualification;
        this.email = d.email;
        this.start_date = d.start_date;
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

    void print()
    {
        super.print();
        System.out.println("Starting time of surgeon: "+start_Surgeon);
        System.out.println("Degree: "+mastersDegree); 
    }
}

class Specialist extends Surgeon
{
    LocalDate start_Specialist;
    String domain;
    Specialist(Surgeon s, int flag)
    {
        Random rand = new Random();

        // Preserving surgeon
        this.firstName = s.firstName;
        this.lastName = s.lastName;
        this.dob = s.dob;
        this.phoneNumber = s.phoneNumber;
        this.qualification = s.qualification;
        this.email = s.email;
        this.start_date = s.start_date;
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

    void print()
    {
        super.print();
        System.out.println("Starting time of specialist: "+start_Specialist);
        System.out.println("Degree: "+domain); 
    }
}
