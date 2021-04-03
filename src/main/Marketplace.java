package main;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This Class that keeps a record for what Games are being offered by what seller along with the functionality related
 * to the MarketPlace
 *
 */
public class Marketplace {
    // Boolean to account for Auction Sale
    public boolean auctionSale = false;
    // Unique ID associated with each game being offered by a Seller
    private int uid = 0;
    // Keeps a record for Seller against their offerings
    private HashMap<String, ArrayList<Game>> gamesOnSale;

    /**
     * Creates a brand new Market with no active Sellers
     *
     */
    public Marketplace() {
        this.gamesOnSale = new HashMap<String, ArrayList<Game>>();

    }

    /**
     * Constructs market from previously pre-existing Market with Sellers and Games
     *
     * @param auctionSale True if Sale was going on, false otherwise
     * @param market a hashmap containing all the sellers and their offerings
     */
    public Marketplace(boolean auctionSale, HashMap<String, ArrayList<Game>> market){
        this.auctionSale = auctionSale;
        this.gamesOnSale = market;
    }

    /**
     * Gets the current auction sale Status for the Market
     *
     * @return true if Sale is offered false otherwise
     */
    public boolean getAuctionSale(){
        return this.auctionSale;
    }

    /**
     * Toggle the Auction Sale in the market
     *
     */
    public void toggleSale(){
        this.auctionSale = !this.auctionSale;
    }

    /**
     * Helper to get all the games currently being Sold
     *
     * @return The Hashmap of the Market, key: UserName value: ArrayList<Game> they have up for sale
     */
    public HashMap<String, ArrayList<Game>> getGamesOnSale() {
        return this.gamesOnSale;
    }


    /**
     * Helper to increment the unique ID for the Game
     *
     */
    public void incrementUID(){
        this.uid ++;
    }

    /**
     * Helper to get the unique ID associated with the Game
     *
     * @return the integer value of the UniqueID
     */
    public int getUid(){
        return this.uid;
    }


    /**
     * Checks if the Seller already exists or not, if the Seller does not exist  then the
     * new Seller is added to the market
     *
     * @param seller User to be added as a Seller
     */
    public void addNewSeller(String seller){
        // Add Seller and an empty ArrayList if the Seller does not exist
        if(!this.checkSellerExist(seller)){
            ArrayList<Game> gamesOnSale = new ArrayList<Game>();
            this.gamesOnSale.put(seller, gamesOnSale);

            System.out.println("Seller: "+ seller +" added to the market");
        }
        // Seller already exists in our market
        else{
            System.out.println("Seller: "+ seller+" already exists in the market");
        }
    }

/*
    //**
     * Adds the Game for the Seller in the market
     *
     * @param seller User adding the Game to their offerings
     * @param game The game to be added to the Seller's offering
     *//*
    public void addGameForSeller(String seller, Game game){
        // Check if the Seller exists and add the Game to their list of offering
        if(this.checkSellerExist(seller)){
            String addGame = game.getName();

            //ArrayList<Game> currOffering = this.getMyOfferings(seller);

            // check if the user is Selling the game or has the game up for sale if not then add the game
            if(!this.checkSellerSellingGame(seller, addGame) && !this.gameToBeUpCheck(seller, addGame)){
                this.addForTomMar(seller, game);
                System.out.println(game.getName() + " has now been added to the seller's offering");
            }
            else {
                System.out.println("Seller: "+ seller+" is already selling " + game.getName());
            }
        }
        // Seller currently does not exist in our market
        else{
            System.out.println("Seller: "+ seller+" does not exist in the market");
        }
    }

    /**
     * Checks if the game title will be up for sale tomorrow
     * Can be used for Future improvements and extra features
     *
     * @param seller User offerings the game
     * @param gameTitle the game title to be checked
     * @return true if the Game title will be up for sale tomorrow
     */
    public boolean gameToBeUpCheck(String seller, String gameTitle){
        boolean result = false;
        // get the game title and check if it is on a Hold for today
        ArrayList<Game> myOffering = this.gamesOnSale.get(seller);
        for(Game myGame : myOffering) {
            String currGameName = myGame.getName();
            if (currGameName.equals(gameTitle)) {
                result = myGame.getHold();
            }
        }
        return result;
    }


    /**
     * Checks if a Seller exists in our Market
     *
     * @param seller a User to check if they exist
     * @return true if they exist in the market false otherwise
     */
    public boolean checkSellerExist(String seller){
        return this.gamesOnSale.containsKey(seller);
    }


    /**
     * Checks if the seller is currently offering the sale of the Game title
     *
     * @param seller User checking if they are selling the game title
     * @param gameTitle The game title being checked
     * @return true if the seller is selling the Game title
     */
    public boolean checkSellerSellingGame(String seller, String gameTitle){
        boolean result = false;
        // if the Seller exists and check if they are selling the Game
        if(this.checkSellerExist(seller)) {
            ArrayList<Game> currOffering = this.getMyOfferings(seller);
            boolean gameFound = false;
            // check if the seller is selling the game title
            for(Game myGame : currOffering){
                String currGameName = myGame.getName();
                if(currGameName.equals(gameTitle)){
                    gameFound = true;
                    // Check if the Game is on hold or not
                    if(!myGame.getHold()){
                        result = true;
                    }
                    else{
                        System.out.println("Seller: "+ seller+" will have the Game: "+ gameTitle
                        + " up for Processing tomorrow. ");
                    }
                }
            }
            if(!gameFound){
                System.out.println("Seller: "+ seller+" is currently not offering "+ gameTitle);
            }
        }
        // Seller currently does not exist in our market
        else{
            System.out.println("Seller: "+ seller+" does not exist in the market");
        }
        return result;
    }


    /**
     * Returns the list of games the seller is offering
     * Can be used for Future improvements and extra features
     *
     * @param seller User selling games
     * @return ArrayList of all the offerings
     */
    private ArrayList<Game> getMyOfferings(String seller){
        return this.gamesOnSale.get(seller);
    }


    /**
     * Returns the CURRENT price offering of the game the seller is selling
     * Can be used for Future improvements and extra features
     *
     * @param seller User selling games, must be a valid seller
     * @param gameTitle The game title being asked for the price, must exist with the Seller
     * @return the price of the game or -1 incase of Seller is not selling the game
     */
    private double calculatePrice(String seller, String gameTitle){
        double price = -1;
        // Get all the games offering from the Seller and check the Price for the game title requested
        ArrayList<Game> currOffering = this.getMyOfferings(seller);
        for(Game myGame : currOffering) {
            String currGameName = myGame.getName();
            if (currGameName.equals(gameTitle)) {
                price = myGame.getPriceWithDiscount(this.auctionSale);
            }
        }
    return price;
    }


    /**
     * Helper to remove the game from the User's offering from the current Market
     *
     * @param seller The person trying to remove the Game
     * @param game The Game to be removed
     */
    public void removeGame(String seller, String game){
        // get all my offerings and remove the game from my offerings if the game is not on hold
        ArrayList<Game> currOffering = this.getMyOfferings(seller);
        Game toRemove = null;
        for(Game curr : currOffering){
            if(curr.getName().equals(game)){
                toRemove = curr;
            }
        }
        // removing the game from the User's offering
        gamesOnSale.get(seller).remove(toRemove);
    }
}
