package com.Anica.vjezba4;

public class Message {

	private String topic;
	private String message;
	private String broker;

	public Message() {

	}

	public Message(String broker, String topic, String message) {
		this.topic = topic;
		this.message = message;
		this.broker = broker;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBroker() {
		return this.broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message{" + "topic=" + topic + ", message=" + message + '}';
	}

}
