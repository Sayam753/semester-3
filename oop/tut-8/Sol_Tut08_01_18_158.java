import java.util.*;
import java.io.*;
import java.text.*;
// Code by Sayam Kumar
// Roll No S20180010158
// Tut8 problem 1
// Just compile and run: javac Sol_Tut08_01_18_158.java && java Sol_Tut08_01_18_158
// Every input is randomly generated

class Ram
{
    float land_area;
    Ram()
    {
        this.land_area = 20;
    }
}

class Suresh extends Ram
{
    float land_area;
    int cash;
    Suresh()
    {
        this.land_area = (super.land_area)/2;
        this.cash = 500000;   
    }
}


class Saritha extends Ram
{
    float land_area;
    int property_value;
    int cash;
    Saritha()
    {
        this.land_area = (super.land_area)/2;
        this.property_value = 10000000;
        this.cash = 8*50000; // 80% cash given from brother
    }

}

class Siva extends Suresh
{
    int cash;
    float land_area;
    Siva()
    {
        this.cash = (super.cash)/10; // 10% cash from father
        this.land_area = (super.land_area)/2;
    }
}

class Hari extends Suresh
{
    int cash;
    float land_area;
    Hari()
    {
        this.cash = (super.cash)/10; // 10% cash from father
        this.land_area = (super.land_area)/2;
    }
}

class daughter1 extends Saritha
{
    int cash;
    float land_area;
    daughter1()
    {
        this.cash = (super.property_value + super.cash)/2;
        this.land_area = (super.land_area)/2;
    }
}

class daughter2 extends Saritha
{
    int cash;
    float land_area;
    daughter2()
    {
        this.cash = (super.property_value + super.cash)/2;
        this.land_area = (super.land_area)/2;
    }
}

public class Sol_Tut08_01_18_158
{

    public static void main(String[] args)
    {
        Random rand = new Random();
        Siva siva = new Siva();
        Hari hari = new Hari();

        daughter1 d1 = new daughter1();
        daughter2 d2 = new daughter2();

        siva.cash += d1.cash;
        siva.land_area += d1.land_area;

        hari.cash += d2.cash;
        hari.land_area += d2.land_area;

        System.out.println("For Siva:" + "\n" + "Total Cash: " + siva.cash + "\n" + "Total Property: " + siva.land_area + " acres");
        System.out.println("\nFor Hari:" + "\n" + "Total Cash: " + hari.cash + "\n" + "Total Property: " + hari.land_area + " acres");
    }  
}
