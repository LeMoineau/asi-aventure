package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.TestVivant;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.TestMonstre;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.TestJoueurHumain;


@RunWith(Suite.class)
@SuiteClasses({

	TestVivant.class,
	TestMonstre.class,
	TestJoueurHumain.class

})

public class AllTestVivants{}
