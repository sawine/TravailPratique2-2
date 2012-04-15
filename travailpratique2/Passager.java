package TravailPratique2;

public class Passager {

    private String nom;
    private String prenom;
    private int numPass;
    private String numBill;
    private boolean classe;
	
	public Passager(String nom, String prenom, int numPass, String numBill, boolean classe)
        {
            this.nom = nom;
            this.prenom = prenom;
            this.numPass = numPass;
            this.numBill = numBill;
            this.classe = classe;
        }
	
        public boolean getClasse()
        {
            return classe;
        }
        
        public String toString()
        {
            return this.prenom + " " + this.nom + " " +this.numPass + " " + this.numBill + " " + this.classe;
        }
        
}
