public class Fibonacci {
	public int fibonacci(int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Invalid argument for Fibonacci series: " + number);
		} 
		else if (number == 1 || number == 2) {
			return 1;
		} 
		else {
			return fibonacci(number - 2) + fibonacci (number - 1);
		}
	}
	
	public int fibonacciTail(int number) {
		return fibonacciTail(number, 1, 1);
	}

	public int fibonacciTail(int number, int preFib, int posFib) {
		if (number < 1) {
			throw new IllegalArgumentException("Invalid argument for Fibonacci series: " + number);
		} 
		else if (number == 1) {
			return preFib; 
		} 
		else {
			return fibonacciTail(number - 1, posFib, preFib+posFib);
		}
	}
}
