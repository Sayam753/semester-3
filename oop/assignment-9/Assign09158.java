import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut9 problem 1
// Just compile and run: javac Assign09158.java Doctor.java Nurse.java Technician.java Staff.java && java Assign09158 300
// 300 is command line arguments
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

    void print()
    {
        System.out.println("Name: "+firstName+" "+lastName);
        System.out.println("Phone: "+phoneNumber+", dob: "+dob);
        System.out.println("Email: "+email);
    }
}

public class Assign09158
{
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
        int n=100;
        if (args.length!=0)
        {
            n = Integer.valueOf(args[0]);
            if (n==0)
                n=100;
        }
        List<Person> person_list = new ArrayList<Person>();
        List<Doctor> doctor_list = new ArrayList<Doctor>();
        List<Surgeon> surgeon_list = new ArrayList<Surgeon>();
        List<Specialist> specialists = new ArrayList<Specialist>();

        List<Nurse> nurse_list = new ArrayList<Nurse>();
        List<ICUNurse> icu_nurse = new ArrayList<ICUNurse>();
        List<GNNurse> gn_nurse = new ArrayList<GNNurse>();

        List<LabTechnician> tech_list = new ArrayList<LabTechnician>();
        List<LTSpecialist> lt_list = new ArrayList<LTSpecialist>();

        List<Staff> staff_list = new ArrayList<Staff>();
        List<Pharmacist> pharma_list = new ArrayList<Pharmacist>();
        List<CanteenSupervisor> canteen_list = new ArrayList<CanteenSupervisor>();
        List<Accountant> accountant_list = new ArrayList<Accountant>();

        // Creating 100 persons
        for(int i=0;i<n;i++)
        {
            Person p = new Person();
            person_list.add(p);
        }
        System.out.println(n+" persons created successfully.\n");

        // Creating doctor/nurse/technician/staff from persons
        for(int i=0;i<n;i++)
        {
            Person p = person_list.get(i);
            LocalDate todayDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(p.start_date, todayDate);
            int choice = Math.abs(rand.nextInt()%4);

            if (choice == 0) // creating doctor
            {
                if (diff >= 420) // 14 month criteria
                {
                    Doctor d = new Doctor(p);
                    doctor_list.add(d);
                }
            }
            else if (choice == 1) // creating nurse
            {
                if (diff >= 365) // 14 month criteria
                {
                    Nurse nu = new Nurse(p);
                    nurse_list.add(nu);
                }
            }
            else if (choice == 2) // creating technician
            {
                if (diff >= 720) // 2 years criteria
                {
                    LabTechnician l = new LabTechnician(p);
                    tech_list.add(l);
                }
            }
            else // creating staff
            {
                if (diff >= 180) // 6 month criteria
                {
                    Staff s = new Staff(p);
                    staff_list.add(s);
                }
            }
            
        }
        
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
        System.out.println(surgeon_list.size()+" surgeons created successfully.");

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

        // Creating icunurse and gnnurse from nurse
        for(int i=0;i<nurse_list.size();i++)
        {
            Nurse nu = nurse_list.get(i);
            int choice = Math.abs(rand.nextInt()%2);
            LocalDate todayDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(nu.start_nurse, todayDate);
            if (choice == 0) // creating icunurse
            {   
                if (diff>=365) // 1 year more experience than nurse
                {
                    ICUNurse icu = new ICUNurse(nu);
                    icu_nurse.add(icu);
                }
            }
            else // creating gnnurse
            {
                if (diff>=180) // 6 months more experience than nurse
                {
                    GNNurse gn = new GNNurse(nu);
                    gn_nurse.add(gn);
                }
            }
        }
        System.out.println(nurse_list.size()+" nurses created successfully.");
        System.out.println(icu_nurse.size()+" icu nurses created successfully.");
        System.out.println(gn_nurse.size()+" gn nurses created successfully.\n");

        // Creating LTSpecialist from lab_technician
        for(int i=0;i<tech_list.size();i++)
        {
            LabTechnician l = tech_list.get(i);
            LocalDate todayDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(l.start_labtech, todayDate);
            if (diff>=180) // 6 month criteria
            {
                LTSpecialist lt = new LTSpecialist(l);
                lt_list.add(lt);
            }
        }
        System.out.println(tech_list.size()+" lab technicians created successfully.");
        System.out.println(lt_list.size()+" lt technicians created successfully.\n");

        // Creating pharmacist, canteen specialist and accountant from staff
        for(int i=0;i<staff_list.size();i++)
        {
            Staff s = staff_list.get(i);
            int choice = Math.abs(rand.nextInt()%3);
            LocalDate todayDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(s.start_staff, todayDate);
            if (choice == 0) // creating pharmacist
            {
                if (diff>=365) // 1 year experience
                {
                    Pharmacist p = new Pharmacist(s);
                    pharma_list.add(p);
                }
            }
            else if (choice == 1) // creating canteen specialist
            {
                if (diff>=180) // 6 months experience
                {
                    CanteenSupervisor c = new CanteenSupervisor(s);
                    canteen_list.add(c);
                }
            }
            else // creating accountant
            {
                if (diff>=365) // 1 year experience
                {
                    Accountant a = new Accountant(s);
                    accountant_list.add(a);
                }
            }
        }
        System.out.println(staff_list.size()+" staff members created successfully.");
        System.out.println(pharma_list.size()+" pharmacists created successfully.");
        System.out.println(canteen_list.size()+" canteen staff members created successfully.");
        System.out.println(accountant_list.size()+" accountants created successfully.\n");

        // Printing Everything
        System.out.println("\nDoctors\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<doctor_list.size();i++)
        {
            Doctor d = doctor_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nSurgeons\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<surgeon_list.size();i++)
        {
            Surgeon s = surgeon_list.get(i);
            s.print();
            System.out.println("\n");
        }

        System.out.println("\nSpecialists\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<specialists.size();i++)
        {
            Specialist s = specialists.get(i);
            s.print();
            System.out.println("\n");
        }

        System.out.println("\nNurses\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<nurse_list.size();i++)
        {
            Nurse d = nurse_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nICUNurses\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<icu_nurse.size();i++)
        {
            ICUNurse d = icu_nurse.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nGNNurses\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<gn_nurse.size();i++)
        {
            GNNurse d = gn_nurse.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nLabTechnicians\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<tech_list.size();i++)
        {
            LabTechnician d = tech_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nLTSpecialists\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<lt_list.size();i++)
        {
            LTSpecialist d = lt_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nStaff\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<staff_list.size();i++)
        {
            Staff d = staff_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nPharmacists\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<pharma_list.size();i++)
        {
            Pharmacist d = pharma_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nCanteen Supervisors\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<canteen_list.size();i++)
        {
            CanteenSupervisor d = canteen_list.get(i);
            d.print();
            System.out.println("\n");
        }

        System.out.println("\nAccountants\n------------------------------------------------------------------------------------------------------");
        for(int i=0;i<accountant_list.size();i++)
        {
            Accountant d = accountant_list.get(i);
            d.print();
            System.out.println("\n");
        }

        // Searching and Printing experience
        System.out.print("----------------------------------------------------------------------------------------------\nLinear Search and ");
        int flag = Math.abs(rand.nextInt()%12);
        LocalDate todayDate = LocalDate.now();
        if (flag==0) // experience of doctor
        {
            int index = Math.abs(rand.nextInt()%doctor_list.size());
            Doctor d = doctor_list.get(index);
            System.out.println("Getting experience of doctor:\n"+"Name: " + d.firstName+" "+d.lastName+", id: "+d.docID);
            experience((int)(ChronoUnit.DAYS.between(d.start_Doctor, todayDate)));
        }
        else if (flag==1) // experience of surgeon
        {
            int index = Math.abs(rand.nextInt()%surgeon_list.size());
            Surgeon s = surgeon_list.get(index);
            System.out.println("Getting experience of surgeon:\n"+"Name: " + s.firstName+" "+s.lastName+", id: "+s.docID);
            experience((int)(ChronoUnit.DAYS.between(s.start_Surgeon, todayDate)));   
        }
        else if (flag==2)// experience of specialistc
        {
            int index = Math.abs(rand.nextInt()%specialists.size());
            Specialist s = specialists.get(index);
            System.out.println("Getting experience of specialistc:\n"+"Name: " + s.firstName+" "+s.lastName+", id: "+s.docID);
            experience((int)(ChronoUnit.DAYS.between(s.start_Specialist, todayDate)));  
        }
        else if (flag==3) // experience of nurse
        {
            int index = Math.abs(rand.nextInt()%nurse_list.size());
            Nurse nu = nurse_list.get(index);
            System.out.println("Getting experience of nurse:\n"+"Name: " + nu.firstName+" "+nu.lastName+", id: "+nu.nurse_id);
            experience((int)(ChronoUnit.DAYS.between(nu.start_nurse, todayDate)));
        }
        else if (flag==4) // experience of icu nurse
        {
            int index = Math.abs(rand.nextInt()%icu_nurse.size());
            ICUNurse nu = icu_nurse.get(index);
            System.out.println("Getting experience of icu nurse:\n"+"Name: " + nu.firstName+" "+nu.lastName+", id: "+nu.nurse_id);
            experience((int)(ChronoUnit.DAYS.between(nu.start_icu, todayDate)));
        }
        else if (flag==5)// experience of gn nurse
        {
            int index = Math.abs(rand.nextInt()%gn_nurse.size());
            GNNurse nu = gn_nurse.get(index);
            System.out.println("Getting experience of gn nurse:\n"+"Name: " + nu.firstName+" "+nu.lastName+", id: "+nu.nurse_id);
            experience((int)(ChronoUnit.DAYS.between(nu.start_gn, todayDate)));
        }
        else if (flag==6) // experience of lab technician
        {
            int index = Math.abs(rand.nextInt()%tech_list.size());
            LabTechnician d = tech_list.get(index);
            System.out.println("Getting experience of lab technician:\n"+"Name: " + d.firstName+" "+d.lastName+", id: "+d.tech_id);
            experience((int)(ChronoUnit.DAYS.between(d.start_labtech, todayDate)));
        }
        else if (flag==7) // experience of lt specialist
        {
            int index = Math.abs(rand.nextInt()%lt_list.size());
            LTSpecialist d = lt_list.get(index);
            System.out.println("Getting experience of lt specialist:\n"+"Name: " + d.firstName+" "+d.lastName+", id: "+d.tech_id);
            experience((int)(ChronoUnit.DAYS.between(d.start_lt, todayDate)));   
        }
        else if (flag==8)// experience of staff
        {
            int index = Math.abs(rand.nextInt()%staff_list.size());
            Staff s = staff_list.get(index);
            System.out.println("Getting experience of staff:\n"+"Name: " + s.firstName+" "+s.lastName+", id: "+s.staff_id);
            experience((int)(ChronoUnit.DAYS.between(s.start_staff, todayDate)));  
        }
        else if (flag==9) // experience of pharmacist
        {
            int index = Math.abs(rand.nextInt()%pharma_list.size());
            Pharmacist d = pharma_list.get(index);
            System.out.println("Getting experience of pharmacist:\n"+"Name: " + d.firstName+" "+d.lastName+", id: "+d.staff_id);
            experience((int)(ChronoUnit.DAYS.between(d.start_pharma, todayDate)));
        }
        else if (flag==10) // experience of canteen supervisor
        {
            int index = Math.abs(rand.nextInt()%canteen_list.size());
            CanteenSupervisor s = canteen_list.get(index);
            System.out.println("Getting experience of canteen supervisor:\n"+"Name: " + s.firstName+" "+s.lastName+", id: "+s.staff_id);
            experience((int)(ChronoUnit.DAYS.between(s.start_canteen, todayDate)));   
        }
        else // experience of accountant
        {
            int index = Math.abs(rand.nextInt()%accountant_list.size());
            Accountant s = accountant_list.get(index);
            System.out.println("Getting experience of accountant:\n"+"Name: " + s.firstName+" "+s.lastName+", id: "+s.staff_id);
            experience((int)(ChronoUnit.DAYS.between(s.start_accountant, todayDate)));  
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }  
}
