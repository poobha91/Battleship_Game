/*
@Author: POOBHARATHII AL BALAKRISHNAN, NITYANANTHEN AL MUNIANDY, VIKNESVARAN AL RAVICHANDRAN
@Title: Battleship game in Java
 */
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import Controller.BattleshipController;
import Model.ModelBattleship;
public class BattleshipGame
{
    //Main function
    public static void main(String[] args)
    {
        /*
        create object for ModelBattleship class to use variables
        create BattleshipController to a method to start the game.
         */
        ModelBattleship mb = new ModelBattleship();
        BattleshipController bc = new BattleshipController();
        try //exception handling to handle any errors
        {
            do //to repeat the game, There is no end for the Game, unless close the windows
            {
                System.out.println("\n\nPlease press any key to start new game");
                String startGame = mb.input.next();
                if(!startGame.isBlank())
                {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                    bc.startGame(); //call the function to start the game
                }
                else
                {
                    System.out.println("Exit from Game!.");
                }
            }while(true);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage()); //if any errors, Game will end
        }
    }
}
