package advanceJava;

import java.util.concurrent.CompletableFuture;

public class SandBox {

	public static void main(String[] args) {
		System.out.println("hello world");
				
		CompletableFuture.supplyAsync(() -> longNetworkProcess(2000)).thenAccept(value -> System.out.println("complete " + value ));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A wake");
	}
	
	public static int longNetworkProcess(int value) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value * 10;
	}

}
