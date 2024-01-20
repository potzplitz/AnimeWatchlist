package settings;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Settings {

	

    
boolean checkbool = false;

	public void Setting() throws UnsupportedLookAndFeelException {

		JFrame settings = new JFrame("Einstellungen");
		settings.setSize(500, 500);
		settings.setLayout(null);
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settings.setResizable(false);
		settings.setVisible(true);

		JPanel settingspanel = new JPanel();
		settingspanel.setBounds(0, 0, 500, 500);
		settingspanel.setLayout(null);

		JLabel apperance = new JLabel("Aussehen");
		apperance.setBounds(1, 10, 100, 30);
		apperance.setFont(new Font(null, Font.BOLD, 16));
		apperance.setVisible(true);
		
		JLabel performance = new JLabel("Performance");
		performance.setBounds(1, 130, 100, 30);
		performance.setFont(new Font(null, Font.BOLD, 16));
		performance.setVisible(true);
		
		int val = 120;
		int valold = 20;
		
		StringBuilder str = new StringBuilder();
		

		for(int i = 0; i <= 3; i++) {
			for(int j = 0; j <= 100; j++) {
			str.append("_");
			}	
				JLabel separator = new JLabel(str.toString());
				separator.setBounds(0, valold, 500, 30);
				separator.setVisible(true);
				settingspanel.add(separator);
		
				valold += val;
				
		}
		
		JLabel darkmode = new JLabel("Darkmode");
		darkmode.setBounds(10, 50, 100, 30);
		darkmode.setVisible(true);
		
		JCheckBox darkmodecheck = new JCheckBox("aktiviert");
		darkmodecheck.setBounds(10, 75, 150, 30);
		darkmode.setVisible(true);

		LoadSettings load = new LoadSettings();

		darkmodecheck.setSelected(load.getDarkmode());

		darkmodecheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
			    	checkbool = true;
				} else if(e.getStateChange() == ItemEvent.DESELECTED) {		
					checkbool = false;		
				}		
	     	}	
    	});

		JLabel threadspeedlabel = new JLabel("Tread geschwindigkeit");
		threadspeedlabel.setBounds(10, 160, 130, 30);
		
		JSpinner threadspeed = new JSpinner();
		threadspeed.setBounds(10, 190, 100, 30);
		threadspeed.setValue(load.getThreadspeed());

		threadspeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if((int) threadspeed.getValue() <= 0) {
					threadspeed.setValue(0);		
				}
			}	
		});
		

		SettingsWriter write = new SettingsWriter();

		JButton save = new JButton("speichern");
		save.setBounds(190, 420, 100, 30);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)  {
			
				try {
					write.write((int) threadspeed.getValue(), checkbool);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			}	
		});
		
		settings.add(settingspanel);
		
		settingspanel.add(apperance);
		settingspanel.add(threadspeedlabel);
		settingspanel.add(darkmode);
		settingspanel.add(threadspeed);
		settingspanel.add(performance);
		settingspanel.add(darkmodecheck);
		settingspanel.add(save);

	}

 

}
