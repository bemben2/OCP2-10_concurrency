package forkjoin.recursuin;

public class SimpleRecursion {

	public static void main(String[] args) {
		new SimpleRecursion().foo(5);

	}

	public void foo(int x) {
		if (x > 0) {
			System.out.println(x);
			foo(x - 1);
		}
	}
}
