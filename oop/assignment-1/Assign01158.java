import java.util.Random;
class Assign01158{
    public static void main(String argv[])
    {
        // 1. generating 20 random circles
        Circle[] circle = new Circle[20];
        for(int i=0;i<20;i++)
        {
            Circle c = new Circle();
            circle[i] = c;
        }

        // 2. area of each circle is calculated in constructor
        System.out.println("Area of each circle: ");
        for(int i=0;i<20;i++)
        {
            System.out.println("Area of " + i + " circle " + circle[i].area + " with radius " + circle[i].radius);
        }

        // 3. finding concentric circles
        System.out.println("\nFinding concentric circles: ");
        int countConcentric = 0;
        for(int i=0;i<20;i++)
        {
            for(int j=i+1;j<20;j++)
            {
                if (circle[i].center.x == circle[j].center.x && circle[i].center.y == circle[j].center.y)
                {
                    System.out.println("Pair found for " + circle[i].center.x + " " + circle[i].center.y + " with radii " + circle[i].radius + " and " + circle[j].radius);
                    countConcentric = countConcentric + 1;
                }
            }
        }
        System.out.println("No of pairs of concentric circles are " + countConcentric + ".");

        // 4. finding circles inside grid of 0, 50
        System.out.println("\nFinding circles inside grid of 0, 50: ");
        int completelyInside = 0;
        for(int i=0;i<20;i++)
        {
            int xCoordinate = circle[i].center.x;
            int yCoordinate = circle[i].center.y;
            float r = circle[i].radius;
            if ((xCoordinate + r <= 50) && (xCoordinate - r >=0) && (yCoordinate + r <= 50) && (yCoordinate - r >= 0))
            {
                System.out.println("Circle found with center " + xCoordinate + " " + yCoordinate + " radius " + r);
                completelyInside  = completelyInside + 1;
            }
        }
        System.out.println("No of circles completely inside the grid are " + completelyInside + ".");
    }
}

class Point{
    int x,y;
    Point()
    {
        Random rand = new Random();
        this.x = 10 + Math.abs((rand.nextInt() % 41)); 
        this.y = 10 + Math.abs((rand.nextInt() % 41));
    }
}

class Color{
    int r,g,b;
    Color()
    {
        Random rand = new Random();
        this.r = Math.abs((rand.nextInt() % 256)); 
        this.g = Math.abs((rand.nextInt() % 256)); 
        this.b = Math.abs((rand.nextInt() % 256)); 
    }
}

class Circle{
    Point center;
    float radius;
    Color c;
    boolean isFilled;
    float area;
    Circle()
    {
        Random rand = new Random();
        this.center = new Point();
        this.radius = (15*rand.nextFloat()) + 5;
        this.c = new Color();
        if (this.c.r == 0 && this.c.g == 0 && this.c.b == 0)
        {
            this.isFilled = false;
        }
        else
        {
            this.isFilled = true;
        }
        this.area = (float)(3.14*((float)this.radius)*((float)this.radius));
    }
}