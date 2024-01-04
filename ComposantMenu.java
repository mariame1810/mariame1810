

import java.util.ArrayList;
/**
 * Classe représentant un composant de menu
 */
public abstract class ComposantMenu {
 
  protected int duree; //la durée de préparation
  protected ArrayList<String> ingredients;
  protected String nom;
  protected double prix;

  
  public ComposantMenu(String nom,int duree, double prix ) {
	    this.duree = duree;
	    this.nom=nom;
      this.prix = prix;
	  }


  public String toString() {
	    String s = "la recette "+nom +" : est composée de \n";
	    for (String i : ingredients) {
	     s += i.toString()+"\n";
	    }
	   return s+"prend "+duree+" minutes a faire et ";
	  }
  public String getNom() {
	  return nom;
  }
  
  public void retirerIngredient(String ingredient) {
		this.ingredients.remove(ingredient);
		
		
		}
  

  
  public ArrayList<String> getIngred() {
	  return ingredients;
  }

  public double getPrix(){
  	return prix;
  }
  public String  afficherIngred(){
  	String c="";
  	for(String i : ingredients){
  		c+= i+" ";

  	}
  	return c;

  }
  public abstract double getCalories();

 
}


