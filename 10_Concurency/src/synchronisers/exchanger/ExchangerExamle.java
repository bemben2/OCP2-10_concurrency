package synchronisers.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExamle {

	public static void main(String[] args) {
		Exchanger<String> msgs = new Exchanger<>();

		new Thread(new CoffeeShopThread(msgs)).start();
		new Thread(new DukeThread(msgs)).start();
	}
}

class DukeThread implements Runnable {

	private Exchanger<String> msgs;

	public DukeThread(Exchanger<String> msgs) {
		this.msgs = msgs;
	}

	@Override
	public void run() {
		String reply = null;
		try {
			reply = msgs.exchange("Duke: Could I have a cup of coffee please?");
			System.out.println(reply);

			reply = msgs.exchange("Duke: Late pleas?");
			System.out.println(reply);
			
			// exchange another set of messages
			reply = msgs.exchange("Duke: Thanks.");
			System.out.println(reply);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class CoffeeShopThread implements Runnable {

	private Exchanger<String> msgs;
	private String greeting = "Hi";

	public CoffeeShopThread(Exchanger<String> msgs) {
		super();
		this.msgs = msgs;
	}

	@Override
	public void run() {
		String reply = null;
		try {
			// an exchange could happen only when both send and receive happens
			reply = msgs.exchange("CS: Of course, what would you like?");
			System.out.println(reply);
			
			//exchange ecound message
			reply = msgs.exchange("CS: Here you go...");
			System.out.println(reply);

			// exchange last message
			reply = msgs.exchange("CS: You are wlcome!");
			System.out.println(reply);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}