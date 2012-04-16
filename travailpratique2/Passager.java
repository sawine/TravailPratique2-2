package TravailPratique2;

public class Passager {

    private String nom;
    private String prenom;
    private int numPass;
    private String numBill;
    private boolean affaire;
	
	public Passager(String nom, String prenom, int numPass, String numBill, boolean affaire)
        {
            this.nom = nom;
            this.prenom = prenom;
            this.numPass = numPass;
            this.numBill = numBill;
            this.affaire = affaire;
        }
	
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
          
                   
      
     
        public int getPass(){ return numPass; }
        
        public String toString()
        {
            return this.prenom + " " + this.nom + " " +this.numPass + " " + this.numBill + " " + this.affaire;
        }
        
}
