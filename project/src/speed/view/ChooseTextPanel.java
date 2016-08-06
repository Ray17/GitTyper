package speed.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


//import com.dto.*;
//import com.controller.AddressBookController;
//import speed.debug.Debug;
//import com.dto.RequestDTO;
//import com.dto.ResponseDTO;

import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class ChooseTextPanel extends JPanel {

	//
	String[] textNames;
	JButton textOptions[];
	//JPanel playPanel;
	sFrame sFrame;
	JPanel textOptionsPanel;
	JPanel enterNamePanel;
	JLabel typeNameInstruction;
	JPanel confirmChoicePanel;
	JLabel selectTextInstruction;
	JTextField typedName;
	JButton confirmChoice;
	JLabel confirmChoiceInstruction;
	simpleWordProcessor username;
	String fileName = "Little Snow-White.txt";
	final Color myRed = new Color(199, 156, 154);
	Font myfont1 = new Font("Serif", Font.BOLD, 17);			
	Font myfont2 = new Font("Serif", Font.BOLD, 24);	
    Border border=BorderFactory.createLineBorder(Color.black,3);

	public ChooseTextPanel(sFrame sFrame) {

		GridBagLayout generalGridbag = new GridBagLayout();
		setLayout(generalGridbag);	
		GridBagConstraints cPanels = new GridBagConstraints();
		setBackground(myRed);
		this.sFrame= sFrame;

		GridBagLayout gridbag = new GridBagLayout();
		enterNamePanel = new JPanel();		
		cPanels.gridy = 0;
		enterNamePanel.setLayout(gridbag);	
        GridBagConstraints cInstruction1 = new GridBagConstraints();
        cInstruction1.fill = GridBagConstraints.BOTH;
        cInstruction1.weightx = 1.0;
        cInstruction1.insets = new Insets(3,3,3,3);
        cInstruction1.gridx = 0;
        cInstruction1.gridy = 0;  
        cInstruction1.anchor = GridBagConstraints.WEST;
        typeNameInstruction = new JLabel("Enter your name (optional) ");
		gridbag.setConstraints(typeNameInstruction, cInstruction1);
		typeNameInstruction.setForeground(Color.BLUE);
		typeNameInstruction.setFont(myfont2);
		
   		typedName = new JTextField("", 10);
   		username = new simpleWordProcessor();
   		typedName.setText(username.getWord());
   		GridBagConstraints cName = new GridBagConstraints();		
        cName.fill = GridBagConstraints.BOTH;
        cName.weightx = 1.0;
        cName.insets = new Insets(3,3,3,3);
        cName.gridx = 1;
        cName.gridy = 0;
        cName.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(typedName, cName);      
		typedName.setBackground(Color.CYAN);
		typedName.setFont(myfont1);
		typedName.setEnabled(true);
		typedName.addKeyListener(new nameListener());
		
        GridBagConstraints cInstruction2 = new GridBagConstraints();
        cInstruction2.fill = GridBagConstraints.BOTH;
        cInstruction2.weightx = 1.0;
        cInstruction2.insets = new Insets(3,3,3,3);
        cInstruction2.gridx = 3;
        cInstruction2.gridy = 0;  
        cInstruction2.anchor = GridBagConstraints.EAST;		
        selectTextInstruction = new JLabel("Then select one text ");
		gridbag.setConstraints(selectTextInstruction, cInstruction2);
		selectTextInstruction.setForeground(Color.BLUE);
		selectTextInstruction.setFont(myfont2);
		
		enterNamePanel.setBackground(myRed);
		enterNamePanel.add(typeNameInstruction);
		enterNamePanel.add(typedName);
		enterNamePanel.add(selectTextInstruction);		
		generalGridbag.setConstraints(enterNamePanel, cPanels);
		add(enterNamePanel);		

		//we show all files in the folder "textos"
		textOptionsPanel = new JPanel();
		textOptionsPanel.setBackground(myRed);
		File folder = new File("textos");
		File[] listOfFiles = folder.listFiles();
	    textNames = new String[ listOfFiles.length ];
	    int nFiles, j=0;
		for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		textNames[j] = listOfFiles[i].getName();
	    		j++;
	    	}
	    }	
		nFiles = j;
		System.out.println(nFiles);
		//
		GridLayout firstLayout = new GridLayout(0, 3);
		textOptionsPanel.setLayout(firstLayout);
		textOptions = new JButton[nFiles];
		for(int i = 0; i<nFiles; i++){ 
			textOptions[i]= new JButton(textNames[i].split("\\.")[0]);
			textOptions[i].setBackground(Color.BLACK);
			textOptions[i].setForeground(Color.WHITE);
			textOptions[i].setFont(myfont1);
			SelectTextController handler = new SelectTextController(textNames[i]);
			textOptions[i].addActionListener(handler);
			textOptionsPanel.add(textOptions[i]);
		}
        textOptionsPanel.setSize(300, 100);
        cPanels.gridy = 1;
		generalGridbag.setConstraints(textOptionsPanel, cPanels);
		add(textOptionsPanel);

		GridBagLayout confirmChoiceGridbag = new GridBagLayout();
		confirmChoicePanel = new JPanel();

        GridBagConstraints cInstruction = new GridBagConstraints();
        cInstruction.fill = GridBagConstraints.BOTH;
        cInstruction.weightx = 1.0;
        cInstruction.insets = new Insets(3,3,3,3);
        cInstruction.gridx = 0;
        cInstruction.gridy = 0;  
        cInstruction.anchor = GridBagConstraints.EAST;		
        confirmChoiceInstruction = new JLabel("Click here to confirm your choice ");
		gridbag.setConstraints(confirmChoiceInstruction, cInstruction);
		confirmChoiceInstruction.setForeground(Color.BLUE);
		confirmChoiceInstruction.setFont(myfont2);		
		confirmChoicePanel.add(confirmChoiceInstruction);
		
		confirmChoice = new JButton("Confirm");
		GridBagConstraints cConfirmChoice = new GridBagConstraints();		
        cConfirmChoice.fill = GridBagConstraints.HORIZONTAL;
        cConfirmChoice.weightx = 1.0;
        cConfirmChoice.insets = new Insets(50,3,50,3);
        cConfirmChoice.gridx = 1;
        cConfirmChoice.gridy = 0;
        //cConfirmChoice.gridwidth = 1000;
        cConfirmChoice.anchor = GridBagConstraints.EAST;
        confirmChoiceGridbag.setConstraints(confirmChoice, cConfirmChoice);
		confirmChoice.setFont(myfont1);
		confirmChoice.setEnabled(true);
		confirmChoice.setBorder(border);
		confirmChoice.setBackground(Color.CYAN);
		confirmChoice.setForeground(Color.RED);
        //confirmChoice.setSize(new Dimension(10, 50));
		confirmChoice.addActionListener(new OpenTextAction());
		//confirmChoice.add(Box.createHorizontalStrut(confirmChoicePanel.getPreferredSize().width));
		//confirmChoice.add(Box.createHorizontalStrut(confirmChoicePanel.getPreferredSize().width));
		//confirmChoice.add(Box.createHorizontalStrut(confirmChoicePanel.getPreferredSize().width));
		confirmChoicePanel.add(confirmChoice);
        cPanels.gridy = 2;
		generalGridbag.setConstraints(confirmChoicePanel, cPanels);
		confirmChoicePanel.setBackground(myRed);
		add(confirmChoicePanel);

	}

	private class SelectTextController implements ActionListener {
		String fname;
		public SelectTextController(String s) {
			fname = s;
		}

		public void actionPerformed(ActionEvent ae) {
			//choosing the file will change the text that is shown at playPanel. 
			fileName = fname;
		}
	} 
	private class nameListener implements KeyListener{
		public void keyPressed(KeyEvent arg0) {
		}
		public void keyReleased(KeyEvent arg0) {			
		}
		@Override
		public void keyTyped(KeyEvent e) {
	        int id = e.getID();
	        if (id == KeyEvent.KEY_TYPED) {
	        	if(id!=KeyEvent.VK_F1 && id!=KeyEvent.VK_ENTER){
	        		char c = e.getKeyChar();
		            processCharacter(c);
	        	}
	        } 
		}
		public void processCharacter(char c){
			//here we read the user's name, using the thread-safe simpleWordProcessor class
			int ascii = (int) c;
			if ((ascii < 32 || ascii > 126) && c!='\b' ) return;
			if(c=='\b')
				username.deleteCharacter();
			else
				username.addCharacter(c);
		}
	}
	private class OpenTextAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			sFrame.playPanel =  new PlayPanel(sFrame, fileName, username.getWord());
			sFrame.pane.removeTabAt(2);
			sFrame.pane.insertTab("Just Play!                                                                                                          ", null, sFrame.playPanel, "utf-8", 2);	
		}	
	}
}
