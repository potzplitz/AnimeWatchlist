package main;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import api.HTTPApi;
import database.AddAnimeGUI;
import database.EditAnime;
import database.TXTExport;
import settings.LoadSettings;
import settings.Settings;



public class AWLGui {

	// klassenübergeifende variablen erstellen
	
	private boolean gesehenboolean; // gesehen ob ja oder nein
	private boolean gesehenbooleane; // gesehen ob ja oder nein (editieren)

	private int geseheneanimes; // anzahl an gesehenen animes
	private int nichtgeseheneanimes; // anzahl an nicht gesehenen animes

	private String buttonpress; // was auf dem gedrücktem button draufsteht

	public JFrame gui = new JFrame("Anime Watch List");
	
	public void Gui() throws IOException, InterruptedException, UnsupportedLookAndFeelException {


		LoadSettings loadsettings = new LoadSettings();
		loadsettings.load();

		if(new LoadSettings().getDarkmode() == true) {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} else {
			UIManager.setLookAndFeel(new FlatLightLaf());
		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		gui.setVisible(false);
		gui.setSize(600, 600);
		gui.setLocation(dim.width / 2 - gui.getSize().width / 2, dim.height / 2 - gui.getSize().height / 2);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setResizable(false);
		gui.setLayout(null);
		
		
		JFrame exporter = new JFrame("Liste exportieren...");
		exporter.setSize(400, 300);
		exporter.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		exporter.setLayout(null);
		exporter.setResizable(false);
		exporter.setVisible(false);

		JPanel gesehen = new JPanel();
		gesehen.setLocation(0, 50);
		
		
		JPanel infos = new JPanel();
		infos.setBounds(250, 50, 350, 450);
		infos.setLayout(null);
		
		
		JProgressBar load = new JProgressBar();
		
		JPanel nichtgesehen = new JPanel();
		nichtgesehen.setLocation(0, 50);
		
		
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
		
		String[] ratingarray = {"0", "1", "2", "3", "4", "5"};
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox ratingselecte = new JComboBox(ratingarray);
		ratingselecte.setBounds(40, 210, 300, 30);
		
		TextField animenamee = new TextField(null, 16);
		animenamee.setBounds(40, 40, 300, 30);
		
		TextField tagse = new TextField(null, 16);
		tagse.setBounds(40, 100, 300, 30);
		
		TextField genree = new TextField(null, 16);
		genree.setBounds(40, 160, 300, 30);
		
		JTextField searchbar = new JTextField();
		searchbar.setBounds(250, 0, 137, 50);
		
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
		
		JButton confirme = new JButton("Speichern");
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

		JButton edit = new JButton("Bearbeiten");
		edit.setBounds(113, 377, 100, 30);
		
		JButton delete = new JButton("Löschen");
		delete.setBounds(5, 377, 100, 30);
		
		JButton favourite = new JButton("Favorit");
		favourite.setBounds(223, 377, 102, 30);
		
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
		
		@SuppressWarnings("unused")
		HTTPApi image = new HTTPApi();
		
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem rclickmenu = new JMenuItem("test");
		popup.add(rclickmenu);
		
		
		JLabel img = new JLabel();
		img.setBounds(10, 100, 225, 324);
		
		
	//	image.apiReader("absolute-duo");
		
	//	URL imageget = new URL(image.GetImage());
		
	//	img.setIcon(new ImageIcon(ImageIO.read(imageget)));

		
		load.setMinimum(0);
		load.setMaximum(animecount + 1);
		
		loadwindow.add(load);
		load.setBounds(1, 138, 398, 20);
		
		Object[] options = {"OK"};
    	Icon icon = UIManager.getIcon("OptionPane.InfoIcon");
    	

		LoadSettings settingsload = new LoadSettings();
		settingsload.load();

		int sleep = settingsload.getThreadspeed();

		for(int i = 0; i < animecount; i++) {
			Thread.sleep(sleep);
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
					//anime.setBackground(Color.decode("#adc5e0"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					anime.setToolTipText("");	
					
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
			gui.setSize(600, 499);
			gui.repaint();
			gui.setSize(600, 500);	
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
		scroll_2.setVisible(false);
		gui.add(scroll_2);

		JButton gesehenbutton = new JButton("gesehen " + "(" + geseheneanimes + " Animes)");
		gesehenbutton.setBounds(0, 0, 250, 50);
		
		
		JButton watchbutton = new JButton("nicht gesehen " + "(" + nichtgeseheneanimes + " Animes)");
		watchbutton.setBounds(0, 0, 250, 50);
		
		JButton settings = new JButton("Settings");
		settings.setMargin(new Insets(1,1,1,1));
		//settings.setFont(new Font("Arial", Font.PLAIN, 23));
		settings.setBounds(484, 0, 50, 50);
		
		JButton add = new JButton("+");
		add.setFont(new Font("Arial", Font.PLAIN, 23));
		add.setBounds(534, 0, 50, 50);
	//	add.setBackground(Color.LIGHT_GRAY);
		
		
		
		JButton exportb = new JButton("Export");
		exportb.setBounds(434, 0, 50, 50);
		exportb.setMargin(new Insets(1,1,1,1));
	//	exportb.setBackground(Color.LIGHT_GRAY);
		
		JButton searchfilter = new JButton("Filter");
		searchfilter.setBounds(384, 0, 50, 50);
		searchfilter.setMargin(new Insets(1,1,1,1));
	//	searchfilter.setBackground(Color.LIGHT_GRAY);
		
		JButton yes = new JButton("Ja");
		yes.setBounds(74, 200, 100, 30);
		
		JButton no = new JButton("Nein");
		no.setBounds(194, 200, 100, 30);
		
		JButton filechooser = new JButton("...");
		filechooser.setBounds(309, 100, 30, 30);
		
		
		
		
		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Settings settings = new Settings();
				try {
					settings.Setting();
				} catch (UnsupportedLookAndFeelException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				AddAnimeGUI adder = new AddAnimeGUI();
				adder.AnimeGUI("", false);
				
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
				
				@SuppressWarnings("unused")
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
		
		

		count.setText("Es werden " + animecount + " Animes exportiert. Fortfahren?");

		

		
		
		
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
		infos.add(favourite);
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
		gui.add(settings);
		gui.add(searchfilter);
		gui.repaint();
		watchbutton.setVisible(false);
		loadwindow.setVisible(false);
		gui.setVisible(true);		
		}
	}