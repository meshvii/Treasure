
// //Item class that has data related to items that player acquires thoughout his game
public class Item{
    private String itemName; //variable to hold the name of item

    //constructor to assign Item Class variables
    public Item(){
        itemName = GameData.getRandomItemName();//this assigns item's name by using GameData getRandomItemName method
    }

    //this method returns the name of the item
    public String toString(){ 
        return itemName;
    }

    /*constructor that assigns Item's name with the value 
    passed as a arguement */
    public Item(String itemName){
        this.itemName=itemName;
    }
}