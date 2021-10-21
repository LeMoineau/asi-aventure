
package fr.insarouen.asi.prog.asiaventure;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import fr.insarouen.asi.prog.asiaventure.elements.TestEntite;
import fr.insarouen.asi.prog.asiaventure.elements.AllTestElements;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.TestVivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.TestPiece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.TestPorte;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.TestSerrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.TestClef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.TestPiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.TestCoffre;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.TestMonstre;
import fr.insarouen.asi.prog.asiaventure.TestSimulateur;
import fr.insarouen.asi.prog.asiaventure.TestConditionDeFin;

@RunWith(Suite.class)
@SuiteClasses({

	TestMonde.class,
	AllTestElements.class,
	TestSimulateur.class,
	TestConditionDeFin.class

})

public class AllTests{}
