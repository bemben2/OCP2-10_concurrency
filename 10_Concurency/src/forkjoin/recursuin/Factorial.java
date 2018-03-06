package forkjoin.recursuin;

public class Factorial {

	public static void main(String[] args) {
		int fact = callFact(5);
		System.out.println("Factorial of 5 is: "+ fact);
	}

	public static int callFact(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * callFact(n - 1);
		}
	}
}
