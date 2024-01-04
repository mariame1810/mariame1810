import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Commande implements RepasPersonnalisable{
	DecimalFormat df = new DecimalFormat("#.##");
		
	private ComposantMenu entreechoix;
	private ComposantMenu platschoix;
	private ComposantMenu dessertchoix;
	private ComposantMenu boissonchoix;
	private double facture;
	private Client client;

	Scanner scan= new Scanner(System.in); 

	public Commande(Client c,ComposantMenu entree, ComposantMenu plat, ComposantMenu dessert, ComposantMenu boisson ){
		client=c;
		entreechoix=entree;
		platschoix=plat;
		dessertchoix=dessert;
		boissonchoix=boisson;


	}

	public Commande(Client c){
		client =c;

	}

	public void retirerIngredient() {
					
			ArrayList<String> allergies = this.client.getAllergies();

			
			// Si les allergenes sont presents dans l'entree, on les supprime.
			
			for(String allergie : allergies ) {
				if (entreechoix.getIngred().contains(allergie)){
					entreechoix.getIngred().remove(allergie);

				}
			}
			
			// Si les allergenes sont prÈsents dans le plat, on les supprime.
			
			for(String allergie : allergies ) {
				if (platschoix.getIngred().contains(allergie)){
					platschoix.getIngred().remove(allergie);
				}
			}
					
			// Si les allergenes sont prÈsents dans le dessert, on les supprime.
			
			for(String allergie : allergies ) {
				if (dessertchoix.getIngred().contains(allergie)){
					dessertchoix.getIngred().remove(allergie);
				}
			}
			
			System.out.println("Les allergènes ont ete retires de votre commande\n\n votre commande est maintenant composee de :\n\n"+ entreechoix.toString() +"\n\n" + platschoix.toString()+ "\n\n" +dessertchoix.toString());
	}




///methode get et set

	public ComposantMenu getEntreechoix() {
		return entreechoix;
	}


	public void setEntreechoix(ComposantMenu entreechoix) {
		this.entreechoix = entreechoix;
	}
	
	public void setBoissonchoix(ComposantMenu boissonchoix) {
		this.boissonchoix = boissonchoix;
	}


	public ComposantMenu getPlatschoix() {
		return platschoix;
	}


	public void setPlatschoix(ComposantMenu platschoix) {
		this.platschoix = platschoix;
	}


	public ComposantMenu getDessertchoix() {
		return dessertchoix;
	}


	public void setDessertchoix(ComposantMenu dessertchoix) {
		this.dessertchoix = dessertchoix;
	}

//methode pour aafficher le choix du client
	public String  afficheChoix() {
		return "\nVotre commande :\n\nEntree: " + entreechoix.getNom()+ "\nPlat: " +platschoix.getNom()+ "\nDessert: " + dessertchoix.getNom()+ "\nBoisson: "+boissonchoix.getNom();
	}
//methode facture
	public void facture(){
		facture+=entreechoix.getPrix()+platschoix.getPrix()+dessertchoix.getPrix()+boissonchoix.getPrix();
		System.out.println("Votre montant a payer est : "+facture+"€"+"\n");
		System.out.println("Comment voulez-vous regler?\n1.Carte\n2.Espece");
		int x= scan.nextInt();
		if (x==1){
			System.out.println("Au plaisir de vous revoir ! joyeuses fête de fin d'année");
		}
		else{
			double apayer=facture;
			double tot=0;
			while(tot != apayer){
				if (tot<apayer){
				System.out.println("il vous manque : "+ df.format((facture -tot))+"€");
				tot+=scan.nextDouble();
				}

				if(tot>apayer){
				System.out.println("votre change :"+ df.format(tot - apayer)+"€");
				break;
			}
				if(tot==apayer){
					break;
				}
				
			}
			System.out.println("Au plaisir de vous revoir ! joyeuses fête de fin d'année");
		}
	}
	//methode composition nutritionnelle
	public void compoNutri(){
		
		System.out.println("Voulez-vous savoir les ingredients composant vos plats ? \nEntrez 1 ou 2 \n1.Oui\n2.Non");
		boolean b = true;
		while(b){
			
			try{
				int n=scan.nextInt();
				if(n==1){
					System.out.println("\n"+entreechoix.toString() +"\n\n"+platschoix.toString()+"\n\n"+dessertchoix.toString()+"\n");
					b = false;
				}
				else if (n==2) {
					b = false;
				}
				else {
					throw new InvalidInputException("Entre 1 pour Oui, 2 pour Non");
				}
			}
			catch(InvalidInputException e){
				System.out.println(e.getMessage());
			}
		}
		if(! (client.getAllergies().isEmpty())) {
			System.out.println("Les alliments vous causant des allergies seront retirÈs");
			System.out.println("\n");
			this.retirerIngredient();
		}
		double calories= entreechoix.getCalories()+platschoix.getCalories()+dessertchoix.getCalories();
		if(calories>client.getmaxCalories()){

			System.out.println("\nVotre repas contient: "+calories+"Kcal"+"\nVous avez depasser votre taux max de calories\n");
		}
		else{
			System.out.println("\nVous avez respecter votre taux max de calories!");
		}
	}

//constructeur par clone
	public Commande clone(){
		return new Commande( client, entreechoix,platschoix,dessertchoix,boissonchoix);
	}

	}

