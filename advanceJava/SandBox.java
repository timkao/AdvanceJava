package advanceJava;

import java.util.concurrent.CompletableFuture;

public class SandBox {

	public static void main(String[] args) {
		System.out.println("hello world");
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> longNetworkProcess(20));
		cf.thenAccept(value -> System.out.println("the result is " + value));
		// 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
