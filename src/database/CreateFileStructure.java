package database;

import java.io.File;
import java.io.IOException;

public class CreateFileStructure {
	
	public void createFileStructure() throws IOException {
		
		
		File file = new File("C:\\AnimeWatchList\\Database");
			file.mkdirs();
			
			File files = new File("C:\\AnimeWatchList\\config");
			files.mkdirs();
			
		File settings = new File("C:\\AnimeWatchList\\config\\config.json");
			settings.createNewFile();
			
			
		
	}

}
