package speed.view;

import java.awt.Color;

import javax.swing.*;

import speed.debug.Debug;

public class HomePanel extends JPanel {

	JLabel lbl;
	ImageIcon icon;

	public HomePanel() {
		Debug.print("HomePanel/Constructor");

		setLayout(null);
		setBackground(Color.green);

		icon = new ImageIcon("img\\logo.jpg");
		lbl = new JLabel(icon);
		lbl.setBounds(50, -150, icon.getIconWidth(), icon.getIconWidth());
		add(lbl);
	}
}
