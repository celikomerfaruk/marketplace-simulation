package elements;

public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	public Transaction(SellingOrder a , BuyingOrder b) {
		this.sellingOrder = a;
		this.buyingOrder = b;
	}
}
