package TravailPratique2;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("+----------------==[Bienvenue]==----------------+");
        System.out.println("|   Travail pratique 2 - Compagnie Aérienne     |");
        System.out.println("|   Par: Steve O'Bomsawin & Mathieu Gagnon      |");
        System.out.println("+-----------------------------------------------+\n");

       Vol vol = new Vol(10);
        vol.ajouter(new Passager("Smith", "Joe", 1337, "23A", false));
        vol.ajouter(new Passager("Desbananes", "Yvan", 1338, "10C", true));
        vol.ajouter(new Passager("Desbalounes", "Yvan", 1339, "20B", true));
        vol.ajouter(new Passager("Charest", "Jean", 133713, "5A", true));
        vol.ajouter(new Passager("Harper", "Stefen", 13371313, "3F", true));
        vol.ajouter(new Passager("Smith", "Joe", 133700, "25B", true));

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
                System.out.println("Veuillez entrer le numéro de passport :");
                int numPass = getInt();
                //recherche if existe deja dans l'array
                System.out.println("Veuillez entrer le numéro de billet :");
                String numBill = getString();
                //recherche if existe deja dans l'array
                int numClasse = 0;
                boolean classe = false;
                boolean isLegal = false;
                do {
                System.out.println("Veuillez entrex le numéro de la classe désirée :");
                System.out.println("1 - Affaire");
                System.out.println("2 - Économique");
                    numClasse = getInt();
                if (numClasse == 1) {  classe = true; isLegal = true;}
                else if (numClasse == 2) { classe = false; isLegal = true;}
                else { System.out.println("Choix invalide!"); isLegal = false;}
                } while (isLegal == false);
                vol.ajouter(new Passager(nom, prenom, numPass, numBill, classe));
                    break;

            case 2:
                
                break;

            case 3:

                break;

            case 4:{

                break;}

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
    
}
