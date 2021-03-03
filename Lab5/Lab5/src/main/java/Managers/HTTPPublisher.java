package Managers;

import Classes.Sensor;
import com.google.gson.Gson;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class HTTPPublisher implements Publisher {
    private HttpClient client;
    public HttpRequest request;
    private String address;
    private Gson gson = new Gson();
    private List<Sensor> sensors;

    public HTTPPublisher(String address, List<Sensor> sensors) {
        this.address = address;
        this.sensors = sensors;
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .build();
    }

    @Override
    public void publish() {
        sensors.stream().map(sensor -> {
            String data = gson.toJson(sensor);

            this.request = HttpRequest.newBuilder()
                    .uri(URI.create(this.address))
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();
            return true;
        }).forEach(System.out::println);
    }
}
