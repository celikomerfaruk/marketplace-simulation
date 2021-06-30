package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;



public class Market {
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList<Transaction> transactions;
	private int fee ;
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	};
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	};
	public void makeOpenMarketOperation(double price) {
		
	};
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
			}
		}
	};
	public Market(int fee) {
		this.fee = fee;
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
	};
	public PriorityQueue<SellingOrder> getSellingPQ(){
		return sellingOrders;
	}
	public PriorityQueue<BuyingOrder> getBuyingPQ(){
		return buyingOrders;
	}
	public int getFee() {
		return fee;
	}
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
}
