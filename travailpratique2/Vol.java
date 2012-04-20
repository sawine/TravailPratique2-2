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
    private Passager[] passagersTriRapide;

    public String fillPassagersTriNom() {
        passagersTriNom = new Passager[nbPassagers];
        for (int j = 0; j < nbPassagers; j++) {
            passagersTriNom[j] = passagers[j];
        }
        trierParNom(passagersTriNom);

        String s = "Liste des passagers selon leur nom et prénoms:\n";

        for (int i = 0; i < nbPassagers - 1; i++) {
            s += "\n" + passagersTriNom[i];
        }

        return s;
    }

    public String fillrapide() {
        passagersTriRapide = new Passager[nbPassagers];
        for (int j = 0; j < nbPassagers; j++) {
            passagersTriRapide[j] = passagers[j];
        }
        trierRapide(passagersTriRapide);

        String s = "Liste des passagers selon leur # de sièges:\n";

        for (int i = nbPassagers -1 ; i >= 0; i--) {
        //for (int i = 0; i < nbPassagers; i++) {
            s += "\n" + passagersTriRapide[i];
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
        for (int i = 0; i < nbPassagers; i++) {
            if (this.passagers[i].getPass() == numPass) {
                return true;
            }
        }
        return false;
    }

    public boolean rechercher2(String numBill) {
        for (int i = 0; i < nbPassagers; i++) {
            if (this.passagers[i].getNumBill().contains(numBill)) {
                return true;
            }
        }
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

    //-- Tri rapide --
    public void trierRapide(Passager[] passagersTriRapide) {
        trierPPMRecursif(0, nbPassagers - 1);
    }

    private void trierPPMRecursif(int inf, int sup) {
        int n = sup - inf + 1;

        if (n <= 3) {
            trierManuellement(inf, sup);
        } else //partition plus large que 3
        {
            double mediane = medianeDeTrois(inf, sup);
            int partition = partionner(inf, sup, mediane);
            trierPPMRecursif(inf, partition - 1);
            trierPPMRecursif(partition + 1, sup);
        }

    }

    private int partionner(int inf, int sup, double pivot) {
        int ptrInf = inf;
        int ptrSup = sup - 1; //position du pivot

        while (true) {
            while (passagers[++ptrInf].getSiegeW() > pivot);
            while (passagers[--ptrSup].getSiegeW() < pivot);

            if (ptrInf >= ptrSup) // NE CHANGE PAS POUR L'ORDRE DÉCROISSANT
            {
                break;
            } else {
                permuter(ptrInf, ptrSup);
            }
        }

        permuter(ptrInf, sup - 1); // restauration du pivot
        return ptrInf;
    }

    private void trierManuellement(int inf, int sup) {
        int n = sup - inf + 1;

        if (n <= 1) {
            return; //pas besoin de tri
        }
        if (n == 2) {   //on fait une permutation si nécessaire
            if (passagers[inf].getSiegeW() < passagers[sup].getSiegeW()) {
                permuter(inf, sup);
            }
            return;
        } else // n = 3
        {
            //-- tri des 3 données --
            if (passagers[inf].getSiegeW() < passagers[sup - 1].getSiegeW()) {
                permuter(inf, sup - 1);
            }
            if (passagers[inf].getSiegeW() < passagers[sup].getSiegeW()) {
                permuter(inf, sup);
            }
            if (passagers[sup - 1].getSiegeW() < passagers[sup].getSiegeW()) {
                permuter(sup - 1, sup);
            }
        }
    }

    private double medianeDeTrois(int inf, int sup) {
        int centre = (inf + sup) / 2;

        //-- tri des 3 données --
        if (passagers[inf].getSiegeW() < passagers[centre].getSiegeW()) {
            permuter(inf, centre);
        }
        if (passagers[inf].getSiegeW() < passagers[sup].getSiegeW()) {
            permuter(inf, sup);
        }
        if (passagers[centre].getSiegeW() < passagers[sup].getSiegeW()) {
            permuter(centre, sup);
        }

        permuter(centre, sup - 1); //on met le pivot au bout - 1 (le bout est déjà trié)
        return passagers[sup - 1].getSiegeW();
    }

    private void permuter(int a, int b) {
        Passager temp = passagers[a];
        passagers[a] = passagers[b];
        passagers[b] = temp;
    }
}