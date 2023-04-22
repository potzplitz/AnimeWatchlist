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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchAnime {
	
	public String buttonpress;
	private int val = 0;
	private GetAnimeImage getApi = new GetAnimeImage();
	
	private JPanel panel;
	
	public void search() {
		
		JFrame searchfield = new JFrame("nach Anime suchen");
		searchfield.setSize(800, 500);
		searchfield.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchfield.setLayout(null);
		searchfield.setResizable(false);
		searchfield.setVisible(true);
		
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
		
		JLabel img = new JLabel();
		img.setBounds(33, 1, 225, 324);
		img.setBackground(Color.LIGHT_GRAY);
		
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem namecopy = new JMenuItem("Name kopieren");
		JMenuItem imgcopy = new JMenuItem("Bild kopieren");
		JMenuItem addtolist = new JMenuItem("zur Liste hinzuf�gen");
		JMenuItem copyjsonstring = new JMenuItem("JSONString der API kopieren");
		
		popup.add(namecopy);
		popup.add(imgcopy);
		popup.add(addtolist);
		popup.add(copyjsonstring);
		
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		
		
		
		Button start = new Button("Suchen");
		start.setBounds(197, 33, 100, 30);
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GetAnimeImage getApi = new GetAnimeImage();
				try {
					getApi.apiReader(search.getText());
					System.out.println(getApi.GetImage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				JSONObject obj = new JSONObject(getApi.array());
				JSONArray a = obj.getJSONArray("data");
				
				ArrayList<String> list = new ArrayList<String>();
				
				panel.removeAll();
				
				for(int i = 0; i < getApi.arrayLength(); i++) {
					
					JButton button = new JButton();

					button.setText(a.getJSONObject(i).getString("title"));
					button.setPreferredSize(new  Dimension(400, 100));
					button.setBackground(Color.LIGHT_GRAY);
					button.setVisible(true);
					
					panel.remove(button);
					
					Map<String, JButton> buttonMap = new HashMap<>();
					Map<String, JButton> linkmap = new HashMap<>();
					
					buttonMap.put(a.getJSONObject(i).getString("title"), button);
					buttonpress = buttonMap.keySet() + "";
					
					
					
					linkmap.put(a.getJSONObject(i).getJSONObject("images").getJSONObject("jpg").getString("image_url"), button);
					
					Map.Entry<String, JButton> firstEntry = linkmap.entrySet().iterator().next();
					
					list.add(i, button.getText());
					
					panel.add(button);
					
					panel.revalidate();
					content.revalidate();
					panel.repaint();
					searchfield.setSize(800, 499);
					searchfield.repaint();
					searchfield.setSize(800, 500);
					searchfield.repaint();
					img.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							if(SwingUtilities.isRightMouseButton(e)) {
							popup.show(e.getComponent(), e.getX(), e.getY());
							
							ActionListener listener = new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e1) {
									
									
									
									if(e1.getActionCommand().equals("Name kopieren")) {
										
										StringSelection stringSelection = new StringSelection(buttonpress);
										clipboard.setContents(stringSelection, null);	
										
									} else if(e1.getActionCommand().equals("Bild kopieren")) {

										StringSelection stringSelection = new StringSelection(firstEntry.getKey() + "");
										
										clipboard.setContents(stringSelection, null);
										
										
										
									} else if(e1.getActionCommand().equals("zur Liste hinzuf�gen")) {
										
										// [TODO]
										
									}else if(e1.getActionCommand().equals("JSONString der API kopieren")) {
										
										StringSelection stringSelection = new StringSelection(getApi.array());
										clipboard.setContents(stringSelection, null);	
										
									}
									
								}
								
								
							};
							namecopy.addActionListener(listener);
							imgcopy.addActionListener(listener);
							addtolist.addActionListener(listener);
							copyjsonstring.addActionListener(listener);
				
							}
						}
						@Override
						public void mousePressed(MouseEvent e) {	
						}
						@Override
						public void mouseReleased(MouseEvent e) {		
						}
						@Override
						public void mouseEntered(MouseEvent e) {	
						}
						@Override
						public void mouseExited(MouseEvent e) {		
						}		
					});
					
					
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							URL imageget;
							
							String link = firstEntry.getKey() + "";
							System.out.println(link);
				
							try {
								imageget = new URL(link);
								img.setIcon(new ImageIcon(ImageIO.read(imageget)));
							} catch (IOException e1) {
								e1.printStackTrace();
							}					
						}			
					});	
				}	
				
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
		info.add(img);
		
		searchfield.add(info);
		
	}

}