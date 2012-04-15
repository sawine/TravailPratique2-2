package TravailPratique2;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;

public class Vol {
	
	int numVol = 666;
	String lieuDepart = "YUL";
	String lieuArrive = "LOL";
        SimpleDateFormat date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date dateDepart = simpleDateFormat.parse("17/04/2012 15:30:00");
        SimpleDateFormat dateArrive = new SimpleDateFormat("17/04/2012 17:30:00");
        
        
	private Passager[] passagers;
        private int nbPassagers;
        

        
        public Vol (int taille){
            this.passagers = new Passager[taille];
            this.nbPassagers = 0;
        }
        
        public boolean ajouter(Passager passager)
        {
        if (nbPassagers == passagers.length){agrandir();}
       //  Passager temp;
           
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
                // passagers[j + 1] = passager;
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
        String s = "dateDepart" + " " + dateArrive + "";

        for (int i = nbPassagers - 1; i >=0; i--)
            s += "\n" + passagers[i];

        return s;
    }        
            
}
