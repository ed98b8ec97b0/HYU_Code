import java.util.*;

public class Prime_problem {
	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		int N;
		ArrayList<Integer> Primes = new ArrayList<Integer>();
		
		System.out.print("Input N : ");
		N = s.nextInt();

		for (int i = 2; i <= N; i++) {
			int primeCheck = 1;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					primeCheck = 0;
				}
			}

			if (primeCheck == 1) {
				Primes.add(i);
			}
		}

		for (int i = 0; i < Primes.size(); i++) {
			System.out.print(Primes.get(i) + " ");
		}
		
		System.out.println(); 
	}
}