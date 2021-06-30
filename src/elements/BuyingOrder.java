package elements;
/**
 * Class that contains fields and methods related to the Buying Order.
 * @author celik
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{
	/**
	 * Constructor that creates the Buying Order object by initializing the required fields.
	 * @param traderID Trader ID 
	 * @param amount  Amount 
	 * @param price Price
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	
	public int compareTo(BuyingOrder e){
			if (this.price != e.price) {
				if (this.price > e.price) {
					return -1;
				}
				else 
					return +1 ;
			}
			 
			if(this.amount != e.amount) {
				if (this.amount > e.amount)
					return -1;
				else {
					return +1;
				}
			}
			
			return  this.traderID - e.traderID ;
			
		
	}

}
