package settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class LoadSettings {

	static boolean darkmode;
	static int threadspeed;

	public void load() throws IOException {
		
		if(new File("C:\\AnimeWatchList\\config\\config.json").exists()) {

			String configjson = Files.readAllLines(Paths.get("C:\\AnimeWatchList\\config\\config.json")).get(0);

			JSONObject obj = new JSONObject(configjson);

			darkmode = obj.getBoolean("DarkMode");
			threadspeed = obj.getInt("ThreadSpeed");
		} else {
			
			System.out.println("config not found");
			
		}
		
		
	}

	public boolean getDarkmode() {
		return darkmode;
	}

	public int getThreadspeed() {
		return threadspeed;
	}

}
