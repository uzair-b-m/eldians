import java.util.ArrayList;

/** Admin type user that extends the Abstract USer class. A sell type user cannot buy games, only sell. And
 * it cannot create or delete users.
 *
 */
public class SellUser extends AbstractUser {

    /**
     *
     * @param username
     * @param credit
     */

    private SellUser(SellUserBuilder builder) {
        this.username = builder.username;
        this.accountBalance = builder.accountBalance;
        this.type = "SS";
    }

    /** Prints that this user cannot buy a game.
     *
     * @param seller the supplier of the game
     * @param game the name of the game
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    @Override
    public void buy(AbstractUser seller, Game game, boolean saleToggle){
        System.out.println(this.username + " cannot buy games.");
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
    public void create(String username, String type, double credit, Application application){
        System.out.println(this.username + " cannot create another user.");
    }

    /**Prints that this user cannot delete another user.
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    @Override
    public void delete(AbstractUser user, double amount){
        System.out.println(this.username + " cannot delete another user.");
    }

    /** Prints that this user cannot issue a refund.
     *
     * @param buyer the customer asking for the refund
     * @param seller the supplier of the games issueing the refund
     * @param amount the value of credits to be transfered among them
     * @return
     */
    //THIS DOES NOT FOLLOW THE RIGHT FORMAT

    @Override
    public boolean refund(AbstractUser buyer, AbstractUser seller, double amount){
        System.out.println(this.username + " cannot issue a refund.");
        return false;
    }

    public static class SellUserBuilder {

        private String username; // required
        //        public String type;
        public double accountBalance;
        public double newFunds;
        public ArrayList<String> transactionHistory;

        public SellUserBuilder(String name) {
            this.username = name;
        }

        public SellUserBuilder balance(double accountBalance){
            this.accountBalance = accountBalance;
            return this;
        }

        public SellUserBuilder newFunds(double newFunds){
            this.newFunds = newFunds;
            return this;
        }

        public SellUserBuilder transactionHistory(ArrayList<String> transactions){
            this.transactionHistory.addAll(transactions);
            return this;
        }

        public SellUser build() {
            SellUser user = new SellUser(this);
            return new SellUser(this);
        }

    }
}