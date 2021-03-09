package main;
/** This is a buy type user that extends the Abstract User class. A Buy type user cannot sell games, only buy. And
 * it cannot create or delete users.
 *
 */
public class BuyUser extends AbstractUser {

    public BuyUser(String username, Float credit) {
        super(username);
        this.accountBalance = credit;
    }

    /** Prints that the user cannot sell a game.
     *
     * @param game game to be sold
     * @param market Marketplace game will be sold on
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT
    @Override
    public void sell(Game game, Marketplace market){
        System.out.println("ERROR: \\ < Failed Constraint: "+ this.username + " cannot sell games.");
    }

    /** Prints that the user cannot create a user
     *
     * @param username a string with a length: 1-15
     * @param type a string representing the User type of the newly created user
     *             where AA=admin, FS=full-standard, BS=buy-standard, SS=sell-standard
     * @param credit a float representing the amount of credits to add to the newly
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    @Override
    public void create(String username, String type, Float credit){
        System.out.println("ERROR: \\ < Failed Constraint: "+ this.username + " cannot create another user.");
    }

    /**Prints that this user cannot delete another user.
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    @Override
    public void delete(){
        System.out.println("ERROR: \\ < Failed Constraint: "+ this.username + " cannot delete another user.");
    }

    /** Prints that this user cannot issue a refund.
     *
     * @param buyer the customer asking for the refund
     * @param seller the supplier of the games issueing the refund
     * @param amount the value of credits to be transfered among them
     * @return
     */
    @Override
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    public boolean refund(AbstractUser buyer, AbstractUser seller, float amount){
        System.out.println("ERROR: \\ < Failed Constraint: "+ this.username + " cannot issue a refund.");
        return false;
    }

}