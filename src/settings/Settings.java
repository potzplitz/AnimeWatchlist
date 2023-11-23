package settings;

<<<<<<< Updated upstream
import javax.swing.JFrame;

public class Settings {
	
	public void Setting() {
		
		@SuppressWarnings("unused")
		JFrame settings = new JFrame("Einstellungen");
		
	}

}
=======
 

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

 

public class Settings {

	public void Setting() {

		JFrame settings = new JFrame("Einstellungen");
		settings.setSize(500, 500);
		settings.setLayout(null);
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settings.setResizable(false);
		settings.setVisible(true);

		JPanel settingspanel = new JPanel();
		settingspanel.setBounds(0, 0, 500, 500);
		settingspanel.setLayout(null);
		settingspanel.setBackground(Color.LIGHT_GRAY);

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
				str.append("_".repeat(90));
				
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
		darkmodecheck.setBackground(Color.LIGHT_GRAY);
		darkmode.setVisible(true);

		JLabel threadspeedlabel = new JLabel("Tread geschwindigkeit");
		threadspeedlabel.setBounds(10, 160, 130, 30);
		
		JSpinner threadspeed = new JSpinner();
		threadspeed.setBounds(10, 190, 100, 30);
		threadspeed.setValue(1);
		
		settings.add(settingspanel);
		
		settingspanel.add(apperance);
		settingspanel.add(threadspeedlabel);
		settingspanel.add(darkmode);
		settingspanel.add(threadspeed);
		settingspanel.add(performance);
		settingspanel.add(darkmodecheck);

	}

 

}
>>>>>>> Stashed changes
