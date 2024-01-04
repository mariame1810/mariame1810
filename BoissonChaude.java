public class BoissonChaude extends Boisson {
	
	
	private String type;			

	public BoissonChaude(String nom, double volume, double nbCalories, int duree, double prix){
		super(nom, volume, nbCalories, duree, prix);
		type = "chaude";
	}
	
	public String toString(){
		return super.toString()+"\nType"+type;
	}
	
}
