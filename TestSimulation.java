import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class TestSimulation {


	public static final int  NB_RESSOURCE = 16; // varibale globale indique le nombre de global de pollen et de Nectar dans le terrain x
    	public static final int  QT_POLLEN = 3 ; //variable global pour indiquer la quantité de pollen dans chaque case
    	public static final int  QT_NECTAR = 3 ; //variable global pour indiquer la quantité de Nectar dans chaque case
    	
    	public static final int  NB_BUTINEUSE = 5;
    	public static final int  NB_MAGASINIERE = 5;
    	public static final int  NB_APICULTEUR = 2;
	
	public static void main(String [] args) {
	
		
		//creation d'un terrain 
        	Terrain t = new Terrain(20,20);
        	
        	//Déclaration d'une arrayList de ressources
        	Ressource [] resList = new Ressource[NB_RESSOURCE];
        	
        	for(int i =0 ; i < NB_RESSOURCE ; i++) {
        		if(i%2 == 0){
        			resList[i] = new Ressource("Pollen",QT_POLLEN);
        		}
        		else{
        			resList[i] = new Ressource("Nectar",QT_NECTAR);
        		}
       	}
       	
       	
        	//Déclaration d'un Tableau de Butineuses
        	Butineuse [] butinList = new Butineuse[NB_BUTINEUSE];
        	for(int i =0 ; i < NB_BUTINEUSE ; i++) {
        		butinList[i] = new Butineuse();
       	}
        	
       	//Déclaration d'un Tableau de Magasinières
        	Magasiniere [] magasList = new Magasiniere[NB_MAGASINIERE];
        	for(int i =0 ; i < NB_MAGASINIERE ; i++) {
        		magasList[i] = new Magasiniere();
       	}
		
		//Déclaration d'un Tableau d'Apiculteurs
        	Apiculteur [] apicuList = new Apiculteur[NB_MAGASINIERE];
        	for(int i =0 ; i < NB_APICULTEUR ; i++) {
        		apicuList[i] = new Apiculteur();
       	}
        	
		 //Declaration d'une simulation
       	 Simulation s = new Simulation(t, butinList, magasList, apicuList, resList);
       	 
       	 t.affiche();
       	 
       	 //affichage du text de bienvenue
		Text.debut();
       	 
       	 s.recolteProduction(t);
       	 //affichage du text de la fin
        	 Text.Fin();
        
	}
}
