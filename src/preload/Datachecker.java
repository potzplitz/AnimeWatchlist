package preload;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import api.HTTPApi;
import database.CreateFileStructure;
import main.AWLGui;
import settings.LoadSettings;

@SuppressWarnings("unused")
public class Datachecker {
	
	public static void main(String[] args) throws IOException, InterruptedException, UnsupportedLookAndFeelException {

		
		CreateFileStructure create = new CreateFileStructure();
		create.createFileStructure();
		
		AWLGui start = new AWLGui();
		start.Gui();
		
		String name = "";

	
		
	}

}
