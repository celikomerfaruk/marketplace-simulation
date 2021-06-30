package elements;

public class SellingOrder extends Order implements Comparable<SellingOrder> {
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	public int compareTo(SellingOrder e){
		if (this.price != e.price) {
			if (this.price < e.price) {
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
		
		return  this.traderID- e.traderID   ;
		
	}
}
