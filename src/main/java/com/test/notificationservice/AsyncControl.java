package com.test.notificationservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncControl {
	public static void main(String args[]) throws InterruptedException, ExecutionException {

		List<String> futures = new ArrayList<>();
		for (int i = 0; i < 100; ++i) {
			futures.add(addWorld("Hello"));
		}
		System.out.println("Running Future eg");
		for (int i = 0; i < 100; ++i) {
			System.out.println(futures.get(i));
		}
	}

	public static String addWorld(String s) {
		System.out.println("adding world");
		return s + " world";
	}

	public static String addQuestionMark(String s) {
		System.out.println("adding world");
		return s + " ?";
	}

}
