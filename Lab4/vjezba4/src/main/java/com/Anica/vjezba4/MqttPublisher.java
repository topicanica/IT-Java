package com.Anica.vjezba4;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MqttPublisher {

	private MqttClient client;

	public void publish(Message msg) {

		try {
			this.client = new MqttClient(msg.getBroker(), UUID.randomUUID().toString());
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			this.client.connect(connOpts);
			this.client.publish(msg.getTopic(), new MqttMessage(msg.getMessage().getBytes()));
			System.out.println(msg.toString());
			this.client.disconnect();
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
