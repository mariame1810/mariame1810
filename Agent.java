public abstract class Agent {

	private int posLigne, posColonne;
	private static int cpt = 0;
	private final int id;

	public Agent(int posLigne, int posColonne) {
		this.posLigne = posLigne;
		this.posColonne = posColonne;
		this.id=++cpt;
	}
	
	//Contructeur par copie
	public Agent(Agent a) {
		posLigne = a.posLigne;
		posColonne = a.posColonne;
		id=a.id;	
	}
	
	public int distance(int posL, int posY) {
		return (int) (Math.sqrt(Math.pow(posLigne-posL, 2) + Math.pow(posColonne-posY, 2)));
	}
	
	public void seDeplace(int posL, int posY){
		posLigne = posL;
		posColonne = posY;
	}
	
	public int getPosLigne(){
		return posLigne;
	}
	
	public int getPosColonne(){
		return posColonne;
	}

	public int getId(){
		return id;
	}
	
	
	public void setPosLigne(int posLigne){
		this.posLigne = posLigne;
	}
	
	public void setColonne(int posColonne){
		this.posColonne = posColonne;
	}
	
	public String toString() {
		return " ID : " + id + " est en position ("+ posLigne +", "+ posColonne +")";
	}	
	
	/*Méthode statique :
	   getCpt() : int
	**/
	
	public static int getCpt(){
		return cpt;
	} 
	
	
	/*Méthodes abstraites:
	  typeAgent() : String, indique le type d'agent créé (Abeille ou Apiculteur)
	  clone() : Agent, permet de colner un agent (Abeille ou Apiculteur)
	**/
	public abstract String typeAgent();
	
	public abstract Agent clone();
}

