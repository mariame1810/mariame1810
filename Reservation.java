import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	 private static int taille=6;
	 
	 private static String[][] tab = new String [taille][taille];
	 private String heure;
	 public static int numRes=0;
	 private String nom;
	 private int numTable;
	 SimpleDateFormat ss=new SimpleDateFormat("HH:mm");
	 
	static Scanner s= new Scanner(System.in); 
	
	 //si le client a reserve
	 public Reservation(String n, String t, int table) {
		 nom=n;
		 heure=t;
		 numTable=table;
		 numRes++;
		 
		 
	 }
	 //constructeur si le client n'a pas reserve
	 public Reservation(String n, int table) {
		 nom=n;
		 
		 numTable=table;
		 Date heure=new Date();
		 this.heure=ss.format(heure);
		 numRes++;
		 
		 
	 }

	 //initialiser le tableau
	  public static void inittab() {
		 
	 for(int i=0;i<taille;i++) {
		 for (int j=0;j<taille;j++) {
			 tab[i][j]= "[   ]";
			 
		 }
	 }
	 }
	  
	 public static String afficherPlace() {
		 
		 String chaine="      *------------------------------*\n\n";
		
		 for (int i=0;i<taille;i++) {
			 
			 for (int j=0;j<taille;j++) {
				 
				 if (j==taille-1) {
					chaine+=  " " +tab[i][j]+" |";
					
					continue;
					
				 }
				 if (j==0) {
						chaine+= "| "+tab[i][j];
						
						continue;
						
					 }
				 
				 chaine+= " "+tab[i][j]+" ";
				
				 
			 }
			 chaine+="\n";
			
		 }
		 chaine+="\n      *------------------------------*";
		return chaine;
		 
		 
	 }
	 ////METHODE QUI CREER UNE NOUVELLE RESERVATION
	 public static Reservation ajouterNouvRes(Client c) {

	boolean b=true;
	int z = 0;
	while(b) {

		System.out.println("\nChoissisez la table ");
		int n=s.nextInt();

		if(n > 36) {
			
			System.out.println("Choisissez une table entre 1 et 36");
			continue;
		}

		z=n;
		int ret=n % taille;
		int r=0;//le nombre de fois que taille divise n

		while(z != ret) {
			z=z-taille;
			r++;
			}

		if (n%taille==0) {
			if (tab[r][5].equals("[ X ]")) {
				System.out.println("table deja prise");
				continue;
			}
			else {tab[r-1][5]="[ X ]";b=false;}
		}
		else {
			if(tab[r][ret-1].equals("[ X ]")){
					System.out.println("table deja prise");
					continue ;
		}
			tab[r][ret-1]="[ X ]";
			b=false;
			System.out.println("\nVous-avez choisi la tale numero : "+ z+"!\n");

		}
	 }
	return new Reservation(c.getNom(),z);

	}
////METHODE QUI RAJOUTE LA RESERVATION DU CLIENT S'IL A RESERVE
	 
	 public static Reservation ajouterRes(Client c) {
		 System.out.println("A quelle heure est votre reservation?");
		 String d = s.nextLine();
		 
		 System.out.println("Et quelle table?");
		 int t=s.nextInt();
		 
		 resTable(t);
		 
		return new Reservation(c.getNom(),d,t);
		 
		 
	 }
		
	 
//remplir le tableau aleatoirement 
	 
	 public static void remplirtab() {
		
			 
		 for (int i=0;i<15;i++) {
			 
			 int j=(int)(Math.random()*(5));
			 int u=(int)(Math.random()*(5));
			
			 tab[j][u]="[ X ]";
		 }
	 
	 }
	 
	 ////METHODE QUI RESERVE UNE TABLE 
	 public static void resTable(int n) {
		 int r=0;
		 int ret=n%taille;
		 
		 while(n!=ret) {
			 n=n-taille;
			 r++;
		 }
		 if (n%taille==0) { tab[r-1][5]="[ X ]";}
		 else {
		 tab[r][ret-1]="[ X ]";
	 }
	 }
	 
	 
////////////////////////////////// METHODES GET//////////////////////////////////////////////////
	 
	 public String  getNom() {
		 return nom;
		 
	 }
	 public String  getHeure() {
		 return heure;
		 
	 }
	 public double  getNumTable() {
		 return numTable;
		 
	 }
	 
	 ///////////////methode de choisir automatiquent une methode de reservation///////////////
	 
	 public static Reservation reserver(Client c) {
			
			if(c.getReservation()==true) {
				return  ajouterRes(c);
				
			}else {
				System.out.println(Reservation.afficherPlace());
				return ajouterNouvRes(c);
			}
			
	 }
	 
	 
	 public String toString() {
		 return  "Reservation au nom : "+nom +" avec numero de reference : "+numRes+ " a "+heure +" h "+ "numero de table :  "+numTable;
	 }
	 
}



