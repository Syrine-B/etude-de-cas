//Auteur: Benali Syrine et Herrmann Hugues

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;


public class UPGMA {

	private int nbre_seq;
	private int matrice[][];
	private ArrayList valeur = new ArrayList();//https://docs.oracle.com/javase/6/docs/api/
	private ArrayList arbre_cree = new ArrayList();
	private int indice_liste_arbre = 0;
	private int min;
	
	//Constructeur
	
	public UPGMA(int matrice[][], int nbre) { 
			this.matrice = matrice;
			this.nbre_seq = nbre;
	}
	
	// Fonction

	@SuppressWarnings("unchecked")
	public void utilisation() { //voir pour conserver seq deja faite pour assembler arbre au fur et a mesure 
		int num_ali=0;
		while (num_ali < (this.nbre_seq*this.nbre_seq) && this.min< 499999999) {
			//System.out.println("\n\n nouveau tour de boucle ");
			lecture_matrice_UPGMA_min();
			moyenne_score((int)this.valeur.get(1),(int)this.valeur.get(2));
			//System.out.println("affichage matrice via UPGMA");
			//afficheMatrice();
			arbre test = new arbre("alignement"+num_ali,String.valueOf(this.valeur.get(1)),String.valueOf(this.valeur.get(2)));
			//System.out.println ("nouvel arbre");
			//test.lecture(test);
			num_ali++;
			test = verif_arbre(test);
			this.arbre_cree.add(indice_liste_arbre,test);
			this.indice_liste_arbre ++;
			System.out.println("nombre arbre " + this.indice_liste_arbre);
			//System.out.println("min actu " + this.min);
			//System.out.println("\n fin de boucle \n");
			
		}
		arbre complet = abr_complet();
		System.out.println("affichage arbre complet ?");
		complet.lecture(complet);
		
		System.out.println("affichage autre arbre");
		arbre arbre =(arbre) arbre_cree.get(1);
		arbre.lecture(arbre);
		
	}
	
	public arbre abr_complet() {
		
		return (arbre) arbre_cree.get(0);
	}
	
	public arbre verif_arbre(arbre nouv) {
		int i;
		boolean fait;
		arbre arbre_exist;
		for (i = 0; i < this.indice_liste_arbre; i++) {
			arbre_exist = (arbre) this.arbre_cree.get(i);
			fait = arbre_commun(arbre_exist, nouv);
			if (fait == true) {
				this.arbre_cree.remove(i);
				this.indice_liste_arbre --;
			} 
		}
		return nouv;
	}
	
	public boolean arbre_commun(arbre arbre1, arbre arbre2) {		 	
	    	if (arbre1.feuille_droit.contentEquals(arbre2.feuille_droit) && arbre2.feuille_droit.compareTo("x")!=0) {
	    		arbre2.abr_droit = arbre1;
	    		arbre2.feuille_droit = "x";
	    		return true;
	    	}
	    	else if (arbre1.feuille_gauche.contentEquals(arbre2.feuille_droit) && arbre2.feuille_droit.compareTo("x")!=0 ) {
	    		arbre2.abr_droit = arbre1;
	    		arbre2.feuille_droit = "x";
	    		return  true;	    		
	    	}
	    	else if (arbre1.feuille_droit.contentEquals(arbre2.feuille_gauche) && arbre2.feuille_gauche.compareTo("x")!=0 ) {
	    		arbre2.abr_gauche = arbre1;
	    		arbre2.feuille_gauche = "x";
	    		return true;
	    	}
	    	else if (arbre1.feuille_gauche.contentEquals(arbre2.feuille_gauche) && arbre2.feuille_gauche.compareTo("x")!=0 ) {
	    		arbre2.abr_gauche = arbre1;
	    		arbre2.feuille_gauche = "x";
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	
	@SuppressWarnings("unchecked")
	public void lecture_matrice_UPGMA_min() { //donne la valeur de la distance la moins �lev� et les sequence correspondante 
		
		int i,j;
		int mini = 500000000;
		
		for(i = 0; i < this.nbre_seq; i++) {    	
	            for(j = 0; j <= i; j++) {
	            	if(this.matrice[i][j] < mini && i!=j) {
	            		mini = this.matrice[i][j];
	            		this.valeur.clear();
	            		this.valeur.add(0,min);
	            		this.valeur.add(1,i);
	            		this.valeur.add(2,j);
	            		
	            	}
	            }
	       }
		
		this.min = mini;
		
		
	}

	public void afficheMatrice() {
		
		// affichage des valeurs de la matrice ligne par ligne
		
		
	    System.out.println("affichage de la matrice via UPGMA");
	    
	    for(int i = 0; i < nbre_seq ; i++){
	    	
	    	for(int j = 0; j < nbre_seq ; j++)
	    	{
	    		System.out.print("\t" + this.matrice[i][j]);
	        }
	    	System.out.println("\n");
	    }
	}

	public void moyenne_score(int a, int b) {
		// seq 1 et 2 représente deux sequence selectionner preceemment, celle ou a distance UPGM etait la plus petite.
		int i,j;
		int seq1= a;
		int seq2=b;
		for(i = 0; i < this.nbre_seq; i++) {
			
			for(j=0; j<this.nbre_seq; j++) {
				//première verif
				if (i == seq1 && j != seq2 && i!=j) {
					if( this.matrice[i][j] != 500000000 ) {
						this.matrice[i][j] = (this.matrice[i][j] + this.matrice[seq2][j]) / 2;
				
					}
				}
					
				else if (i != seq1 && j==seq2 && i!=j){
					
					if( this.matrice[i][j] != 500000000 ) {
						this.matrice[i][j] = ((this.matrice[i][j] + this.matrice[i][seq1]) / 2);
				
					}
				}
				else if (i==seq1 && j==seq2) {
					this.matrice[i][j] = 500000000;
				}
				//seconde verif
				if (i == seq2 && j != seq1 && i!=j) {
					if( this.matrice[i][j] != 500000000 ) {
						this.matrice[i][j] = (this.matrice[i][j] + this.matrice[seq1][j]) / 2;
					}
				}
				else if (i != seq2 && j==seq1 && i!=j){
					if( this.matrice[i][j] != 500000000 ) {
						this.matrice[i][j] = (this.matrice[i][j] + this.matrice[i][seq2]) / 2;
					}
				}
				else if (i==seq2 && j==seq1) {
					this.matrice[i][j] = 500000000;
				}
				
			}
			
		}
		
	}
	
	
}

