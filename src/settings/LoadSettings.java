package settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class LoadSettings {

	static boolean darkmode;
	static int threadspeed;

	public void load() throws IOException {
		
		String configjson = Files.readAllLines(Paths.get("C:\\AnimeWatchList\\config\\config.json")).get(0);

		JSONObject obj = new JSONObject(configjson);

		darkmode = obj.getBoolean("DarkMode");
		threadspeed = obj.getInt("ThreadSpeed");
		
	}

	public boolean getDarkmode() {
		return darkmode;
	}

	public int getThreadspeed() {
		return threadspeed;
	}

}
