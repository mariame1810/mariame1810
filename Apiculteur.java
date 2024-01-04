import java.util.Scanner;

public class Apiculteur extends Agent{
  
	private String nom;
	private int cireRecoltee;
	private int mielRecolte;
	private static int totalCireRecoltee = 0;
	private static int totalMielRecolte = 0;
	private double salaire;

	Scanner scan= new Scanner(System.in);
	
	public Apiculteur(int posLigne, int posColonne){
		super(posLigne,posColonne);
		cireRecoltee = 0;
		mielRecolte = 0;
		System.out.println("Saisissez le nom de l'apiculteur d'ID : "+ this.getId());
		nom =scan.nextLine();
		System.out.println("Le nom saisi est "+nom+" ?");
		System.out.println("\n1.Oui\n2.Je souhaite saisir un autre");
		boolean b= true;
		while(b){
			try{
				int num=scan.nextInt();
				if(num==1) {
					b=false;
				}
				else if(num==2) {
					System.out.println("Saisissez le nouveau nom");
					Scanner scan= new Scanner(System.in);
					nom =scan.nextLine();
					System.out.println("Le nom saisi est "+nom+" ?");
					System.out.println("\n1.Oui\n2.Je souhaite saisir un autre");
				}
				else{
					throw new InvalidInputException("Répondez par 1 ou 2");
				}
			}
			catch(InvalidInputException e){
				System.out.println(e.getMessage());

			}
		}

		System.out.println("Le nom "+nom+" a été attribué à l'apicutleur d'ID : "+ this.getId());
		salaire = 0; //salaire nul avant la récolte
	}

	public Apiculteur(){
		super((int)(Math.random()*(Terrain.NBLIGNESMAX-1)), (int)(Math.random()*(Terrain.NBCOLONNESMAX-1)));
		System.out.println("Saisissez le nom de l'apiculteur d'ID : "+ this.getId());
		nom =scan.nextLine();
		System.out.println("Le nom saisi est "+nom+" ?");
		System.out.println("\n1.Oui\n2.Je souhaite saisir un autre");
		boolean b= true;
		while(b){
			try{
				int num=scan.nextInt();
				if(num==1) {
					b=false;
				}
				else if(num==2) {
					System.out.println("Saisissez le nouveau nom");
					Scanner scan= new Scanner(System.in);
					nom =scan.nextLine();
					System.out.println("Le nom saisi est "+nom+" ?");
					System.out.println("\n1.Oui\n2.Je souhaite saisir un autre");
				}
				else{
					throw new InvalidInputException("Répondez par 1 ou 2");
				}
			}
			catch(InvalidInputException e){
				System.out.println(e.getMessage());

			}
		}

		System.out.println("Le nom "+nom+" a été attribué à l'apicutleur d'ID : "+ this.getId());
		this.salaire = 0; //salaire nul avant la récolte
	}
  
  	public void recolteProduction(Ressource r){
  		if(r.type == "Cire"){
			cireRecoltee += r.getQuantite();
			totalCireRecoltee += cireRecoltee;
			salaire += (cireRecoltee * 100)/2;
		}
		else {
			mielRecolte += r.getQuantite();
			totalMielRecolte += mielRecolte;
			salaire += (mielRecolte * 100)/2;
		}
	}
  
	public static int totalCireRecoltee(){
		return totalCireRecoltee;
	}
	
	public static int totalMielRecolte(){
		return totalMielRecolte;
	}
	
	public int getCireRecoltee(){
		return cireRecoltee;
	}
	
	public int getMielRecolte(){
		return mielRecolte;
	}
  
	public String getNom(){
		return nom;
	}

	public double getSalaire(){
		return salaire;
	}

	public void setSalaire(double salaire){
		this.salaire = salaire;
	}

	public String toString(){
		return "l'Apiculteur "+nom+"."+super.toString()+" a récolté "+getCireRecoltee()+" g de Cire et "+this.getMielRecolte()+" g de miel, pour un gain de "+getSalaire()+" $";
	}

	public String typeAgent(){
		return "Apiculteur";
	}

	public Agent clone(){
		Apiculteur a = new Apiculteur(this.getPosLigne(), this.getPosColonne());
		return a;
	}
}
