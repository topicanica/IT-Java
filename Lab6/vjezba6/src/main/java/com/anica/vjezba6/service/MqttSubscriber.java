package com.anica.vjezba6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber implements MqttCallback {

	private MqttClient client;
	private static final List<String> messages = new ArrayList<>();

	public MqttSubscriber() {
	}

	public void subscribe(String broker, String port, String topic) {
		try {
			this.client = new MqttClient(broker, UUID.randomUUID().toString());
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			client.connect();
			client.setCallback(this);
			client.subscribe(topic);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}

	}

	public void disconnect() {
		try {
			messages.clear();
			client.disconnect();

		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public List<String> getMessages() {
		return messages;
	}

	public MqttClient getClient() {
		return client;
	}

	public void setClient(MqttClient client) {
		this.client = client;
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		messages.add(message.toString());

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
}
