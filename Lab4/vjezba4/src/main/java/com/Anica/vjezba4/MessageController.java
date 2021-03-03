package com.Anica.vjezba4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController extends ResourceServerConfigurerAdapter {

	@Autowired
	private MqttPublisher publisher;

	@PostMapping(value = "/send_message")
	public void send_message(@RequestBody Message message) throws InterruptedException {
		this.publisher.publish(message);
	}

}
