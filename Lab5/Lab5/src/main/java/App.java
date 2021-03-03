import java.io.IOException;

import Classes.Watermeter;

public class App {

	public static void main(String args[]) throws IOException, InterruptedException {
		Watermeter watermeter = Watermeter.createWatermeter("config.json");
		watermeter.publish();
	}
}
