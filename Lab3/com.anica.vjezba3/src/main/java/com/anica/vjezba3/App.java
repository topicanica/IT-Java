package com.anica.vjezba3;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;



public class App {

	public static void main(String[] args) throws MqttException, InterruptedException, IOException {
		String wfmConfigFile = "waterflowmeter.json";
		WaterFlowMeter flowMeter = WaterFlowMeter.setConfigFile(wfmConfigFile);
		flowMeter.publish();

	}
}
