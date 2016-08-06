package speed.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SynonymProcessor {//class to create the dictionary of synonyms and process inquiries

	static List <Separator> dictionary = new ArrayList<Separator>();
	String Index;
	String Dic;
	
	public SynonymProcessor () {

		setIndex();
		cleanDic();
		setDic();
	}
	
	public static boolean isSynonym (String word, String syn){//verifies if syn is in the list of synonyms of word
		int aux1;
		int locate=0;
		char c1; char c2;
		boolean b=false;
		if(syn.equals("")) return b;
		Separator sep=new Separator();
		SynList sl = new SynList();
		for(int i=0;i<dictionary.size();i++){
			sep=dictionary.get(i);
			aux1=word.compareToIgnoreCase(sep.separator);
			locate=i;
			if(aux1<0){
				locate=i-1;
				break;	
			}
		}
		sep=dictionary.get(locate);
		for(int i=0; i<sep.reducedDic.size();i++){
			sl=sep.reducedDic.get(i);
			if(word.equals(sl.word)){
				aux1=sl.synList.indexOf(syn);
				if(aux1!=-1){
					c1=sl.synList.charAt(aux1+syn.length());
					c2=sl.synList.charAt(aux1-1);
					if(c1=='_'&&c2=='_'){
						b=true;
					}	
				}
				break;
			}
		}
		return b;
	}
	
	public void setIndex ()  {//sets the index of the dictionary
		//that is, attributes the String separator to the Separators in the list

		try {
			Index= readFile("dictionary/IndexDic.txt","utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		char current='0';
		char previous;
		String parameter;
		String sep=""; String number;
		int begin=-1;int end=-1;
		int aux;
		for(int i=0;i<Index.length();i++){
			previous=current;
			current=Index.charAt(i);
			if(current != ' ' && current != '\n'&& current !='' && current !='\r'){
				if (begin ==-1){
					begin=i;
				}
			} else if (previous != ' ' && previous != '\n' && previous !='' && previous !='\r'){
				end=i;
				parameter = Index.substring(begin,end);
				aux=(int)parameter.charAt(0)-(int)'0';
				if(aux<10){
					number=parameter;
					dictionary.add(new Separator(sep,number,-1));
					begin=-1;
				}else {
					sep=trimm(parameter);
					begin=-1;
				}
			}
		}		
	}
	
	public void cleanDic(){//cleans some excessive index from the dictionary
		//for example, eliminates the Separator where separator=="A"
		Separator sep= new Separator();
		String str;
		for(int i=0;i<dictionary.size();i++){
			sep=dictionary.get(i);
			str=sep.number;
			if(str.endsWith(".0")){
				dictionary.remove(i);
			}
		}
	}
	
	public void setDic () {//set the reducedDic for each Separator
		
		try {
			Dic=readFile("dictionary/Dictionary.txt","utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int position=0;
		Separator sep= new Separator();
		Separator sep2= new Separator();
		char current='0';
		char previous='0';
		char prePrevious;
		char c;
		int begin=-1;
		int end=-1;
		String parameter="zzz";
		String oldParameter;
		SynList sl = new SynList("","");
		boolean aux1;
		boolean aux2;
		boolean aux3;
		
		for(int i=0;i<dictionary.size();i++){
			sep=dictionary.get(i);
			position=Dic.indexOf(sep.index,position);
			sep.position=position;
		}
		
		for(int i=0;i<dictionary.size()-1;i++){
			sep=dictionary.get(i);
			sep2=dictionary.get(i+1);

			position=Dic.indexOf(sep.separator, sep.position+30);
			for(int j=position; j<sep2.position; j++){

				previous=current;
				current=Dic.charAt(j);
				if(current != ' ' && current != '\n'&& current !='' && current !='\r'){
					if (begin ==-1){
						begin=j;
					}
				} else if (previous != ' ' && previous != '\n' && previous !='' && previous !='\r'){
					end=j;
					oldParameter = parameter;
					parameter = Dic.substring(begin,end);
					begin=-1;
					if(parameter.equals("v.")||parameter.equals("adj.")||parameter.equals("n.")||parameter.equals("adv.")||parameter.equals("prep.")){
						sl.synList=sl.synList+"_";
						sl=new SynList(oldParameter,"");
						sep.reducedDic.add(sl);
					} else{
						prePrevious=oldParameter.charAt(oldParameter.length()-1);
						aux1=prePrevious=='.'||prePrevious==','||prePrevious==';'||prePrevious==':'||((int)prePrevious-(int)'0'<10 && (int)prePrevious-(int)'0'>0);
						c=parameter.charAt(parameter.length()-1);
						aux2=c=='.'||c==','||c==';'||c==':';
						c=parameter.charAt(0);
						aux3=c!='-';
						if(aux1 && aux2 &&aux3){
							sl.synList=sl.synList+"_"+trimm2(parameter);
						}
					}

				}
			
			}
		}
		
	}
	
	public static String trimm (String s){
		int l; String aux=s;
		l=s.length();
		if(s.endsWith("...")){
			aux=s.substring(0, l-3);
		}
		return aux;
	}
	
	public static String trimm2 (String s){
		int l; String aux=s;
		l=s.length();
		if(s.endsWith(".")||s.endsWith(",")||s.endsWith(":")||s.endsWith(";")){
			aux=s.substring(0, l-1);
		}
		return aux;
	}
	

	public static String readFile(String file, String csName)//function readFile to read the documents
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
        // it will mask any others. It would be better to use a utility
        // method which would log exceptions and swallow them
        stream.close();
    }        
	}
	
	//These are some tests:
	/*public static void main (String[] args) throws IOException{
		SynonymProcessor S = new SynonymProcessor();
		S.setIndex(); int aux; boolean b;
		S.cleanDic();
		S.setDic();
		Separator sep = new Separator();
		SynList sl = new SynList();
		//for(int i=0;i<S.dictionary.size();i++){
		//	sep=S.dictionary.get(i);
		//	System.out.println(sep.number+sep.separator+" "+sep.position);
		//}
		//System.out.println("funciona!!!");
		//System.out.println(S.Index);
		//aux= (int)S.Index.charAt(8);
		//System.out.println(aux);
		sep=S.dictionary.get(7);
		sl=sep.reducedDic.get(0);
		System.out.println(sl.word+"\n"+sl.synList);
		b=S.isSynnonym("aid", "help");
		System.out.println(b);
	}*/
	
}
