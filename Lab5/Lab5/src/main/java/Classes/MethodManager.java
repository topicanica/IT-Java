package Classes;

import Managers.HTTPPublisher;
import Managers.MQTTPublisher;
import Managers.Publisher;

import java.util.List;

public class MethodManager implements Publisher {
    public String method;
    private String address;
    private final String mqtt = "MQTT";
    private final String http = "HTTP";
    List<Sensor> sensors;

    public MethodManager(String method, String address, List<Sensor> sensors) {
        this.method = method;
        this.address = address;
        this.sensors = sensors;
    }

    private boolean isMQTT() {
        String METHOD = this.method.toUpperCase();

        return METHOD.equals(mqtt);
    }

    private boolean isHTTP() {
        String METHOD = this.method.toUpperCase();

        return METHOD.equals(http);
    }

    @Override
    public void publish() {
        if (this.isMQTT()) {
            MQTTPublisher mqttPublisher = new MQTTPublisher(address, sensors);
            mqttPublisher.publish();
        } else if(this.isHTTP()){
            HTTPPublisher httpPublisher = new HTTPPublisher(address, sensors);
            httpPublisher.publish();
        }else {
            System.out.println("Not allowed method");
        }
    }
}
