import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	static Scanner scan= new Scanner(System.in); 
	
	private static  ArrayList <ComposantMenu> menuNormal= new ArrayList<ComposantMenu>();
	private static ArrayList <ComposantMenu> menuVegan= new ArrayList <ComposantMenu>();
	private static ArrayList <ComposantMenu> menuVeget= new ArrayList <ComposantMenu>();
	private static ArrayList <ComposantMenu> menuDessert= new ArrayList <ComposantMenu>();
	private static ArrayList <ComposantMenu> menuBoisson= new ArrayList <ComposantMenu>();
	
	private static final Menu MENU =new Menu();
	
	private Menu() {
		menuNormal.add( new Entree("Soupe de lentille",10,new ArrayList<String> (Arrays.asList("Huile d'olive", "Carottes", "Oignons", "Lentilles ")),24.5,"0,9 g proeteine, 3,9 g glucides, 0,3 g lipides",5.00));
		menuNormal.add( new Entree("Tartare de boeuf ",10,new ArrayList<String> (Arrays.asList("Moutarde","Persil","Oignons","Huile d'Olive","Oeufs","steack")),400,"44g proetéines,3g glucides,22 g lipides",13.90));
		menuNormal.add( new Entree("Salade caesar au poulet ",10,new ArrayList<String> (Arrays.asList("Salade ","Citron","Piment","Sel","Escalope de poulet","Oeuf","Echalote","Parmesan","Biscottes")),345,"1 g proteines,2 g glucides, 3.5 g lipides",4.50));
		
		menuNormal.add( new Plats("Entrecôte grillée accompagné de purée de pomme de terre",25,new ArrayList<String> (Arrays.asList("1 Entrecôte", "Huile d'olive", "Sel", "Purée de PDT", "Beurre", "Lait")),600,"70 g proeteine, 3,9 g glucides, 0,3 g lipides",17.90));
		menuNormal.add( new Plats("Spaghetti bolognese",20,new ArrayList<String> (Arrays.asList("spaghetti","Huile d’olive","Oignons","Boeuf haché","Sel","concentré de tomate","Parmesan")),537,"5.91 g proetéines, 16.9 g glucides, 4.83 g lipides",13.90));
		menuNormal.add( new Plats("Tartiflette",15,new ArrayList<String> (Arrays.asList("Reblochon","Pomme de Terre","Oignon","Lardons fumés","Vin blanc", "Noix de beurre","Poivre du moulin")),344,"4.7 g proteines, 12.5 g glucides, 8.2 g lipides",9.90));
		
		menuVeget.add( new Entree("Quiche ricotta épinards",10,new ArrayList<String> (Arrays.asList("Pâte feuilletée","épinards","Ricotta","Parmesan")),242,"1 g proteines,2 g glucides, 3.5 g lipides",6.0));
		menuVeget.add( new Entree("Soupe Ribollita",15,new ArrayList<String> (Arrays.asList("Haricots blancs", "Choux noir", "épinards", "pain sec", "Oignon")),238,"12 g proteines, 32 g glucides, 5 g lipides",6.90));
		menuVeget.add( new Entree("Salade thai au quinoa",5,new ArrayList<String> (Arrays.asList("Aubergine","Courgette","huile d’olive","quinoa","tomates cerises","pignons de pin")),335,"10 g proteines, 44 g glucides, 25 g lipides",8.90));
		
		menuVeget.add( new Plats("Couscous aux légumes",25,new ArrayList<String> (Arrays.asList("Couscous","Tomates","Haricots verts","Carotte","Courgette","pois-chiches")),420,"2 g proteines, 5.5 g glucides, 0.5 g lipides",8.45));
		menuVeget.add( new Plats("Lasagne au légumes",15,new ArrayList<String> (Arrays.asList("Lasagne", "Courgettes", "Champignons", "épinards", "Carotte", "Oignon")),254,"12 g proteines, 24 g glucides, 11 g lipides",8.90));
		menuVeget.add( new Plats("Risotto de petit épeautre aux topinambours",25,new ArrayList<String> (Arrays.asList("petit épeautre mondé","parmesan râpé","huile d’olive","bouillon de légumes","topinambour","noisettes grillées")),435,"14 g proteines, 71 g glucides, 18 g lipides",10.00));
									
		menuVegan.add( new Entree("Hummus",5,new ArrayList<String> (Arrays.asList("Pois chiche","Tahin","Citron","Huile d'olive","Cumin")),120,"4 g proteines, 5 g glucides, 5 g lipides",6.00));
		menuVegan.add( new Entree("tofu sautés à la sauce soja",15,new ArrayList<String> (Arrays.asList("Tofu ferme nature", "Piment de Cayenne", "graines de sésame", "Huile de sésame", "Sauce soja")),220,"8.19 g proteines, 1.69 g glucides, 2.87 g lipides",7.00));
		menuVegan.add( new Entree("Soupe au maïs,tomates et couregttes",5,new ArrayList<String> (Arrays.asList("courgettes coupées","bouillon de légumes","huile d’olive","tomates entières","maïs")),24.5,"0,9 g proeteine, 3,9 g glucides, 0,3 g lipides",5.00));
		
		menuVegan.add( new Plats("Risotto de chou fleur aux champignons",25,new ArrayList<String> (Arrays.asList("chou fleur","noix muscade","crème végétale Risotto","champignons","parmiggiano reggiano","Huile d'olive")),335,"14 g proteines, 71 g glucides, 18 g lipides",10.90));
		menuVegan.add( new Plats("Spaghetti d’Estate",20,new ArrayList<String> (Arrays.asList("spaghetti", "Courgettes", "tomates", "herbes de Provence", "piment doux")),367,"5.8 g proteines, 30.86 g glucides, 0.93 g lipides",13.50));
		menuVegan.add( new Plats("Burger a base soja",20,new ArrayList<String> (Arrays.asList("soja", "pain", "tomates", "salade", "pomme de terre")),400,"5.8 g proteines, 30.86 g glucides, 0.93 g lipides",13.50));
		
		
		menuDessert.add( new Dessert("Tiramisu",5,new ArrayList<String> (Arrays.asList("biscuits", "Oeufs", "mascarpone", "café noir")),350,"5.8 g proteines, 30.86 g glucides, 0.93 g lipides",5.00));
		menuDessert.add( new Dessert("Fondant au chocolat",5,new ArrayList<String> (Arrays.asList("Chocolat noir ", "Oeufs", "beurre", "farine de blé")),400,"5.8 g proteines, 30.86 g glucides, 0.93 g lipides",5.00));												   
		menuDessert.add( new Dessert("Salade de fruits",5,new ArrayList<String> (Arrays.asList("raisins", "bananes", "pommes", "kiwi", "mangue")),158,"5.8 g proteines, 30.86 g glucides, 0.93 g lipides",5.00));												   
		menuDessert.add( new Dessert("Crêpes au chocolat",5,new ArrayList<String> (Arrays.asList("Farine", "Oeufs", "Lait", "beurre fondu", "chocolat")),300,"0.8 g proteines, 30.86 g glucides, 0.93 g lipides",5.00));

		menuBoisson.add(new BoissonFroide("Coca",330,150,1,2));
	    menuBoisson.add(new BoissonFroide("Sprite",330,150,1,2)); 
	    menuBoisson.add(new BoissonFroide("Fanta",330,150,1,2));
	    menuBoisson.add(new BoissonFroide("Jus d'Orange",350,150,4,2));
	    menuBoisson.add(new BoissonFroide("Jus de Pomme",350,150,4,2));
	    menuBoisson.add(new BoissonFroide("Jus de Carotte",350,150,4,2));
	    menuBoisson.add(new BoissonChaude("Café",150,50,2,2));
	    menuBoisson.add(new BoissonChaude("Thé",250,26,1,2));

	}
	
	
	
	///affiche tout le menu en fonction du regime du client
	public static void afficherMenu(Client c) {
		if (c.getRegime().equals("Vegetarien")) {
			System.out.println("	menu ");
			afficher(menuVeget);
		}
		if(c.getRegime().equals("Vegan")) {
			afficher(menuVegan);
		}
		else {
			System.out.println("voici le menu");
			afficher(menuVeget);
			afficher(menuVegan);
			afficher(menuNormal);
		}
	}
	

	
	
	///mehthode qui affiche les noms des plats
	public static void afficher(ArrayList <ComposantMenu> r) {
		for(int i=0;i<r.size();i++) {
		
			System.out.println( (i+1)+": "+ (r.get(i)).getNom() +"  :  "+r.get(i).getPrix()+ "€");
		}
		
	}
	///methode qui affiche le menu en fonction du regime et fait le choix des plats dessert et entree
	
public static void choixMenu(Commande c,Client n) {
		
		
		
		System.out.println("Choisissez votre entree");
		
		
				if (n.getRegime().equals("Vegetarien")) {
					afficherEntree(menuVeget);
					
					boolean b=true;
					while(b){
						try{
							int d=scan.nextInt();
							if(d>3|| d<1){
								throw new InvalidInputException ("Tapez un nombre entre 1 et 3");
							}
							else{
								c.setEntreechoix(menuVeget.get(d-1));

								b=false;
							}
						}
						catch(InvalidInputException e){
							System.out.println(e.getMessage());

						}
					
				}
					
				}
					
					
				
				else if (n.getRegime().equals("Vegan")) {
					afficherEntree(menuVegan);
					boolean b=true;
					while(b){
						try{
							int d=scan.nextInt();
							if(d>3|| d<1){
								throw new InvalidInputException ("Tapez un nombre entre 1 et 3");
							}
							else{
								c.setEntreechoix(menuVegan.get(d-1));

								b=false;
							}
						}
						catch(InvalidInputException e){
							System.out.println(e.getMessage());

						}
					
				}
				}
				else if (n.getRegime().equals("Normal")) {
					afficherE();
					boolean b=true;
					while(b){
						
						try{
						int d=scan.nextInt();
						if(d>0 && d<4) {
						c.setEntreechoix(menuNormal.get(d-1));
						b=false;
						}
						if(d>3 && d<7) {
							c.setEntreechoix(menuVegan.get(d-4));
							b=false;
							}
						if(d>6 && d<10) {
							c.setEntreechoix(menuVeget.get(d-7));
							b=false;
						}
						if(d>9 || d<1){
							throw new InvalidInputException("Tapez un nombre entre 1 et 9");
						}
						
					}
					catch(InvalidInputException e){
							System.out.println(e.getMessage());
						}

					}
					
				}
//////////////////////////////CHOIX DE PLATS////////////////////////////////////////				
				System.out.println("Choisissez votre plats");
				
				if (n.getRegime().equals("Vegetarien")) {
					afficherPlats(menuVeget);
					boolean b=true;
					while(b){
						try{
							int d=scan.nextInt();
							if(d>3|| d<1){
								throw new InvalidInputException ("Tapez un nombre entre 1 et 3");
							}
							else{
								c.setPlatschoix(menuVeget.get(d+2));
								b=false;
							}
						}
						catch(InvalidInputException e){
							System.out.println(e.getMessage());

						}
					
				}
					
				}
					
					
				
				else if (n.getRegime().equals("Vegan")) {
					afficherPlats(menuVegan);
					
					
					boolean b=true;
					while(b){
						try{
							int d=scan.nextInt();
							if(d>3|| d<1){
								throw new InvalidInputException ("Tapez un nombre entre 1 et 3");
							}
							else{
								c.setPlatschoix(menuVegan.get(d+2));
								b=false;
							}
						}
						catch(InvalidInputException e){
							System.out.println(e.getMessage());

						}
					
				}

				}
				else if (n.getRegime().equals("Normal")) {
						afficherP();
					boolean b=true;
					while(b){
						
						try{
							int d=scan.nextInt();
						if(d>0 && d<4) {
						c.setPlatschoix(menuNormal.get(d+2));
						b=false;
						}
						if(d>3 && d<7) {
							c.setPlatschoix(menuVegan.get(d-2));
							b=false;
							}
						if(d>6 && d<10) {
							c.setPlatschoix(menuVeget.get(d-4));
							b=false;
						}
						if(d>9 || d<1){
							throw new InvalidInputException("Tapez un nombre entre 1 et 9");
						}
						
					}
					catch(InvalidInputException e){
							System.out.println(e.getMessage());
						}

					}
					
				}
//////////////////////////////CHOIX DE DESSERT////////////////////////////////////////		
				
				
	System.out.println("Choisissez votre dessert");
	afficher(menuDessert);
	
	
	boolean b=true;
	while(b){
		try{
			int d=scan.nextInt();
			if(d>4|| d<1){
				throw new InvalidInputException ("Tapez un nombre entre 1 et 4");
					}
				else{
					c.setDessertchoix(menuDessert.get(d-1));
					b=false;
				}
			}
			catch(InvalidInputException e){
			System.out.println(e.getMessage());

						}
					
				}
	
	///////////////////////////////CHOIX DE BOISSON////////////////////////////////////////
  		System.out.println("Choisissez votre boisson");
  		afficher(menuBoisson);
  		b=true;
  		while(b){
		try{
			int d=scan.nextInt();
			if(d>8|| d<1){
				throw new InvalidInputException ("Tapez un nombre entre 1 et 8");
					}
			else{
					c.setBoissonchoix(menuBoisson.get(d-1));
					b=false;
				}
			}
			catch(InvalidInputException e){
					System.out.println(e.getMessage());

						}
					
				}

}
				
	
		
		
	
	
	public static void afficherEntree(ArrayList <ComposantMenu> r) {
		for(int i=0;i<3;i++) {
		
			
			System.out.println( (i+1)+": "+ (r.get(i)).getNom()+ "  :  "+r.get(i).getPrix() +"€");
		}
	}
		
	public static void afficherPlats(ArrayList <ComposantMenu> r) {
		int c=1;
			for(int i=3;i<6;i++) {
				
				System.out.println( (c)+": "+ (r.get(i)).getNom() + "  :  "+r.get(i).getPrix()+"€");
				c++;
			}
	}
	
	////////////affichage des entree 
	
	public static void afficherE() {
		int c=1;
		for (int i=0;i<3;i++) {
			System.out.println( c+": "+ (menuNormal.get(i)).getNom() +"  : "+ menuNormal.get(i).getPrix()+"€");
			c++;
		}
		for (int i=0;i<3;i++) {
			System.out.println( c+": "+ (menuVegan.get(i)).getNom() +"  : "+ menuNormal.get(i).getPrix()+"€");
			c++;
		}
		for (int i=0;i<3;i++) {
			System.out.println( c+": "+ (menuVeget.get(i)).getNom()+"  : "+ menuNormal.get(i).getPrix() +"€");
			c++;
		}
		
		
		
	}
	
	public static void afficherP() {
		int c=1;
		for (int i=3;i<6;i++) {
			System.out.println( c+": "+ (menuNormal.get(i)).getNom() +"  : "+ menuNormal.get(i).getPrix() +"€");
			c++;
		}
		for (int i=3;i<6;i++) {
			System.out.println( c+": "+ (menuVegan.get(i)).getNom() +"  : "+ menuNormal.get(i).getPrix()+"€");
			c++;
		}
		for (int i=3;i<6;i++) {
			System.out.println( c+": "+ (menuVeget.get(i)).getNom() +"  : "+ menuNormal.get(i).getPrix()+"€");
			c++;
		}

	}
	
///////get instance menu////
	
	public static Menu getMenu() {
		return MENU;
	}
	
	
}
	
	
	

	
	


