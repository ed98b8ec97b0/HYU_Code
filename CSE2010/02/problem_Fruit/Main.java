package problem_Fruit;

public class Main {

	public static void main(String[] args) {
		FruitSeller seller1 = new FruitSeller(30, 4000, 500);
		FruitSeller seller2 = new  FruitSeller(50, 10000, 1000);
		FruitBuyer buyer = new FruitBuyer(15500, 10);
		
		buyer.buyApple(seller1, 4200);
		buyer.buyApple(seller2, 7300);

		System.out.println("판매자1의 상태.");
		seller1.showSaleResult();
		
		System.out.println("판매자2의 상태.");
		seller2.showSaleResult();

		System.out.println("구매자의 상태.");
		buyer.showBuyResult();

	}
}
