import java.io.*;
import java.util.Scanner;
/*This class is used to read data from the files
   that will be helpful for our game.
   This will also help us to intialize various class instances*/
public class FileLoader{
    
    // this method accepts data from a file regarding map and assigns that data to a Map instance
    public static Map loadMap(String fileName){
        Map tempMap=null; //map object that is to be intialized with data extracted from file
        try{
            FileReader fileRead=new FileReader(fileName); //fileReader to read data from the file
            BufferedReader inFile=new BufferedReader(fileRead);
            Scanner lineReader= new Scanner(inFile);
            int rowNum=lineReader.nextInt(); //variable to hold the value of number of rows
            int colNum=lineReader.nextInt(); //variable to hold the value of number of columns
            char[][]tempMapArr=new char[rowNum][colNum]; //temporary array to store the data from the file
            String tempString=""; //string variable to store lines that are read from the file
            /*this loop reads throught he file line by line and
               stores it inside string which is assigned to the map variable */
            for(int i=0;i<rowNum;i++){
                tempString=lineReader.next(); 
                for(int j=0;j<colNum;j++)
                    tempMapArr[i][j]=tempString.charAt(j);
            }
            tempMap=new Map(tempMapArr); //intialize the map by calling it's constructor
            inFile.close(); //to close the file

        }
        //catch method to handle any exceptions thrown by the try block eg. IOexceptions
        catch(IOException e){
            System.out.println("FILE NOT FOUND");
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return tempMap; //returns the variable that has all the data from the file
    }
    
    //main method that has file name where the map data exists and it is used to call the loadMap method
    //this method is also used to check the output
    public static void main(String[] args){
        String fileName="MapFile.txt";
        Map tempMap=loadMap(fileName); 
        System.out.println(tempMap.toString());
        String heroFile="HeroFile.txt";
        Hero tempHero=loadHero(heroFile);
        System.out.println(tempHero.toString());
    }

    /*this method is used to assign data from the file and
    assign to a Hero instance*/
    public static Hero loadHero(String heroFile){
        Hero heroObject=null; //variable that will be assigned data from the file
        try{
            FileReader fileRead=new FileReader(heroFile); //fileReader to read data from file
            BufferedReader inFile=new BufferedReader(fileRead);
            Scanner lineReader= new Scanner(inFile);
            String heroName=lineReader.next();
            int tempCurrentHP=lineReader.nextInt(); //variable to hold hero's currentHP
            int tempInventory=lineReader.nextInt(); //variable to hold the total number of items in hero's inventory

            String[] itemList=new String[tempInventory]; //variable to hold names of items inside Hero's inventory

            /*loop that traverses through the file
               to read items' names and assign it to itemList variable*/
            for(int i=0;i<tempInventory;i++){
                itemList[i]=lineReader.next();
                itemList[i]+=" ";
                itemList[i]+=lineReader.next();
            }
            //to intilaize hero's instance with the data that is read from the file
            heroObject=new Hero(heroName,tempCurrentHP); 

            Item[] items=new Item[tempInventory]; //items array to assign items' instances with data read from file

            /*loop that assigns Items instances with item's names
               and adds it to hero inventory*/
            for(int i=0;i<tempInventory;i++){ 
                items[i]=new Item(itemList[i]);
                heroObject.collectItem(items[i]);
            }

            inFile.close(); //to close the file
        }
        //catch method to handle any exceptions thrown by the try block eg. IOexceptions
        catch(IOException e){
            System.out.println("File not Found");
            System.out.println(e.toString());
            e.printStackTrace();
        }
        //catch method to handle any exceptions thrown block other than IOExceptions
        catch(Exception e){
            System.out.println("File not properly formatted");
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return heroObject; ////returns the variable that has all the data from the file
    } 
}
