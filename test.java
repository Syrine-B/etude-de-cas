import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
	
	private int matrice[][]; 
	private String tableau[] = new String[6];
	private int nombre_sequence;
	private int tmp;
	
	public void lecture_fichier() throws FileNotFoundException {
		
		this.tmp=0;	
		Scanner sc = new Scanner(System.in);
		System.out.println("ecrire le chemin ou se trouve le fichier");
		///home/benali_syrine/Bureau/Semestre2/ADN16S2.fasta
		String str = sc.nextLine();
		sc.close();
		File file2 = new File(str);
		Scanner sc2 = new Scanner (file2);		
		while (sc2.hasNextLine()) {
			sc2.nextLine();
			if(sc2.hasNextLine()) {
				this.tableau[this.tmp] = sc2.nextLine();
				this.tmp++;
				
			}
		}
		sc2.close();
				
		this.nombre_sequence = tmp;
		
		this.matrice = new int [nombre_sequence][nombre_sequence];
	}
	
	

	public void utilisation_NW(){
	// envoyer les sequences à NW
	//crée moyen pour obtenir alignement des sequences et les conserver
	//conserver score alignement 
	 
		for (int i = 0; i < tmp ; i++) {
		 for(int j=0; j<tmp ; j++){
	 		if (j==i) {
	 			matrice[i][j] = 500000000;	
	 		}
	 		else {
	 			needleman test = new needleman(tableau[i],tableau[j]); //changer le nom du needleman pour en avoir un avec iterateur.
	 			test.alignement_sequence();
	 			matrice[i][j] = test.score_tot;
	 		}
		 }
		}
	}
	
public void afficheMatrice() {
		
		// affichage des valeurs de la matrice ligne par ligne
		
		
	    System.out.println("affichage de la matrice Via test");
	    
	    for(int i = 0; i < this.tmp ; i++){
	    	
	    	for(int j = 0; j < this.tmp ; j++)
	    	{
	    		System.out.print("\t" + this.matrice[i][j]);
	        }
	    	System.out.println("\n");
	    }
	}
	
	public void matrice_UPGMA() {
		UPGMA test = new UPGMA(this.matrice,this.tmp);
		test.utilisation();
		
	}
	
	// envoyer score a UPGMA + selection du plus petit + arbre
	/*
	 * envoyer tout les scores a la matrice UPGMA //OK
	 * 
	 *  
	 * class UPGM gère selection petite distance et creation arbre
	 * et mise a jour de la matrice // OK
	 * 
	 */
	
	// verifier assemblage des arbres au fur et a mesure // OK
	
	//ecriture
	/*
	 * test.utilisation; // lecture UPGMA + création arbre 
	 * mon_arbre = test.abr_complet //recupère arbre complet
	 *
	 * le lire de manière recursive
	 * a chaque itération faire alignement d'alignement ou
	 * faire aligmenent entre sequence et alignement
	 */
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		test essai = new test(); //ok
		essai.lecture_fichier(); //ok
		essai.utilisation_NW(); //ok
		//System.out.println("1er affichage matrice");
		//essai.afficheMatrice(); //ok
		//System.out.println("on passe a UPGMA");
		essai.matrice_UPGMA();
		
		
		
		
		
		
	}

}
