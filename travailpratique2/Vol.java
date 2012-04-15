package TravailPratique2;

import java.util.Date;

public class Vol {
	
	int numVol;
	String lieuDepart;
	String lieuArrive;
	Date dateDepart;
	Date dateArrive;
	private Passager[] passagers;
        private int nbPassagers;
        
        public Vol (int taille){
            this.passagers = new Passager[taille];
            this.nbPassagers = 0;
        }
        
        public boolean ajouter(Passager passager)
        {
        if (nbPassagers == passagers.length){agrandir();}
         Passager temp;
           
        for (int i = nbPassagers - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
		if (passagers[j].getClasse() == true)
		{ 
                    passagers[nbPassagers++] = passager;
                    //push at the top of the array
		}
            
                else 
            { 
                passagers[j + 1] = passagers[j];
                passagers[j] = passager;
                // ou passagers[j+1] = passager; ???
                //push (or unshift?) at the bottom of the array
            }
            
            //getNumBill , bubble sort ?
            
        return false;
    }
        
        
            private void agrandir()
    {
        Passager[] temp = new Passager[passagers.length * 2];

        for (int i = 0; i < passagers.length; i++)
            temp[i] = passagers[i];

        passagers = temp;
    }

            
        public String toString()
    {
        String s = "";

        for (int i = 0; i < nbPassagers; i++)
            s += "\n" + passagers[i];

        return s;
    }        
            
}
