public class BoissonFroide extends Boisson {
	
	
	private String type;	

	public BoissonFroide(String nom, double volume, double nbCalories, int duree, double prix){
		super(nom, volume, nbCalories, duree, prix);
		type = "froide";
	}

	public String toString(){
		return super.toString()+"\nType"+type;
	}
}
