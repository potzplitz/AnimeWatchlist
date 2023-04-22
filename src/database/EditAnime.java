package database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EditAnime {
	
public void editAnime(boolean state, String animeName, String genre, String tags, boolean gesehen, int rating) throws IOException {
		
		System.out.println("Old Anime Name: "+ " Anime Name: " + animeName + " Genre: " + genre + " Tags: " + tags + " Gesehen: " + gesehen);
		
		File file = new File("C:\\AnimeWatchList\\Database\\" + animeName + ".anime");
		
		if(state == true) {
		
		file.delete();
		file.createNewFile();
		
		PrintWriter writer = new PrintWriter("C:\\AnimeWatchList\\Database\\" + animeName + ".anime", "UTF-8");
		writer.println(animeName);
		writer.println(genre);
		writer.println(tags);
		writer.println(gesehen);
		writer.println(rating);
		writer.close();

		
		} else if(state == false) {
			
			file.delete();
			
		}
		
		
		
	}

}
