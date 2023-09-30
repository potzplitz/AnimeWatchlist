package preload;

import java.io.IOException;

import api.HTTPApi;
import database.CreateFileStructure;
import database.Import;
import main.AWLGui;

@SuppressWarnings("unused")
public class Datachecker {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		
		CreateFileStructure create = new CreateFileStructure();
		create.createFileStructure();
		
		AWLGui start = new AWLGui();
		start.Gui();
		
		String name = "";
		
	//	Import importer = new Import();
	//	importer.AnimeImport();
		
		
		//GetAnimeImage image = new GetAnimeImage();
	//	image.getAnimeImage(name);
		
	}

}
