//class static qui continue l'ensenble des phrases à mettre dans le programme
//afin de le mettre plus comprehensible et facile à utiliser par l'utilisateur
public class Text {
    //constructeur privé pour ne pas avoir accès a l'instanciation de cette class etant donnée static
    private Text(){}
    //le text de debut
    static public void debut ()
    {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("--------------------------------DÉBUT DE LA SIMULATION------------------------------------------");
            System.out.println("        Ce programme fait la simulation d'un ecosystème ou il y a des ");
            System.out.println("abeilles qui recoltent le Pollen et le Nectar et produisent du Miel qui est récolté ensuite par des Apiculteur");
            System.out.println("---------------------(Il est loin de la réalité :D )----------------------");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("\n \n");
    } 
    //le text de fin
    static public void Fin ()
    {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("--------------------------------FIN DE LA SIMULATION-----------------------------------------");
        System.out.println("                                     FAIT PAR :                                        ");
        System.out.println("                         Yasser BENCHIKH & COULIBALY Mariame                         ");
        System.out.println("--------------------------------------------------------------------------------- ");
        System.out.println("\n \n");
    }      
}
