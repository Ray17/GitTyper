package speed.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

public class RecordsPanel extends JPanel {

	JLabel nameLbl;
	Records rec = new Records();
	String fileName;
	JTextField nameTxt;
	JButton searchBtn, refresh;
	JTextField title;
	JTextArea text;
	final Color myRed = new Color(199, 156, 154);

	public RecordsPanel() {


		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);		

		setBackground(myRed);
		fileName = "top10.txt"; //fileName receives the document with the top scores
		title = new JTextField("Records", 20);
		try {//text receives what is written in top10.txt
			//the function readFile is in the class SynonymProcessor
			text = new JTextArea(SynonymProcessor.readFile(fileName, "utf-8"), 20, 50);		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//traditional implementation of the text boxes
		GridBagConstraints cTitle = new GridBagConstraints();
        cTitle.fill = GridBagConstraints.BOTH;
        cTitle.weightx = 1.0;
        cTitle.insets = new Insets(3,3,3,3);
        //cTitle.gridx = 0;
        cTitle.gridy = 1;
        GridBagConstraints cText = new GridBagConstraints();
        cText.fill = GridBagConstraints.BOTH;
        cText.weightx =1.0;
        cText.insets = new Insets(3,3,3,3);
        //cText.gridx = 0;
        cText.gridy = 2;                
        Border border=BorderFactory.createLineBorder(Color.black,3);		
		Font myfont1=new Font("Serif", Font.BOLD, 24);
		gridbag.setConstraints(title, cTitle);
		//gridbag.setConstraints(text, cText);
		title.setFont(myfont1);
		title.setEnabled(false);
		//title.setBorder(border);
		text.setFont(myfont1);
		text.setEnabled(false);
		text.setBorder(border);

		title.setBackground(Color.BLACK);
		title.setForeground(Color.YELLOW);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.YELLOW);
				
    	add(title);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		gridbag.setConstraints(scrollPane, cText);
		add(scrollPane);
	}
		
}