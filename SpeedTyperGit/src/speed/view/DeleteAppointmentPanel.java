package speed.view;

import javax.swing.*;

import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DeleteAppointmentPanel extends JPanel {

	JLabel nameLbl, msgLbl;
	JTextField nameTxt;
	JButton deleteBtn;

	public DeleteAppointmentPanel() {

		Debug.print("DeleteAppointmentPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);
		nameTxt = new JTextField(15);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(Color.BLACK);
		deleteBtn.setForeground(Color.WHITE);
		nameLbl.setBounds(15, 15, 200, 150);
		nameTxt.setBounds(100, 82, 250, 20);
		nameTxt.setBackground(Color.CYAN);
		deleteBtn.setBounds(160, 250, 140, 40);

		add(nameLbl);
		add(nameTxt);

		add(deleteBtn);
		DeleteAppointmentController handler = new DeleteAppointmentController();
		deleteBtn.addActionListener(handler);
	}

	class DeleteAppointmentController implements ActionListener {

		String name;

		public DeleteAppointmentController() {
			Debug.print("DeleteAppointmentController/Constructor");
			name = null;
		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("DeleteAppointmentController/actionPerformed()");
			AddressBookController controller = new AddressBookController();

			if (ae.getActionCommand() == "Delete") {

				name = nameTxt.getText();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					nameTxt.setText(null);
					Vector v = new Vector();
					v.add(name);
					RequestDTO req = new RequestDTO("appointment", "delete", v);
					ResponseDTO res = controller.processRequest(req);
					if (res.msg != null)
						JOptionPane.showMessageDialog(null, res.msg);
				}
				setVisible(true);
			}
		}
	}
}