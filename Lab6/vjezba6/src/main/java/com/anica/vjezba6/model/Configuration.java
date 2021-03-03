package com.anica.vjezba6.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "configurations")
public class Configuration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String broker;

	private String serverPort;

	private String topic;

	@OneToOne(mappedBy = "configuration")
	User user;

	public Configuration() {
	}

	public Configuration(String broker, String serverPort, String topic, User user) {
		this.broker = broker;
		this.serverPort = serverPort;
		this.topic = topic;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
