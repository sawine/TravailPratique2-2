package TravailPratique2;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Vol {

    int numVol = 666;
    String lieuDepart = "YUL";
    String lieuArrive = "LOL";
    //SimpleDateFormat date = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
    Date aujourdhui = new Date();
    SimpleDateFormat dateDepart = new SimpleDateFormat("17/04/2012 15:30:00");
    SimpleDateFormat dateArrive = new SimpleDateFormat("17/04/2012 17:30:00");
    private Passager[] passagers;
    private Passager[] passagersTriNom;
    private Passager[] passagersTriPlusMoins;

    public String fillPassagersTriNom() {
        passagersTriNom = new Passager[nbPassagers];
        for (int j = 0; j < nbPassagers; j++) {
            passagersTriNom[j] = passagers[j];
        }
        trierParNom(passagersTriNom);

        String s = "Liste des passagers selon leur nom et prénoms:\n";

        for (int i = 0; i < nbPassagers-1; i++){
        
        //for (int i = nbPassagers - 1; i >= 0; i--) {
            s += "\n" + passagersTriNom[i];
        }

        return s;
    }

    public String fillPassagersTriPlusMoins() {
        passagersTriPlusMoins = new Passager[nbPassagers];
        for (int j = 0; j < nbPassagers; j++) {
            passagersTriPlusMoins[j] = passagers[j];
        }
        passagerTrierPlusMoins(passagersTriPlusMoins);

        String s = "Liste des passagers selon leur # de sièges:\n";

        for (int i = nbPassagers - 1; i >= 0; i--) {
            s += "\n" + passagersTriPlusMoins[i];
        }

        return s;
    }
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
            for (i = nbPassagers - 1; i >= 0; i--) // i already set
            {
                //Classe affaire
                //Conditions :  tasse tout les classe économiques sans exeption
                //      tasse tout les plus petit de classe affaire

                if (passager.isAffaire()) {
                    if (!passagers[i].isAffaire() || passager.getRange() > passagers[i].getRange()) {
                        passagers[i + 1] = passagers[i];
                    } else {
                        //  System.out.println("Classe affaire " + passager+"\n");
                        break;
                    }
                } //classe économique 
                //condition : ne dois pas tassé un 1ere classe,
                //      doit être plus grand que sont suivant
                else {
                    if ((!passagers[i].isAffaire()) && (passager.getRange() > passagers[i].getRange())) {
                        passagers[i + 1] = passagers[i];
                    } else {
                        //    System.out.println("Classe économique : + passager "+ "\n" );
                        break;
                    }
                }
            }

            passagers[i + 1] = passager;
            nbPassagers++;

        }
        return true;
    }

    
    public boolean rechercher(int numPass) {
        for (int i=0; i < nbPassagers; i++)
            if (this.passagers[i].getPass() == numPass )
                return true;
        return false;
    }
        public boolean rechercher2(String numBill) {
        for (int i=0; i < nbPassagers; i++)
            if (this.passagers[i].getNumBill().contains(numBill))
                return true;
        return false;
    }
    
    
    private void agrandir() {
        Passager[] temp = new Passager[passagers.length * 2];

        for (int i = 0; i < passagers.length; i++) {
            temp[i] = passagers[i];
        }

        passagers = temp;
    }

    public String toString() {
        String s = "Numéro du vol: " + numVol + "\nLieu de départ: " + lieuDepart + "\nLieu d'arrivée: " + lieuArrive + "\nDate de départ: " + dateDepart.format(aujourdhui) + "\nDate d'arrivée: " + dateArrive.format(aujourdhui) + "\n\nListe des passagers:";

        for (int i = nbPassagers - 1; i >= 0; i--) {
            s += "\n" + passagers[i];
        }

        return s;
    }

    public String toString2() {
        String s = "Ordre d'embarquement des passagers:\n";

        for (int i = 0; i < nbPassagers - 1; i++) {
            s += "\n" + passagers[i];
        }

        return s;
    }

    /*
     * Tri shell
     */
    public void trierParNom(Passager[] passagers) {
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

                while (j > h - 1 && passagers[j - h].isHeavier(temp)) {
                    passagers[j] = passagers[j - h];
                    j -= h;
                }

                passagers[j] = temp;
            }

            h = (h - 1) / 3;
        }

    }

    //-- Tri fusion --
    public void passagerTrierPlusMoins(Passager[] passagers) {
        Passager[] workspace = new Passager[nbPassagers];
        trierPlusMoinsRecursif(workspace, 0, nbPassagers - 1);
    }

    public void trierPlusMoinsRecursif(Passager[] workspace, int inf, int sup) {
        if (inf == sup) {
            return;
        } else {
            int centre = (inf + sup) / 2;
            trierPlusMoinsRecursif(workspace, inf, centre);
            trierPlusMoinsRecursif(workspace, centre + 1, sup);
            fusionner(workspace, inf, centre + 1, sup);
        }
    }

    public void fusionner(Passager[] workspace, int ptrTabInf, int ptrTabSup, int sup) {
        int j = 0;
        int inf = ptrTabInf;
        int centre = ptrTabSup - 1;
        int n = sup - inf + 1;

        while (ptrTabInf <= centre && ptrTabSup <= sup) {
            if (passagers[ptrTabInf].getRange() > passagers[ptrTabSup].getRange()) {
                workspace[j++] = passagers[ptrTabInf++];
            } else if (passagers[ptrTabInf].getRange() == passagers[ptrTabSup].getRange()) {
                if (passagers[ptrTabInf].getSiegeW() > passagers[ptrTabSup].getSiegeW()) {
                    workspace[j++] = passagers[ptrTabInf++];
                } else {
                    workspace[j++] = passagers[ptrTabSup++];
                }
            }
        }

        while (ptrTabInf <= centre) {
            workspace[j++] = passagers[ptrTabInf++];
        }

        while (ptrTabSup <= sup) {
            workspace[j++] = passagers[ptrTabSup++];
        }

        for (j = 0; j < n; j++) {
            passagers[inf + j] = workspace[j];
        }
    }
}