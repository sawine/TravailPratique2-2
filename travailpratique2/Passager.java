package TravailPratique2;

public class Passager {

    private String nom;
    private String prenom;
    private int numPass;            //passeport
    private String numBill;         //#deticket
    private boolean affaire;        //true si affaire
    private char siege;             //#de siege (a to f
    private int nWeight;            //le poid du nom (pour le triage)
	
	public Passager(String nom, String prenom, int numPass, String numBill, boolean affaire)
        {
            this.nom = nom;
            this.prenom = prenom;
            this.numPass = numPass;
            this.numBill = numBill;
            this.affaire = affaire;
        }
	/* plan pour vérifier numBill 
	créé variable numSiege et numRange
	créé fonction checkNumBill() qui retourne un bool et qui prend une string en paramêtre
	utilise le code de getRange et getSiegeW
	modifie ses deux fonction la aussi pour quil retourne les valeur des 2 variable créé
	
	créé une fonction private setNumBill qui va faire un peu la meme chose que checkNumBill
	 sauf que celle-ci va être utilise à l'intérieur du constructeur pour setté les valeur de
	 numSiege et numRange
	 
	
	
	
	
	*/
        public boolean isAffaire()
        {
            return affaire;
        }
       //test change
        public String getNumBill()
        {
            return numBill;
        }
        public int getRange() {
          String s = new String();
          int i=0;
      
          while(Character.isDigit(numBill.charAt(i))) { s += numBill.charAt(i); i++;}
            return Integer.parseInt(s);
        }
          
           public int getSiegeW(){  // retourne le poid ASCII du numero de siege
           return Character.getNumericValue(numBill.charAt(numBill.length()-2));
        }
        
        public String getLastName(){ return nom;}
        public String getFirstName(){return prenom.;}
        public int getNameWeight(){ //calcul le poid du nom selon un calcul
            return nWeight;
        }
        
/*
        private void setNameWeight(){
			// pas fini
			* //calcule le poid du nom + prenom
            int out;
            int p=3; //niveau de précision 
            int i;S
            for(i=p;i>=0;i--)
                out = 
        }
*/          
      
     
        public int getPass(){ return numPass; }
        
        public String toString()
        {
            return this.prenom + " " + this.nom + " " +this.numPass + " " + this.numBill + " " + this.affaire;
        }
        
}

