
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.CommandeImpossiblePourLeVivantException;

import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public class Main {

  private Simulateur simu;

  /**
	* Constructeur du Main
	*/
  public Main() {
    this.simu = null;
  }

  /**
	* afficherMenu affiche le menu du jeu
	*/
  private void afficherMenu() {
    System.out.println("\n --- Menu --- ");
    System.out.println("1/ jouer ");
    System.out.println("2/ charger un fichier de description ");
    System.out.println("3/ sauver la partie actuelle ");
    System.out.println("4/ charger une partie ");
    System.out.println("5/ quitter \n");
  }

  /**
	* lireChoix lit les choix du joueur
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
  * @throws FileNotFoundException
  * @throws ClassNotFoundException
	*/
  private void lireChoix ()
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException, FileNotFoundException, ClassNotFoundException, Throwable {
    Scanner sc = new Scanner(System.in);
    boolean run = true;
    while (run) {
      afficherMenu();
      int choix = sc.nextInt();
      switch(choix) {
        case 1:
          System.out.println("jouer");
          jouer();
          break;
        case 2:
          System.out.println("Veuillez entrer le nom du fichier à charger: ");
          chargerFichierDescription(sc.next());
          break;
        case 3:
          System.out.println("Veuillez entrer le chemin de sauvegarde: ");
          sauvegarder(sc.next());
          break;
        case 4:
          System.out.println("Veuillez entrer le nom de la sauvegarde à charger: ");
          chargerSauvegarde(sc.next());
          break;
        case 5:
          run = false;
          System.out.println("Vous quitter le jeu");
          break;
      }
    }
    sc.close();
  }

  /**
	* chargerFichierDescription charge une partie depuis un ficher texte
  * @param filename chemin d'acces au fichier
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws IOException
	*/
  private void chargerFichierDescription(String filename)
  throws NomDEntiteDejaUtiliseDansLeMondeException, IOException {
    this.simu = new Simulateur(new FileReader(new File(filename)));
    System.out.println("fichier bien charge !");
  }

  /**
	* sauvegarder enregistre la partie dans un fichier de sauvegarde
  * @param nomSauvegarde chemin d'acces au fichier de sauvegarde
  * @throws ClassNotFoundException
  * @throws IOException
  * @throws FileNotFoundException
	*/
  private void sauvegarder(String nomSauvegarde)
  throws ClassNotFoundException, IOException, FileNotFoundException {
    if (this.simu != null) {
      ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(nomSauvegarde));
      this.simu.enregistrer(oos);
      oos.close();
      System.out.println("fichier bien sauvegarde");
    } else {
      System.out.println("Vous n'avez pas charge de partie !");
    }

  }

  /**
	* chargerSauvegarde charge une partie depuis un ficher de sauvegarde
  * @param nomSauvegarde chemin d'acces au fichier de sauvegarde
  * @throws NomDEntiteDejaUtiliseDansLeMondeException
  * @throws ClassNotFoundException
  * @throws IOException
  * @throws FileNotFoundException
	*/
  private void chargerSauvegarde(String nomSauvegarde)
  throws NomDEntiteDejaUtiliseDansLeMondeException, ClassNotFoundException, IOException, FileNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomSauvegarde));
    this.simu = new Simulateur(ois);
    ois.close();
    System.out.println("fichier bien charge !");
  }

  /**
	* jouer action de joueur un tour ou plus
  * @throws Throwable
	*/
  private void jouer() throws Throwable {
    if (this.simu != null){
      boolean continuer = true;
      Scanner sc ;
      do{
        try {
          this.simu.executerUnTour();
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        if (this.simu.getEtatDuJeu() == EtatDuJeu.ENCOURS) {
          System.out.println("Veux tu rejouer?(1=oui, autre = non)");
          sc = new Scanner(System.in);
          continuer = (sc.next().equals("1"));
        }
      } while((continuer) & (this.simu.getEtatDuJeu() == EtatDuJeu.ENCOURS));
      if (this.simu.getEtatDuJeu() == EtatDuJeu.SUCCES) {
        System.out.println("Bravo ! Vous avez gagné la partie !");
      }
      else if (this.simu.getEtatDuJeu() == EtatDuJeu.ECHEC) {
        System.out.println("Game Over...");
      }
    }
  }

  /**
	* main fonction principale du projet
  * @param args arguments donnee lors de l'execution terminal
  * @throws Throwable
	*/
	public static void main(String[] args)
  throws Throwable {
    Main game = new Main();
    game.lireChoix();
	}

}
