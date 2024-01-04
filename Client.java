mport java.util.Scanner;
import java.util.ArrayList;

public class Client{
	
	private String nom;
	private boolean reservation;
	private ArrayList <String> allergies=new ArrayList <String>();
	private String regime;
	private double maxCalories;

	
	Scanner scan= new Scanner(System.in); 
	Scanner sscan= new Scanner(System.in); 

	public Client(){
			//NOM
		System.out.println("Tapez votre nom");
		nom =scan.nextLine();

		//RESERVATION
		System.out.println("Avez-vous reserve?");
		String h =scan.nextLine();
		if (h.equals("non")|| h.equals("no")){
			reservation=false;
		}
		else {reservation=true;}
		
	
		
		//REGIME
		System.out.println("\nQuel est votre regime? \n1.Vegetarien\n2.Vegan\n3.Normal ");
		boolean b= true;
		while(b){
		try{
			
				int num=scan.nextInt();
				if(num==1) {regime ="Vegetarien";b=false;}
				else if(num==2) {regime ="Vegan";b=false;}
				if(num==3){regime = "Normal";b=false;}
				if(num>3||num<1){
					throw new InvalidInputException("Tapez un nombre entre 1 et 3");
				}
			}
			catch(InvalidInputException e){
				System.out.println(e.getMessage());
			}
		}


		///Taux calories
		System.out.println("Quel est votre taux maximal de calories par repas?\n");
		b=true;
		while(b){
			try{
			
				double num=scan.nextDouble();
				if(num<700){
					throw new InvalidInputException("Votre taux de calories est plus bas du taux recommandé,\nSaisissez un nombre supérieure a 700!");
						}
				else if(num>0&&num>=700){
					maxCalories=num;
					b=false;
				}
			}
			
			catch(InvalidInputException e){
				System.out.println(e.getMessage());
			}
		}

		//Allergies
		System.out.println("Avez-vous des allergies à certains aliments ? \n1.Oui\n2.Non");
		b = true;
		while(b){
			try{
		
				int m=scan.nextInt();
				if(m==1) {
					System.out.println("Sélectionnez un ou plusieurs allergËnes parmis : Oeufs, Lait, Poissons, Moutarde, Graines de sésame");
					String s="";
					while(!(s.equals("done"))){
			
						s=sscan.nextLine();
			
						if( s.equals("done") || s.equals("") ){
							break;
						}
						allergies.add(s);
					}
					b=false;
				}
				else if (m==2) {
					System.out.println("Vous n'avez aucune allergie");
					b=false;
				}
				else {
					throw new InvalidInputException("Entre 1 pour Oui, 2 pour Non");
				}
			
			}catch(InvalidInputException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
		
	

	public void AllergieOuNon() {
		
		System.out.println("\nAvez-vous des allergies? si oui lesquelles. Sinon laissez vide. ");

		 	String s="" ;

		while(!(s.equals("done"))){

			s=sscan.nextLine();

			if( s.equals("done") || s.equals("") ){
				break;
			}

		allergies.add(s);
	}
		
	}
	

	public String toString() {
		return nom+ ": allergies "+allergies+" regime: "+regime;
	}
	

/////////////////////////////////////////////METHODE GET////////////////////////////////////////////////////////////////
	public boolean getReservation(){
		return reservation;
	}

	public ArrayList <String> getAllergies(){
		return allergies;

		}
	public String getNom() {
		return nom;
		
	}
	public String getRegime() {
		return regime;
		
	}
	public double getmaxCalories(){
		return maxCalories;
	}


	
/////////////////////////////////////////////METHODE gET////////////////////////////////////////////////////////////////
	public void setReservation(boolean bb){
		 reservation=bb;
	}


	public void setNom(String n) {
		nom=n;
		
		}
	public void setRegime(String s) {
		 regime=s;
		
	}

}
	

	 
	
	
