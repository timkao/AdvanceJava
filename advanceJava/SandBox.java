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
		
		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> longNetworkProcess(20));
		cf2 = cf2.thenApplyAsync(value -> anotherProcess(value));
		cf2.thenAccept(result -> System.out.println("the second result is " + result));
		
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("has all results");

	}
	
	public static int longNetworkProcess(int value) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value * 10;
	}
	
	public static int anotherProcess(int value) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value * 3;
		
	}
	
	/*
	 * CompletableFuture可以指定异步处理流程：
	 *   thenAccept()处理正常结果；
	 *   exceptional()处理异常结果；
	 *   thenApplyAsync()用于串行化另一个CompletableFuture；
	 *   anyOf()和allOf()用于并行化多个CompletableFuture。
	 * 
	 * */
}
