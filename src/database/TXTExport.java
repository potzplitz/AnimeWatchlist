package database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TXTExport {
	
	public void export(String path) throws IOException {
	
		
		PrintWriter writer = new PrintWriter(path + "\\Watchlist.txt");
		
		File file = new File("C:\\AnimeWatchList\\Database");
		File[] listOfFilesGesehen = file.listFiles();
		int animecount = file.list().length;
		
		writer.println("_________________________________________________________________"); 
		writer.println("|                                                               |"); 
		writer.println("|                          Gesehen                              |"); 
		writer.println("|_______________________________________________________________|");
		
		int gesehen = 0;
		int nichtgesehen = 0;
		
		for(int i = 1; i < animecount; i++) {
				String filename = listOfFilesGesehen[i].getName() + "";	
			 filename = filename.substring(0, filename.lastIndexOf("."));
		
			 if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("true")) {
				gesehen++;
				writer.println("|" + gesehen + ": " + filename);
				}	
		}
		
		writer.println("|________________________________________________________________");
		writer.println(" ");
		writer.println(" ");
		writer.println(" ");
		writer.println("_________________________________________________________________"); 
		writer.println("|                                                               |"); 
		writer.println("|                         nicht Gesehen                         |");
		writer.println("|_______________________________________________________________|");
		
		for(int i = 1; i < animecount; i++) {
			String filename = listOfFilesGesehen[i].getName() + "";	
		 filename = filename.substring(0, filename.lastIndexOf("."));
	
		 if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("false")) {
			nichtgesehen++; 
			writer.println("|" + nichtgesehen + ": " + filename);
			}	
	}
		writer.println("|________________________________________________________________"); 
		writer.close();
		
		
	}

}
