	import java.util.ArrayList;
public class Dessert extends ComposantMenu {
	
	private double numCalories;
	private String compositionNut;
	

	public Dessert (String nom, int duree, ArrayList<String> ingredient,double nbCalories, String c, double prix) {
		
		super(nom, duree, prix);
		numCalories = nbCalories;
		compositionNut=c;
  		ingredients=ingredient;
  

		
	}
	
		
		public String toString() {
		    String s = "Le dessert "+ nom+ "  est composé de : \n";
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



