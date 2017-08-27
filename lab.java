//Kanika Saini 2016047 lab3
import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
abstract class animal{
    protected int x;
    protected int y;
    protected int time;
    protected String name;
    protected int turns;
    public animal(){}
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public String getName()
    {
        return this.name;
    }
    public animal(int x, int y, int time, String name)
    {
        this.x = x;
        this.y = y;
        this.time = time;
        this.name= name;
        turns=0;
    }
    public void setCordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getTime()
    {
        return this.time;
    }
    public abstract int getHealth();
    public int getDistance(int a, int b)
    {
        return (int)(Math.sqrt((x-a)*(x-a)+ (y-b)*(y-b)));
    }
     public void updateTime(int m, int t)
    {
        Random rand = new Random();
        time = rand.nextInt(t)+ m+1; 
    }
    public abstract void turn(boolean fh, boolean fc, grassland g1, grassland g2, herbivore h1, herbivore h2 , carnivore c1, carnivore c2);
}

class herbivore extends animal{
    private static int hunger;
    private static int health;
    public herbivore(int x, int y, int time, String name)
    {
        super(x, y, time, name);
    }
    public herbivore(){
        super();
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    public void setHunger(int hunger)
    {
        this.hunger= hunger;
    }
    public int getHealth()
    {
        return this.health;
    }
    public void turn(boolean fh, boolean fc, grassland g1, grassland g2, herbivore h1, herbivore h2, carnivore c1, carnivore c2)
    {
        int s;
        Random rand;
        int slope;
        if(fc==false)
        {
            if(g1.inside(x,y))
            {
                turns=0;
                 if(x-g2.getx()==0)
                            {
                                if(g2.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g2.gety())/ (x-g2.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                // go towards g2 by 5 units
            }
            else if(g2.inside(x,y))
            {
                turns=0;
                //go towards g1 by 5 units
                 if(x-g1.getx()==0)
                            {
                                if(g1.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g1.gety())/ (x-g1.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
            }
            else{
                turns++;
                if(turns>7)
                health-=5;
                rand = new Random();
                s = rand.nextInt(100)+1;
               if(s<=50)
               {
                   if(getDistance(g1.getx(), g1.gety()) < getDistance(g2.getx(), g2.gety()) )
                   {
                       
                        if(x-g1.getx()==0)
                            {
                                if(g1.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g1.gety())/ (x-g1.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                   }
                   else
                   {
                        if(x-g2.getx()==0)
                            {
                                if(g2.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g2.gety())/ (x-g2.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                   }
                   // go to nearest grassland 5 units
               }   
               else
               {
                   health-=25;
               }
            }
        }
        else
        {
            if(!g1.inside(x, y) && !g2.inside(x, y))
            {
                turns++;
                if(turns>7)
                health-=5;
                rand = new Random();
                s= rand.nextInt(100)+1;
                if(s>5)
                {
                    s= rand.nextInt(100)+1;
                    if(s<=65)
                    {
                        if(getDistance(g1.getx(), g1.gety()) < getDistance(g2.getx(), g2.gety()) )
                        {
                            if(x-g1.getx()==0)
                            {
                                if(g1.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g1.gety())/ (x-g1.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        else
                        {
                            if(x-g2.getx()==0)
                            {
                                if(g2.gety()>y)
                                    y+=5;
                                else
                                    y-=5;
                            }
                            else
                            {
                                slope=(y-g2.gety())/ (x-g2.getx());
                                x= 5/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 5*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        //move 5 units towards grassland
                    }
                    else
                    {
                        if(getDistance(c1.getx(), c1.gety()) < getDistance(c2.getx(), c2.gety()) )
                        {
                            if(x-c1.getx()==0)
                            {
                                if(c1.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                                slope=(y-c1.gety())/ (x-c1.getx());
                                x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        else
                        {
                            if(x-c2.getx()==0)
                            {
                                if(c2.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                                slope=(y-c2.gety())/ (x-c2.getx());
                                x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                    //move 4 units away from a carnivore
                    }
                }
            }
            else{
                grassland g;
                turns=0;
                if(g1.inside(x, y))
                     g = g1;
                else 
                     g= g2;
                if(g.getGrass()>=hunger)
                {
                    rand = new Random();
                    s= rand.nextInt(100)+1;
                    if(s<=90)
                    {
                        g.decreaseGrass(hunger);
                        health+=health/2;
                    }
                    else
                    {
                        s= rand.nextInt(100)+1;
                        if(s<=50)
                        {

                            if(getDistance(c1.getx(), c1.gety()) < getDistance(c2.getx(), c2.gety()) )
                        {
                            if(x-c1.getx()==0)
                            {
                                if(c1.gety()>y)
                                    y+=2;
                                else
                                    y-=2;
                            }
                            else
                            {
                                slope=(y-c1.gety())/ (x-c1.getx());
                                x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        else
                        {
                            if(x-c2.getx()==0)
                            {
                                if(c2.gety()>y)
                                    y+=2;
                                else
                                    y-=2;
                            }
                            else
                            {
                                slope=(y-c2.gety())/ (x-c2.getx());
                                x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                            //move 2 units away from the carnivore
                        }
                        else
                        {
                            if(getDistance(g1.getx(), g1.gety()) < getDistance(g2.getx(), g2.gety()) )
                            {
                                if(x-g1.getx()==0)
                            {
                                if(g1.gety()>y)
                                    y+=3;
                                else
                                    y-=3;
                            }
                            else
                            {
                                slope=(y-g1.gety())/ (x-g1.getx());
                                x= 3/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 3*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                            }
                            else
                            {
                                if(x-g2.getx()==0)
                            {
                                if(g2.gety()>y)
                                    y+=3;
                                else
                                    y-=3;
                            }
                            else
                            {
                                slope=(y-g2.gety())/ (x-g2.getx());
                                x= 3/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 3*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                            }
                            //move 3 units nearest grassland
                        }
                    }
                }
                else{
                    rand = new Random();
                    s= rand.nextInt(100)+1;
                    if(s<=20 && g.getGrass()>0)
                    {
                        g.decreaseGrass(hunger);
                        health+=health/5;
                    }
                    else
                    {
                        s= rand.nextInt(100)+1;
                        if(s<=70)
                        {
                            if(getDistance(c1.getx(), c1.gety()) < getDistance(c2.getx(), c2.gety()) )
                        {
                            if(x-c1.getx()==0)
                            {
                                if(c1.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                            slope=(y-c1.gety())/ (x-c1.getx());
                            x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                            y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        else
                        {
                            if(x-c2.getx()==0)
                            {
                                if(c2.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                                slope=(y-c2.gety())/(x-c2.getx());
                                x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                        }
                        }
                        // move 4 units ways from carnivvore
                        else
                        {
                            if(getDistance(g1.getx(), g1.gety()) < getDistance(g2.getx(), g2.gety()) )
                            {
                                
                                if(x-g1.getx()==0)
                            {
                                if(g1.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                                slope=(y-g1.gety())/ (x-g1.getx());
                                x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                            }
                            else
                            {
                                 if(x-g2.getx()==0)
                            {
                                if(g2.gety()>y)
                                    y+=4;
                                else
                                    y-=4;
                            }
                            else
                            {
                                slope=(y-g2.gety())/ (x-g2.getx());
                                x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                                y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                            }
                            }
                            //move 2 units to grassland
                        }
                    }
                }
            }
        }
    }
   
}
class carnivore extends animal{
    private static int health;
    public carnivore(int x, int y, int time, String name)
    {
        super(x, y, time, name);
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    public carnivore()
    {
        super();
    }
    public int getHealth()
    {
        return this.health;
    }
    public void turn( boolean fh, boolean fc, grassland g1, grassland g2, herbivore h1, herbivore h2, carnivore c1, carnivore c2)
    {
        int s;
        Random rand = new Random();
        int slope;
        if(h1.getDistance(x,y)<=5 || h2.getDistance(x, y)<=5)
        {
            turns=0;
        }
        else
            turns++;
        if(turns>7)
            health-=6;
        if(fh!=false)
        {
            int d1= h1.getDistance(x, y);
            int d2= h2.getDistance(x, y);
            if(d1<=1 || d2<=1)
            {
                if(d1<d2)
                {
                    if(d1<=1)
                    {
                        health+= 2*h1.getHealth()/3;
                        h1.setHealth(0);
                    }
                }
                else
                {
                    if(d2<=1)
                    {
                        health+= 2*h2.getHealth()/3;
                        h2.setHealth(0);
                    }
                }
            }
            else
            {
                if(g1.inside(x, y)|| g2.inside(x,y))
                {
                    s= rand.nextInt(100)+1;
                    if(s<=92)
                    {
                        if(getDistance(h1.getx(), h1.gety()) < getDistance(h2.getx(), h2.gety()) )
                        {
                            slope=(y-h1.gety())/ (x-h1.getx());
                            x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                            y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                        }
                        else
                        {
                            slope=(y-h2.gety())/ (x-h2.getx());
                            x= 4/(int)Math.sqrt(1+ slope*slope)+x ;
                            y= 4*slope/(int)Math.sqrt(1+ slope*slope) + y;
                        }
                        //move 4 towards herbivore
                    }
                    else
                    {
                        health-=30;
                    }
                }
                else
                {
                    s= rand.nextInt(100)+1;
                    if(s>25)
                    {
                        if(getDistance(h1.getx(), h1.gety()) < getDistance(h2.getx(), h2.gety()) )
                        {
                            slope=(y-h1.gety())/ (x-h1.getx());
                            x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                            y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                        }
                        else
                        {
                            slope=(y-h2.gety())/ (x-h2.getx());
                            x= 2/(int)Math.sqrt(1+ slope*slope)+x ;
                            y= 2*slope/(int)Math.sqrt(1+ slope*slope) + y;
                        }
                        //move 2 units towards herbivore
                    }
                    else
                    {
                        health-=60;
                    }
                }
            }
        }
    }    
}
class grassland{
    private int x;
    private int y;
    private int radius;
    private int grassLeft;
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public grassland(int x, int y, int radius, int grassLeft)
    {
        this.x= x;
        this.y= y;
        this.radius= radius;
        this.grassLeft= grassLeft;
    }
    public boolean inside(int a, int b)
    {
        if(a<= x+radius && a>=x-radius && b<=y+radius && b>=y-radius)
            return true;
        else return false;
    }
    public int getGrass()
    {
        return this.grassLeft;
    }
    public void decreaseGrass(int n)
    {
        grassLeft-=n;
    }
}
class sort implements Comparator<animal> {
    @Override
    public int compare(animal one, animal two)
    {
        if(one.getTime()<two.getTime())
            return -1;
        else if(one.getTime()>two.getTime())
            return 1;
        else
        {
            if(one.getHealth()> two.getHealth())
                return -1;
            else if(one.getHealth()< two.getHealth())
                return 1;
            else
            {
                if(one.getClass()==two.getClass())
                {
                    if(one.getDistance(0, 0)< two.getDistance(0, 0))
                        return -1;
                    else 
                        return 1;
                }
                else{
                    String n1=one.getClass().getName();
                    String n2= two.getClass().getName();
                    if(n1.equals("herbivore"))
                        return -1;
                    else 
                        return 1;
                }
            }
        }
    }
}
class world
{
    static grassland  g1;
    static grassland  g2;
    static herbivore  h1 = new herbivore();
    static herbivore  h2;
    static carnivore  c1 = new carnivore();
    static carnivore c2;
	public static void main(String[] args) throws Exception
        {
            BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
            String line;
            String[] lar; 
            System.out.println("Enter total time final for simulation:");
            line= inp.readLine();
            int t= Integer.parseInt(line);
            int m=0;
            System.out.println("Enter x, y center, radius and grass available for  First Grassland:");
            line= inp.readLine();
            lar= line.trim().split("\\s+");
            g1= new grassland(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), Integer.parseInt(lar[3]) );
            System.out.println("Enter x, y center, radius and grass available for  First Grassland:");
            line= inp.readLine();
            lar= line.trim().split("\\s+");
            g2= new grassland(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), Integer.parseInt(lar[3]) );
            System.out.println("Enter health and grass capacity for herbivores:");
            line = inp.readLine();
            lar= line.trim().split("\\s+");
            h1.setHealth(Integer.parseInt(lar[0]));
            h1.setHunger(Integer.parseInt(lar[1]));
            System.out.println("Enter x, y position and timestamp for First Herbivore:");
            line = inp.readLine();
            lar= line.trim().split("\\s+");
            h1= new herbivore(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), "First Herbivore");
            if(Integer.parseInt(lar[2])> m)
                m= Integer.parseInt(lar[2]);
            System.out.println("Enter x, y position and timestamp for Second Herbivore:");
            line = inp.readLine();
            lar= line.trim().split("\\s+");
            h2= new herbivore(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), "Second Herbivore");
            if(Integer.parseInt(lar[2])> m)
                m= Integer.parseInt(lar[2]);
            System.out.println("Enter health for carnivores:");
            line = inp.readLine();
            c1.setHealth(Integer.parseInt(line));
            System.out.println("Enter x, y position and timestamp for First Carnivore:");
            line = inp.readLine();
            lar= line.trim().split("\\s+");
            c1= new carnivore(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), "First Carnivore");
            if(Integer.parseInt(lar[2])> m)
                m= Integer.parseInt(lar[2]);
            System.out.println("Enter x, y position and timestamp for Second Carnivore:");
            line = inp.readLine();
            lar= line.trim().split("\\s+");
            c2 = new carnivore(Integer.parseInt(lar[0]), Integer.parseInt(lar[1]), Integer.parseInt(lar[2]), "Second Carnivore");
            if(Integer.parseInt(lar[2])> m)
                m= Integer.parseInt(lar[2]);
            System.out.println("The simulation begins-");
            sort comparator= new sort();
            PriorityQueue<animal> q= new PriorityQueue<animal>(4,comparator);
            q.add(h1);
            q.add(h2);
            q.add(c1);
            q.add(c2);
            int i;
            i=0;
            boolean fh, fc;
            fh= fc= true;
            while(i<t && q.size()>0)
            {
                animal ob = q.remove();
                if(ob.getHealth()>0)
                {System.out.println("It is "+ob.getName()+".");
                if(h1.getHealth()<=0 && h2.getHealth()<=0)
                    fh=false;
                if(c1.getHealth()<=0 && c2.getHealth()<=0)
                    fc=false;
                ob.turn(fh, fc,g1, g2, h1, h2, c1, c2);
                if(ob.getHealth()>0)
                {
                    System.out.println("Its health after taking turn is "+ ob.getHealth());
                    ob.updateTime(m, t);
                    if(ob.getTime()!=t-1 )
                    {
                        q.add(ob);
                        if(ob.getTime()>m)
                            m= ob.getTime();      
                    }
                }
                else
                    System.out.println("It is dead.");
                }   
                i++;             
            }           
        }
}