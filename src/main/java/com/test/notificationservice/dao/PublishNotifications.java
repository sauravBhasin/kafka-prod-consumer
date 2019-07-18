package com.test.notificationservice.dao;

public class PublishNotifications {

	public void publishNotification() {
		System.out.println("notification publising begin");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("notification publising end");
	}
}
