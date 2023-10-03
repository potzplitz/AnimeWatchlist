package api;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import database.AddAnime;
import database.AddAnimeGUI;

public class SearchAnime {
	
	private String buttonpress;
	private String pressedimage;
	private String pressedlink;
	
	private HTTPApi getApi = new HTTPApi();
	private AddAnime addAnime = new AddAnime();
	
	private JPanel panel;
	
	private int result;
	
	public void search() {
		
		// GUI components erstellen
		
		JFrame searchfield = new JFrame("nach Anime suchen");
		searchfield.setSize(800, 500);
		searchfield.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchfield.setLayout(null);
		searchfield.setResizable(false);
		searchfield.setVisible(true);
		
		JFrame animeInfos = new JFrame("Infos");
		animeInfos.setSize(300, 300);
		animeInfos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		animeInfos.setLayout(null);
		animeInfos.setResizable(false);
		animeInfos.setVisible(false);
		
		panel = new JPanel(); 
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(getApi.arrayLength(), 1, 1, 1));
		panel.setLocation(0, 0);
		
		JPanel content = new JPanel();
		content.setBounds(0, 100, 500, 361);
		content.setLayout(null);
		content.setBackground(Color.LIGHT_GRAY);
		
		JPanel info = new JPanel();
		info.setBounds(500, 0, 283, 500);
		info.setBackground(Color.LIGHT_GRAY);
		info.setLayout(null);
		
		JTextField search = new JTextField("nach Animes suchen");
		search.setBounds(1, 1, 500, 30);
		
		JLabel separator = new JLabel("____________________________________________________________________________________________________");
		separator.setBounds(0, 330, 500, 30);
		
		JLabel titel = new JLabel("", SwingConstants.CENTER);
		titel.setBounds(0, 320, 283, 30);
		
		JLabel img = new JLabel("", SwingConstants.CENTER);
		img.setBounds(0, 1, 283, 324);
		img.setBackground(Color.GRAY);
		
		JLabel results = new JLabel("Ergebnisse: 0");
		results.setBounds(1, 37, 100, 30);
		
		// kopierfunktion erstellen
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		
		
		
		
		Button start = new Button("Suchen");
		start.setBounds(197, 33, 100, 30);
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HTTPApi getApi = new HTTPApi();
				try {
					getApi.apiReader(search.getText());
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				JSONObject obj = new JSONObject(getApi.array());
				JSONArray a = obj.getJSONArray("data");
				
				panel.removeAll();
				
				for(int i = 0; i < getApi.arrayLength(); i++) {
					
					result = i;
					
					JButton button = new JButton();

					button.setText(a.getJSONObject(i).getString("title"));
					button.setPreferredSize(new  Dimension(400, 100));
					button.setBackground(Color.LIGHT_GRAY);
					button.setVisible(true);
					
					panel.remove(button);
					
					// Maps erstellen damit button und wert miteinander verknüpft werden können
					
					Map<String, JButton> buttonMap = new HashMap<>(); // name und keypress in einer map
					Map<String, JButton> linkmap = new HashMap<>(); // link des bildes und keypress in einer map
					Map<String, JButton> malmap = new HashMap<>(); // my anime list link und keypress in einer map
					Map<String, JButton> genremap = new HashMap<>();
					
					
					// Maps mit infos bestücken
					
					buttonMap.put(a.getJSONObject(i).getString("title"), button); // titel und button werden in eine map gepackt
					linkmap.put(a.getJSONObject(i).getJSONObject("images").getJSONObject("jpg").getString("image_url"), button); // link des bildes und button werden in eine map gepackt
					malmap.put(getApi.malurl(), button); // my anime list und button werden in eine map gepackt
					
					
					
					//genremap.put(, button);
					
					// map entrys erstellen, damit gedrückte buttonelemente einfacher ausgegeben werden können
					
					Map.Entry<String, JButton> picturelinkentry = linkmap.entrySet().iterator().next(); // entry für das bild des animes
					Map.Entry<String, JButton> malentry = malmap.entrySet().iterator().next(); // entry für den link der my anime list
					Map.Entry<String, JButton> buttonentry = buttonMap.entrySet().iterator().next(); // entry für die buttons
					
			
					
					// buttons ordnungsgemäß dem scrollpanel hinzufügen
					
					panel.add(button);
					panel.revalidate();
					content.revalidate();
					panel.repaint();
					searchfield.setSize(800, 499);
					searchfield.repaint();
					searchfield.setSize(800, 500);
					searchfield.repaint();
					
					
					
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {	
							
							buttonpress = buttonentry.getKey(); // sagt, was für ein button gedrückt wurde
							buttonpress = buttonpress.substring(0, buttonpress.length()); // schneidet den string zu
							
							pressedimage = picturelinkentry.getKey();
							pressedlink = malentry.getKey();
							
							try {
								URL imageget = new URL(pressedimage);
								img.setIcon(new ImageIcon(ImageIO.read(imageget)));
								
								titel.setText(buttonpress);
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}					
						}			
					});	
				}	

				results.setText("Ergebnise: " + result);
				
			}	
		});
		
		
		
		
		img.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {

			img.setToolTipText(buttonpress);
				
			}	
			@Override
			public void mouseClicked(MouseEvent e) {
			
				animeInfos.setVisible(true);
				
			}
		});

		Button CopyTitle = new Button("Titel Kopieren");
		CopyTitle.setBounds(7, 365, 130, 30);
		
		Button AddAnime = new Button("Anime hinzufügen");
		AddAnime.setBounds(147, 365, 130, 30);
		
		Button CopyPicture = new Button("Bild kopieren");
		CopyPicture.setBounds(7, 410, 130, 30);
		
		Button CopyLink = new Button("MAL Link kopieren");
		CopyLink.setBounds(147, 410, 130, 30);
		
		CopyTitle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection selection = new StringSelection(buttonpress);			
				clipboard.setContents(selection, selection);
			}	
		});
		
		CopyPicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection selection = new StringSelection(pressedimage);
				clipboard.setContents(selection, selection);			
			}	
		});
		
		CopyLink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection selection = new StringSelection(pressedlink);
				clipboard.setContents(selection, selection);		
			}		
		});
		
		AddAnime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddAnimeGUI adder = new AddAnimeGUI();
				adder.AnimeGUI(buttonpress, true);
				
			}	
		});
		
		final JScrollPane scroll_2 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_2.setBounds(0, 0, 500, 361);
		scroll_2.getVerticalScrollBar().setUnitIncrement(10);
		scroll_2.setForeground(Color.LIGHT_GRAY);
		scroll_2.setVisible(true);
		content.add(scroll_2);
		
		
		searchfield.add(search);
		searchfield.add(start);
		searchfield.add(content);
		searchfield.add(results);
		info.add(img);
		info.add(titel);
		info.add(CopyTitle);
		info.add(AddAnime);
		info.add(CopyPicture);
		info.add(CopyLink);
		info.add(separator);
		
		searchfield.add(info);
		
	}

}
