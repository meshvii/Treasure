
//Hero Class that handles data related to player's journey 
public class Hero{
    private String name; //variable to hold Hero's Name
    private int currentHP; //variable to hold player's HP
    private final int STARTING_HP = 8; //intial player's HP

    private final int MIN_DAMAGE = 1; //variable that stores minimum damage
    private final int MAX_DAMAGE = 4; //variable that stores maximum damage

    // Phase 3
    private ItemList inventory; //variable of ItemList to hold the data of items collected by the Hero

    //Hero constructor to intialize the values inside the class
    public Hero(){
        currentHP = STARTING_HP;//to intialize player's HP with STARTING_HP value
        name = GameData.getRandomName(); //to assign hero's name by usng GameData randomName method
        inventory = new ItemList();//assigning an ItemList object to inventory variable
    }

    //accessor method to get the name of the Hero
    public String getName(){
        return name;
    }

    //accessor method to get the HP of the Hero
    public int getHP(){
        return currentHP;
    }

    //method to print the data of Hero's Class
    public String toString(){
        String s = "-----------------\n";
        s += "Current Hero: "+ name + "\n";
        s += "Current HP: " + currentHP + "\n";
        s += "-----------------\n";
        return s;
    }

    // Phase 3
    //this methods accept an item object and adds it to the inventory variable
    public void collectItem(Item i){
        inventory.addItem(i);
    }

    //this method returns a string containing the list of items collected
    public String getItemsCollected(){
        return inventory.toString();
    }

    // Phase 5
    // k. Roll Damage
    //this method returns the value that randomRoll function returns
    public int damageDealt(){
        return GameData.randomRoll(MIN_DAMAGE, MAX_DAMAGE);
    }

    // l. bonus
    //this method returns the no. of items that player has to calculate the damage
    public int damageBonus(){
        return inventory.itemCount();
    }

    //this method returns the damage that the player has dealt with
    public void takeDamage(int damageAmount){
        currentHP -= damageAmount;
    }

    /*constructor that assigns Hero's name and currentHP with the
    arguements passed*/
    public Hero(String name, int currentHP){
        this.name=name;
        this.currentHP=currentHP;
        inventory = new ItemList();
    }
}