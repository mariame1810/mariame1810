import java.util.ArrayList;

public class Plats extends ComposantMenu{

	private double numCalories;
	private String compositionNut;
	
	
	public Plats(String nom, int duree, ArrayList<String> ingredient,double numC,String c, double prix) {
		super(nom, duree, prix);
    
		numCalories=numC;
		compositionNut=c;
    	ingredients=ingredient;

	}
	
	
	public String toString() {
		 String s = "Le plat "+nom +"  est composé de : \n";
		    for (String i : ingredients) {
		     s += i.toString()+" ";
		    }
		   return s+" temps de preparation: "+ duree +" minutes, contient "+ numCalories + " calories"+"\nMacronutriments: "+compositionNut+"\nprix :"+prix+"€";
		  }

	public double getPrix() {
		  return prix;
	  }
	  
	public double getCalories(){
		return numCalories;
	  }
	
}
