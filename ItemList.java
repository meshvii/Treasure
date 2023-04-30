//this class hold every  item that has been collected by the player throughout his game
public class ItemList{

    private int currentItemCount; //variable to hold the no. of items that the player has collected during his game
    private Item[] items;//variable of an Item[] to hold item objects 
    
    /*constructor that sets the length of items array */
    public ItemList(){
        items = new Item[100];
    }

    /*accessor method for itemCount*/
    public int itemCount(){
        return currentItemCount;
    }

    /* method that accepts an item object and assign it to items variable  */
    public void addItem(Item i){
        items[currentItemCount]= i; //assigns item to items array and increments count variable
        currentItemCount++;
    }

    /* this method returns the string with names of items that the player has collected                    */
    public String toString(){
        String output = "";
        for(int i = 0; i < currentItemCount; i++){
            output += items[i].toString() + "\n";
        }
        return output;
    }

}