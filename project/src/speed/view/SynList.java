package speed.view;

public class SynList {

	String word; //word is the word being analyzed
	String synList; //synList is the list of synonyms of "word"
	
	public SynList (String word, String synList){
		this.word=word;
		this.synList=synList;
	}
	
	public SynList (){
		this.word="";
		this.synList="";
	}
}
