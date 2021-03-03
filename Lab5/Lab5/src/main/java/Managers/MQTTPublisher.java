package Managers;

import Classes.Sensor;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class MQTTPublisher implements Publisher {
    private String BROKER;
    private String client = "Stipo123";
    private MqttClient mqttClient;
    private List<Sensor> sensors;
    private Gson gson = new Gson();

    public MQTTPublisher(String address, List<Sensor> sensors) {
        this.BROKER = address;
        this.sensors = sensors;
        try {
            mqttClient = new MqttClient(BROKER, client);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void publish() {
        MqttMessage mqttMessage = new MqttMessage();
        sensors.stream().map(sensor -> {
            sensor.RandomizeCurrentValue();
            String data = gson.toJson(sensor);
            mqttMessage.setPayload(data.getBytes());
            try {
                this.mqttClient.connect();
                this.mqttClient.publish("topic", mqttMessage);
                this.mqttClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            return true;
        }).forEach(System.out::println);
    }
}
