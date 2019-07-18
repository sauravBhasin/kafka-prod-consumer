package com.test.notificationservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTesting {

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		long startTime = System.currentTimeMillis();
		ExecutorService service = Executors.newFixedThreadPool(100);

		List<CompletableFuture<String>> futures = new ArrayList<>();

		for (int i = 0; i < 100; ++i) {
			System.out.println("In loop");
			futures.add(CompletableFuture.supplyAsync(() -> {
				return "Hello ";
			}, service).thenApply((s) -> {
				return addWorld(s);
			}).thenApply((s) -> {
				return addQuestionMark(s);
			}));
		}

		System.out.println("Running Future eg");
		for (int i = 0; i < 100; ++i) {
			System.out.println(futures.get(i).get());
		}
		service.shutdown();
		System.out.println("execution in " + (System.currentTimeMillis() - startTime));
	}

	public static String addWorld(String s) {
		System.out.println("adding world");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s + " world";
	}

	public static String addQuestionMark(String s) {
		System.out.println("adding ?");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s + " ?";
	}

}
