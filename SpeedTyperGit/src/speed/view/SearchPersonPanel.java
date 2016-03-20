package speed.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

import com.dto.Person;
import com.controller.*;
import com.debug.Debug;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

public class SearchPersonPanel extends JPanel {
	JLabel tempLbl, nameLbl, msLbl;
	JTextField nameTxt;
	JButton searchBtn;
	JButton refresh;
	JTable t;
	ImageIcon icon;

	public SearchPersonPanel() {

		Debug.print("SearchPersonPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);
		icon = new ImageIcon("img\\big_search.png");
		tempLbl = new JLabel(icon);
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);
		nameTxt = new JTextField(10);
		nameTxt.setBackground(Color.CYAN);
		searchBtn = new JButton("Search");
		searchBtn.setBackground(Color.BLACK);
		searchBtn.setForeground(Color.WHITE);
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLACK);
		refresh.setForeground(Color.WHITE);

		nameLbl.setBounds(15, 15, 200, 150);
		nameTxt.setBounds(110, 82, 250, 20);
		searchBtn.setBounds(260, 15, 140, 40);
		refresh.setBounds(450, 15, 140, 40);
		tempLbl.setBounds(400, 150, 256, 256);
		add(nameLbl);
		add(nameTxt);
		add(searchBtn);
		add(refresh);
		add(tempLbl);
		SearchPersonController handler = new SearchPersonController();
		searchBtn.addActionListener(handler);
		refresh.addActionListener(handler);
	}

	class SearchPersonController implements ActionListener {

		String name;

		public SearchPersonController() {
			Debug.print("SearchPersonController/Constructor");
			name = null;
		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("SearchPersonController/actionPerformed()");
			AddressBookController controller = new AddressBookController();
			if (ae.getActionCommand() == "Refresh") {
				if (t != null) {
					t.setBounds(0, 0, 0, 0);
				}
				updateUI();
			}
			if (ae.getActionCommand() == "Search") {

				updateUI();
				name = nameTxt.getText();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					nameTxt.setText(null);
					Vector v = new Vector();
					v.add(name);
					RequestDTO req = new RequestDTO("person", "search", v);
					ResponseDTO temp = controller.processRequest(req);
					if (temp.data.elementAt(0) != null) {
						Vector rows = new Vector();
						if (t != null) {
							t.setBounds(0, 0, 0, 0);
						}
						updateUI();
						Vector<String> rowLbl = new Vector<String>();
						rowLbl.add("Name");
						rowLbl.add("Address");
						rowLbl.add("Phone");
						rowLbl.add("Email Address");
						rows.add(rowLbl);

						Vector<String> row1 = new Vector<String>();
						Person p = (Person) temp.data.elementAt(0);
						row1.add(p.getName());
						row1.add(p.getAddress());
						row1.add(p.getPhone());
						row1.add(p.getEmail());
						rows.add(row1);
						Vector col = new Vector();
						col.add("Name");
						col.add("Address");
						col.add("Phone");
						col.add("Email");
						t = new JTable(rows, col);
						t.setBounds(40, 120, 700, 30);
						add(t);
						updateUI();
						validate();
						t.setVisible(true);
					} else {
						if (t != null) {
							t.setBounds(0, 0, 0, 0);
						}
						updateUI();
					}
				}
				updateUI();
				setVisible(true);
			}
		}
	}
}