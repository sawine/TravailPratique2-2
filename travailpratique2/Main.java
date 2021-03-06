package TravailPratique2;

import java.io.*;

public class Main {

static String numBill = "";
static int numPass = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("+----------------==[Bienvenue]==----------------+");
        System.out.println("|   Travail pratique 2 - Compagnie Aérienne     |");
        System.out.println("|   Par: Steve O'Bomsawin & Mathieu Gagnon      |");
        System.out.println("+-----------------------------------------------+\n");

        Vol vol = new Vol(120);
        vol.ajouter(new Passager("Smith", "Joe", 13372341, "23A", false));
        vol.ajouter(new Passager("Desbananes", "Yvan", 13382314, "10C", true));
        vol.ajouter(new Passager("Desbalounes", "Yvan", 13393476, "4B", true));
        vol.ajouter(new Passager("Charest", "Jean", 13371375, "5A", true));
        vol.ajouter(new Passager("Harper", "Stefen", 13371313, "3F", true));
        vol.ajouter(new Passager("Wing", "Boe", 13370025, "25B", false));
        vol.ajouter(new Passager("Wing", "Tow", 15451337, "25A", false));
        vol.ajouter(new Passager("Despoires", "Yvan", 87951338, "9C", true));
        vol.ajouter(new Passager("Rienpentoute", "Yvan", 36413139, "21B", false));
        vol.ajouter(new Passager("Mainmoites", "Yale", 13223713, "2A", true));
        vol.ajouter(new Passager("Dlayeul", "Ypu", 13399913, "3E", true));
        vol.ajouter(new Passager("Aupier", "Gemal", 13213700, "29F", false));
        vol.ajouter(new Passager("Dider", "Gepu", 85463284, "18A", false));
        vol.ajouter(new Passager("Maipartou", "Yan", 63278591, "6F", true));
        vol.ajouter(new Passager("Mainulpar", "Yan", 176785179, "26c", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 176575179, "26a", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 1764685179, "27a", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 17655859, "27d", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 1676658517, "2b", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 176865179, "27f", false));
        vol.ajouter(new Passager("Mainulpar", "Yan", 1768655179, "27E", false));


        int choix = -1;

        do {



            System.out.println("+----------------------------------==[Info du vol]==--------------------------------+");
            System.out.println(vol.toString());
            System.out.println("+-----------------------------------------------------------------------------------+");

            System.out.println("+-------------------------------------==[MENU]==------------------------------------+");
            System.out.println("|  [1] - Enregistrer un passager                                                    |");
            System.out.println("|  [2] - Afficher l'ordre d'embarquement des passagers                              |");
            System.out.println("|  [3] - Afficher la liste des passagers en fonction de leur nom et prénom          |");
            System.out.println("|  [4] - Afficher la liste des passagers en fonction de leur position dans l'avion  |");
            System.out.println("|  [5] - Quitter                                                                    |");
            System.out.println("+-----------------------------------------------------------------------------------+");
            System.out.println("[Faites votre choix et appuyez sur ENTER]");
            choix = getInt();


            switch (choix) {
                case 1:

                    System.out.println("Veuillez entrer le prénom :");
                    String prenom = getString();
                    System.out.println("Veuillez entrer le nom :");
                    String nom = getString();

                    do {
                    System.out.println("Veuillez entrer le numéro de passport :");
                    numPass = getInt();

                  //   do {System.out.println("Ce passport existe déjà, essayez de nouveau:");}
                  //  while ((vol.rechercher(numPass)) == false);

                     } while ((vol.rechercher(numPass)) == true);

                    do {
                    System.out.println("Veuillez entrer le numéro de billet :");
                        numBill = getString();

                    } while ((!numBillCheck() == true) || (vol.rechercher2(numBill.toUpperCase())) == true);

                    int numClasse = 0;
                    boolean classe = false;
                    boolean isLegal = false;

                    do {
                        System.out.println("Veuillez entrer le numéro de la classe désirée :");
                        System.out.println("1 - Affaire");
                        System.out.println("2 - Économique");
                        numClasse = getInt();
                        if (numClasse == 1) {
                            classe = true;
                            isLegal = true;
                        } else if (numClasse == 2) {
                            classe = false;
                            isLegal = true;
                        } else {
                            System.out.println("Choix invalide!");
                            isLegal = false;
                        }
                    } while (isLegal == false);
                    vol.ajouter(new Passager(nom, prenom, numPass, numBill, classe));
                    break;

                case 2:
                    System.out.println(vol.toString2());

                    break;

                case 3:
                System.out.println(vol.fillPassagersTriNom());
                    break;

                case 4: {
                    vol.mergeSort();
                System.out.println(vol.getTriRange());
                    break;
                }

                case 5:
                    System.out.println("\nAu revoir!");
                    break;

                default:
                    System.out.println("\nChoix invalide!  Veuillez recommencer.");
                    break;
            }
        } while (choix != 5);
    }

    public static String getString() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static int getInt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(br.readLine());
    }
     public static boolean numBillCheck() {
    String s = new String();
         int i=0;
         while(Character.isDigit(numBill.charAt(i))) { s += numBill.charAt(i); i++;}
           if(Integer.parseInt(s) <= 30 && Character.getNumericValue(numBill.charAt(numBill.length()-1)) <=Character.getNumericValue('F') )
           return true;
           return false;

       }

         public static boolean numPassCheck(Passager[] numPass) {
        for (int i = 0; i < numPass.length; i++) {
            for (int j = 0; j < numPass.length; j++) {
                if (numPass[i].equals(numPass[j]) && i != j) {
                    return false;
                }
            }
        }
        return true;
    }
}