package elements;



/**
 * Class that contains fields and methods related to the Trader.
 * @author celik
 *
 */
public class Trader {
	/**
	 * ID of the Trader.
	 */
	private final int id;
	/**
	 * Wallet of the Trader.
	 */
	private Wallet wallet;
	/**
	 * Variable created to give specific id to traders.
	 */
	static int tempID = 0 ;
	/**
	 * Constructor that creates the Trader object by initializing the required fields.
	 * @param dollars The trader's initial money
	 * @param coins   The trader's initial PQoins.
	 */
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars, coins);
		this.id = tempID ;
		tempID++;
	};
	/**
	 * Performs the transactions required by the sale
	 * @param amount Amount of PQoins.
	 * @param price Price of PQoin.
	 * @param market The market where the sale takes place.
	 * @return 0
	 */
	public int sell(double amount, double price,Market market) {
		if(market.getSellingPQ().peek().getAmount() > amount ) {
			market.getSellingPQ().peek().setAmount(market.getSellingPQ().peek().getAmount()-amount);
			this.getWallet().setBlockedCoins(this.wallet.getBlockedCoins()-amount);
			this.wallet.setDollars(this.wallet.getDollars()+(amount*price*(1.0-(market.getFee()/1000.0))));
			
		}
		else {
			market.getSellingPQ().poll();
			this.getWallet().setBlockedCoins(this.wallet.getBlockedCoins()-amount);
			this.wallet.setDollars(this.wallet.getDollars()+(amount*price*(1.0-(market.getFee()/1000.0))));
		}
		return 0;
	};
	/**
	 * Performs the necessary procedures for the purchase.
	 * @param amount  Amount of PQoins.
	 * @param price Price of PQoin.
	 * @param market The market where the purchase takes place.
	 * @return 0
	 */
	public int buy(double amount, double price, Market market) {
		if(price < market.getBuyingPQ().peek().price) {
			double xtra = market.getBuyingPQ().peek().price - price ;
			this.wallet.setBlockedDollars(this.wallet.getBlockedDollars()-xtra*amount);
			this.wallet.setDollars(this.wallet.getDollars()+xtra*amount);
		}
		if(market.getBuyingPQ().peek().getAmount() > amount) {
			market.getBuyingPQ().peek().setAmount(market.getBuyingPQ().peek().getAmount()-amount);
			this.getWallet().setBlockedDollars(this.wallet.getBlockedDollars()-(amount*price));
			this.wallet.setCoins(this.wallet.getCoins()+amount);
			
		}
		else {
			market.getBuyingPQ().poll();
			this.getWallet().setBlockedDollars(this.wallet.getBlockedDollars()-(amount*price));
			this.wallet.setCoins(this.wallet.getCoins()+amount);
		}
		return 0 ;
	};
	/**
	 * Static variable to hold number of traders
	 */
	public static int numberOfUsers = 0;
	/**
	 * Allows you to access the wallet.
	 * @return Wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}
	
	public String toString() {
		String z = "Trader " + this.id + ": " + (this.wallet.getDollars()+this.wallet.getBlockedDollars()) + "$ " + (this.wallet.getCoins()+this.wallet.getBlockedCoins()) +"PQ";
		return z;
	}
	/**
	 * Allows you to access the id.
	 * @return ID
	 */
	public int getId() {
		return id;
	}
	
}
	