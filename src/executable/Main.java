package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;



import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;

/**
 * The class that prints the output after reading the input and performing the necessary operations and calculations.
 * @author celik
 *
 */
public class Main {
	/**
	 * Generates a random number
	 */
		public static Random myRandom;
		/**
		 * The total dollar amount in the queue used when calculating the market size
		 */
		public static double totalDolarInPQ = 0;
		/**
		 * The total coin amount in the queue used when calculating the market size
		 */
		public static double totalCoinInPQ = 0;
		
		/**
		 * The method that prints the output after reading the input and performing the necessary operations and calculations.
		 * @param args Receives two arguments: path to input file and path to output file.
		 * @throws FileNotFoundException Action to be taken in case of a possible file not found error.
		 */
	public static void main(String args[]) throws FileNotFoundException {
		
		Scanner reader = new Scanner(new File(args[0]));
		PrintStream writer = new PrintStream(new File(args[1]));
		
		int seed = reader.nextInt();
		myRandom = new Random(seed);
		int invalidQueries = 0;
		
		Market market = new Market(reader.nextInt());
		Trader.numberOfUsers = reader.nextInt();
		int numberOfQueries = reader.nextInt();
		
		ArrayList<Trader> traders = new ArrayList<Trader>();
		for(int i=0 ; i< Trader.numberOfUsers ; i++) {
			traders.add(new Trader(reader.nextDouble(), reader.nextDouble())) ;
		}
		reader.nextLine();
		for(int i= 0 ; i< numberOfQueries ; i++) {
			Scanner line = new Scanner(reader.nextLine());
			int eventType = line.nextInt();
			if (eventType == 10) {
				int tempID = line.nextInt();
				double tempPrice = line.nextDouble();
				double tempAmount = line.nextDouble();
				if(traders.get(tempID).getWallet().getDollars() >= tempAmount*tempPrice) {
					market.giveBuyOrder(new BuyingOrder(tempID, tempAmount, tempPrice));
					traders.get(tempID).getWallet().setDollars(traders.get(tempID).getWallet().getDollars()-tempAmount*tempPrice);
					traders.get(tempID).getWallet().setBlockedDollars(traders.get(tempID).getWallet().getBlockedDollars()+tempAmount*tempPrice);
					totalDolarInPQ += tempAmount*tempPrice;
				}
				else {
					invalidQueries++;
				}
				market.checkTransactions(traders);
			}
			if (eventType == 11) {
				int tempID = line.nextInt();
				double tempAmount = line.nextDouble();
				
				if(market.getSellingPQ().peek() !=null) {
					double tempPrice = market.getSellingPQ().peek().getPrice();
					if(traders.get(tempID).getWallet().getDollars() >= tempAmount*tempPrice) {
						market.giveBuyOrder(new BuyingOrder(tempID, tempAmount, tempPrice));
						traders.get(tempID).getWallet().setDollars(traders.get(tempID).getWallet().getDollars()-tempAmount*tempPrice);
						traders.get(tempID).getWallet().setBlockedDollars(traders.get(tempID).getWallet().getBlockedDollars()+tempAmount*tempPrice);
						totalDolarInPQ += tempAmount*tempPrice;
					}
					else {
						invalidQueries++;
				}
			}
				else {
					invalidQueries++;
				}
				market.checkTransactions(traders);
			}
			if (eventType == 20) {
				int tempID = line.nextInt();
				double tempPrice = line.nextDouble();
				double tempAmount = line.nextDouble();
				if (traders.get(tempID).getWallet().getCoins() >= tempAmount) {
					market.giveSellOrder(new SellingOrder(tempID, tempAmount, tempPrice));
					traders.get(tempID).getWallet().setCoins(traders.get(tempID).getWallet().getCoins() - tempAmount);
					traders.get(tempID).getWallet().setBlockedCoins(traders.get(tempID).getWallet().getBlockedCoins()+tempAmount);
					totalCoinInPQ+= tempAmount;
				}
				else {
					invalidQueries++;
				}
				market.checkTransactions(traders);
			}
			if (eventType == 21) {
				int tempID = line.nextInt();
				double tempAmount = line.nextDouble();
				if(market.getBuyingPQ().peek() != null) {
					double tempPrice = market.getBuyingPQ().peek().getPrice();
					if (traders.get(tempID).getWallet().getCoins() >= tempAmount) {
						market.giveSellOrder(new SellingOrder(tempID, tempAmount, tempPrice));
						traders.get(tempID).getWallet().setCoins(traders.get(tempID).getWallet().getCoins() - tempAmount);
						traders.get(tempID).getWallet().setBlockedCoins(traders.get(tempID).getWallet().getBlockedCoins()+tempAmount);
						totalCoinInPQ+= tempAmount;
					}
					else {
						invalidQueries++;
					}
					}
				else {
					invalidQueries++;
				}
				market.checkTransactions(traders);
			}
			if (eventType == 3) {
				int tempID = line.nextInt();
				traders.get(tempID).getWallet().setDollars(traders.get(tempID).getWallet().getDollars()+line.nextDouble());
			}
			if(eventType == 4) {
				int tempID = line.nextInt();
				double tempAmount = line.nextDouble();
				if (traders.get(tempID).getWallet().getDollars()>= tempAmount) {
					traders.get(tempID).getWallet().setDollars(traders.get(tempID).getWallet().getDollars()-tempAmount);
				}
				else {
					invalidQueries++;
				}
			}
			if (eventType == 5) {
				int tempID = line.nextInt();
				writer.printf("Trader " + tempID  + ": " + "%.5f" + "$ " + "%.5f" +"PQ\n",(traders.get(tempID).getWallet().getDollars() + traders.get(tempID).getWallet().getBlockedDollars()),(traders.get(tempID).getWallet().getCoins()+ traders.get(tempID).getWallet().getBlockedCoins()));
				
			}
			if (eventType == 777) {
				for(int j =0 ; j< Trader.numberOfUsers ; j++) {
					traders.get(j).getWallet().setCoins(traders.get(j).getWallet().getCoins()+myRandom.nextDouble()*10);
				}
				
			}
			if(eventType == 500) {
				writer.printf("Current market size: "+"%.5f" +" "+"%.5f" +"\n", totalDolarInPQ,totalCoinInPQ );
			}
			if (eventType == 501) {
				writer.print("Number of successful transactions: " + market.getTransactions().size() +"\n");
			}
			
			if (eventType == 502) {
				writer.print("Number of invalid queries: "+invalidQueries + "\n");
			}
			if (eventType == 505) {
				double buyPrice , sellPrice, avgPrice;
				 if(market.getBuyingPQ().peek() != null) {
					 buyPrice = market.getBuyingPQ().peek().getPrice();
				 }
				 else {
					buyPrice = 0 ;
					 
				}
				 if(market.getSellingPQ().peek() != null) {
					 sellPrice = market.getSellingPQ().peek().getPrice();
				 }
				 else {
					 sellPrice =0 ;
				}
				if (buyPrice == 0 && sellPrice == 0) {
					avgPrice = 0;
				}
				else if(buyPrice == 0) {
					avgPrice = sellPrice;
				}
				else if(sellPrice == 0) {
					avgPrice = buyPrice;
				}
				else {
					avgPrice = (buyPrice+ sellPrice)/2;
				}
				 
				 
				writer.printf("Current prices: " + "%.5f" + " " + "%.5f" + " " + "%.5f" +"\n",buyPrice,sellPrice,avgPrice );
			}
			if (eventType == 555) {
				for (Trader trader : traders) {
					writer.printf("Trader " + trader.getId()  + ": " + "%.5f" + "$ " + "%.5f" +"PQ\n",(trader.getWallet().getDollars() + trader.getWallet().getBlockedDollars()),(trader.getWallet().getCoins()+ trader.getWallet().getBlockedCoins()));
				}
			}
			
			if (eventType == 666) {
				double givenPrice = line.nextDouble();
				
				if(market.getBuyingPQ().peek() != null && market.getBuyingPQ().peek().getPrice() > givenPrice) {
					while(market.getBuyingPQ().peek().getPrice() >= givenPrice) {
						market.giveSellOrder(new SellingOrder(0, market.getBuyingPQ().peek().getAmount(), market.getBuyingPQ().peek().getPrice()));
						totalCoinInPQ += market.getBuyingPQ().peek().getAmount();
						market.checkTransactions(traders);
						if(market.getBuyingPQ().peek() ==null)
							break;
					}
				}
				else if(market.getSellingPQ().peek() !=null){
					while(market.getSellingPQ().peek().getPrice() <= givenPrice) {
						market.giveBuyOrder(new BuyingOrder(0, market.getSellingPQ().peek().getAmount(), market.getSellingPQ().peek().getPrice()));
						totalDolarInPQ += market.getSellingPQ().peek().getAmount()* market.getSellingPQ().peek().getPrice();
						market.checkTransactions(traders);
						if(market.getSellingPQ().peek() ==null)
							break;
					}
					
				}
			}
		}
		
	}
}

