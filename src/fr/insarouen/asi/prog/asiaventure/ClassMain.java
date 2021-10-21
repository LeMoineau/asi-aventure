
package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

import java.io.IOException;

public class ClassMain {

	public static void main(String[] args) throws NomDEntiteDejaUtiliseDansLeMondeException,
			EntiteDejaDansUnAutreMondeException, ObjetNonDeplacableException, ObjetAbsentDeLaPieceException,
			VivantAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException {


		System.out.println("\n=================================Test sur les entites=================================\n");

		// Test sur les entites
		Monde monde = new Monde("testeur");
		Entite ent1 = new Entite("Pierrot", monde) {
		};
		System.out.println(monde.toString());

		try {
			Entite ent1_like = new Entite("Pierrot", monde) {
			}; // a commenter car throws Exception
			System.out.println(ent1.equals(ent1_like));
		}
		catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
			e.printStackTrace();
		}
		Entite ent2 = new Entite("Louis", monde) {
		};
		System.out.println(ent1.toString());
		System.out.println(ent2.toString());
		System.out.println(ent1.equals(ent2));
		System.out.println(ent1.equals(ent1));
		System.out.println("\n=================================Test sur les mondes=================================\n");

		// Test sur les mondes
		System.out.println(monde.getEntite("Pierrot").toString());
		System.out.println(monde.getEntite("Louis").toString());
		System.out.println("\n");
		try {
			monde.ajouter(ent1); // erreur car nom Pierrot deja dedans
		} catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
			e.printStackTrace();
		}
		Monde monde2 = new Monde("Un Autre");
		Entite ent3 = new Entite("Audrey", monde2) {};
		System.out.println(ent3);
		try {
			monde.ajouter(ent3); // erreur car ent3 dans un autre monde
		} catch(EntiteDejaDansUnAutreMondeException e) {
			e.printStackTrace();
		}

		Entite e1 = new Entite("truc", monde) {};
		try {
			monde2.ajouter(e1);
		} catch(EntiteDejaDansUnAutreMondeException e) {
			e.printStackTrace();
		}
 		Entite e2 = new Entite("truc", monde2) {};

		System.out.println("\n=================================Test sur les enums=================================\n");


		// Test sur les enums
		Etat etat = Etat.valueOf("CASSE");
		System.out.println(etat);
		Etat[] etats = Etat.values();
		for (Etat e : etats) {
			System.out.println(e);
		}
		System.out.println("\n=================================Test sur les objets=================================\n");

		// Test sur les objets
		Objet obj = new Objet("test", monde) {
			public boolean estDeplacable() {
				return false;
			}
		};
		System.out.println(obj.toString());
		PiedDeBiche pied = new PiedDeBiche(monde);
		System.out.println(pied.toString());
		// Objet pied2 = new PiedDeBiche(monde); //a commenter car lance exception
		// System.out.println(pied2.toString());
		System.out.println("\n=================================Test sur les structures=================================\n");

		// Test des structures
		ElementStructurel struct = new ElementStructurel("mur", monde) {
		};
		System.out.println(struct.toString());

		// Test des pieces
		Piece p = new Piece("Une piece", monde);
		p.deposer(obj);
		System.out.println(p.toString());
		System.out.println(p.contientObjet(obj));
		// p.retirer(obj.getNom()); //erreur car objet non deplacable par default
		p.deposer(pied);
		System.out.println(p.toString());
		System.out.println(p.contientObjet(obj.getNom()));
		System.out.println(p.contientObjet(pied));

		System.out.println("\n=================================Test sur les vivants=================================\n");
		Vivant v = new Vivant("Coucou", monde, 10, 5, p, obj, pied) {};
		Objet pasDansPiece = new Objet("JeSuisPasDansLaPiece", monde) {
			public boolean estDeplacable() {
				return false;
			}
		};
		System.out.println(v.toString());
		v.deposer(pied);
		try {
			v.deposer(pasDansPiece);
		} catch (ObjetNonPossedeParLeVivantException e) {
			e.printStackTrace();
		}
		System.out.println(v.toString());
		v.prendre(pied);
		try {
			v.prendre(pasDansPiece);
		} catch (ObjetAbsentDeLaPieceException e) {
			e.printStackTrace();
		}
		System.out.println(v.toString());
		System.out.println("\n=================================Test sur les pièces=================================\n");

		//Test des Vivants dans les pieces
		p.entrer(v);
		System.out.println(p.toString());
		p.sortir(v);
		try {
			Piece p2 = new Piece("CoucouLesAMis", monde);
			Vivant v2 = new Vivant("Castor", monde, 100, 1000, p2) {};
			p.sortir(v2);
		} catch (VivantAbsentDeLaPieceException e) {
			e.printStackTrace();
		}
		System.out.println(p.toString());

		Monstre m = new Monstre("un monde", monde, 100, 10, p);
		JoueurHumain j = new JoueurHumain("un joueur", monde, 100000, 100000, p);


		ConditionDeFinVivantMort c1 = new ConditionDeFinVivantMort(EtatDuJeu.SUCCES, m);
		ConditionDeFinVivantDansPiece c2 = new ConditionDeFinVivantDansPiece(EtatDuJeu.SUCCES, m, p);
		ConditionDeFinVivantPossedeObjets c3 = new ConditionDeFinVivantPossedeObjets(EtatDuJeu.SUCCES, m, new Objet[0]);
		ConditionDeFinVivantDansPieceEtPossedeObjets c4 = new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.SUCCES, m, p);
		ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction c5 = new ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(EtatDuJeu.SUCCES, m, p, new Objet[0]);
		try {
			Simulateur sim = new Simulateur(monde,c1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
