package speed.view;

import java.util.ArrayList;
import java.util.List;

public class Separator {

	String separator;//word that separates the dictionary in a part of it for faster search
	String number;
	String index;
	int position;
	List<SynList> reducedDic=new ArrayList<SynList>();//ArrayList with all the words after this.eparator and before the next separator
	
	public Separator(){
		this.separator="";
		this.number="";
		this.index="";
		this.position=-1;
	}
	
	public Separator (String separator, String number,int position){
		this.separator=separator;
		this.number=number;
		this.index=number+" "+separator;
		this.position=position;
	}
}
