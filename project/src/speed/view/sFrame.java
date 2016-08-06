package speed.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class sFrame extends JFrame {
	JTabbedPane pane;
	HomePanel homePanel;
	ChooseTextPanel chooseTextPanel;
	PlayPanel playPanel;
	RecordsPanel recordsPanel;
	final Font myfont1=new Font("Serif", Font.BOLD, 24);		

	public sFrame() {

		super("Synonym Bonanza");
	}

	public void initGUI() {

		setLayout(new BorderLayout());
		pane = new JTabbedPane(JTabbedPane.TOP);
		homePanel = new HomePanel();
		homePanel.setFont(myfont1);
		chooseTextPanel = new ChooseTextPanel(this);
		chooseTextPanel.setFont(myfont1);
		playPanel = new PlayPanel(this, "Little Snow-White.txt", "");
		playPanel.setFont(myfont1);
		recordsPanel = new RecordsPanel();
		recordsPanel.setFont(myfont1);
		pane.addTab("Home                                                                                                                ", null, homePanel);
		pane.addTab("Choose Text                                                                                                         ", null , chooseTextPanel);
		pane.addTab("Just Play!                                                                                                          ", null , playPanel);
		pane.addTab("Records                                                                                                            ", null , recordsPanel);
		add(pane, BorderLayout.CENTER);
		pane.setBackground(Color.WHITE);
		pane.setForeground(Color.RED);
		setResizable(false);
		setBounds(0, 0, 1600, 930);
		setVisible(true);
	}


}
