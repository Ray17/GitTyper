package speed.view;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextField;

public class simpleWordProcessor {
	// this class implements methods to a word of type String
	String word;
	JTextField typedName;
	public simpleWordProcessor(){
		word = "";
	}
	public simpleWordProcessor(String s){
		word = s;
	}
	public String getWord(){
		return word;
	}
	public void eliminateNonLetterCharacter(){
		System.out.println(word);
		String newWord = "";
		for(int i=0; i<word.length(); i++)
			if(isAcceptedLetter(word.charAt(i)))
				newWord+=word.charAt(i);
		word = newWord;			
	}
	public void addCharacter(char c){
		word+=c;
	}
	public void deleteCharacter(){
		if(!word.equals(""))
			word = word.substring(0, word.length()-1);
	}
	public boolean isAcceptedLetter(char c){
		return ((c-'a'>=0) && ('z'-c >=0)) || ((c-'A'>=0) && ('Z'-c>=0)) || c=='-' || c=='\'';
	}
	public boolean empty(){
		return word=="";
	}
	public void reset(){
		word = "";
	}
	public void print(){
		System.out.println(word);
	}
	public boolean equalWords(WordProcessor w){
		return this.word.equalsIgnoreCase( w.getWord());
	}
}
