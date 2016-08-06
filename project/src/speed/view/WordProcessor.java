package speed.view;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WordProcessor {
	//this class that represents a word followed by some punctuation signs
	private String word;
	private String punctuation;
	private SynonymProcessor sProcessor;
	public WordProcessor(SynonymProcessor sProcessor){
		word = "";
		punctuation = "";
		this.sProcessor = sProcessor;
	}
	String getWord(){
		return word;
	}
	String getPunctuation(){
		return punctuation;
	}
	public WordProcessor(String s, SynonymProcessor sProcessor){
		s = s.toLowerCase();
		int j = s.length()-1;
		for(; j>=0 && !isLetter(s.charAt(j)); j--);
		word = s.substring(0, j+1);
		punctuation = s.substring(j+1);
		this.sProcessor = sProcessor;		
	}
	public void addPunctuation(char p){
			punctuation+=p;
	}
	public void addLetter(char c){
		char d = (char)Character.toLowerCase(c);
		word+=d;
	}
	public void addCharacter(char c){
		if(isLetter(c))
			addLetter(c);
		else
			addPunctuation(c);
	}
	
	public void deleteCharacter(){
		if(!punctuation.equals(""))
			punctuation = punctuation.substring(0, punctuation.length()-1);
		else if(!word.equals(""))
			word = word.substring(0, word.length()-1);
	}
	public static boolean isLetter(char c){
		return ((c-'a'>=0) && ('z'-c >=0)) || ((c-'A'>=0) && ('Z'-c>=0)) || c=='-' || c=='\'';
	}
	public void reset(){
		word = "";
		punctuation = "";
	}
	public void print(){
		System.out.println(word+"   "+punctuation);
	}
	public boolean equalWords(WordProcessor w){
		return this.word.equalsIgnoreCase( w.getWord());
	}
	public boolean equalPunctuations(WordProcessor w){
		return this.punctuation.equals(w.getPunctuation());
	}
	public boolean acceptedWord(WordProcessor w){
		if (!this.equalPunctuations(w))
			return false;
		if (this.equalWords(w))
			return true;
		return SynonymProcessor.isSynonym(this.word, w.getWord());
	}
	public int score(WordProcessor w){
		return Scoring.score(this.word, !this.equalWords(w));
	}
	
	public boolean empty(){
		return word=="" && punctuation=="";
	}
	public static void main(String args[]){
		SynonymProcessor sProcessor = new SynonymProcessor();
		WordProcessor w = new WordProcessor("Aid", sProcessor);
		WordProcessor v = new WordProcessor("aid", sProcessor);
		w.print();
		v.print();
		System.out.println(w.acceptedWord( v ));
		
	}
}
