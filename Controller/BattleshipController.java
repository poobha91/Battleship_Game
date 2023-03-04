/*
@Author: POOBHARATHII AL BALAKRISHNAN, NITYANANTHEN AL MUNIANDY, VIKNESVARAN AL RAVICHANDRAN
@Title: Battleship game in Java
 */
package Controller;
import View.BattleshipView;
import Model.ModelBattleship;
public class BattleshipController
{
    /*
        create object for ModelBattleship class to use variables
        create BattleshipView for method that contain views.

        this object create in Global private object, because this object can use
        within this whole class BattleshipController
    */
    private static ModelBattleship mb = new ModelBattleship();
    private static BattleshipView bv = new BattleshipView();
    public static void startGame()
    {
        //Welcome screen
        System.out.println("===========================================================================");
        System.out.println("                      Welcome to Battle Ship game                          ");
        System.out.println("===========================================================================");
        System.out.println("\n\n\t\tOcean is empty now.");
        //creating empty map
        bv.createMap();
        //set PLayer ships
        setPlayerShips();
        //set Computer ships
        setComputerShips();
        System.out.println("===========================================================================");
        System.out.println("            All ships has been deployed. Battle Game is starting           ");
        System.out.println("===========================================================================");

        //beginGame
        int game = 1;
        while(mb.player != 0 && mb.computer != 0)
        {
            System.out.println("===========================================================================");
            System.out.println("                     Game started. Round : "+game +"                       ");
            System.out.println("===========================================================================");
            beginPlayerAttack();
            beginComputerAttack();
            System.out.println("===========================================================================");
            System.out.println("                          end of Round : "+game +"                         ");
            System.out.println("===========================================================================");
            game++;
            bv.printMap();
            //Game Score
            bv.gameScore();
            System.out.println("\n\n\n\n");
        }
        //game start
    }
    //set ships for Player
    private static void setPlayerShips()
    {
        mb.player = 5; //total 5 ships
        System.out.println("Set your ships in Ocean");
        //deploying ships
        for(int i=1; i <= mb.player; )
        {
            int x = 10;
            int y = 10;
            try //to detect any error throws by wrong input
            {
                System.out.println("Enter ship no." + i + " coordinates (X coordinate <SPACE> Y ) eg.'3 4' : ");
                String[] xyCoordinate = mb.inGameinput.nextLine().split(" ");
                 x = Integer.parseInt(xyCoordinate[0]);
                 y = Integer.parseInt(xyCoordinate[1]);
            }
            catch (Exception e)
            {
                System.out.println("Please enter only numbers and with single space. eg. '3<SPACE>4' = '3 4'");
            }
            //System.out.println(x + " - X");
            //System.out.println(y + " - Y");
            //check the coordinate if available and assign if available
            if(x == 10 || y == 10)
            {
                System.out.println("Something wrong please try again, enter numbers between 0-9");
            }
            else if((x >= 0 && x < mb.oceanRows) && (y >= 0 && y < mb.oceanCols) && (mb.tenBytenGrid[y][x] == "   "))
            {
                mb.tenBytenGrid[y][x] = " @ ";
                i++;
            }
            else if ((x >= 0 && x < mb.oceanRows) && (y >= 0 && y < mb.oceanCols) && (mb.tenBytenGrid[y][x] == " @ "))
            {
                System.out.println("There is ship in this coordinate already");
            }
            else if((x < 0 || x >= mb.oceanRows) || (y < 0 || y >= mb.oceanCols))
            {
                System.out.println("Out of boundary. X-"+ x + " & Y-" + y + " are not valid coordinate");
            }
            bv.printMap();
        }
    }
    //set ships for Computer
    private static void setComputerShips()
    {
        System.out.println("Set Computer ships in Ocean");
        mb.computer =5;
        for (int i=1; i <= mb.computer; )
        {
            int x = ((int)(Math.random() * 10)); //to get random number from 0-9
            int y = ((int)(Math.random() * 10)); //to get random number from 0-9

            if((x >= 0 && x < mb.oceanRows) && (y >= 0 && y < mb.oceanCols) && (mb.tenBytenGrid[y][x] == "   "))
            {
                mb.tenBytenGrid[y][x] = " $ ";
                System.out.println(i + " ship set in Ocean by Computer");
                i++;
            }
        }
        bv.printMap();
    }
    //Player turn to Attack the computer ships
    private static void beginPlayerAttack()
    {
        //Player turn
        System.out.println("\tYour turn to attack the ship");
        int x=11, y=11; //assign not valid coordinate, so no mistake in attack
        do
        {
            try //Exception handling to capture any error on input from user.
            {
                System.out.println("Enter coordinates to attack [no. of attack : " + mb.attack + " ]  (X coordinate <SPACE> Y ) eg.'3 4' : ");
                String[] xyCoordinate = mb.inGameinput.nextLine().split(" ");
                x = Integer.parseInt(xyCoordinate[0]);
                y = Integer.parseInt(xyCoordinate[1]);
            }
            catch (Exception e)
            {
                e.getMessage();
                //System.out.println("Please enter only numbers and with single space. eg. '3<SPACE>4' = '3 4'");
            }

            if((x >= 0 && x < mb.oceanRows) && (y >= 0 && y < mb.oceanCols)) //if valid coordinate
            {
                if(mb.tenBytenGrid[y][x] == " $ ") //guess correctly
                {
                    System.out.println("Boom! Successfully attacked the Computer ship.");
                    mb.tenBytenGrid[y][x] = " X ";
                    --mb.computer;
                    mb.attack++;
                }
                else if(mb.tenBytenGrid[y][x] == " X ")
                {
                    System.out.println("Loose a turn, already attacked this coordinate!!!");
                    mb.attack++;
                }
                else if (mb.tenBytenGrid[y][x] == " @ ") //guess own ship
                {
                    System.out.println("Ooops, are you blind?! You attacked own ship! ");
                    mb.tenBytenGrid[y][x] = " = ";
                    --mb.player;
                    mb.attack++;
                }
                else if(mb.tenBytenGrid[y][x] == " = ")
                {
                    System.out.println("Confirmed that you're blind. Still attacking at own ship!!");
                    System.out.println("You loose a turn");
                    mb.attack++;
                }
                else if (mb.tenBytenGrid[y][x] == "   " || mb.tenBytenGrid[y][x] == " ! ") //guess wrongly
                {
                    System.out.println("Ooops, you missed, please try again");
                    mb.tenBytenGrid[y][x] = " ! ";
                    mb.attack++;
                }
            }
            else if((x < 0 || x >= mb.oceanRows) || (y < 0 || y >= mb.oceanCols)) //invalid coordinate
            {
                if(x ==11 || y == 11)
                {
                    System.out.println("Please enter only numbers and with single space. eg. '3<SPACE>4' = '3 4'");
                }
                else
                {
                    System.out.println("Where you attack la?!. X-"+ x + " & Y-" + y + " are not valid coordinate");
                }

            }
        }while((x < 0 || x >= mb.oceanRows) || (y < 0 || y >= mb.oceanCols));//loop until enter correct coordinate
    }
    //Computer turn to attack
    private static void beginComputerAttack()
    {
        System.out.println("\tComputer turn to attack the ship");
        int x=11, y=11; //assign not valid coordinate, so no mistake in attack
        boolean loop = false;
        do
        {
            do //this do..while for not repeating attack at same coordinate
            {
                x = ((int)(Math.random() * 10)); //to get random number from 0-9
                y = ((int)(Math.random() * 10)); //to get random number from 0-9
                if(mb.tenBytenGrid[y][x] == " # " || mb.tenBytenGrid[y][x] == " * " || mb.tenBytenGrid[y][x] == " / ")
                {
                    loop = true;
                }
                else
                {
                    loop = false;
                }
            }
            while(loop);

            if((x >= 0 && x < mb.oceanRows) && (y >= 0 && y < mb.oceanCols)) //if valid coordinate
            {
                if(mb.tenBytenGrid[y][x] == " @ ") //guess correctly
                {
                    mb.tenBytenGrid[y][x] = " # ";
                    --mb.player;
                    System.out.println("Boom! Successfully attacked the Player ship.");
                }
                else if (mb.tenBytenGrid[y][x] == " $ ") //guess own ship
                {
                    mb.tenBytenGrid[y][x] = " * ";
                    --mb.computer;
                    System.out.println("Ooops, Computer are blind, attacked own ship! ");
                }
                else if (mb.tenBytenGrid[y][x] == "   ") //guess wrongly
                {
                    mb.tenBytenGrid[y][x] = " / ";
                    System.out.println("Ooops, Computer missed, please try again");
                }
            }
            else if((x < 0 || x >= mb.oceanRows) || (y < 0 || y >= mb.oceanCols)) //invalid coordinate
            {
                System.out.println(" X-"+ x + " & Y-" + y + " are not valid coordinate");
            }
        }while((x < 0 || x >= mb.oceanRows) || (y < 0 || y >= mb.oceanCols)); //loop until enter correct coordinate
        System.out.println(" Computer Attack: X-"+ x + " & Y-" + y);
    }
}
