/**
  * Classe Boisson
  *permet de créer des ingrédients pour les diffférentes recettes proposées
*/

public abstract class Boisson extends ComposantMenu {

  /** Attributs final car non modifiables une fois la boisson créée
    * Si un client souhaite changer de boisson, il faudra en créer une nouvelle
  */
  private final double volume;
  private final double nbCalories;

/**
  * Constructeur de boisson
  * @param double volume de  la boisson
  * @param double quantité de calories
  * @param String nom
  * @param int durée de préparation
  * @param double prix
*/
  public Boisson(String nom, double volume, double nbCalories, int duree, double prix) {
    super(nom, duree, prix);
    this.volume = volume;
    this.nbCalories = nbCalories;
  }
  
 /**
     * Accesseur du volume de la boisson
     * @return double volume
     */
  public double getVolume() {
    return volume; 
  }
  
  /**
     * Accesseur du nombre de calories
     * @return double nbcalories
     */
  public double getNbCalories() {
    return nbCalories;
  }
  
  
  /**
     * Redéfinition de la fonction toStrong
     * @return String texte d'affichage d'un ingrédient
     */
  public String toString() {
    return "La boisson "+ this.nom + " : volume = " + volume + " cl, nombre de calories = " + nbCalories;
  }
  
   public double getCalories(){
      return nbCalories;
    }
}
  

