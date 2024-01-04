public class Abeille extends Agent implements EvolutionAbeille {
	
	protected int taille;
	protected int age; //À sa création, une abeille a 0 jours d'existence
	
	public Abeille(int posLigne, int posColonne, int taille){
		super(posLigne, posColonne);
		this.taille = taille;
		age = 0;
	}
	
	public int getTaille(){
		return taille;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setTaille(int taille){
		this.taille = taille;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public String toString(){
		return "une abeille identifiée par " + super.toString();
	}
	
	public String typeAgent(){
		return "Abeille";
	}
	
	public Agent clone(){
		Abeille a = new Abeille(super.getPosLigne(), super.getPosColonne(), this.taille);
		a.age = this.age;
		return a;
	}
	
	public void vieillir(){
		age++;
	}
	
	public boolean transformationAbeille(){
		//au bout des 3 jours suivant sa naissance, l'abeille se transforme en Butineuse
		if (age == 3){
			System.out.println("l'abeille doit se transformer en abeille Butineuse");
			return true;
		}
		//au bout des 10 jours suivant sa naissance, l'abeille devenue Butineuse se transforme en Magasinière
		else if (age == 10){
			System.out.println("l'abeille doit se transformer en abeille Butineuse");
			return true;
		}
		return false;
	}
}
