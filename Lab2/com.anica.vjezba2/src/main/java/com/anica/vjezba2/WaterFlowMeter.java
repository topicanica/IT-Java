package com.anica.vjezba2;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class WaterFlowMeter {
	private int sensorNum;
	private Sensor sensors[];
	
	public WaterFlowMeter()
	{
		this.sensorNum = 4;
		this.sensors = new Sensor[this.sensorNum];
		this.sensors[0] = new Sensor("Current temperature", 10, -32668, 32668, " °C");
		this.sensors[1] = new Sensor("Current pressure", 1000, 0, 65336, " Bar");
		this.sensors[2] = new Sensor("Consumption in the last 1 minute, 10 minutes, 1 hour and 1 day", 0, 0, 65336, " liters");
		this.sensors[3] = new Sensor("Consumption in the last 1 week, 1 month, 1 year", 10, 0, 65336, " liters");
	}
	public void publish() throws InterruptedException, MqttException  { 
		String broker = "tcp://localhost:1883";
		MemoryPersistence persistence = new MemoryPersistence();
		int qos =2;
		for (Sensor sensor : sensors){
			try {
				MqttClient client = new MqttClient(broker,UUID.randomUUID().toString(),persistence);
				MqttConnectOptions connectivityOptions = new MqttConnectOptions();
				connectivityOptions.setCleanSession(true);
				System.out.println("Connecting to broker: " + broker);
				client.connect(connectivityOptions);
	            System.out.println("Connected");
	            String topic = sensor.getName();
				String data = sensor.getData();
				System.out.println("Publishing message: " + data);
				MqttMessage message = new MqttMessage(data.getBytes());
				message.setQos(qos);
				client.publish(topic, message);
				System.out.println("Message published");
                client.disconnect();
                client.close();
                System.out.println("Disconnected" + "\n");

			}catch (MqttException exception)
			{
				System.out.println("reason "+exception.getReasonCode());
	            System.out.println("msg "+exception.getMessage());
	            System.out.println("loc "+exception.getLocalizedMessage());
	            System.out.println("cause "+exception.getCause());
	            System.out.println("excep "+exception);
	            exception.printStackTrace();
			}
			TimeUnit.SECONDS.sleep(5);		
		}
	}
	
	public static void main(String[] args) throws InterruptedException, MqttException {
		WaterFlowMeter waterMeter = new WaterFlowMeter();
		waterMeter.publish();
	}
}

