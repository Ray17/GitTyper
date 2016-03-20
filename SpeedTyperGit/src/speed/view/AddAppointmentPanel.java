package speed.view;

import javax.swing.*;
import com.controller.AddressBookController;
import com.debug.Debug;
import com.dto.Appointment;
import com.dto.RequestDTO;
import com.dto.ResponseDTO;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AddAppointmentPanel extends JPanel {

	JLabel nameLbl, timeLbl, dateLbl, colonLbl;
	JTextField nameTxt;
	JButton addBtn;
	String year[] = { "2008", "2009", "2010", "2011", "2012", "2013" };
	String month[] = { "January", "Febraury", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	String day[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String hour[] = { "00", "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12", "13", "15", "16", "17", "18", "19", "20",
			"21", "22", "23" };
	String min[] = { "00", "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12", "13", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
			"32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
			"43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53",
			"54", "55", "56", "57", "58", "59" };

	JComboBox yearBox, monthBox, dayBox, hourBox, minuteBox;

	public AddAppointmentPanel() {

		Debug.print("AddAppointmentPanel/Constructor");

		setLayout(null);
		setBackground(Color.pink);
		yearBox = new JComboBox(year);
		monthBox = new JComboBox(month);
		dayBox = new JComboBox(day);
		hourBox = new JComboBox(hour);
		minuteBox = new JComboBox(min);
		colonLbl = new JLabel(" : ");
		nameLbl = new JLabel("Enter Name");
		nameLbl.setForeground(Color.BLUE);
		timeLbl = new JLabel("Enter Time");
		timeLbl.setForeground(Color.BLUE);
		dateLbl = new JLabel("Enter Date");
		dateLbl.setForeground(Color.BLUE);

		nameTxt = new JTextField(15);
		nameTxt.setBackground(Color.CYAN);

		addBtn = new JButton("Add");
		addBtn.setBackground(Color.BLACK);
		addBtn.setForeground(Color.WHITE);
		nameLbl.setBounds(15, 15, 200, 150);
		nameTxt.setBounds(110, 82, 250, 20);
		timeLbl.setBounds(15, 50, 200, 150);

		dateLbl.setBounds(15, 85, 200, 150);

		dayBox.setBounds(110, 148, 50, 20);
		monthBox.setBounds(180, 148, 100, 20);
		yearBox.setBounds(310, 148, 70, 20);

		hourBox.setBounds(110, 115, 50, 20);
		colonLbl.setBounds(165, 115, 50, 20);
		minuteBox.setBounds(180, 115, 50, 20);

		addBtn.setBounds(160, 250, 140, 40);
		add(nameLbl);
		add(nameTxt);
		add(colonLbl);

		add(timeLbl);

		add(dateLbl);

		add(yearBox);
		add(monthBox);
		add(dayBox);
		add(hourBox);
		add(minuteBox);
		add(colonLbl);

		add(addBtn);

		AddAppointmentController handler = new AddAppointmentController();
		addBtn.addActionListener(handler);
		yearBox.addActionListener(handler);
		monthBox.addActionListener(handler);
		dayBox.addActionListener(handler);
		hourBox.addActionListener(handler);
		minuteBox.addActionListener(handler);

	}

	class AddAppointmentController implements ActionListener {
		String name, time, hr, m;
		String date, dy, mon, yr;
		int index = 0;

		public AddAppointmentController() {
			Debug.print("AddAppointmentController/Constructor");
			name = time = null;
			date = null;

		}

		public void actionPerformed(ActionEvent ae) {
			Debug.print("AddAppointmentController/actionPerformed()");
			AddressBookController controller = new AddressBookController();

			if (ae.getActionCommand() == "Add") {

				name = nameTxt.getText();

				index = hourBox.getSelectedIndex();
				hr = hour[index];
				index = minuteBox.getSelectedIndex();
				m = min[index];

				time = hr + " : " + m;

				index = dayBox.getSelectedIndex();
				dy = day[index];
				index = monthBox.getSelectedIndex();
				mon = month[index];
				index = yearBox.getSelectedIndex();
				yr = year[index];

				date = dy + " " + mon + " " + yr;
				if (name.equals("") || date.equals("") || time.equals("")) {
					JOptionPane.showMessageDialog(null, "Input all Fields",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Appointment p = new Appointment(name, date, time);

					nameTxt.setText(null);

					Vector v = new Vector();
					v.add(p);
					RequestDTO req = new RequestDTO("appointment", "add", v);
					ResponseDTO res = controller.processRequest(req);

					if (res.msg != null)
						JOptionPane.showMessageDialog(null, res.msg);
				}
				setVisible(true);

			}
		}
	}
}
