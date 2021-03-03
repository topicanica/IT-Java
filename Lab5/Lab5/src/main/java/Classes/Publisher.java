package Classes;

import java.util.List;

public class Publisher {
    public Publisher(String method, String address, List<Sensor> sensors) {
        MethodManager methodManager = new MethodManager(method, address, sensors);
        methodManager.publish();
    }
}
