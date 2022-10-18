//Auteur: Benali Syrine et Herrmann Hugues


public class arbre {
	// un arbre est constitu� de plusieurs sous arbre
	public String valeur;
    public arbre abr_gauche;
    public arbre abr_droit;
    public String feuille_droit;
    public String feuille_gauche;
    

   // CONSTRUCTEURS
    public arbre(String x, arbre g, arbre d) {
        this.valeur = x;
        this.abr_gauche = g;
        this.abr_droit = d;
    }
    
    public arbre(String x, String g, String d) {
        this.valeur = x;
        this.feuille_gauche = g;
        this.feuille_droit = d;
    }
    
    public void lecture(arbre mon_arbre) {
    	
    	if (mon_arbre != null) {
    		System.out.println("sous arbre droit ");
    		lecture(mon_arbre.abr_droit);
    		System.out.println("sous arbre gauche");
        	lecture(mon_arbre.abr_gauche);
        	//System.out.println("arbre ");
        	if(mon_arbre.feuille_droit.compareTo("x")!=0) {
        		System.out.println("feuille droite : " +mon_arbre.feuille_droit);
        	}
        	if(mon_arbre.feuille_gauche.compareTo("x")!=0) {
        		System.out.println("feuille gauche : "+ mon_arbre.feuille_gauche);
        	}
    	}
    	
    }
    
    
   
}
