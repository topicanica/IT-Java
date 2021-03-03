package Classes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Watermeter {

    private String address;
    public String method;
    private List<Sensor> sensors;

    public void publish() throws InterruptedException, IOException {
        Publisher publisher = new Publisher(method, address, sensors);
    }

    public static Watermeter createWatermeter(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File(path), Watermeter.class);
    }


    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

