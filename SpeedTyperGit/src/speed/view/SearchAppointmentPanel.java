package speed.view;

import javax.swing.*;

import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchAppointmentPanel extends JPanel {

	JLabel nameLbl;
	JTextField nameTxt;
	JButton searchBtn, refresh;
	JTable t;

	public SearchAppointmentPanel() {

		Debug.print("SearchAppointmentPanel/Constructor");

		setBackground(Color.pink);
		setLayout(null);
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);
		nameTxt = new JTextField(15);
		nameTxt.setBackground(Color.CYAN);

		searchBtn = new JButton("Search");
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLACK);
		refresh.setForeground(Color.WHITE);
		searchBtn.setBackground(Color.BLACK);
		searchBtn.setForeground(Color.WHITE);
		nameLbl.setBounds(15, 15, 200, 150);
		nameTxt.setBounds(100, 82, 250, 20);
		searchBtn.setBounds(260, 15, 140, 40);
		refresh.setBounds(450, 15, 140, 40);

		add(nameLbl);
		add(nameTxt);

		add(searchBtn);
		add(refresh);
		SearchAppointmentController handler = new SearchAppointmentController();
		searchBtn.addActionListener(handler);
		refresh.addActionListener(handler);
	}

	class SearchAppointmentController implements ActionListener {

		String name;

		public SearchAppointmentController() {
			Debug.print("SearchAppointmentController/Constructor");
			name = null;
		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("SearchAppointmentController/actionPerformed()");
			AddressBookController controller = new AddressBookController();
			if (ae.getActionCommand() == "Refresh") {
				if (t != null) {
					t.setBounds(0, 0, 0, 0);
				}
				updateUI();
			}
			if (ae.getActionCommand() == "Search") {

				name = nameTxt.getText();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					nameTxt.setText(null);
					Vector v = new Vector();
					v.add(name);
					RequestDTO req = new RequestDTO("appointment", "search", v);
					ResponseDTO temp = controller.processRequest(req);

					if (temp.data != null) {
						if (temp.data.elementAt(0) != null) {
							Vector vApp = temp.data;
							Vector rows = new Vector();
							if (t != null) {
								t.setBounds(0, 0, 0, 0);
							}
							updateUI();
							Vector<String> rowLbl = new Vector<String>();
							rowLbl.add("Name");
							rowLbl.add("Appointment Date");
							rowLbl.add("Appointment Time");
							rows.add(rowLbl);
							for (int i = 0; i < vApp.size(); i++) {

								Vector<String> row1 = new Vector<String>();
								row1.add(((Appointment) vApp.get(i)).getName());
								row1.add(((Appointment) vApp.get(i)).getDate());
								row1.add(((Appointment) vApp.get(i)).getTime());
								rows.add(row1);
							}

							Vector col = new Vector();
							col.add("Name");
							col.add("Date");
							col.add("Time");

							t = new JTable(rows, col);
							t.setBounds(40, 120, 700, 200);
							add(t);
							updateUI();
							validate();
							t.setVisible(true);

							/*
							 * Vector rows = new Vector(); if (t != null) {
							 * t.setBounds(0, 0, 0, 0); } updateUI(); Vector<String>
							 * rowLbl = new Vector<String>();
							 * rowLbl.add("Name"); rowLbl.add("Date");
							 * rowLbl.add("Time"); rows.add(rowLbl);
							 * 
							 * Vector<String> row1 = new Vector<String>();
							 * Appointment app = (Appointment)
							 * temp.data.elementAt(0); row1.add(app.getName());
							 * row1.add(app.getDate()); row1.add(app.getTime());
							 * rows.add(row1); Vector col = new Vector();
							 * col.add("Name"); col.add("Date");
							 * col.add("Time"); t = new JTable(rows, col);
							 * t.setBounds(40, 150, 700, 30); add(t);
							 * updateUI(); validate(); t.setVisible(true);
							 */
						}
					} else {
						if (t != null) {
							t.setBounds(0, 0, 0, 0);
						}
						updateUI();
					}
				}
				setVisible(true);
			}
		}
	}
}