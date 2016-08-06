package speed.view;

public class Scoring {

	public Scoring(){
		
	}
	
	public static int score(String word, boolean isSynonym){
		
		int aux;
		System.out.println(word);
		aux=word.length();
		System.out.println(aux);
		if (isSynonym){
			aux*=10;
		}
		return aux;
	}
}
