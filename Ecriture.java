//Auteur: Benali Syrine et Herrmann Hugues

import java.io.File;
import java.util.Scanner;

//fonctionne nickel

public class Ecriture {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ecrire le chemin ou doit etre le fichier ainsi que le titre voulu + extention .txt");
				
		String str = sc.nextLine();
		
		sc.close();
		
		File fichier = new File(str);
	      if (fichier.createNewFile())
	        System.out.println("Le fichier a été créé");
	      else
	        System.out.println("Erreur, Impossible de créer ce fichier");
	      

	}

}
