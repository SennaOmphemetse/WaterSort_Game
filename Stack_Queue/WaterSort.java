import java.util.Random;
import java.util.Scanner;

public class WaterSort
{
    private static StackAsMyArrayList bottles[] = new StackAsMyArrayList[5];
    
    static StackAsMyArrayList<Character> bottle1 = new StackAsMyArrayList<Character>();
    static StackAsMyArrayList<Character> bottle2 = new StackAsMyArrayList<Character>();
    static StackAsMyArrayList<Character> bottle3 = new StackAsMyArrayList<Character>();
    static StackAsMyArrayList<Character> bottle4 = new StackAsMyArrayList<Character>();
    static StackAsMyArrayList<Character> bottle5 = new StackAsMyArrayList<Character>();
    
    

    static Character red= new Character('r');
    static Character blue = new Character('b');
    static Character green= new Character('g');

    public static void main(String[] args)
    {
        System.out.printf("\f");
        
        bottles[0] = bottle1;
        bottles[1] = bottle2;
        bottles[2] = bottle3;
        bottles[3] = bottle4;
        bottles[4] = bottle5;
        
        FillUp();
        Fillbot();
        
        showAll();
        PlayGame();
        
        System.out.printf("\n");
        showAll();
    }
    
    //Fill bottles
    public static void FillUp()
    {
        for(int k = 0; k < 4; k++)
        {
            
            bottle1.push(green);
            bottle2.push(red);
            bottle3.push(blue);
        }

    }
    //playing Game
    public static void PlayGame()
    {
         Scanner inputRem = new Scanner(System.in);
         Scanner inputAd = new Scanner(System.in);
         Scanner decide = new Scanner(System.in);
         
         //Mix items to make the un-uniform
         int popItem, pushItem;
         
         do
         {
            
             System.out.printf("\n\nEnter Bottle number(0,1,2,3,4) to remove item: ");
             popItem = inputRem.nextInt();
             
             //To make sure that the ink in the bottle doesn't spill because color(ink) allowed in the bottle covers 4 slots only
             //other than that return the color to its original place
             
             if(bottles[popItem].getStackSize() > 0)
             {
                 bottles[popItem].peek();
                 
                 System.out.printf("\nEnter Bottle number(0,1,2,3,4) to add item: ");
                 pushItem = inputAd.nextInt();
                 
                 if(bottles[pushItem].getStackSize() > 0 && bottles[pushItem].getStackSize() < 4)
                 {
                     bottles[pushItem].peek();
                     if(bottles[popItem].peek() == bottles[pushItem].peek())
                     {
                         bottles[pushItem].push(bottles[popItem].pop());
                     }
                     else
                     {
                         System.out.printf("\n------------------------------------------------");
                         System.out.printf("\nColor not added, Colors are not the same");
                         System.out.printf("\n------------------------------------------------");
                     }
                 }
                 else if(bottles[pushItem].getStackSize() == 0)
                 {
                     bottles[pushItem].push(bottles[popItem].pop());
                 }
                 else
                 {
                     System.out.printf("\n------------------------------------------------");
                     System.out.printf("\nBottle is full");
                     System.out.printf("\n------------------------------------------------");
                 }
             }
             else
             {
                 System.out.printf("\n------------------------------------------------");
                 System.out.printf("\nBottle is empty!!!!");
                 System.out.printf("\n------------------------------------------------");
             }
             
             //print color
             System.out.println("\nUpdated Bottles:");
             System.out.println("\n------------------------------------------------");
             showAll();
             System.out.println("\n------------------------------------------------");
             CheckUniform();
             
         }
         while(solved() == false);
    }
    
    
    public static void Fillbot()
    {
        int moves = 0;
        int max = 4;
    
        int source = 0; // number of bottle to pour FROM
        int target = 0;
         
        Random randomNum = new Random();
        
        while (moves<=100) // use 100 valid moves to mix bottles
        {
             // get source bottle
             source = randomNum.nextInt(max);
             
             while (bottles[source].getStackSize() ==0)// source is empty
             {
                 source = randomNum.nextInt(max);
             }
             // get target bottle
             target =  randomNum.nextInt(max);
      
             while (bottles[target].getStackSize() == 4)// target is full
             {
                 target = randomNum.nextInt(max);
             }
             // pour from source to target
             bottles[target].push(bottles[source].pop());
     
             // increment valid moves
             moves++;
      
        }
    
    }
    
    public static void CheckUniform()
    {
        for(int i=0; i<=4;i++)
        {
            if(bottles[i].getStackSize()== 4 && bottles[i].checkStackUniform() == true)
            {
                System.out.println("-------------------");
                System.out.println(bottles[i] + ": Completed.");
                System.out.println("-------------------");
            }
        }
    }
    
    public static boolean solved()
    {
        Scanner decide = new Scanner(System.in);
        boolean ch = false;
        
         while((bottles[0].getStackSize()== 0 || bottles[0].getStackSize() == 4 && bottles[0].checkStackUniform() == true) &&
         (bottles[1].getStackSize()== 0 || bottles[1].getStackSize() == 4 && bottles[1].checkStackUniform() == true) &&
         (bottles[2].getStackSize()== 0 || bottles[2].getStackSize() == 4 && bottles[2].checkStackUniform() == true) &&
         (bottles[3].getStackSize()== 0 || bottles[3].getStackSize() == 4 && bottles[3].checkStackUniform() == true) && 
         (bottles[4].getStackSize()== 0 || bottles[4].getStackSize() == 4 && bottles[4].checkStackUniform() == true))
         {
             System.out.println("Game complete......Congradulation!!!!!");
             return true;
             
         }
         return ch;
    }
    
    //Show data
    public static void showAll()
    {
        for(int i=0;i<bottles.length;i++)
        {
            System.out.println("Bottle " + i + ":\t" + bottles[i].toString());
        }
    }
}