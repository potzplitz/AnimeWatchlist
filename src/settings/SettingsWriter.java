package settings;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class SettingsWriter {
	
	public void write(int threadSpeed, boolean darkmode) throws IOException {
		
		// JSONObject - Instanz erstellen
		JSONObject writeSettings = new JSONObject();
		
		
		// Settings in JSON - Format bringen
		writeSettings.put("DarkMode", darkmode);
		writeSettings.put("ThreadSpeed", threadSpeed);
		
		
		// FileWriter instanz erstellen
		FileWriter writer = new FileWriter("C:\\AnimeWatchList\\config\\config.json");
		
		System.out.println("Write " + writeSettings.toString());
		
		// JSON in txt dokument schreiben
		writer.write(writeSettings.toString());
		
		writer.flush();
		writer.close();
	}

}
