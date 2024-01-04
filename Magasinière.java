public class Magasiniere extends Abeille {

	private int nectarConsomme;
	private int mielProduit; //La quantité de miel produit, Nulle au début
		private static int totalNectarConsomme = 0;
	private static int totalMielProduit = 0;
	
	public Magasiniere(int taille, int posLigne, int posColonne){
		super(posLigne, posColonne, taille);
		this.age = 10;//l'abeille magasinière a entre 10 à 20 jours
		nectarConsomme = 0;
		mielProduit = 0;
	}
	
	public Magasiniere(){
		super((int)(Math.random()*(Terrain.NBLIGNESMAX-1)), (int)(Math.random()*(Terrain.NBCOLONNESMAX-1)), (int)(Math.random()*3+3));
		this.age = 10;//l'abeille magasinière a entre 10 à 20 jours
		nectarConsomme = 0;
		mielProduit = 0;
	}
	
	public void recolteProduction(Ressource r){
		nectarConsomme += r.getQuantite();
		totalNectarConsomme += nectarConsomme;
		mielProduit  = (int) (r.getQuantite() / 2); //La quantité de Miel produite est la moitiée de la quantité de Nectar consommée.
		totalMielProduit += mielProduit;
	}
	
	public static int totalNectarConsomme(){
		return totalNectarConsomme;
	}
	
	public static int totalMielProduit(){
		return totalMielProduit;
	}
	
	public int getMielProduit(){
		return mielProduit;
	}
	
	public void setMielProduit(int mielProduit){
		this.mielProduit = mielProduit;
	}
	
	public String toString(){
		return "La Magasiniere " + super.toString() + " a consommé "+nectarConsomme+" g de Nectar et produit " + mielProduit + "g de Miel";
	}
	
	public String typeAgent(){
		return "Magasinière";
	}
	
	public boolean mortMag(){
		if(this.age >= 20){
			System.out.println("l'abeille magasinière meur d'ID " + super.getId() + "est morte à l'age de "+this.age+" jours");
			return true;
		}
		return false;
	}
	
	
}
