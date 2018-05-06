package problem_Fruit;

public class FruitSeller {
	int numOfApple;
	int myMoney;
	int APPLE_PRICE;
	
	public FruitSeller(int numApple, int money, int applePrice){
		numOfApple = numApple;
		myMoney = money;
		APPLE_PRICE = applePrice;
			
	}
	
	public int saleApple(int money){
		myMoney += money - (money % APPLE_PRICE);
		numOfApple -= money / APPLE_PRICE;

		return money % APPLE_PRICE; 
	}
	
	public void showSaleResult(){
		System.out.println("남은 사과의 개수: " + numOfApple);
		System.out.println("현재 잔액: " + myMoney);
		System.out.println();
	}
	
}