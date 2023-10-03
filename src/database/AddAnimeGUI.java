package database;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import api.SearchAnime;

public class AddAnimeGUI {

	private boolean gesehenboolean;
	
	public void AnimeGUI(String name, boolean Search) {
		
		JFrame addframe = new JFrame("Anime hinzufügen...");
		addframe.setSize(400, 400);
		addframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addframe.setLayout(null);
		addframe.setResizable(false);
		addframe.setVisible(true);
		
		JLabel addanimelabel = new JLabel("Anime Name");
		addanimelabel.setBounds(40, 10, 100, 30);
		
		JLabel taglabel = new JLabel("Tags (Tags mit Komma trennen)");
		taglabel.setBounds(40, 70, 200, 30);
		
		JLabel genrelabel = new JLabel("Genre");
		genrelabel.setBounds(40, 130, 100, 30);
		
		TextField animename = new TextField(name, 16);
		animename.setBounds(40, 40, 300, 30);
		
		TextField tags = new TextField(null, 16);
		tags.setBounds(40, 100, 300, 30);
		
		TextField genre = new TextField(null, 16);
		genre.setBounds(40, 160, 300, 30);
		
		String[] ratingarray = {"0", "1", "2", "3", "4", "5"};
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox ratingselect = new JComboBox(ratingarray);
		ratingselect.setBounds(40, 210, 300, 30);
		
		JCheckBox gesehenbool = new JCheckBox("Gesehen");
		gesehenbool.setBounds(150, 260, 100, 30);
		gesehenbool.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
			    	gesehenboolean = true;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {		
					gesehenboolean = false;		
				}		
	     	}	
    	});
		
		Button confirm = new Button("hinzufügen");
		confirm.setBounds(135, 320, 100, 30);
		
		Button getanime = new Button("Animes suchen");
		getanime.setBounds(140, 0, 100, 30);
		
		
		if(Search == true) {		
			getanime.setVisible(false);	
		} else if(Search == false) {		
			getanime.setVisible(true);
		}
		
		

		getanime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchAnime anime = new SearchAnime();
				anime.search();
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAnime adder = new AddAnime();
				addframe.setVisible(false);
				try {
					adder.addAnime(animename.getText(), genre.getText(), tags.getText(), gesehenboolean, Integer.parseInt(ratingselect.getSelectedItem() + ""));
				} catch (IOException e1) {e1.printStackTrace();}
			}	
		});
		
		addframe.add(genrelabel);
		addframe.add(genre);
		addframe.add(confirm);
		addframe.add(gesehenbool);
		addframe.add(taglabel);
		addframe.add(tags);
		addframe.add(animename);
		addframe.add(addanimelabel);
		addframe.add(ratingselect);
		addframe.add(getanime);
		
		
	}
	
}
