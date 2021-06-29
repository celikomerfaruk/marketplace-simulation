package elements;

public class Trader {
	private int id;
	private Wallet wallet;
	static int tempID = 0 ;
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars, coins);
		this.id = tempID ;
		tempID++;
	};
	public int sell(double amount, double price,Market market) {
		if(market.getSellingPQ().peek().getAmount() > amount ) {
			market.getSellingPQ().peek().setAmount(market.getSellingPQ().peek().getAmount()-amount);
			this.getWallet().setBlockedCoins(this.wallet.getBlockedCoins()-amount);
			this.wallet.setDollars(this.wallet.getDollars()+amount*price*(1-market.getFee())/1000);
		}
		else {
			market.getSellingPQ().poll();
			this.getWallet().setBlockedCoins(this.wallet.getBlockedCoins()-amount);
			this.wallet.setDollars(this.wallet.getDollars()+amount*price*(1-market.getFee())/1000);
		}
		return 0;
	};
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
	public static int numberOfUsers = 0;
	
	public Wallet getWallet() {
		return wallet;
	}
	public String toString() {
		String z = "Trader " + this.id + ": " + (this.wallet.getDollars()+this.wallet.getBlockedDollars()) + "$ " + (this.wallet.getCoins()+this.wallet.getBlockedCoins()) +"PQ";
		return z;
	}
	public int getId() {
		return id;
	}
	
}
	