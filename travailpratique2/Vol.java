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
            if(nbPassagers==0)passagers[nbPassagers++]= passager; //is liste vide           
            else{
                if (nbPassagers == passagers.length)agrandir(); // si pleine               
                                  
            //on cherche s'il n'existe pas un autre passeport du meme #            
                if(passagers[i].getPass() == passager.getPass())
                    return false;
                
            //inséré un passager Classe affaire
            for(i=nbPassagers-1;i>=0;i--)
            {
                if(passager.getRange()< passagers[i].getRange())
                    passagers[i+1] = passagers[i];
                else
                    break;
            }
            passagers[i+1]= passager;
            nbPassagers++;
            
            }
            return true;
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
