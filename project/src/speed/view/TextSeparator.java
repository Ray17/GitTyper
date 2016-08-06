package speed.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextSeparator {//transform the text2 in an ArrayList of strings with one word from the text2 per String
	
	SynonymProcessor sp = new SynonymProcessor();
	String text2;
	List <String> text = new ArrayList<String>();

	public TextSeparator(String text) throws IOException{
		this.text2=SynonymProcessor.readFile(text, "utf-8");
		setText2();
	}
	
	public void setText2 () {		

		
		char current='0';
		char previous;
		String parameter;
		int begin=-1;int end=-1;
		int i;
		for( i=0;i<text2.length();i++){
			previous=current;
			current=text2.charAt(i);
			if(current != ' ' && current != '\n'&& current !='' && current !='\r'){
				if (begin ==-1){
					begin=i;
				}
			} else if (previous != ' ' && previous != '\n' && previous !='' && previous !='\r'){
				end=i;
				parameter = text2.substring(begin,end);
				begin=-1;
				text.add(parameter);
			}
		}
		if(current != ' ' && current != '\n'&& current !='' && current !='\r'){
			end=i;
			parameter = text2.substring(begin,end);
			begin=-1;
			text.add(parameter);
		}
	}
	
	/*public static void main (String[] args) throws IOException{
		TextSeparator t= new TextSeparator("textos/Our Lady's Child.txt");
		for(int i=0;i<t.text.size();i++){
			System.out.println(t.text.get(i));
		}
	}*/
	
}