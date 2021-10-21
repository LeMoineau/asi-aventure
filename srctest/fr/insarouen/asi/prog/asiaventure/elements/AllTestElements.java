package fr.insarouen.asi.prog.asiaventure.elements;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import fr.insarouen.asi.prog.asiaventure.elements.TestEntite;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.AllTestVivants;
import fr.insarouen.asi.prog.asiaventure.elements.structure.AllTestStructure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.AllTestObjets;

@RunWith(Suite.class)
@SuiteClasses({

	TestEntite.class,
	AllTestVivants.class,
	AllTestStructure.class,
	AllTestObjets.class

})

public class AllTestElements{}
