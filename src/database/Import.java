package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Import {
	
	public void AnimeImport() throws IOException {
		
		File file = new File("C:\\Users\\jansc\\Desktop\\liste.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line;
		
		while(true) {
			
			line = reader.readLine();
			
			if(line.contains("|*")) {
				break;	
			}
			
			
			line = line.substring(5, 58);
			
			System.out.println(line);
			
		}
		
		
	}

}
                                                                                                                                                                                                                                                                                                           