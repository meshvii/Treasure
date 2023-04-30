import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
/*this class is the driver of the game. It contains
 instances of Hero and Map. This helps to operate the movement of the hero as per
 user's input*/
public class Game
{
    //this method will create the instances of Map and Hero
    public static void main(String[] args){

        Map mapObject=FileLoader.loadMap("MapFile.txt");//to create Map instance by reading data from the file
        Hero heroObject=FileLoader.loadHero("HeroFile.txt");//to create Hero instance by reading data from the file
        System.out.println(mapObject.toString());
        System.out.println(heroObject.toString());
        System.out.println(heroObject.getItemsCollected());
        
     }
     
     /*this method returns an integer as per the key pressed by the user
        different values are returned for up,dow,right and left */
    public static int getInput(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
            return 0;
        else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
            return 1;
        else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
            return 2;
        else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
            return 3;
        else
            return -1;
    }
}
