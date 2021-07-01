package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class that contains fields and methods related to the Market.
 * @author celik
 *
 */
public class Market {
	/**
	 * Priority queue holding selling orders
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * Priority queue holding buying orders
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * ArrayList holding transactions.
	 */
	private ArrayList<Transaction> transactions;
	/**
	 * Commission rate of the market.
	 */
	private final int fee ;
	/**
	 * Adds the order to the selling priority queue
	 * @param order Selling Order
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	};
	/**
	 * Adds the order to the buying priority queue
	 * @param order Buying Order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	};
	/**
	 * I did this operation inside the main function
	 * @param price
	 */
	public void makeOpenMarketOperation(double price) {
		
	};
	/**
	 * Performs necessary transactions at the intersections between selling orders and buying orders.
	 * @param traders ArrayList contains traders.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		if(sellingOrders.peek()!= null && buyingOrders.peek()!= null) {
			while(sellingOrders.peek().getPrice() <= buyingOrders.peek().getPrice()) {
				transactions.add(new Transaction(this.sellingOrders.peek(), this.buyingOrders.peek()));
				double sellingPrice = sellingOrders.peek().getPrice();
				double sellingAmount;
				if(sellingOrders.peek().getAmount() < buyingOrders.peek().getAmount()) {
					sellingAmount = sellingOrders.peek().getAmount();
				}
				else {
					sellingAmount = buyingOrders.peek().getAmount();
				}
				traders.get(sellingOrders.peek().getTraderID()).sell(sellingAmount, sellingPrice, this);
				traders.get(buyingOrders.peek().getTraderID()).buy(sellingAmount, sellingPrice, this);
				executable.Main.totalCoinInPQ -= sellingAmount;
				executable.Main.totalDolarInPQ -= sellingPrice*sellingAmount;
				if(sellingOrders.peek()== null || buyingOrders.peek()== null)
					break;
			}
		}
	};
	/**
	 * Constructor that creates the Market object by initializing the required fields.
	 * @param fee Commission rate of the market.
	 */
	public Market(int fee) {
		this.fee = fee;
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
	};
	/**
	 * Allows you to access the SellingOrders PQ.
	 * @return SellingOrders PQ
	 */
	public PriorityQueue<SellingOrder> getSellingPQ(){
		return sellingOrders;
	}
	/**
	 * Allows you to access the BuyingOrders PQ.
	 * @return BuyingOrders PQ
	 */
	public PriorityQueue<BuyingOrder> getBuyingPQ(){
		return buyingOrders;
	}
	/**
	 * Allows you to access the Fee.
	 * @return Fee
	 */
	public int getFee() {
		return fee;
	}
	/**
	 * Allows you to access the Transactions ArrayList.
	 * @return Transactions ArrayList
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
}
