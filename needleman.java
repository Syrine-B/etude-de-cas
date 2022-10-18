//programme executant l'algorithme de Needleman et Wunsch avec cr�ation de la matrice de score
//Auteur: Benali Syrine et Herrmann Hugues
//

public class needleman {
	
	private int match;
	private int mismatch;
	private int gap;
	private String sequence1;
	private String sequence2 ;
	private String alignement1;
	private String alignement2;
	public int score_tot;
	public int matrice[][];
	
	//constructeur 
	public needleman(String s1, String s2) { 
		this(2,1,-10,s1,s2);		
	}
	
	public needleman(int ma, int mi, int ga, String s1, String s2) { 
		this.match = ma;
		this.mismatch = mi;
		this.gap = ga;
		this.sequence1 = s1;
		this.sequence2 = s2;
		
	}
	
	//Fonction
	
	public void createMatriceSequence(){ 
		int init = 0; //initialise la matrice a 0
		int i,j; //permet de parcourir la matrice
		int p1 = -10; // permet de garder les valeur possible en cas de match ou mismatch
		int p2,p3; //permet de sauvegarder les differentes valeurs possibles provenant du haut et de la gauche
		
		this.matrice = new int [this.sequence1.length()+1][this.sequence2.length()+1]; // la matrice prend la taille des sequences
		 
		// tout d'abord on remplis la matrice de 0
		for(i= 0; i < this.sequence1.length()+1; i++)
		{
            this.matrice[i][0] =  init;
        }
         
        for(i = 0; i < this.sequence2.length()+1; i++)
        {
            this.matrice[0][i] = init;
        }
        
		
        // On met les valeurs de la premi�re ligne et la premi�re colonne differemment car sinon probleme dans le remplissage de la matrice
        
        // premi�re valeur en haut a droite
        if(this.sequence1.charAt(0) == this.sequence2.charAt(0))
		{
        	this.matrice[1][1] = this.matrice[0][0] + this.match; // si match, on ajoute le score de la diagonale + le match
		}
		else
		{
			this.matrice[1][1] = this.matrice[0][0]+ this.mismatch; // si mismatch on ajoute le score de mismatch
		}
        
        //premi�re ligne et premi�re colonne
        
        for(j = 2; j <= this.sequence2.length(); j++) {
        	
        	this.matrice[1][j]=this.matrice[1][j-1]+this.gap;
        }
        for(i = 2; i <= this.sequence1.length(); i++) {
        	this.matrice[i][1]=this.matrice[i-1][1]+this.gap;
        }
       
        
        // ajout des valeurs dans le reste de la matrice
        
        for(i = 2; i <= this.sequence1.length(); i++)
        {
        	
            for(j = 2; j <= this.sequence2.length(); j++)
            {

            	if(this.sequence1.charAt(i-1) == this.sequence2.charAt(j-1)) //on fait charAt(i-1) car compte des caract�re commence a 0 et non a 1
            	{
            		p1 = this.matrice[i-1][j-1] + this.match; // si match, on ajoute le score de la diagonale + le match
            	}
            	else
            	{
            		p1 = this.matrice[i-1][j-1]+ this.mismatch; // si mismatch on ajoute le score de mismatch
            	}
            	
                p2 = this.matrice[i-1][j]+this.gap;
                p3 = this.matrice[i][j-1]+this.gap;
                
                this.matrice[i][j] = max(p1,p2,p3); //la valeur i,j de la matrice prend la valeur maximum
                }
            }
	}
		

	public int max(int i, int j, int k) {
		// renvoie la valeur la plus haute entre 3 valeurs mise en parametre
		if (i>=j)
		{
			if (i>=k)
			{
				return i;
			}
			else
			{
				return k;
			}
		}
		else
		{
			if (j>=k)
			{
				return j;
			}
			else
			{
				return k;
			}
		}			
		
		
	}
	
	public void afficheMatrice() {
		
		// affichage des valeurs de la matrice ligne par ligne
		
		System.out.println("Affichage de la sequence 1 : "+this.sequence1);
	    System.out.println("Affichage de la sequence 2 : "+this.sequence2);
	    System.out.println("affichage de la matrice");
	    
	    for(int i = 0; i < this.sequence1.length()+1; i++){
	    	
	    	for(int j = 0; j < this.sequence2.length()+1; j++)
	    	{
	    		System.out.print("\t" + this.matrice[i][j]);
	        }
	    	System.out.println("\n");
	    }
	}
	
	public void alignement_sequence() { // affiche l'alignement obtenu a partir de la matrice grace a un parcours en sens inverse
		createMatriceSequence();
		String AlignmentA = "";
		String AlignmentB = "";
		int i = this.sequence1.length();
		int j = this.sequence2.length();
		
		int score_alignement = matrice[this.sequence1.length()][this.sequence2.length()];
		
		//System.out.println("score debut: "+score_alignement+"\n");
		
		while (i>0 && j>0){
			
			int Score = matrice[i][j];
		    int ScoreDiag= matrice[i-1][j-1];
		    int ScoreUp = matrice[i][j-1];
		    int ScoreLeft = matrice[i-1][j];
		    
		    
		    if(this.sequence1.charAt(i-1) == this.sequence2.charAt(j-1)) // si les caract�re st les meme verifie si le score provient d'un match
        	{
		    	 if (Score == ScoreDiag + this.match)
				    {
				      AlignmentA = this.sequence1.charAt(i-1) + AlignmentA;
				      AlignmentB = this.sequence2.charAt(j-1) + AlignmentB;
				      score_alignement = score_alignement+ScoreDiag;
				      i--;
				      j--;
				      
				    }
        		
        	} 
		    
		    if (Score == ScoreDiag + this.mismatch) { // cas ou score provient de la diagonal mais en mismatch
		    	
		    	AlignmentA = this.sequence1.charAt(i-1) + AlignmentA;
			    AlignmentB = this.sequence2.charAt(j-1) + AlignmentB;
			    score_alignement = score_alignement+ScoreDiag;
			    i--;
			    j--;
		    	
		    }
		    
		    else if (Score == (ScoreLeft + this.gap)) // cas ou le score provient de la gauche
		    {
		      AlignmentA = this.sequence1.charAt(i-1) + AlignmentA;
		      AlignmentB = "-" + AlignmentB;
		      score_alignement = score_alignement+ScoreLeft;
		      i--;
		    }
		    
		    else if (Score == (ScoreUp+this.gap)) // cas ou le score provient du haut
		    {
		      AlignmentA = "-" + AlignmentA;
		      AlignmentB = this.sequence2.charAt(j-1) + AlignmentB;
		      score_alignement = score_alignement+ScoreUp;
		      j--;
		    }
		    
		    //System.out.println(i + "\n");
		    //System.out.println(j + "\n");
		}
		this.alignement1 = AlignmentA;
		this.alignement2 = AlignmentB;
		this.score_tot = score_alignement;
		//System.out.println("sequence 1: " +AlignmentA+ "\n");
		//System.out.println("sequence 2: " + AlignmentB+ "\n");
		//System.out.println("Score alignement total: "+score_alignement+"\n");
		
	}

}

