package TravailPratique2;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;

public class Vol {

    int numVol = 666;
    String lieuDepart = "YUL";
    String lieuArrive = "LOL";
    //SimpleDateFormat date = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
    Date aujourdhui = new Date();
    SimpleDateFormat dateDepart = new SimpleDateFormat("17/04/2012 15:30:00");
    SimpleDateFormat dateArrive = new SimpleDateFormat("17/04/2012 17:30:00");
    private Passager[] passagers;
    private int nbPassagers;

    public Vol(int taille) {
        this.passagers = new Passager[taille];
        this.nbPassagers = 0;        
    }
    
 public boolean ajouter(Passager passager) {
        int i;
        if (nbPassagers == 0) {
            passagers[nbPassagers++] = passager; //is liste vide           
        } else {
            if (nbPassagers == passagers.length) {
                agrandir(); // si pleine //!need doublecheck              
            }
            //on cherche s'il n'existe pas un autre passeport du meme #            
            for (i = 0; i < nbPassagers; i++) {
                if (passagers[i].getPass() == passager.getPass()) {
                    return false;
                }
            }
           
            //tri insersion
            //La classe affaire et économique on étés séparer pour  simplifié
            for (i= nbPassagers-1; i >= 0; i--) // i already set
            {
                //Classe affaire
                //Conditions :  Priorité sur la classe économiques sans exeption
                //      Priorité sur un siège plus petit.
              
                if(passager.isAffaire()){
                    if ( !passagers[i].isAffaire() || passager.getRange() > passagers[i].getRange() )  
                        passagers[i + 1] = passagers[i];
                    else {
                     //  System.out.println("Classe affaire " + passager+"\n");
                        break;
                    }                        
                }
                
                //classe économique 
                //condition : ne dois pas déclasser un 1ere classe,
                //      Priorité sur un siège plus petit de la classe économique.
                
                else{
                    if (/*(!passagers[i].isAffaire()) &&*/ (passager.getRange() > passagers[i].getRange()) )
                        passagers[i + 1] = passagers[i];                        
                    else{                            
                    //    System.out.println("Classe économique : + passager "+ "\n" );
                        break;
                    }
                }    
            }
            
            passagers[i+1] = passager;
            nbPassagers++;

        }
     return true;
    }

    private void agrandir() {
        Passager[] temp = new Passager[passagers.length * 2];

        for (int i = 0; i < passagers.length; i++) {
            temp[i] = passagers[i];
        }

        passagers = temp;
    }

    public String toString() {
        String s = "Numéro du vol: " + numVol + "\nLieu de départ: " + lieuDepart + 
                "\nLieu d'arrivée: " + lieuArrive + "\nDate de départ: " + dateDepart.format(aujourdhui) + 
                "\nDate d'arrivée: " + dateArrive.format(aujourdhui) + "\n\nListe des passagers:";

        for (int i = nbPassagers - 1; i >= 0; i--) {
            s += "\n" + passagers[i];
        }

        return s;
    }
    
    public String toString2() {
        String s = "Ordre d'embarquement des passagers:\n";

        for (int i = nbPassagers - 1; i >= 0; i--) {
            s += "\n" + passagers[i];
        }

        return s;
    }    


    /* Tri shell */
    public void trierParNom(Passagers[] passagers){
    Passager temp;
        int i, j;
        int h = 1;
        while (h <= nbPassagers / 3) {
            h = h * 3 + 1; //(1,4,13,40,121)
        }
        while (h > 0) {
            for (i = h; i < nbPassagers; i++) {
                temp = passagers[i];
                j = i;

                while (j > h - 1 && passagers[j - h].getNameWeight() <= temp.getNameWeight()) {
                    passagers[j] = passagers[j - h];
                    j -= h;
                }

                passagers[j] = temp;
            }

            h = (h - 1) / 3;
        }
        
    }


    
}