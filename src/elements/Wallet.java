package elements;

public class Wallet {
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0;
		blockedCoins = 0;
	};
	
	public double getDollars() {
		return dollars;
	}
	public double getCoins() {
		return coins;
	}

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public double getBlockedDollars() {
		return blockedDollars;
	}

	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}

	public double getBlockedCoins() {
		return blockedCoins;
	}

	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	
}
