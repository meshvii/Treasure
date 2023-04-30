/* This class is for handling maps for the game
It includes methods that creates new maps and has methods to update them. */ 

public class Map{
    private char[][] mapVariable; //character array to hold the map location

    private int heroRow; //variable that holds row postion of the Hero
    private int heroCol; //variable that holds column position of the Hero

    /*constructor that intializes mapVariable with '.' as per the arguements passed which 
    includes number of rows and columns that a map will have*/
    public Map(int rows,int cols){ 
        mapVariable=new char[rows][cols]; //intialized with the size provided as arguement
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++)
                mapVariable[i][j]='.'; //sets every field with '.'which is a floor in our game
        }
    }

    /*constructor that intializes the mapVariable with the array containing map data passed
    as an arguement*/
    //this method won't work if the number of columns is not the same for every row   
    public Map(char[][] mapTemplate){
        if(mapTemplate!=null){
            //setting the length of mapVariable as  per the length of array passed    
            mapVariable= new char [mapTemplate.length][mapTemplate[0].length]; 
            for(int i=0;i<mapTemplate.length;i++){
                for(int j=0;j<mapTemplate[i].length;j++)
                    mapVariable[i][j]=mapTemplate[i][j];
            } 
        }       
    }

    /* this method returns the formatted layout of the map
    this will contain string which holds the data of the map itself*/
    public String toString(){ 
        String output="";//variable to hold the formatted layout of map
        for(int i=0;i<mapVariable.length;i++){
            for(int j=0;j<mapVariable[i].length;j++)
                output=output+mapVariable[i][j];
            output+="\n";
        }
        return output;
    }

    /*This method is used to add Walls to the map */
    public void addWalls(){ 
        //loop to add walls to the top and bottom
        for(int i=0;i<mapVariable.length;i+=(mapVariable.length-1)){ 
            for(int j=0;j<mapVariable[i].length;j++)
                mapVariable[i][j]='X';
        }
        //loop to add walls to the sides
        for(int j=0;j<mapVariable[0].length;j+=(mapVariable[0].length-1)){ 
            for(int i=0;i<mapVariable.length;i++)
                mapVariable[i][j]='X';
        }
    }

    //method to check whether the given entries provided by the user can be added in the map or not
    public boolean checkMap(int row, int col, char entry){  
        //condition to check whether the instance has mapVariable variable initialized or not
        if(mapVariable!=null){
            //condtion to check the entries provided by the user are valid or not because this might give an error
            if(row<mapVariable.length && col<mapVariable[row].length && row>=0 && col>=0){
                mapVariable[row][col]=entry;
                return true;
            }
            else{
                System.out.println("Array Out Of Bounds");
                return false;
            }
        }
        else{
            System.out.println("Map doesn't exist");
            return false;
        } 
    }

    //main method to check the working of map methods 
    public static void main(String[] args){
        Map tempMap=new Map(3,4);
        tempMap.addWalls();
        System.out.println(tempMap.toString());
    }

    /*accessor method to use the mapVariable that holds the map data */
    public char[][] getMap(){
        return mapVariable;
    }

    //this method is used to add items(as integers) on the map at random locations
    public void addItems(int numOfItems){

        //loop that traverses through no of items and assigns random locations by Gamedata function
        for(int i=0;i<numOfItems;i++){
            //variables that hold the value returned by Gamedata function
            //it passes coordinates range that is not occupied by walls
            int rowAllow=GameData.randomRoll(1,mapVariable.length-1);
            int colAllow=GameData.randomRoll(1,mapVariable[0].length-1); 

            char character=Character.forDigit(i,10); //assigns character as per nth item(0,1,..)
            mapVariable[rowAllow][colAllow]=character;//adds the character as per the coordinates in the mapVariable
        }
    }

    /* This method is used to set Hero's location.
    If the hero already exists then it will update heroCol and heroRow.
    And If not it generate random locations(coordinates) for the Hero*/
    public void initheroLocation(){
        int count=0; //variable to store whether H exists, it increments if H exists
        /*this loop checks whether 'H' exists inside the map or not, if exists it will
        coordiantes to heroRow and heroCol */
        for(int i=0;i<mapVariable.length;i++){
            for(int j=0;j<mapVariable[0].length;j++)
                if(mapVariable[i][j]=='H'){
                    count++;
                    heroRow=i;
                    heroCol=j;
                }
        }

        boolean flag=true; //boolean variable that is true when random location assigned to 'H' is not a 'X' 

        /*this while loop will run as long as a valid coordinates are
        assigned to 'H' */
        while(count==0 && flag){
            //using Game Data's random to assign coordinates to 'H'
            heroRow=GameData.randomRoll(1,mapVariable.length-1);
            heroCol=GameData.randomRoll(1,mapVariable[0].length-1);
            //to check if the the values assigned are not 'X'(walls)
            if(mapVariable[heroRow][heroCol]=='.'){
                mapVariable[heroRow][heroCol]='H';
                flag=false;

            }
            //else condition to re-assign values to heroRow and heroCol
            else{
                heroRow=GameData.randomRoll(1,mapVariable.length-1);
                heroCol=GameData.randomRoll(1,mapVariable[0].length-1);
            }
        }

    }

    /*this method is used to check whether the passed arguements are not the location for the walls               */
    public boolean isValidMove(int row,int col){
        //returns true if the passed coordinates are not for the walls
        if(row >0 && col>0 && 
        row<mapVariable.length-1 && col<mapVariable[0].length-1 
        && mapVariable[row][col]!='X')
            return true;
        else
            return false;
    }

    /*this method is used to move 'H' through the whole map
    it also returns the value of the that we moved the 'H' to*/
    private char applyMove(int endRow, int endCol){
        char temp='\0'; //variable to hold the value of the tile we moved to
        mapVariable[endRow][endCol]=temp; //assigning Hero to passed arguements
        heroRow=endRow; //assigning hero's coordinates with passed arguements
        heroCol=endCol;
        mapVariable[heroRow][heroCol]='H'; //assigning 'H' to the tile we moved on
        // System.out.println(heroRow+" "+heroCol);
        return temp; //returning character of the tile where 'H' moved to
    }

    /*this method is used to move 'H' as per user's choice.
    It checks whether user's choice is valid move or not.
    If the choice is valid then it will move the 'H' to desired 
    location and return the tile user moved on and to change it to '.'.
    It will also update heroRow and heroCol
     */
    public char processInput(int direction){
        /*two copies of hero coordinates one to pass modify according user's choice and
        other to assign the tile with '.', if the move is valid or not
         */
        int tempRow=heroRow,tempCol=heroCol;
        int temp2Row=heroRow,temp2Col=heroCol;
        //changes the coordinates according user's choice
        if(direction==0)
            tempRow-=1;
        else if(direction==1)
            tempCol+=1;
        else if(direction==2)
            tempRow+=1;
        else if(direction==3)
            tempCol-=1;

        char toReturn='\0'; //character to hold the value of tile we moved on
        //calling isValidMove to check for our move and then assign the coordiantes to 'H'
        if(isValidMove(tempRow,tempCol)){
            toReturn=applyMove(tempRow,tempCol);
            mapVariable[temp2Row][temp2Col]='.'; //to change hero's position to '.', after he moved to another tile
        }
        else
            mapVariable[temp2Row][temp2Col]='H'; //assigns 'H' to the coordinates, if the move is not valid

        return toReturn; //returns the character that we moved to
    }
}
