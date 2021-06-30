package elements;
/**
 * Class that holds transactions
 * @author Windows10
 *
 */

public class Transaction {
	/**
	 * Selling Order
	 */
	private SellingOrder sellingOrder;
	/**
	 *  Buying Order
	 */
	private BuyingOrder buyingOrder;
	/**
	 *  Constructor that creates the Transaction object by initializing the required fields.
	 * @param a Selling Order
	 * @param b Buying Order
	 */
	public Transaction(SellingOrder a , BuyingOrder b) {
		this.sellingOrder = a;
		this.buyingOrder = b;
	}
}
