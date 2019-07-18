package com.test.notificationservice.controllers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		sendMessage(message);
	}

	public void sendMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send("test", message);
	}

	private final Logger logger1 = LoggerFactory.getLogger(Consumer.class);

//	@KafkaListener(topics = "test", groupId = "group_id")
//	public void consume(String message) {
//		logger1.info(String.format("$$ -> Consumed Message -> %s", message));
//	}

}
