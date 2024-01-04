import java.util.ArrayList;

public class Entree extends ComposantMenu{
	
	private double numCalories;
	private String compositionNut ;
 
	
	public Entree (String nom, int duree, ArrayList<String> ingredients,double numC, String c, double prix) {
		
		super(nom, duree, prix);
		numCalories=numC;
		compositionNut=c;
    this.ingredients=ingredients;
		
	}
	
	public void enleverAllergies(ArrayList<String> ingred,Client c) {
		ArrayList <String> z=c.getAllergies();
		
		for (int i=0;i<ingred.size();i++) {
			if (z.contains(ingred.get(i))) {
				ingred.remove(i);
			}
		}
		this.ingredients=ingred;
		///System.out.println(ingredients);
	}
	
	public String toString() {
		    String s = "L'entree: "+nom +"  est composée de : \n";
		    for (String i : ingredients) {
		     s += i.toString()+" ";
		    }
		   return s+" temps de preparation: "+ duree +" minutes, contient "+ numCalories + " calories"+"\nMacronutriments: "+compositionNut+"\nprix :"+prix+"€";
		  }

	public String getCompositionNut() {
		return compositionNut;
	}

	public void setCompositionNut(String compositionNut) {
		this.compositionNut = compositionNut;
	}
	public String getNom() {
		  return nom;
	  }
	  public double getPrix() {
		  return prix;
	  }
	  
	  public double getCalories(){
	  	return numCalories;
	  }
}