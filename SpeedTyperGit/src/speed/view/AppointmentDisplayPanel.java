package speed.view;

import javax.swing.*;

import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

public class AppointmentDisplayPanel extends JPanel {

	JLabel nameLbl;
	JButton displayBtn, refresh;
	JTable t;

	public AppointmentDisplayPanel() {

		Debug.print("AppointmentDisplayPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);
		displayBtn = new JButton("Display");
		displayBtn.setBackground(Color.BLACK);
		displayBtn.setForeground(Color.WHITE);
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLACK);
		refresh.setForeground(Color.WHITE);

		add(displayBtn).setBounds(260, 10, 100, 20);
		add(refresh).setBounds(380, 10, 100, 20);

		AppointmentDisplayController handler = new AppointmentDisplayController();
		displayBtn.addActionListener(handler);
		refresh.addActionListener(handler);
	}

	class AppointmentDisplayController implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			Debug.print("AppointmentDisplayController/actionPerformed()");
			AddressBookController controller = new AddressBookController();
			if (ae.getActionCommand() == "Refresh") {
				if (t != null) {
					t.setBounds(0, 0, 0, 0);
				}
				updateUI();
			}
			if (ae.getActionCommand() == "Display") {

				Vector v = new Vector();
				RequestDTO req = new RequestDTO("appointment", "display", v);
				ResponseDTO res = controller.processRequest(req);

				Vector vApp = res.data;
				if (vApp.capacity() != 0) {
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
					t.setBounds(40, 50, 700, 300);
					t.setAutoResizeMode(5);
					add(t);
					updateUI();
					validate();
					t.setVisible(true);
				}
			}
		}
	}
}