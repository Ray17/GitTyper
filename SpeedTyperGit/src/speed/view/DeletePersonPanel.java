package speed.view;

import javax.swing.*;

import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

public class DeletePersonPanel extends JPanel {

	JLabel nameLbl, addressLbl, phoneLbl, msgLbl;
	JTextField nameTxt, addressTxt, phoneTxt;
	JButton delBtn;

	public DeletePersonPanel() {

		Debug.print("DeletePersonPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);

		nameTxt = new JTextField(10);
		delBtn = new JButton("Delete");
		delBtn.setBackground(Color.BLACK);
		delBtn.setForeground(Color.WHITE);
		nameLbl.setBounds(20, 2, 200, 150);
		nameTxt.setBounds(110, 70, 250, 20);
		nameTxt.setBackground(Color.CYAN);
		delBtn.setBounds(160, 250, 140, 40);

		add(nameLbl);
		add(nameTxt);
		add(delBtn);

		DeletePersonController handler = new DeletePersonController();
		delBtn.addActionListener(handler);

	}

	class DeletePersonController implements ActionListener {

		String name;

		public DeletePersonController() {
			Debug.print("DeletePersonController/Constructor");
			name = null;
		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("DeletePersonController/actionPerformed()");
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
					RequestDTO req = new RequestDTO("person", "delete", v);
					ResponseDTO res = controller.processRequest(req);
					if (res.msg != null)
						JOptionPane.showMessageDialog(null, res.msg);
				}
				setVisible(true);
			}
		}
	}
}