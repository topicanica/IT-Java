package com.anica.vjezba3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WaterFlowMeter {
	@JsonProperty
    private String broker;
    @JsonProperty
    private ArrayList<Sensor> sensors;
    @JsonIgnore
    private MqttClient client;
	
	@JsonCreator
    public WaterFlowMeter(@JsonProperty("broker") String broker, @JsonProperty("sensors") ArrayList<Sensor> sensors) {
		this.broker = broker;
        this.sensors = sensors;
    }
	@JsonIgnore
	public static WaterFlowMeter setConfigFile(String ConfigFile) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		WaterFlowMeter flowMeter = mapper.readValue(new File(ConfigFile), WaterFlowMeter.class);
		return flowMeter;
	}
	public void publish() throws InterruptedException, MqttException  { 
		MemoryPersistence persistence = new MemoryPersistence();
		int qos =2;
		this.client = new MqttClient(broker,UUID.randomUUID().toString(),persistence);
		while (true) {
			for (Sensor sensor : this.sensors){
				try {
					MqttConnectOptions connectivityOptions = new MqttConnectOptions();
					connectivityOptions.setCleanSession(true);
					System.out.println("Connecting to broker: " + this.broker);
					this.client.connect(connectivityOptions);
		            System.out.println("Connected");
		            String topic = sensor.getName();
					String data = sensor.getData();
					System.out.println("Publishing message: " + data);
					MqttMessage message = new MqttMessage(data.getBytes());
					message.setQos(qos);
					this.client.publish(topic, message);
					sensor.setData();
					System.out.println("Message published");
	                this.client.disconnect();
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
		
	}
	
}
