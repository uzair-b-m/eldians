package main;
import java.util.HashMap;
import java.util.ArrayList;

public class Marketplace {
    public boolean auctionSale = true;
    public HashMap<AbstractUser, ArrayList<Game>> gamesOnSale;

    public Marketplace() {
        this.gamesOnSale = new HashMap<AbstractUser, ArrayList<Game>>();
    }

    public boolean getAuctionSale(){
        return this.auctionSale;
    }

    /**
     * Toggle the Sale in the market
     */
    private void toggleSale(){
        this.auctionSale = !this.auctionSale;
    }

    public HashMap<AbstractUser, ArrayList<Game>> getGamesOnSale() {
        return this.gamesOnSale;
    }
    // this is just returning the marketplace change the method name
    // for some reason the data types cannot be accessed in abstract class - something to do with packages and main?
}