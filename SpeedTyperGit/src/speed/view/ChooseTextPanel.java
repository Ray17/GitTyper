package speed.view;

import java.awt.*;

import javax.swing.*;

import com.dto.*;
import com.controller.AddressBookController;
import speed.debug.Debug;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

import java.awt.event.*;
import java.util.Vector;

public class ChooseTextPanel extends JPanel {

	//
	String textNames[];
	JButton textOptions[];
	JPanel Panel, adPanel, phPanel;

	public ChooseTextPanel() {

		Debug.print("ChooseTextPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);

		//
		for(int i=1;i>0;i++){ //botar condição certa aqui
			textNames[i]= "O nome do texto";

		}
		
		lbl = new JLabel(" @ ");
		lbl.setForeground(Color.BLUE);
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);
		addressLbl = new JLabel("Enter Address");
		addressLbl.setForeground(Color.BLUE);
		phoneLbl = new JLabel("Enter Phone");
		phoneLbl.setForeground(Color.BLUE);
		emailLbl = new JLabel("Enter Email");
		emailLbl.setForeground(Color.BLUE);

		nameTxt = new JTextField(15);
		nameTxt.setBackground(Color.CYAN);
		addressTxt = new JTextField(15);
		addressTxt.setBackground(Color.CYAN);
		phoneTxt = new JTextField(15);
		phoneTxt.setBackground(Color.CYAN);
		emailTxt1 = new JTextField(15);
		emailTxt1.setBackground(Color.CYAN);
		emailTxt2 = new JTextField(15);
		emailTxt2.setBackground(Color.CYAN);
		
		//
		for(int i =1;i>0;i++){ //botar condição certa aqui
			textOptions[i]= new JButton(textNames[i]);
			textOptions[i].setBackground(Color.BLACK);
			textOptions[i].setForeground(Color.WHITE);
		}

		nameLbl.setBounds(15, 15, 200, 150);
		nameTxt.setBounds(110, 82, 250, 20);
		addressLbl.setBounds(15, 50, 200, 150);
		addressTxt.setBounds(110, 115, 250, 20);
		phoneLbl.setBounds(15, 85, 200, 150);
		phoneTxt.setBounds(110, 148, 250, 20);
		emailLbl.setBounds(15, 120, 200, 150);
		emailTxt1.setBounds(110, 185, 200, 20);
		lbl.setBounds(320, 185, 20, 20);
		emailTxt2.setBounds(350, 185, 120, 20);
		addBtn.setBounds(160, 250, 140, 40);

		add(lbl);
		add(emailTxt2);
		add(nameLbl);
		add(nameTxt);

		add(addressLbl);
		add(addressTxt);

		add(phoneLbl);
		add(phoneTxt);

		add(emailLbl);
		add(emailTxt1);

		add(addBtn);

		AddPersonController handler = new AddPersonController();
		addBtn.addActionListener(handler);

	}

	class AddPersonController implements ActionListener {
		String name, address;
		String phone, email;

		public AddPersonController() {
			Debug.print("AddPersonController/Constructor");
			name = address = null;
			phone = null;
			email = null;
		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("AddPersonController/actionPerformed()");
			AddressBookController controller = new AddressBookController();

			if (ae.getActionCommand() == "Add") {

				name = nameTxt.getText();
				address = addressTxt.getText();
				phone = phoneTxt.getText();
				email = emailTxt1.getText() + "@" + emailTxt2.getText();
				if (name.equals("") || address.equals("") || phone.equals("")
						|| emailTxt1.getText().equals("")
						|| emailTxt2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Input all Fields",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Person p = new Person(name, address, phone, email);

					nameTxt.setText(null);
					addressTxt.setText(null);
					phoneTxt.setText(null);
					emailTxt1.setText(null);
					emailTxt2.setText(null);

					Vector v = new Vector();
					v.add(p);
					RequestDTO req = new RequestDTO("person", "add", v);
					ResponseDTO res = controller.processRequest(req);

					if (res.msg != null)
						JOptionPane.showMessageDialog(null, res.msg);
				}
				setVisible(true);

			}
		}
	}
}
