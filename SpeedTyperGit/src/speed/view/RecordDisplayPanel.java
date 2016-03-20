package speed.view;

import javax.swing.*;
import com.dto.Person;
import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

public class RecordDisplayPanel extends JPanel {

	JLabel nameLbl;
	JButton displayBtn, refresh;
	JTable t;

	public RecordDisplayPanel() {

		Debug.print("RecordDisplayPanel/Constructor");

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

		RecordDisplayController handler = new RecordDisplayController();
		displayBtn.addActionListener(handler);
		refresh.addActionListener(handler);
	}

	class RecordDisplayController implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			Debug.print("RecordDisplayController/actionPerformed()");
			AddressBookController controller = new AddressBookController();

			if (ae.getActionCommand() == "Refresh") {
				if (t != null) {
					t.setBounds(0, 0, 0, 0);
				}
				updateUI();
			}
			if (ae.getActionCommand() == "Display") {
				Vector v = new Vector();
				RequestDTO req = new RequestDTO("person", "display", v);
				ResponseDTO res = controller.processRequest(req);

				Vector vp = res.data;

				Vector rows = new Vector();
				if (t != null) {
					t.setBounds(0, 0, 0, 0);
				}
				updateUI();
				if (vp.capacity() != 0) {
					Vector<String> rowLbl = new Vector<String>();
					rowLbl.add("Name");
					rowLbl.add("Address");
					rowLbl.add("Phone");
					rowLbl.add("Email Address");
					rows.add(rowLbl);

					for (int i = 0; i < vp.size(); i++) {

						Vector<String> row1 = new Vector<String>();
						row1.add(((Person) vp.get(i)).getName());
						row1.add(((Person) vp.get(i)).getAddress());
						row1.add(((Person) vp.get(i)).getPhone());
						row1.add(((Person) vp.get(i)).getEmail());
						rows.add(row1);
					}

					Vector col = new Vector();
					col.add("Name");
					col.add("Address");
					col.add("Phone");
					col.add("Email");

					t = new JTable(rows, col);
					t.setBounds(40, 50, 700, 300);
					add(t);
					validate();
					updateUI();
					t.setVisible(true);
				}
			}
		}
	}
}