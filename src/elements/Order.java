package elements;
/**
 * Class that contains fields and methods related to the Order.
 * @author celik 
 *
 */
public class Order{
	/**
	 * Amount of PQoins
	 */
	protected double amount;
	/**
	 * Price of PQoin
	 */
	protected double price;
	/**
	 * ID of trader.
	 */
	protected final int traderID;
	/**
	 * Constructor that creates the Order object by initializing the required fields.
	 * @param traderID ID of the trader
	 * @param amount Amount of PQoins
	 * @param price Price of PQoin
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	/**
	 * Allows you to access  Amount.
	 * @return Amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Allows you to access  Price.
	 * @return Price 
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Allows you to access the ID of trader .
	 * @return Trader ID
	 */
	public int getTraderID() {
		return traderID;
	}
	/**
	 * Allows you to change Amount.
	 * @param amount New Amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * Allows you to change Price.
	 * @param price New Price
	 */
	public void setPrice(double price) {
		this.price = price;
	};
	
	
}

