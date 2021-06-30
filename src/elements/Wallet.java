package elements;
/**
 *  Class that contains fields and methods related to the Wallet.
 * @author celik
 *
 */
public class Wallet {
	/**
	 * Amount of dollars in the wallet
	 */
	private double dollars;
	/**
	 * Amount of coins in the wallet
	 */
	private double coins;
	/**
	 * Amount of dollars in wallet but inaccessible
	 */
	private double blockedDollars;
	/**
	 * Amount of coins in wallet but inaccessible
	 */
	private double blockedCoins;
	/**
	 * Constructor that creates the Wallet object by initializing the required fields.
	 * @param dollars Amount of dollars in the wallet
	 * @param coins Amount of coins in the wallet
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0;
		blockedCoins = 0;
	};
	/**
	 *  Allows you to access Dollars.
	 * @return
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 *  Allows you to access Coins.
	 * @return
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 *  Allows you to change Dollar amount.
	 * @param dollars
	 */

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	/**
	 *  Allows you to change Coin amount.
	 * @param coins
	 */
	public void setCoins(double coins) {
		this.coins = coins;
	}
	/**
	 * Allows you to access BlockedDollars.
	 * @return
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * Allows you to change BlockedDollar amount.
	 * @param blockedDollars
	 */
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}
	/**
	 *  Allows you to access Blocked Coins.
	 * @return
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	/**
	 *  Allows you to change Blocked Coins.
	 * @param blockedCoins
	 */
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	
}
