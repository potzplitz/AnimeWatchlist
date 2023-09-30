package database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AddAnime {
	
	public void addAnime(String animeName, String genre, String tags, boolean gesehen, int rating) throws IOException {
		
		
		// .anime-Datei erstellen
		File file = new File("C:\\AnimeWatchList\\Database\\" + animeName + ".anime");
		file.createNewFile();
		
		
		
		// Informationen in die datei schreiben
		PrintWriter writer = new PrintWriter("C:\\AnimeWatchList\\Database\\" + animeName + ".anime", "UTF-8");
		writer.println(animeName);
		writer.println(genre);
		writer.println(tags);
		writer.println(gesehen);
		writer.println(rating);
		writer.close();
		
		
		
	}

}
