public class Butineuse extends Abeille {

	private int pollenConsomme;
	private int cireProduite; //la quantité de cire produite par les abeille Butineuse, Nulle au début
	private static int totalPollenConsomme = 0;
	private static int totalCireProduite = 0;
	
	public Butineuse(int taille, int posLigne, int posColonne){
		super(posLigne, posColonne, taille);
		this.age = 3; //l'abeille butineuse a entre 3 à 10 jours
		pollenConsomme = 0;
		this.cireProduite=0;
	}
	
	public Butineuse(){
		super((int)(Math.random()*(Terrain.NBLIGNESMAX-1)), (int)(Math.random()*(Terrain.NBCOLONNESMAX-1)), (int)(Math.random()*3+3));
		this.age = 3; //l'abeille butineuse a entre 3 à 10 jours
		pollenConsomme = 0;
		this.cireProduite=0;
	}
	
	public void recolteProduction(Ressource r){
		pollenConsomme += r.getQuantite();
		totalPollenConsomme += pollenConsomme;
		cireProduite  = (int) (r.getQuantite() / 2); //La quantité de Cire produite est la moitiée de la quantité de pollen consommée.
		totalCireProduite += cireProduite;
	}
	
	public static int totalPollenConsomme(){
		return totalPollenConsomme;
	}
	
	public static int totalCireProduite(){
		return totalCireProduite;
	}
	
	public int getCireProduite(){
		return cireProduite;
	}
	
	public void setCireProduite(int cireProduite){
		this.cireProduite = cireProduite;
	}
	
	public String toString(){
		return "La Butineuse " + super.toString() + " a consommé "+pollenConsomme+" g de Pollen et produit " + cireProduite + "g de cire";
	}
	
	public String typeAgent(){
		return "Butineuse";
	}
	
}
