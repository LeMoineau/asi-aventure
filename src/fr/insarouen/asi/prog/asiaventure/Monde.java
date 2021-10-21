
package fr.insarouen.asi.prog.asiaventure;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.Collections;
import java.lang.Throwable;
import java.util.List;
import java.util.stream.Collectors;


import java.io.Serializable;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;



public class Monde implements Serializable {

	String nomDuMonde;
	HashMap<String,Entite> entites;

	/**
	* Constructeur de Monde
	* @param nomDuMonde nom du monde
	*/
	public Monde(String nomDuMonde) {
		this.nomDuMonde = nomDuMonde;
		entites = new HashMap<String,Entite>();
	}

	/**
	* GetNom Getter du nom du monde
	* @return nom du monde
	*/
	public String getNom() {
		return this.nomDuMonde;
	}

	/**
	* GetEntite cherche une entite par son nom
	* @param nomEntite nom de l'entite a rechercher
	* @return Entite cherche
	*/
	public Entite getEntite(String nomEntite) {
		return this.entites.get(nomEntite);
	}

	/**
	 * Ajouter ajoute une entite dans le monde
	 *
	 * @param entite l'entite a rechercher
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 * @throws EntiteDejaDansUnAutreMondeException
	 */
	public void ajouter(Entite entite)
	throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
		if (this.getEntite(entite.getNom()) != null) {
			throw new NomDEntiteDejaUtiliseDansLeMondeException();
		} else if (entite.getMonde() != this) {
			throw new EntiteDejaDansUnAutreMondeException(String.format("L'entite %s est déjà dans un autre monde", entite.getNom()));
		} else {
			this.entites.put(entite.getNom(),entite);
		}
	}

	/**
	* toString créer un String décrivant le monde
	* @return le String de description
	*/
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n----- Monde -----\n");
		str.append(String.format("nomDuMonde: %s\nentites:", this.nomDuMonde));
		for (Entite e : this.entites.values()) {
			str.append(String.format("\n - '%s'", e.getNom()));
		}
		str.append("\n-----------------\n");
		return str.toString();
	}

	/**
	* getExecutables renvoie tous les executables du monde
	* @return Collection des executables du monde
	*/
	public Collection<Executable> getExecutables(){
		return this.entites.values()
			.stream()
			.filter(p -> (p instanceof Executable))
			.map(e -> (Executable)e)
			.collect(Collectors.toList());
	}
}
