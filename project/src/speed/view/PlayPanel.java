package speed.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.EventListener;

public class PlayPanel extends JPanel {
	JLabel typeWordInstruction;
	JTextField title;
	JTextArea text;
	JTextField typedWord;
	JButton PlayBtn;
	JTextField counter;
	JTextField score;
	Integer scoreValue;
	final Timer timer;	
	Integer seconds;
	final sFrame sFrame;
	final Color myRed = new Color(199, 156, 154);
	final Font myfont1=new Font("Serif", Font.BOLD, 24);		
	final Border border=BorderFactory.createLineBorder(Color.black,3);		
	SynonymProcessor sProcessor;
	String fileName;
	WordProcessor currentWord;
	WordProcessor textWord;
	TextSeparator separatedText;
	List<String> wordsFromText;
	int ind;
	simpleWordProcessor username;
	
	public PlayPanel(final sFrame sFrame, String s, String usrname) {

		this.sFrame = sFrame;
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);		
		setBackground(myRed);
		sProcessor = new SynonymProcessor();
		this.username = new simpleWordProcessor (usrname);
		fileName = s;
		title = new JTextField(fileName.split("\\.")[0], 10);
		try {
			text = new JTextArea(readFile("textos/" + fileName, "utf-8"), 20, 50);		
			separatedText = new TextSeparator("textos/" + fileName);
			wordsFromText = separatedText.text;
		} catch (IOException e) {
			System.out.println("IOException : Text file not found");
		}
		GridBagConstraints cTitle = new GridBagConstraints();
        cTitle.fill = GridBagConstraints.BOTH;
        cTitle.weightx = 1.0;
        cTitle.insets = new Insets(3,3,3,3);
        cTitle.gridx = 0;
        cTitle.gridy = 1;
		title.setFont(myfont1);
		title.setEnabled(false);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.YELLOW);
		gridbag.setConstraints(title, cTitle);				
		add(title);
		
        GridBagConstraints cText = new GridBagConstraints();
        cText.fill = GridBagConstraints.BOTH;
        cText.weightx =1.0;
        cText.insets = new Insets(3,3,3,3);
        cText.gridx = 0;
        cText.gridy = 2;                
		text.setFont(myfont1);
		text.setEnabled(false);
		text.setBorder(border);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.YELLOW);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		gridbag.setConstraints(scrollPane, cText);
    	add(scrollPane);
		
        GridBagConstraints cInstruction = new GridBagConstraints();
        cInstruction.fill = GridBagConstraints.BOTH;
        cInstruction.weightx = 1.0;
        cInstruction.insets = new Insets(3,3,3,3);
        cInstruction.gridx = 0;
        cInstruction.gridy = 3;  
        cInstruction.anchor = GridBagConstraints.WEST;
        typeWordInstruction = new JLabel("Enter Word");
		gridbag.setConstraints(typeWordInstruction, cInstruction);
		typeWordInstruction.setForeground(Color.BLUE);
		typeWordInstruction.setFont(myfont1);
   		add(typeWordInstruction);
	
   		typedWord = new JTextField(10);
		GridBagConstraints cWord = new GridBagConstraints();		
        cWord.fill = GridBagConstraints.BOTH;
        cWord.weightx = 1.0;
        cWord.insets = new Insets(3,3,3,3);
        cWord.gridx = 0;
        cWord.gridy = 4;
        cInstruction.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(typedWord, cWord);      
		typedWord.setBackground(Color.CYAN);
		typedWord.setFont(myfont1);
		typedWord.setEnabled(false);
		typedWord.addKeyListener(new wordListener());
		add(typedWord);

		counter = new JTextField("Time", 10);
		GridBagConstraints cCounter = new GridBagConstraints();		
        cCounter.fill = GridBagConstraints.HORIZONTAL;
        cCounter.weightx = 1.0;
        cCounter.insets = new Insets(3,3,3,3);
        cCounter.gridx = 1;
        cCounter.gridy = 1;
        cCounter.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(counter, cCounter);
		counter.setFont(myfont1);
		counter.setEnabled(false);
		counter.setBorder(border);
		counter.setBackground(Color.BLACK);
		add(counter);		
		
		PlayBtn = new JButton("Play");
		GridBagConstraints cPlay = new GridBagConstraints();		
        cPlay.fill = GridBagConstraints.HORIZONTAL;
        cPlay.weightx = 1.0;
        cPlay.insets = new Insets(3,3,3,3);
        cPlay.gridx = 1;
        cPlay.gridy = 3;
        cPlay.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(PlayBtn, cPlay);
		PlayBtn.setFont(myfont1);
		//PlayBtn.setEnabled(false);
		PlayBtn.setBorder(border);
		PlayBtn.setBackground(Color.BLACK);
		PlayBtn.setForeground(Color.WHITE);
		PlayBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//starts the timer and enables the textField <typedWord>
				seconds = new Integer(30);
				counter.setText("Time: "+seconds.toString());
				PlayBtn.setEnabled(false);
				typedWord.setEnabled(true);
				currentWord = new WordProcessor("", sProcessor);
				ind=0;
				textWord = new WordProcessor(wordsFromText.get(ind), sProcessor);
				scoreValue = 0;
				score.setText("Score: "+scoreValue.toString());
				typedWord.requestFocusInWindow();
				timer.start();
			}
		});
		add(PlayBtn);
		
		score = new JTextField("Score", 10);
		GridBagConstraints cScore = new GridBagConstraints();		
        cScore.fill = GridBagConstraints.HORIZONTAL;
        cScore.weightx = 1.0;
        cScore.insets = new Insets(3,3,3,3);
        cScore.gridx = 1;
        cScore.gridy = 2;
        cScore.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(score, cScore);
		score.setFont(myfont1);
		score.setEnabled(false);
		score.setBorder(border);
		score.setBackground(Color.BLACK);		
		add(score);

		timer = new Timer(10, new ActionListener(){
			int temp=0;
			public void actionPerformed(ActionEvent e) {
				temp++;
				if(temp%100!=0) return;
				else temp = 0;
				if (seconds.intValue()>=0){
					counter.setText("Time: "+seconds.toString());				
					seconds = new Integer(seconds.intValue()-1);
				}
				else{
					PlayBtn.setEnabled(true);
					typedWord.setEnabled(false);
					//Records r = new Records();
					if(!username.getWord().equals("")){
						//id the username is not empty, it registers the record in the records list once the game ended	
						username.eliminateNonLetterCharacter();
						Records.updateList(username.getWord(), scoreValue.intValue());
						//then we update the tabs (actually, the panels) recordsPanel and choosetextPanel
						sFrame.recordsPanel = new RecordsPanel();
						sFrame.pane.removeTabAt(3);
						sFrame.pane.insertTab("Records                                                                                                            ", null, sFrame.recordsPanel, "utf-8", 3);
						sFrame.chooseTextPanel = new ChooseTextPanel(sFrame);
						sFrame.pane.removeTabAt(1);
						sFrame.pane.insertTab("Choose Text                                                                                                         ", null, sFrame.chooseTextPanel, "utf-8", 1);
					}
					typedWord.setText("");
					timer.stop();
				}
			}			
		});
		
		
        setSize(300, 100);
	}
	private class wordListener implements KeyListener{
		public void keyPressed(KeyEvent arg0) {
		}
		public void keyReleased(KeyEvent arg0) {			
		}
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
			//process keys typed at <typedWord>
			//uses the thread-safe WordProcessor class
			int ascii = (int) c;
			//we ignore weird characters
			if ((ascii < 32 || ascii > 126) && c!='\b' ) return;
			if(c=='\b'){
				currentWord.deleteCharacter();
			}
			else{
				if(c==' ' || c=='\n'){
					currentWord.print();
					textWord.print();
					//if an space is typed, we check if the typedWord is the corresponding word in the text
					if(textWord.acceptedWord(currentWord)){
						scoreValue += textWord.score(currentWord);
						score.setText("Score: "+ scoreValue.toString());
						currentWord.reset();
						typedWord.setText("");
						ind++;
						textWord = new WordProcessor(wordsFromText.get(ind), sProcessor);
						typedWord.setForeground(Color.BLACK);
					}
					else{
						currentWord.addCharacter(c);
						typedWord.setForeground(Color.RED);
					}
				}
				else{
					currentWord.addCharacter(c);
				}
			}
		}
		
	}
	
	public static String readFile(String file, String csName)
				throws IOException {
		Charset cs = Charset.forName(csName);
		return readFile(file, cs);
	}
	
	public static String readFile(String file, Charset cs)
				throws IOException {
		// No real need to close the BufferedReader/InputStreamReader
		// as they're only wrapping the stream
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			// Potential issue here: if this throws an IOException,
			// it will mask any others. Normally I'd use a utility
			// method which would log exceptions and swallow them
			stream.close();
		}        
	}

}