package main;



import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import api.GetAnimeImage;
import api.SearchAnime;
import database.AddAnime;
import database.EditAnime;
import database.TXTExport;



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

	private int geseheneanimes;
	private int nichtgeseheneanimes;

	private String buttonpress;
	
	public void Gui() throws IOException, InterruptedException {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		Frame gui = new Frame("Anime Watch List");
		gui.setVisible(false);
		gui.setLocation(dim.width/2-gui.getSize().width/2, dim.height/2-gui.getSize().height/2);
		
		JFrame addframe = new JFrame("Anime hinzufügen...");
		addframe.setSize(400, 400);
		addframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addframe.setLayout(null);
		addframe.setResizable(false);
		addframe.setVisible(false);
		
		JFrame exporter = new JFrame("Liste exportieren...");
		exporter.setSize(400, 300);
		exporter.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		exporter.setLayout(null);
		exporter.setResizable(false);
		exporter.setVisible(false);
		
		JLabel addanimelabel = new JLabel("Anime Name");
		addanimelabel.setBounds(40, 10, 100, 30);
		
		JLabel taglabel = new JLabel("Tags (Tags mit Komma trennen)");
		taglabel.setBounds(40, 70, 200, 30);
		
		JLabel genrelabel = new JLabel("Genre");
		genrelabel.setBounds(40, 130, 100, 30);
		
		TextField animename = new TextField(null);
		animename.setBounds(40, 40, 300, 30);
		
		TextField tags = new TextField(null);
		tags.setBounds(40, 100, 300, 30);
		
		TextField genre = new TextField(null);
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

		JPanel gesehen = new JPanel();
		gesehen.setLocation(0, 50);
		gesehen.setBackground(Color.LIGHT_GRAY);
		
		JPanel infos = new JPanel();
		infos.setBounds(250, 50, 250, 450);
		infos.setLayout(null);
		infos.setBackground(Color.WHITE);
		
		JProgressBar load = new JProgressBar();
		
		JPanel nichtgesehen = new JPanel();
		nichtgesehen.setLocation(0, 50);
		nichtgesehen.setBackground(Color.LIGHT_GRAY);
		
		JLabel gesehenjn = new JLabel("Gesehen: ");
		gesehenjn.setBounds(5, 30, 300, 30);
		
		JLabel genrename = new JLabel("Genre:  ");
		genrename.setBounds(5, 50, 300, 30);
		
		JLabel rating = new JLabel("Bewertung: ");
		rating.setBounds(5, 70, 300, 30);
		
		JLabel animelabel = new JLabel("Infos");
		animelabel.setFont(new Font(null, Font.BOLD, 14));
		animelabel.setBounds(10, 1, 300, 30);
		
		JLabel addanimelabele = new JLabel("Anime Name");
		addanimelabele.setBounds(40, 10, 100, 30);
		
		JLabel taglabele = new JLabel("Tags (Tags mit Komma trennen)");
		taglabele.setBounds(40, 70, 200, 30);
		
		JLabel genrelabele = new JLabel("Genre");
		genrelabele.setBounds(40, 130, 100, 30);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox ratingselecte = new JComboBox(ratingarray);
		ratingselecte.setBounds(40, 210, 300, 30);
		
		TextField animenamee = new TextField(null);
		animenamee.setBounds(40, 40, 300, 30);
		
		TextField tagse = new TextField(null);
		tagse.setBounds(40, 100, 300, 30);
		
		TextField genree = new TextField(null);
		genree.setBounds(40, 160, 300, 30);
		
		JTextField searchbar = new JTextField();
		searchbar.setBounds(300, 1, 137, 50);
		
		JTextField path = new JTextField("Pfad festlegen");
		path.setBounds(60, 100, 250, 30);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setVisible(false);
		
		JLabel exportinfo = new JLabel("Animes exportieren");
		exportinfo.setBounds(114, 1, 300, 30);
		exportinfo.setFont(exportinfo.getFont().deriveFont(15f));
		
		JLabel count = new JLabel();
		count.setBounds(54, 170, 300, 30);
		
		JLabel separator3 = new JLabel("___________________________________________________________________________________");
		separator3.setBounds(0, 14, 500, 30);

		JCheckBox gesehenboole = new JCheckBox("Gesehen");
		gesehenboole.setBounds(150, 260, 100, 30);
		
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
		confirme.setBounds(135, 320, 100, 30);
		
		JLabel editanimelabel = new JLabel();
		editanimelabel.setBounds(20, 1, 400, 30);
		
		JLabel separator = new JLabel("________________________________________________________________________________________________");
		separator.setBounds(0, 6, 500, 30);
		
		JLabel separator2 = new JLabel("________________________________________________________________________________________________");
		separator2.setBounds(0, 350, 500, 30);
		
		File file = new File("C:\\AnimeWatchList\\Database");
		File[] listOfFilesGesehen = file.listFiles();
		int animecount = file.list().length;

		Button edit = new Button("Anime bearbeiten");
		edit.setBounds(123, 377, 100, 30);
		
		Button delete = new Button("Anime löschen");
		delete.setBounds(10, 377, 100, 30);
		
		JFrame editwindow = new JFrame("Anime bearbeiten [WORK IN PROGRESS]");
		editwindow.setSize(400, 400);
		editwindow.setResizable(false);
		editwindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		editwindow.setLayout(null);
		editwindow.setVisible(false);
		
		JFrame loadwindow = new JFrame("Datenbank wird gelesen...");
		loadwindow.setSize(400, 200);
		loadwindow.setUndecorated(true);
		loadwindow.setResizable(false);
		loadwindow.setLocation(dim.width/2-loadwindow.getSize().width/2, dim.height/2-loadwindow.getSize().height/2);
		loadwindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		loadwindow.setBackground(Color.LIGHT_GRAY);
		loadwindow.setLayout(null);
		loadwindow.setVisible(true);
		
		JLabel label1 = new JLabel("by potzplitz");
		label1.setBounds(168, 80, 200, 30);
		label1.setVisible(true);
		loadwindow.add(label1);
		
		JLabel loading = new JLabel();
		loading.setBounds(1, 155, 200, 30);
		loadwindow.add(loading);
		
		JLabel label = new JLabel("Anime Watchlist");
		label.setBounds(125, 20, 300, 100);
		label.setFont(label.getFont().deriveFont(20f));
		label.setVisible(true);
		loadwindow.add(label);
		
		GetAnimeImage image = new GetAnimeImage();
		
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem rclickmenu = new JMenuItem("test");
		popup.add(rclickmenu);
		
		
		JLabel img = new JLabel();
		img.setBounds(10, 100, 225, 324);
		img.setBackground(Color.LIGHT_GRAY);
		
		
		//image.apiReader("absolute-duo");
		
		//URL imageget = new URL(image.GetImage());
		
		//img.setIcon(new ImageIcon(ImageIO.read(imageget)));

		
		load.setMinimum(0);
		load.setMaximum(animecount + 1);
		
		loadwindow.add(load);
		load.setBounds(1, 138, 398, 20);
		
		Object[] options = {"OK"};
    	Icon icon = UIManager.getIcon("OptionPane.InfoIcon");
    	

		for(int i = 0; i < animecount; i++) {
			Thread.sleep(0);
			load.setValue(i);

			String filename = listOfFilesGesehen[i].getName() + "";	
			File del = new File("C:\\AnimeWatchList\\Database\\" + filename);
			
			if(!filename.contains(".anime")) {
				del.delete();
				JOptionPane.showOptionDialog(null, "Fehlerhafter Datenbankeintrag: " + filename + ". Dieser wurde gelöscht.", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]);	
			} else {
			
			filename = filename.substring(0, filename.lastIndexOf("."));
			
			loading.setText(filename);
			
			
			JButton anime = new JButton(filename);
			anime.setPreferredSize(new  Dimension(220, 50));
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
					animelabel.setToolTipText(buttonname);
					try {
						gesehenjn.setText("Gesehen:      " + Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(3));
						genrename.setText("Genre:           " + Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(1));
						rating.setText("Bewertung:  " + Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(4) + "/5");
						
						tagse.setText(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(2));
						genree.setText(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(1));
						gesehenboole.setSelected(Boolean.parseBoolean(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(3)));
						ratingselecte.setSelectedItem(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonname + ".anime")).get(4));
						animenamee.setText(buttonname);			
					} catch (IOException e1) {e1.printStackTrace();}
					
					animelabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}
						@Override
						public void mousePressed(MouseEvent e) {		}
						@Override
						public void mouseReleased(MouseEvent e) {
							StringSelection stringSelection = new StringSelection(anime.getText());
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							clipboard.setContents(stringSelection, null);	
							animelabel.setText(anime.getText() + " [kopiert]");
						}
						@Override
						public void mouseEntered(MouseEvent e) {}
						@Override
						public void mouseExited(MouseEvent e) {}	
					});
				}
			});
			
			anime.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					anime.setToolTipText(anime.getText());	
					anime.setBackground(Color.decode("#adc5e0"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					anime.setToolTipText("");	
					anime.setBackground(Color.LIGHT_GRAY);
				}	
			});

			if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("true")) {
				gesehen.add(anime);
				geseheneanimes++;
			} else if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + filename + ".anime")).get(3).equals("false")) {
				nichtgesehen.add(anime);
				nichtgeseheneanimes++;
			}
			searchbar.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					
					String searchInput = searchbar.getText().toLowerCase();
					String buttonName = anime.getText().toLowerCase();
					
					
					 if (buttonName.contains(searchInput)) {
				          try {
							if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\Database\\" + buttonName + ".anime")).get(3).equals("true")) {
							  gesehen.add(anime);
							  gesehen.revalidate();
					          gesehen.repaint();
							} else {
								nichtgesehen.add(anime);
								nichtgesehen.revalidate();
						        nichtgesehen.repaint();
							}    
						} catch (IOException e1) {e1.printStackTrace();}
				        }else if(!buttonName.contains(searchInput)){
				        	if(gesehen.isVisible()) {
				        		gesehen.remove(anime);
				        		gesehen.revalidate();
				        		gesehen.repaint();
				        }  		
				        	if(nichtgesehen.isVisible()) {
				        		nichtgesehen.remove(anime);
				        		nichtgesehen.revalidate();
				        		nichtgesehen.repaint();
				        	}
				    }
				}
		    });
			

			gesehen.repaint();
			nichtgesehen.repaint();
			gui.setSize(500, 499);
			gui.repaint();
			gui.setSize(500, 500);	
			}
		}
		
		
		nichtgesehen.setLayout(new GridLayout(nichtgeseheneanimes, 1, 1 ,1));
		gesehen.setLayout(new GridLayout(geseheneanimes, 1, 1 ,1));
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String buttonname = buttonpress;			
				buttonname = buttonname.substring(1, buttonname.lastIndexOf("]"));
				editwindow.setVisible(true);		
				confirme.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						editwindow.dispose();
						String buttonname = buttonpress;
						buttonname = buttonname.substring(1, buttonname.lastIndexOf("]"));
						EditAnime edit = new EditAnime();
						try {
							
							System.out.println(ratingselecte.getSelectedItem());
							edit.editAnime(true, animenamee.getText(), genree.getText(), tagse.getText(), gesehenbooleane, Integer.parseInt(ratingselecte.getSelectedItem() + ""));
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
					}	
				});
			}	
		});

		
		
		
		final JScrollPane scroll_1 = new JScrollPane(gesehen, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_1.setBounds(0, 50, 250, 411);
		scroll_1.getVerticalScrollBar().setUnitIncrement(10);
		gui.add(scroll_1);
		
		final JScrollPane scroll_2 = new JScrollPane(nichtgesehen, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_2.setBounds(0, 50, 250, 411);
		scroll_2.getVerticalScrollBar().setUnitIncrement(10);
		scroll_2.setForeground(Color.LIGHT_GRAY);
		scroll_2.setVisible(false);
		gui.add(scroll_2);

		Button gesehenbutton = new Button("gesehen " + "(" + geseheneanimes + " Animes)");
		gesehenbutton.setBounds(0, 0, 250, 50);
		
		Button watchbutton = new Button("nicht gesehen " + "(" + nichtgeseheneanimes + " Animes)");
		watchbutton.setBounds(0, 0, 250, 50);
		
		Button add = new Button("+");
		add.setFont(new Font("Arial", Font.PLAIN, 23));
		add.setBounds(434, 0, 50, 50);
		add.setBackground(Color.LIGHT_GRAY);
		
		Button confirm = new Button("hinzufügen");
		confirm.setBounds(135, 320, 100, 30);
		
		Button exportb = new Button("Export");
		exportb.setBounds(250, 0, 50, 50);
		exportb.setBackground(Color.LIGHT_GRAY);
		
		Button yes = new Button("Ja");
		yes.setBounds(74, 200, 100, 30);
		
		Button no = new Button("Nein");
		no.setBounds(194, 200, 100, 30);
		
		Button filechooser = new Button("...");
		filechooser.setBounds(309, 99, 30, 30);
		
		Button getanime = new Button("Animes suchen");
		getanime.setBounds(140, 0, 100, 30);
		
		
		getanime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchAnime anime = new SearchAnime();
				anime.search();
			}
		});

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
				addframe.setVisible(false);
				try {
					adder.addAnime(animename.getText(), genre.getText(), tags.getText(), gesehenboolean, Integer.parseInt(ratingselect.getSelectedItem() + ""));
				} catch (IOException e1) {e1.printStackTrace();}
			}	
		});
		
		delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {	
            	Object[] options = {"Ja", "Nein"};
            	Icon icon = UIManager.getIcon("OptionPane.warningIcon");
            	int antwort = JOptionPane.showOptionDialog(null, "Möchtest du " + animenamee.getText() + " wirklich löschen?", animenamee.getText() + " löschen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
               
            	if (antwort == JOptionPane.YES_OPTION) {
                    EditAnime edit = new EditAnime();
                    try {
                        edit.editAnime(false, animenamee.getText(), genree.getText(), tagse.getText(), gesehenbooleane, Integer.parseInt(ratingselecte.getSelectedItem() + ""));
                    } catch (NumberFormatException | IOException e1) { e1.printStackTrace();}
                } else if (antwort == JOptionPane.NO_OPTION) {}
            }
        });

		exportb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exporter.setVisible(true);				
			}	
		});
	
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exporter.dispose();
				TXTExport export = new TXTExport();
				try {
					export.export(path.getText());
				} catch (IOException e1) {e1.printStackTrace();}			
			}
		});
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exporter.dispose();				
			}
		});

		filechooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "png", "jpg");
				
				JFileChooser fileselect1 = new JFileChooser("watchlist.txt");
				fileselect1.showDialog(null, "Dateipfad auswählen...");
				fileselect1.setCurrentDirectory(new java.io.File("."));
				fileselect1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileselect1.setAcceptAllFileFilterUsed(false);
				path.setText(fileselect1.getSelectedFile().getAbsolutePath());
				
				
				fileselect1.setVisible(true);
			}	
		});
		
		
		
			//if(Files.readAllLines(Paths.get("C:\\AnimeWatchList\\config\\config.json")).get(3).equals("true"))
		

		count.setText("Es werden " + animecount + " Animes exportiert. Fortfahren?");
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
		editwindow.add(genrelabele);
		editwindow.add(genree);
		editwindow.add(confirme);
		editwindow.add(gesehenboole);
		editwindow.add(taglabele);
		editwindow.add(tagse);
		editwindow.add(animenamee);
		editwindow.add(addanimelabele);
		editwindow.add(editanimelabel);
		editwindow.add(ratingselecte);
		infos.add(gesehenjn);
		infos.add(animelabel, SwingConstants.CENTER);
		infos.add(separator);
		infos.add(genrename);
		infos.add(edit);
		infos.add(separator2);
		infos.add(delete);
		infos.add(rating);
		infos.add(img);
		exporter.add(exportinfo);
		exporter.add(count);
		exporter.add(separator3);
		exporter.add(yes);
		exporter.add(no);
		exporter.add(path);
		exporter.add(filechooser);
		gui.add(searchbar);
		gui.add(infos);
		gui.add(add);
		gui.add(watchbutton);
		gui.add(gesehenbutton);
		gui.add(exportb);
		gui.repaint();
		watchbutton.setVisible(false);
		loadwindow.setVisible(false);
		gui.setVisible(true);		
		}
	}