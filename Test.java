import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Test{
	public static void main (String [] args){
		Scanner ss= new Scanner(System.in); 

		Client n = new Client();
	
		Reservation.inittab();
		Reservation.remplirtab();
		
		
		Reservation r1 = Reservation.reserver(n);
		
		System.out.println(Reservation.afficherPlace()+"\n");
		
		//System.out.println(n.toString()+"\n");

		System.out.println(r1.toString()+"\n");
		
		Menu r= Menu.getMenu();
		Commande c1=new Commande(n);
		
		
		Menu.choixMenu(c1,n);
	
		System.out.println(c1.afficheChoix()+"\n");

		//n.AllergieOuNon();

		c1.compoNutri();

		//c1.enleverAllergene(n);
		c1.facture();
		//System.out.println("\n"+n.getEntreechoix().toString());
		
	
		
	
}
	}