/*
@Author: POOBHARATHII AL BALAKRISHNAN, NITYANANTHEN AL MUNIANDY, VIKNESVARAN AL RAVICHANDRAN
@Title: Battleship game in Java
 */
package Model;
import java.util.Scanner;

public class ModelBattleship
{
    /*declare global variables in Model package so with import of the
    class and object can use the variable throughout the program
     */
    public static int oceanRows = 10;
    public static int oceanCols = 10;
    public static int player , computer, attack = 1;;
    public static String[][] tenBytenGrid = new String[oceanRows][oceanCols];
    public static Scanner input = new Scanner(System.in);
    public static Scanner inGameinput = new Scanner(System.in);
}
