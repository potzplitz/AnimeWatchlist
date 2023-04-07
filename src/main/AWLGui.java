package main;



import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import database.AddAnime;
import database.EditAnime;



@SuppressWarnings("serial")
class Frame extends JFrame{
	 
	Frame(String title) {
	    super(title);
	    
	    setSize(500, 500);
	    setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setLayout(null);
	    

	  }
	 
 }

public class AWLGui {
	
	private boolean gesehenboolean;
	private boolean gesehenbooleane;
	private String oldname;
	private String oldnamestore;

	private String buttonpress;
	
	public void Gui() throws IOException {
		
		Frame gui = new Frame("Anime Watch List");
		gui.setVisible(true);
		
		JFrame addframe = new JFrame("Anime hinzufügen...");
		addframe.setSize(400, 300);
		addframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addframe.setLayout(null);
		addframe.setResizable(false);
		addframe.setVisible(false);
		
		JLabel addanimelabel = new JLabel("Anime Name");
		addanimelabel.setBounds(40, 10, 100, 30);
		
		JLabel taglabel = new JLabel("Tags (Tags mit Komma trennen)");
		taglabel.setBounds(40, 70, 200, 30);
		
		JLabel genrelabel = new JLabel("Genre");
		genrelabel.setBounds(40, 130, 100, 30);
		
		TextField animename = new TextField();
		animename.setBounds(40, 40, 300, 30);
		
		TextField tags = new TextField();
		tags.setBounds(40, 100, 300, 30);
		
		TextField genre = new TextField();
		genre.setBounds(40, 160, 300, 30);
		
		JCheckBox gesehenbool = new JCheckBox("Gesehen");
		gesehenbool.setBounds(150, 185, 100, 30);
		
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
		
		
		
		JPanel gesehen = new JPanel();
		gesehen.setBounds(0, 50, 250, 411);
		gesehen.setBackground(Color.LIGHT_GRAY);
		
		JPanel infos = new JPanel();
		infos.setBounds(250, 50, 250, 450);
		infos.setLayout(null);
		infos.setBackground(Color.WHITE);
		
		JPanel nichtgesehen = new JPanel();
		nichtgesehen.setBounds(0, 50, 250, 411);
		
		JLabel gesehenjn = new JLabel("Gesehen: ");
		gesehenjn.setBounds(5, 30, 300, 30);
		
		JLabel genrename = new JLabel("Genre:  ");
		genrename.setBounds(5, 50, 300, 30);
		
		JLabel animelabel = new JLabel("Infos");
		animelabel.setFont(new Font(null, Font.BOLD, 14));
		animelabel.setBounds(10, 1, 300, 30);
		
		JLabel addanimelabele = new JLabel("Anime Name");
		addanimelabele.setBounds(40, 10, 100, 30);
		
		JLabel taglabele = new JLabel("Tags (Tags mit Komma trennen)");
		taglabele.setBounds(40, 70, 200, 30);
		
		JLabel genrelabele = new JLabel("Genre");
		genrelabele.setBounds(40, 130, 100, 30);
		
		TextField animenamee = new TextField();
		animenamee.setBounds(40, 40, 300, 30);
		
		TextField tagse = new TextField();
		tagse.setBounds(40, 100, 300, 30);
		
		TextField genree = new TextField();
		genree.setBounds(40, 160, 300, 30);
		
		JCheckBox gesehenboole = new JCheckBox("Gesehen");
		gesehenboole.setBounds(150, 185, 100, 30);
		
		gesehenboole.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
			    	gesehenbooleane = true;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {		
					gesehenbooleane = false;		
				}		
	     	}	
    	});
		
		Button confirme = new Button("Speichern");
		confirme.setBounds(135, 220, 100, 30);
		
		JLabel editanimelabel = new JLabel();
		editanimelabel.setBounds(20, 1, 400, 30);
		
		JLabel separator = new JLabel("________________________________________________________________________________________________");
		separator.setBounds(0, 6, 500, 30);
		
		JLabel separator2 = new JLabel("________________________________________________________________________________________________");
		separator2.setBounds(0, 350, 500, 30);
		
		JCheckBox gesehenyn = new JCheckBox("gesehen");
		gesehenyn.setBounds(20, 377, 100, 30);
		gesehenyn.setBackground(Color.WHITE);
		
		File file = new File("C:\\AnimeWatchList\\Database");
		File[] listOfFilesGesehen = file.listFiles();
		int animecount = file.list().length;

		Button edit = new Button("Anime bearbeiten");
		edit.setBounds(110, 377, 100, 30);
		
		JFrame editwindow = new JFrame("Anime bearbeiten [WORK IN PROGRESS]");
		editwindow.setSize(400, 300);
		editwindow.setResizable(false);
		editwindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		editwindow.setLayout(null);
		editwindow.setVisible(false);
		
		
		for(int i = 0; i < animecount; i++) {	
			String filename = listOfFilesGesehen[i].getName() + "";	
			filename = filename.substring(0, filename.lastIndexOf("."));

			gesehen.setLayout(new GridLayout(animecount, 1, 1 ,1));
			nichtgesehen.setLayout(new GridLayout(animecount, 1, 1 ,1));
			
			JButton anime = new JButton(filename);
			anime.setPreferredSize(new  Dimension(250, 50));
			anime.setBackground(Color.LIGHT_GRAY);
	
			Map<String, JButton> buttonMap = new HashMap<>();
			buttonMap.put(filename, anime);
			
			buttonpress = buttonMap.keySet() + "";
	
			anime.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {		
					String buttonname = buttonMap.keySet() + "";	
					buttonname = buttonname.substring(1, buttonname.lastIndexOf("]"));
					
					animelabel.setText(buttonname);
					try {
						gesehenjn.setText("Gesehen:   " + Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(3));
						genrename.setText("Genre:        " + Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(1));
					} catch (IOException e1) {e1.printStackTrace();}			
					//temp
					try {
						tagse.setText(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(2));
						genree.setText(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(1));
						gesehenboole.setSelected(Boolean.parseBoolean(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(3)));
						gesehenyn.setSelected(Boolean.parseBoolean(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(3)));
						animenamee.setText(buttonname);			
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						
				}
			});
			if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("true")) {
				gesehen.add(anime);
			} else if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("false")) {
				nichtgesehen.add(anime);
			}
			gesehen.repaint();
			gui.setSize(500, 499);
			gui.repaint();
			gui.setSize(500, 500);			
		}
		
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String buttonname = buttonpress;			
				buttonname = buttonname.substring(1, buttonname.lastIndexOf("]"));
				editwindow.setVisible(true);		
				confirme.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String buttonname = buttonpress;
						buttonname = buttonname.substring(1, buttonname.lastIndexOf("]"));
						EditAnime edit = new EditAnime();
						try {
							edit.editAnime(oldname, animenamee.getText(), genree.getText(), tagse.getText(), gesehenbooleane);
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
					}	
				});
			}	
		});
		
		final JScrollPane scroll_1 = new JScrollPane(gesehen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_1.setBounds(0, 50, 250, 411);
		scroll_1.getVerticalScrollBar().setUnitIncrement(10);
		
		gui.add(scroll_1);
		
		final JScrollPane scroll_2 = new JScrollPane(nichtgesehen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_2.setBounds(0, 50, 250, 411);
		scroll_2.getVerticalScrollBar().setUnitIncrement(10);
		scroll_2.setForeground(Color.LIGHT_GRAY);
		scroll_2.setVisible(false);
		
		gui.add(scroll_2);
		
	    
		
		
		Button gesehenbutton = new Button("gesehen");
		gesehenbutton.setBounds(0, 0, 250, 50);
		
		Button watchbutton = new Button("nicht gesehen");
		watchbutton.setBounds(0, 0, 250, 50);
		
		Button add = new Button("+");
		add.setBounds(434, 0, 50, 50);
		
		Button confirm = new Button("hinzufügen");
		confirm.setBounds(135, 220, 100, 30);
		
		gesehenbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				
				scroll_1.setVisible(false);
				scroll_2.setVisible(true);
				
				gesehenbutton.setVisible(false);
				watchbutton.setVisible(true);
			}	
		});
		
		watchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scroll_1.setVisible(true);
				scroll_2.setVisible(false);	
				
				
				gesehenbutton.setVisible(true);
				watchbutton.setVisible(false);
			}	
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				addframe.setVisible(true);			
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAnime adder = new AddAnime();
				addframe.setVisible(true);
				try {
					adder.addAnime(animename.getText(), genre.getText(), tags.getText(), gesehenboolean);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}	
		});
		
		
		

	
		gesehenbutton.setVisible(true);
		watchbutton.setVisible(false);
		animename.setVisible(true);
		addanimelabel.setVisible(true);
		add.setVisible(true);
		tags.setVisible(true);
		taglabel.setVisible(true);
		gesehenbool.setVisible(true);
		confirm.setVisible(true);
		genre.setVisible(true);
		genrelabel.setVisible(true);
		infos.setVisible(true);
		gesehenjn.setVisible(true);
		animelabel.setVisible(true);
		separator.setVisible(true);
		genrename.setVisible(true);
		edit.setVisible(true);
		separator2.setVisible(true);
		editanimelabel.setVisible(true);
		confirme.setVisible(true);
		gesehenyn.setVisible(true);
		addframe.add(genrelabel);
		addframe.add(genre);
		addframe.add(confirm);
		addframe.add(gesehenbool);
		addframe.add(taglabel);
		addframe.add(tags);
		addframe.add(animename);
		addframe.add(addanimelabel);
		editwindow.add(genrelabele);
		editwindow.add(genree);
		editwindow.add(confirme);
		editwindow.add(gesehenboole);
		editwindow.add(taglabele);
		editwindow.add(tagse);
		editwindow.add(animenamee);
		editwindow.add(addanimelabele);
		editwindow.add(editanimelabel);
		infos.add(gesehenjn);
		infos.add(animelabel, SwingConstants.CENTER);
		infos.add(separator);
		infos.add(genrename);
		infos.add(edit);
		infos.add(separator2);
		infos.add(gesehenyn);
		gui.add(infos);
		gui.add(add);
		gui.add(watchbutton);
		gui.add(gesehenbutton);
		
		gui.repaint();
	}

	}

