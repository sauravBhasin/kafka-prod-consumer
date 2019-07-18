package com.test.notificationservice.queuelistener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.test.notificationservice.dao.PublishNotifications;

@Component
public class QueueListener {

	private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public void pollQueue() {

		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("polling Queue");
				new PublishNotifications().publishNotification();

			}
		}, 0, 2, TimeUnit.SECONDS);

		System.out.println("Listener init completed.");

	}
}
