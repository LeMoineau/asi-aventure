package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class PiedDeBiche extends Objet {

	static int nb_pied_created = 1;

	public static String generateNom(Monde monde) {
		String tmpNom = String.format("PiedDeBiche_%s", nb_pied_created);
		while (monde.getEntite(tmpNom) != null) {
			nb_pied_created += 1;
			tmpNom = String.format("PiedDeBiche_%s", nb_pied_created);
		}
		return tmpNom;
	}

	/**
	 * Constructeur de l'objet pied de biche
	 *
	 * @param monde monde de l'objet
	 * @throws EntiteDejaDansUnAutreMondeException
	 */
	public PiedDeBiche(Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(PiedDeBiche.generateNom(monde), monde);
	}

	/**
	 * estDeplacable redefinition de la méthode abstraite de Objet
	 * @return true pour deplacable, false sinon
	 */
	public boolean estDeplacable() {
		return true;
	}

}
