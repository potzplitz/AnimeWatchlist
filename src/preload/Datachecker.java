package preload;

import java.io.IOException;

import database.CreateFileStructure;
import main.AWLGui;

public class Datachecker {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		
		CreateFileStructure create = new CreateFileStructure();
		create.createFileStructure();
		
		AWLGui start = new AWLGui();
		start.Gui();
		
	}

}
