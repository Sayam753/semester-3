import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// Code by Sayam Kumar
// Roll No S20180010158
// Just compile and run: javac Assign10158.java && java Assign10158
// Every input is randomly generated

class GatedCommunity
{
    String name_of_society;
    String community_id;

    GatedCommunity()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting name_of_society
        String[] names={"Gokuldham", "Kalandi Welfare", "Gyan Shri Welfare", "Silver Living", "Sai Bliss", "Fit Zone", "All India", "Helping Care Society"};
        int len = names.length;
        this.name_of_society = names[Math.abs(rand.nextInt()%len)];

        // Setting community_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.community_id = for_id.toString();
    }

}

class SocialInfra extends GatedCommunity
{
    String manager;
    int years_left;

    SocialInfra()
    {
        Random rand = new Random();

        // Setting manager
        String[] names = {"naman", "rahul", "rajat", "sayam", "raman", "abhishek", "vicky", "rajkumar", "vijay", "virat", "dhoni", "yuvraj", "palak", "tanvi", "anjali", "pawan", "simar", "simran", "vasudha", "vanshika", "gurlovleen", "gauri", "dikhsa", "disha", "alia", "sridevi", "madhuri", "rekha", "smriti", "jaspinder", "preet", "priyanka"};
        int len = names.length;
        this.manager = names[Math.abs(rand.nextInt()%len)];

        // Setting years_left
        this.years_left = Math.abs(rand.nextInt()%5)+1;
    }
}

class Events extends SocialInfra
{
    String name;
    String id;
    LocalDate event_date;
    String location;
    int number_of_participants;
    Dweller organiser;

    Events(List<Dweller> dweller_list)
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting name
        String[] names = {"yoga morning", "freshers party", "annual marathon", "society cricket match", "sociesociety football match"};
        int len = names.length;
        this.name = names[Math.abs(rand.nextInt()%len)];

        // Setting id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.id = for_id.toString();

        // Setting event_date
        StringBuilder for_date = new StringBuilder();
        int year = 2010 + Math.abs((rand.nextInt() % 8));
        int month = 1 + Math.abs((rand.nextInt() % 12)), date;
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
        this.event_date = LocalDate.parse(for_date.toString(), dateFormat);

        // Setting location
        this.location = "Society Auditorium";

        // Setting number_of_participants
        this.number_of_participants = Math.abs(rand.nextInt()%11)+90; // between 10 and 100

        // Setting organiser
        int index = Math.abs(rand.nextInt()%dweller_list.size());
        this.organiser = dweller_list.get(index);
    }

    void printDetails()
    {
        System.out.println("Event Name: " + this.name + ", Event Id: " + this.id);
        System.out.println("Date: "+this.event_date);
        System.out.println("Number of participants: "+this.number_of_participants);
        System.out.println("Location: "+this.location+"\n");
    }
}

class Festivals extends SocialInfra
{
    String name;
    String id;
    LocalDate festival_date;
    Dweller organiser;

    Festivals(List<Dweller> dweller_list)
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting name
        String[] names = {"holi", "diwali", "dussehra", "christman", "lohri", "pongal", "ganesh chaturthi", "janamasthmi"};
        int len = names.length;
        this.name = names[Math.abs(rand.nextInt()%len)];

        // Setting id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.id = for_id.toString();

        // Setting festival_date
        StringBuilder for_date = new StringBuilder();
        int year = 2010 + Math.abs((rand.nextInt() % 8));
        int month = 1 + Math.abs((rand.nextInt() % 12)), date;
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
        this.festival_date = LocalDate.parse(for_date.toString(), dateFormat);

        // Setting organiser
        int index = Math.abs(rand.nextInt()%dweller_list.size());
        this.organiser = dweller_list.get(index);
    }   

    void printDetails()
    {
        System.out.println("Festival Name: "+this.name);
        System.out.println("Festival ID: "+this.id);
        System.out.println("Festival Date: "+this.festival_date);
        System.out.println("Organizer: "+this.organiser.first_name+"\n");
    }
}

class Market extends GatedCommunity
{
    String market_id;
    String location;

    Market()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting market_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.market_id = for_id.toString();

        // Setting location
        int number = Math.abs(rand.nextInt()%5)+1;
        this.location = Integer.toString(number)+" km from society";
    }
}

class Shops extends Market
{
    Dweller owner;
    String type_of_shop;
    int items_available;
    float discount;
    int reputation_score;
    
    Shops(List<Dweller> dweller_list)
    {
        Random rand = new Random();

        // Setting owner
        int index = Math.abs(rand.nextInt()%dweller_list.size());
        this.owner = dweller_list.get(index);

        // Setting type_of_shop
        String[] names = {"Grocery", "Utensils", "Clothes Shop", "Fast Food", "Electronic Appliances", "Photographers", "Diary"};
        int len = names.length;
        this.type_of_shop = names[Math.abs(rand.nextInt()%len)];

        // Setting items_available
        this.items_available = Math.abs(rand.nextInt()%50);

        // Setting discount
        this.discount = Math.abs(rand.nextFloat()*10)+5;

        // Setting reputation_score
        this.reputation_score = Math.abs(rand.nextInt()%100)+1;
    }

    void printDetails()
    {
        System.out.println("Owner: "+this.owner.first_name+" "+this.owner.last_name);
        System.out.println("Type of Shop: "+this.type_of_shop);
        System.out.println("Items Available: "+this.items_available);
        System.out.println("Discount Offered: "+this.discount+"%\n");
    }
}

class Services extends Market
{
    Dweller service_provider;
    String service_given;
    int reputation_score;

    Services(List<Dweller> dweller_list)
    {
        Random rand = new Random();
        
        // Setting service_provider
        int index = Math.abs(rand.nextInt()%dweller_list.size());
        this.service_provider = dweller_list.get(index);

        // Setting service_given
        String[] names = {"Plumber", "Electrician", "Water Supplier", "Guard", "Maid", "Baby Sitter"};
        int len = names.length;
        this.service_given = names[Math.abs(rand.nextInt()%len)];

        // Setting reputation_score
        this.reputation_score = Math.abs(rand.nextInt()%100)+1;
    }

    void printDetails()
    {
        System.out.println("Service Provider: "+this.service_provider.first_name+" "+this.service_provider.last_name);
        System.out.println("Service Given: "+this.service_given);
        System.out.println("Reputation Score: "+this.reputation_score+"\n");
    }

}

class Blocks extends GatedCommunity
{
    String block_id;
    String manager;
    static String[] facilities = {"24 by 7 water supply", "Porch location", "School, market, hospital, college, parks and malls nearby", "All Parking", "Immediate help provided"};

    Blocks()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting block_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.block_id = for_id.toString();

        // Setting manager
        String[] names = {"naman", "rahul", "rajat", "sayam", "raman", "abhishek", "vicky", "rajkumar", "vijay", "virat", "dhoni", "yuvraj", "palak", "tanvi", "anjali", "pawan", "simar", "simran", "vasudha", "vanshika", "gurlovleen", "gauri", "dikhsa", "disha", "alia", "sridevi", "madhuri", "rekha", "smriti", "jaspinder", "preet", "priyanka"};
        int len = names.length;
        this.manager = names[Math.abs(rand.nextInt()%len)];
    }
}

class PentHouse extends Blocks
{
    String penthouse_id;
    int penthouse_number;
    int cost;
    String location;
    String design;

    PentHouse()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting penthouse_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.penthouse_id = for_id.toString();

        // Setting penthouse_number
        this.penthouse_number = Math.abs(rand.nextInt()%100)+1;

        // Setting cost
        this.cost = Math.abs(rand.nextInt()%100001)+100000;

        // Setting location
        this.location = "Near the market";

        // Setting design
        this.design = "Italian Design";
    }

    void printDetails()
    {
        System.out.println("Penthouse ID: "+this.penthouse_id+", House number: "+this.penthouse_number);
        System.out.println("Cost: "+this.cost);
        System.out.println("Design: "+this.design+"\n");
    }
}

class Villas extends Blocks
{
    String villa_id;
    int villa_number;
    int cost;
    String location;

    Villas()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting villa_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        // Setting villa_id
        this.villa_id = for_id.toString();

        // Setting villa_number
        this.villa_number = Math.abs(rand.nextInt()%100)+1;

        // Setting cost
        this.cost = Math.abs(rand.nextInt()%100001)+300000;

        // Setting location
        this.location = "Near the school";
    }

    void printDetails()
    {
        System.out.println("Villa ID: "+this.villa_id+", House number: "+this.villa_number);
        System.out.println("Cost: "+this.cost);
        System.out.println("Location: "+this.location+"\n");
    }

}

class Flats extends Blocks
{
    String flat_id; 
    int flat_number;
    int bhk;
    int cost;
    String location;

    Flats()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting flat_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.flat_id = for_id.toString();

        // Setting flat_number
        this.flat_number = Math.abs(rand.nextInt()%100)+1;

        // Setting bhk
        this.bhk = Math.abs(rand.nextInt()%5)+1;

        // Setting cost
        this.cost = Math.abs(rand.nextInt()%100001)+50000;

        // Setting location
        this.location = "Near the hospital";
    }

    void printDetails()
    {
        System.out.println("Flat ID: "+this.flat_id+", House number: "+this.flat_number);
        System.out.println("Cost: "+this.cost);
        System.out.println("BHK: "+this.bhk+"\n");
    }
}

class Dweller
{
    String dweller_id;
    String first_name;
    String last_name;
    PentHouse penthouses;
    Villas villas;
    Flats flats;
    LocalDate dob;
    String email;
    String phone_number;
    int flag;
    String gender;
    String announcement;

    Dweller(List<PentHouse> p, List<Villas> v, List<Flats> f, String g)
    {
        Random rand = new Random();
        String[] names = {"naman", "rahul", "rajat", "sayam", "raman", "abhishek", "vicky", "rajkumar", "vijay", "virat", "dhoni", "yuvraj", "palak", "tanvi", "anjali", "pawan", "simar", "simran", "vasudha", "vanshika", "gurlovleen", "gauri", "dikhsa", "disha", "alia", "sridevi", "madhuri", "rekha", "smriti", "jaspinder", "preet", "priyanka"};
        String[] surnames = { "kumar","bansal","goyal","tiwari","mishra","malhotra","anand","khare","talari","patil","pandey","nad","singla","singh","kaur","arora","saurav","roy","khan","samrat"}; 
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting dweller_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.dweller_id = for_id.toString();

        // Setting first_name
        int number_of_names = names.length;
        this.first_name = names[Math.abs((rand.nextInt() % number_of_names))];

        // Setting last_name
        int number_of_surnames = surnames.length;
        this.last_name = surnames[Math.abs((rand.nextInt() % number_of_surnames))];

        // Setting houses
        int choice = Math.abs(rand.nextInt()%7)+1;
        this.flag = choice; // to capture the choice
        if (choice == 1 || choice == 4 || choice == 6 || choice == 7)
        {
            int index = Math.abs(rand.nextInt()%p.size());
            this.penthouses = p.get(index);
        }
        if (choice == 2 || choice == 4 || choice == 5 || choice == 7)
        {
            int index = Math.abs(rand.nextInt()%v.size());
            this.villas = v.get(index);
        }
        if (choice == 3 || choice == 5 || choice == 6 || choice == 7)
        {
            int index = Math.abs(rand.nextInt()%f.size());
            this.flats = f.get(index);
        }

        // Setting dob
        StringBuilder for_date = new StringBuilder();
        int year = 2010 + Math.abs((rand.nextInt() % 8));
        int month = 1 + Math.abs((rand.nextInt() % 12)), date;
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
        this.dob = LocalDate.parse(for_date.toString(), dateFormat);

        // Setting email
        StringBuilder for_email = new StringBuilder();
        for_email.append(this.first_name);
        for_email.append(this.last_name);
        for_email.append(Integer.toString(Math.abs((rand.nextInt() % 10))) + "@gmail.com");
        this.email = for_email.toString(); //  example - sayamkumar4@gmail.com

        // Setting phone_number
        StringBuilder for_phone = new StringBuilder();
        for_phone.append(Integer.toString(1 + Math.abs((rand.nextInt() % 9))));
        for(int i=1;i<=9;i++)
        {
            for_phone.append(Integer.toString(Math.abs((rand.nextInt() % 10))));
        } 
        this.phone_number = for_phone.toString();

        // Setting gender
        this.gender = g;
    }

    void printDetails()
    {
        System.out.println("Name: "+this.first_name+" "+this.last_name);
        System.out.println("Email: "+this.email);
        System.out.println("Phone Number: "+this.phone_number);
        System.out.println("Gender: "+this.gender);
        int f = this.flag;

        if (f == 1 || f == 4 || f == 6 || f == 7)
        {
            System.out.println("Dweller has penthouse of number: "+this.penthouses.penthouse_number);
        }
        if (f == 2 || f == 4 || f == 5 || f == 7)
        {
            System.out.println("Dweller has villa of number: "+this.villas.villa_number);
        }
        if (f == 3 || f == 5 || f == 6 || f == 7)
        {
            System.out.println("Dweller has flat of number: "+this.flats.flat_number);
        }
    }
}

class Education extends GatedCommunity
{
    String school_name;
    String school_id;
    int reputation_score;
    String location;
    String facilities;

    Education()
    {
        Random rand = new Random();
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // Setting school_name
        String[] names = {"Atam", "BCM", "Govt", "KVM"};
        int len = names.length;
        this.school_name = names[Math.abs(rand.nextInt()%len)]+" Public School";

        // Setting school_id
        StringBuilder for_id = new StringBuilder();
        for(int i=0;i<6;i++)
        {
            int index = Math.abs(rand.nextInt()%charSet.length()); //  for selecting the char
            for_id.append(charSet.charAt(index));
        }
        this.school_id = for_id.toString();

        // Setting reputation_score
        this.reputation_score = Math.abs(rand.nextInt()%51)+50;

        // Setting location
        this.location = "Nearby hospital";

        // Setting facilities
        this.facilities = "Smart classes, Encouragement in sports and Overall development";
    }
}

public class Assign10158
{
    static void printDwellers(List<Dweller> dweller_list)
    {
        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all dwellers");
        for(int i=0;i<dweller_list.size();i++)
        {
            dweller_list.get(i).printDetails();
            System.out.println("");
        }
    }

    public static void main(String[] args)
    {
        // Question 1
        Random rand = new Random();
        int n = 20; // Creating twenty instances of each child class
        List<PentHouse> penthouse_list = new ArrayList<PentHouse>();
        List<Villas> villas_list = new ArrayList<Villas>();
        List<Flats> flats_list = new ArrayList<Flats>();

        List<Events> events_list = new ArrayList<Events>();
        List<Festivals> festival_list = new ArrayList<Festivals>();

        List<Shops> shops_list = new ArrayList<Shops>();
        List<Services> services_list = new ArrayList<Services>();

        List<Dweller> dweller_list = new ArrayList<Dweller>();

        System.out.println("Printing details of all penthouses");
        // Creating penthouses
        for(int i=0;i<n;i++)
        {
            PentHouse p = new PentHouse();
            p.printDetails();
            penthouse_list.add(p);
        }

        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all villas");
        // Creating villas
        for(int i=0;i<n;i++)
        {
            Villas v = new Villas();
            v.printDetails();
            villas_list.add(v);
        }

        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all flats");
        // Creating flats
        for(int i=0;i<n;i++)
        {
            Flats f = new Flats();
            f.printDetails();
            flats_list.add(f);
        }

        // Creating 200 dwellers
        // 50% Males
        for(int i=0;i<100;i++)
        {
            Dweller d = new Dweller(penthouse_list, villas_list, flats_list, "Male");
            dweller_list.add(d);
        }

        // 50% Females
        for(int i=0;i<100;i++)
        {
            Dweller d = new Dweller(penthouse_list, villas_list, flats_list, "Female");
            dweller_list.add(d);
        }

        // Creating events
        n=6;
        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all events");
        for(int i=0;i<n;i++)
        {
            Events e = new Events(dweller_list);
            e.printDetails();
            events_list.add(e);
        }

        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all festivals");
        // Creating festivals
        for(int i=0;i<n;i++)
        {
            Festivals f = new Festivals(dweller_list);
            f.printDetails();
            festival_list.add(f);
        }

        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all shops");
        // Creating shops
        for(int i=0;i<n;i++)
        {
            Shops s = new Shops(dweller_list);
            s.printDetails();
            shops_list.add(s);
        }

        System.out.println("\n---------------------------------------------------------------------\nPrinting details of all services");
        // Creating services
        for(int i=0;i<n;i++)
        {
            Services s = new Services(dweller_list);
            s.printDetails();
            services_list.add(s);
        }

        // Question 2: 50 Male and 50 Female
        printDwellers(dweller_list);

        // Question 3
        int type_of_announcement = Math.abs(rand.nextInt()%2);
        String[] announcements = {"Today discount is 20%", "New Mall is opening tomorrow", "Buy 1 and get 2 free", "Diwali Sale is coming soon", "Shop will be closed today"}; 
        if(type_of_announcement == 0) // general announcement
        {
            int index = Math.abs(rand.nextInt()%announcements.length);
            System.out.println("\n---------------------------------------------------------------------\nGeneral Announcement: "+announcements[index]+" to all dwellers");
            for(int i=0;i<dweller_list.size();i++)
            {
                dweller_list.get(i).announcement = announcements[index];
            }
            for(int i=0;i<dweller_list.size();i++)
            {
                dweller_list.get(i).printDetails();
                System.out.println("Announcement: "+announcements[index]+"\n");
            }
        }
        else
        {
            int choice = Math.abs(rand.nextInt()%7)+1;
            int index = Math.abs(rand.nextInt()%announcements.length);
            System.out.print("\n---------------------------------------------------------------------\nSpecific Announcement: "+announcements[index]+" to ");
            if (choice == 1 || choice == 4 || choice == 6 || choice == 7)
            {
                System.out.print("dwellers with penthouses\n"); // to penthouses
                for(int i=0;i<dweller_list.size();i++)
                {
                    int f = dweller_list.get(i).flag;
                    if (f == 1 || f == 4 || f == 6 || f==7)
                    {
                        dweller_list.get(i).printDetails();
                        System.out.println("Announcement: "+announcements[index]+"\n");
                    }
                }
            }
            if (choice == 2 || choice == 4 || choice == 5 || choice == 7)
            {
                System.out.print("dwellers with villas\n"); // to villas
                for(int i=0;i<dweller_list.size();i++)
                {
                    int f = dweller_list.get(i).flag;
                    if (f == 2 || f == 4 || f == 5 || f==7)
                    {
                        dweller_list.get(i).printDetails();
                        System.out.println("Announcement: "+announcements[index]+"\n");
                    }
                }
            }
            if (choice == 3 || choice == 5 || choice == 6 || choice == 7)
            {
                System.out.print("dwellers with flats\n"); // to flats
                for(int i=0;i<dweller_list.size();i++)
                {
                    int f = dweller_list.get(i).flag;
                    if (f == 3 || f == 5 || f == 6 || f==7)
                    {
                        dweller_list.get(i).printDetails();
                        System.out.println("Announcement: "+announcements[index]+"\n");
                    }
                }
            }
        }

        // Question 4
        System.out.println("\n---------------------------------------------------------------------\nFor new stack holder, I created class Education inherited from Gated Comunity.");
        System.out.println("The education is important in today's world. It cannot be neglected.\n");
        // creating new instance

        Education edu = new Education();
        System.out.println("School Name: "+edu.school_name);
        System.out.println("School Id: "+edu.school_id);
        System.out.println("Location: "+edu.location);
        System.out.println("Facilities: "+edu.facilities);
        System.out.println("Reputation Score: "+edu.reputation_score);

        // Used virtual methods for printing details for each class
    }
}
