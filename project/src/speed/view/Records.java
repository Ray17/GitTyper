package speed.view;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Records {
	
	static List <RecordsKnot> records = new ArrayList<RecordsKnot>();
	static String sRecords;
	static String updatedRecords;
	static String top10;
	
	public Records(){

	}
	
	public static void readRecords (){
		try {
			sRecords =SynonymProcessor.readFile("records.txt","utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setRecords () {//put the records from records.txt in the ArrayList records
		if(!records.isEmpty()) return;
		readRecords();
		RecordsKnot rk= new RecordsKnot();
		char current='0';
		char previous;
		String parameter;
		int begin=-1;int end=-1;
		int aux=1;
		int i;
		for( i=0;i<sRecords.length();i++){
			previous=current;
			current=sRecords.charAt(i);
			if(current != '\n'&&  current !='\r' && current != ' '){
				if (begin ==-1){
					begin=i;
				}
			} else if (previous != '\n' && previous !='\r'&& previous != ' '){
				end=i;
				parameter = sRecords.substring(begin,end);
				begin=-1;
				if(aux==1){
					rk= new RecordsKnot();
					records.add(rk);
					rk.name=parameter;
					aux=2;
				}else if(aux==2){
					rk.score= Integer.parseInt(parameter);		
					aux=1;
				}
			}
		}

	}
	
	public static void RecordsToString (){//transforms the ArrayList records in a String
		StringBuffer sb;
		sb = new StringBuffer();
		RecordsKnot rk= new RecordsKnot();
		for (int i =0; i<records.size();i++){
			rk=records.get(i);
			sb.append(rk.name);
			sb.append(" ");
			sb.append(rk.score);
			sb.append("\r\n");
		}
		updatedRecords=sb.toString();
	}
	
	public static void updateTXT (){//writes records.txt
		RecordsToString();
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("records.txt"), "utf-8"));
		    writer.write(updatedRecords);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	public static void updateList (String name, int score){//updates the list with a new player and a new score
		setRecords();
		boolean found=false;
		RecordsKnot rk= new RecordsKnot();
		for (int i =0; i<records.size();i++){
			rk=records.get(i);
			if (score>rk.score){
				found=true;
				rk=new RecordsKnot(name,score);
				records.add(i, rk);
				break;
			}
		}
		if(!found){
			rk=new RecordsKnot(name,score);
		    records.add(rk);
		}
		updateTXT();
		top10txt();
	}
	
	public static void topList(){//sets the top20 list
		StringBuffer sb;
		sb = new StringBuffer();
		RecordsKnot rk= new RecordsKnot();
		int max;
		if(20>records.size())
			max=records.size();
		else max=20;
		for(int i=0;i<max;i++){
			rk=records.get(i);
			sb.append(i+1);
			sb.append(": ");
			sb.append(rk.name);
			sb.append(" ");
			sb.append(rk.score);
			sb.append("\r\n");
		}
		top10=sb.toString();
	}

	public static void top10txt (){//writes top10.txt
		topList();
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("top10.txt"), "utf-8"));
		    writer.write(top10);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	//some tests:
	/*public static void main (String[] args){
		updateList("Sucker",10000);
		RecordsKnot rk= new RecordsKnot();
		//System.out.println(sRecords);
		for(int i=0;i<records.size();i++){
			rk=records.get(i);
			System.out.println(i+1+" "+rk.name+" "+rk.score);
		}
		top10txt();
		System.out.println(top10);
	}*/
	
}
