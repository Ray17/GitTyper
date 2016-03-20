package speed.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import speed.debug.Debug;

public class sFrame extends JFrame {
	JMenu menu[];
	JMenuItem item[];
	JMenuBar menuBar;
	JTabbedPane pane;
	HomePanel homePanel;
	ChooseTextPanel chooseTextPanel;
	/*SearchPersonPanel searchPersonPanel;
	DeletePersonPanel deletePersonPanel;
	AddAppointmentPanel addAppPanel;
	SearchAppointmentPanel searchAppPanel;
	DeleteAppointmentPanel deleteAppPanel;
	AppointmentDisplayPanel appDisplayPanel;
	RecordDisplayPanel recordDisplayPanel;*/
	ImageIcon i[];
	JFrame f;
	JPanel p;
	JLabel lbl[];

	public sFrame() {

		super("My Address Book");
		Debug.print("AddressBookFrame/Constructor");
	}

	public void initGUI() {

		Debug.print("sFrame/initGUI()");
		setLayout(new BorderLayout());
		pane = new JTabbedPane(JTabbedPane.LEFT);
		i = new ImageIcon[10];
		menuBar = new JMenuBar();
		menu = new JMenu[5];
		item = new JMenuItem[6];
		menu[0] = new JMenu("File");
		item[0] = new JMenuItem("New         Ctrl+N");
		item[1] = new JMenuItem("Open        Ctrl+O");
		menu[0].add(item[0]);
		menu[0].add(item[1]);
		menu[1] = new JMenu("Edit");
		item[2] = new JMenuItem("Undo        Ctrl+Z");
		item[3] = new JMenuItem("Redo        Ctrl+Y");
		menu[1].add(item[2]);
		menu[1].add(item[3]);
		menu[2] = new JMenu("Help");
		item[4] = new JMenuItem("About      Ctrl+F1");
		item[5] = new JMenuItem("Help              F1");
		menu[2].add(item[4]);
		menu[2].add(item[5]);
		menuBar.add(menu[0]);
		menuBar.add(menu[1]);
		menuBar.add(menu[2]);
		add(menuBar, BorderLayout.NORTH);
		homePanel = new HomePanel();
		chooseTextPanel = new ChooseTextPanel();
		/*searchPersonPanel = new SearchPersonPanel();
		deletePersonPanel = new DeletePersonPanel();
		addAppPanel = new AddAppointmentPanel();
		searchAppPanel = new SearchAppointmentPanel();
		deleteAppPanel = new DeleteAppointmentPanel();
		recordDisplayPanel = new RecordDisplayPanel();
		appDisplayPanel = new AppointmentDisplayPanel();*/
		i[0] = new ImageIcon("img\\add.png");
		i[1] = new ImageIcon("img\\Search.png");
		i[2] = new ImageIcon("img\\delete.png");
		i[3] = new ImageIcon("img\\update.png");
		i[4] = new ImageIcon("img\\add.png");
		i[5] = new ImageIcon("img\\Search.png");
		i[6] = new ImageIcon("img\\delete.png");
		i[7] = new ImageIcon("img\\update.png");
		i[9] = new ImageIcon("img\\home.png");
		pane.addTab("Home         ", i[9], homePanel);
		pane.addTab("Choose Text   ", i[0], chooseTextPanel);
		/*pane.addTab("Search Person", i[1], searchPersonPanel);
		pane.addTab("Delete Person", i[2], deletePersonPanel);
		pane.addTab("Display Persons", i[3], recordDisplayPanel);
		pane.addTab("Add Appointment   ", i[4], addAppPanel);
		pane.addTab("Search Appointment  ", i[5], searchAppPanel);
		pane.addTab("Delete Appointment  ", i[6], deleteAppPanel);
		pane.addTab("Display Appointments     ", i[7], appDisplayPanel);*/
		add(pane, BorderLayout.CENTER);
		pane.setBackground(Color.BLUE);
		pane.setForeground(Color.WHITE);
		setResizable(false);
		setBounds(0, 0, 970, 490);
		setVisible(true);
		AddressBookFrameHandler handler = new AddressBookFrameHandler();
		item[4].addActionListener(handler);
		item[5].addActionListener(handler);
	}

	class AddressBookFrameHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == item[4]) {

				f = new JFrame("About");
				p = new JPanel();
				p.setLayout(null);
				f.setLayout(null);
				// p.setBackground(Color.BLACK);
				f.setBounds(150, 150, 300, 310);
				p.setBounds(0, 0, 300, 300);
				lbl = new JLabel[10];
				i[8] = new ImageIcon("img\\about.png");
				lbl[0] = new JLabel(i[8]);
				lbl[0].setBounds(0, 0, 300, 300);
				p.add(lbl[0]);
				f.setVisible(true);
				f.add(p);
				f.setResizable(false);
			}
			if (ae.getSource() == item[5]) {
				JOptionPane.showMessageDialog(null,
						"visit www.kamran.tk for help");
			}
		}
	}
}
