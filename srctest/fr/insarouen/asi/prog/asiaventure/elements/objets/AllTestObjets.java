package fr.insarouen.asi.prog.asiaventure.elements.objets;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import fr.insarouen.asi.prog.asiaventure.elements.objets.TestPiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.TestSerrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.TestClef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.TestCoffre;


@RunWith(Suite.class)
@SuiteClasses({

	TestPiedDeBiche.class,
	TestClef.class,
	TestSerrure.class,
	TestCoffre.class

})

public class AllTestObjets{}
