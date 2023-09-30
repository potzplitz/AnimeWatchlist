package database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EditAnime {
	
	
// Variablen für das Editieren    löschen         name            genre          tags          gesehen      bewertung
     public void editAnime(boolean state, String animeName, String genre, String tags, boolean gesehen, int rating) throws IOException {
		
    	 // Anime datenbankdatei wird erstellt
		File file = new File("C:\\AnimeWatchList\\Database\\" + animeName + ".anime");
		
		// testen, ob gelöscht werden soll oder nicht
		if(state == true) { // nein
		
		file.delete();
		file.createNewFile();
		
		PrintWriter writer = new PrintWriter("C:\\AnimeWatchList\\Database\\" + animeName + ".anime", "UTF-8");
		writer.println(animeName);
		writer.println(genre);
		writer.println(tags);
		writer.println(gesehen);
		writer.println(rating);
		writer.close();

		
		} else if(state == false) { // ja
			
			file.delete();
			
		}
		
		
		
	}

}
