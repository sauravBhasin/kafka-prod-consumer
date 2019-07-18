package com.test.notificationservice.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.test.notificationservice.Employee;
import com.test.notificationservice.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	static List<Employee> employees = new ArrayList<>();
	static {
		Employee e = new Employee();
		e.setId("1");
		e.setName("saurav");
		Employee e1 = new Employee();
		e1.setId("2");
		e1.setName("bhasin");

		employees.addAll(Arrays.asList(e, e1));
	}

	@Override
	@Cacheable(value = "helloCache", key = "#emp.id")
	public Employee hello(Employee emp) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Employee e : employees) {
			if (e.getId().equals(emp.getId())) {
				return e;
			}
		}
		return null;
	}

	@Override
	@CachePut(value = "helloCache", key = "{#emp.id}")
	public String update(Employee emp) {
		for (Employee e : employees) {
			if (e.getId().equals(emp.getId())) {
				e.setName(emp.getName());
			}
		}
		return "done";
	}

	@Override
	@CacheEvict(value = "helloCache", key = "#emp.id")
	public String delete(Employee emp) {
		return "done";
	}

	public static void main(String args[]) throws InterruptedException, ExecutionException {

//		String s = "hello";
//
//		CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> {
//			return Arrays.asList(s + "world1", s + "world2");
//		});
//
//		CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> {
//			return Arrays.asList(s + "world");
//		});
//
//		CompletableFuture<List<String>> future3 = CompletableFuture.supplyAsync(() -> {
//			return Arrays.asList(s + "world");
//		});
//
//		String returnValue = Stream.of(future1, future2, future3).map(CompletableFuture::join)
//				.flatMap(listStream -> listStream.parallelStream()).collect(Collectors.joining(" "));
//
//		System.out.println(returnValue);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return "1saf";
		});

		future = future.thenApply(Integer::parseInt) // input String: "not detected"
				.thenApply(r -> r * 2 * Math.PI).thenApply(s -> "apply>> " + s)
				.exceptionally(ex -> "Error: " + ex.getMessage());

		System.out.println(future.get());
	}
}
