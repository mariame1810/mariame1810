import java.util.ArrayList;

public class Simulation {

	private Terrain t;
	private Ressource [] res; //tableau de Ressources
	private Butineuse [] tabButineuses ; // tableau d'Abeilles Butineuses
	private Magasiniere [] tabMagasinieres; // tableau d'Abeilles Magasinieres
	private Apiculteur [] tabApiculteurs; //tableau d'Apiculteurs
	
	public Simulation(Terrain t, Butineuse [] tabButineuses, Magasiniere [] tabMagasinieres, Apiculteur [] tabApiculteurs, Ressource [] res){
		
		// affectation des valeurs passé par arguments aux attributs
		this.t = t;
		this.tabButineuses = tabButineuses;
		this.tabMagasinieres = tabMagasinieres;
		this.tabApiculteurs = tabApiculteurs;
		this.res = res;
		//varibales temporraire
		int posx;
		int posy;
		//boucles pour rechercher aleatoirement une case valide et libre et placer chaque ressource dans ces cases
		for(int i=0; i < res.length; i++){
			
			do {
				posx = (int) (Math.random()*(Terrain.NBLIGNESMAX-1));
				posy = (int) (Math.random()*(Terrain.NBCOLONNESMAX-1));
			}
			while ( !(t.sontValides(posx, posy) || t.caseEstVide(posx, posy))); 
			t.setCase(posx, posy, res[i]);
		}
	}
	
	// methode qui effectue des recoltes de pollen et des production de miel
	public void recolteProduction(Terrain t) {

		for (int i = 0; i<Terrain.NBLIGNESMAX; i++){
			for(int j=0; j < Terrain.NBCOLONNESMAX; j++){
				// si ya du ressource dans la case i j 
				if(t.getCase(i, j) != null) {
					if(t.getCase(i,j).type == "Pollen"){
						// on calcul la distance entre la première abeille dans le tableau et cette ressource (pollen)
						int distance = this.tabButineuses[0].distance(i, j);
						int position = 0; // indique l'indice de l'abeille
						//boucle pour parcourir le tableau d'abeilles et trouvé l'abeille la plus proche à cette ressource
						for (int k=1; k < this.tabButineuses.length; k++){
							if(this.tabButineuses[k].distance(i,j) < distance){
								distance = this.tabButineuses[k].distance(i,j);
								position = k; // on memorise son indice
							}
						}
						// on deplace l'abeille la plus proche
						this.tabButineuses[position].seDeplace(i, j);
						
						// on recupère le pollen (on le consomme en totalité) et on produit la Cire et on le pose dans la meme case
						
						Ressource recup = t.videCase(i, j);
						tabButineuses[position].recolteProduction(recup);
						
						t.setCase(i, j, new Ressource("Cire", tabButineuses[position].getCireProduite()));
						
						// on affiche la quantité du pollen consommée et de cire produite par l'abeille Butineuse
						System.out.println(tabButineuses[position].toString());

					}
					
					else if(t.getCase(i,j).type == "Nectar"){
						// on calcul la distance entre la première abeille dans le tableau et cette ressource (Nectar)
						int distance = this.tabMagasinieres[0].distance(i, j);
						int position = 0; // indique l'indice de l'abeille
						//boucle pour parcourir le tableau d'abeilles et trouvé l'abeille la plus proche à cette ressource
						for (int k=1; k < this.tabMagasinieres.length; k++){
							if(this.tabMagasinieres[k].distance(i,j) < distance){
								distance = this.tabMagasinieres[k].distance(i,j);
								position = k; // on memorise son indice
							}
						}
						// on deplace l'abeille la plus proche
						this.tabMagasinieres[position].seDeplace(i, j);
						
						// on recupère le Nectar (on le consomme en totalité) et on produit le Miel et on le pose dans la meme case
						
						Ressource recup = t.videCase(i, j);
						tabMagasinieres[position].recolteProduction(recup);
						
						t.setCase(i, j, new Ressource("Miel", tabMagasinieres[position].getMielProduit()));
						
						// on affiche la quantité du Nectar consommée et de Miel produit par l'abeille Magasiniere
						System.out.println(tabMagasinieres[position].toString());
					}
					
					else if(t.getCase(i,j).type == "Miel"){
									// on calcul la distance entre le premier apiculteur dans le tableau et cette ressource (Miel)
									int distance = this.tabApiculteurs[0].distance(i, j);
									int position = 0; // indique l'indice de l'appiculteur
									//boucle pour parcourir le tableau d'apiculteurs et trouvé l'apiculteur le plus proche de cette ressource
									for (int k=1; k < this.tabApiculteurs.length; k++){
										if(this.tabApiculteurs[k].distance(i,j) < distance){
											distance = this.tabApiculteurs[k].distance(i,j);
											position = k; // on memorise son indice
										}
									}
									// on deplace l'apiculteur le plus proche
									this.tabApiculteurs[position].seDeplace(i, j);
									
									// on recupère la Cire et le Miel produits par les abeilles
									
									Ressource recup = t.videCase(i, j);
									//tabApiculteurs[position].recolteProduction(recup);
									
									// on affiche la quantités de Cire et de Miel récupérées par l'appiculteur
									System.out.println(tabApiculteurs[position].toString());
					}
					else {
									// on calcul la distance entre le premier apiculteur dans le tableau et cette ressource (Cire)
									int distance = this.tabApiculteurs[0].distance(i, j);
									int position = 0; // indique l'indice de l'appiculteur
									//boucle pour parcourir le tableau d'apiculteurs et trouvé l'apiculteur le plus proche de cette ressource
									for (int k=1; k < this.tabApiculteurs.length; k++){
										if(this.tabApiculteurs[k].distance(i,j) < distance){
											distance = this.tabApiculteurs[k].distance(i,j);
											position = k; // on memorise son indice
										}
									}
									// on deplace l'apiculteur le plus proche
									this.tabApiculteurs[position].seDeplace(i, j);
									
									// on recupère la Cire et le Miel produits par les abeilles
									
									Ressource recup = t.videCase(i, j);
									//tabApiculteurs[position].recolteProduction(recup);
									
									// on affiche la quantités de Cire et de Miel récupérées par l'appiculteur
									System.out.println(tabApiculteurs[position].toString());
					}
					
					//affichage du terrain
					t.affiche();
					//affichage des statistique
					this.afficheStatistiques();
				}
				
				//sinon on continue sans rien faire
			}
		}
		
		//affichage du terrain
		t.affiche();
	}
	
	// fonction qui affiche les statistique de notre simulation
	public void afficheStatistiques() {
		System.out.println("Quantité de pollen consommé est : "+Butineuse.totalPollenConsomme() +" g\n" + "Quantité de Cire récoltée "+Apiculteur.totalCireRecoltee()+" g\n"+"Quantité de Nectar consommée "+Magasiniere.totalNectarConsomme()+" g\n"+"Quantité de Miel récoltée est : "+Apiculteur.totalMielRecolte());
	} 
}
