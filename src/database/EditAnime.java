package database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EditAnime {
	
public void editAnime(String oldAnimeName, String animeName, String genre, String tags, boolean gesehen) throws IOException {
		
		System.out.println("Old Anime Name: "+ oldAnimeName + " Anime Name: " + animeName + " Genre: " + genre + " Tags: " + tags + " Gesehen: " + gesehen);
		
		File file = new File("C:\\AnimeWatchList\\Database\\" + animeName + ".anime");
		File oldanime = new File("C:\\AnimeWatchList\\Database\\" + oldAnimeName + ".anime");
		oldanime.delete();
		file.createNewFile();
		
		PrintWriter writer = new PrintWriter("C:\\AnimeWatchList\\Database\\" + animeName + ".anime", "UTF-8");
		writer.println(animeName);
		writer.println(genre);
		writer.println(tags);
		writer.println(gesehen);
		writer.close();
		
	}

}
