package fr.insarouen.asi.prog.asiaventure.elements.structure;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import fr.insarouen.asi.prog.asiaventure.elements.structure.TestPiece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.TestPorte;

@RunWith(Suite.class)
@SuiteClasses({

  TestPiece.class,
  TestPorte.class

})

public class AllTestStructure{}
