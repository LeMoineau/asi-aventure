package fr.insarouen.asi.prog.asiaventure.elements.objets;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;

public class TestPiedDeBiche{

	public Monde mondeTest;
	public PiedDeBiche pieddebicheTest;


	@Before
	public void init() throws Exception {
	  mondeTest = new Monde("un monde");
		pieddebicheTest = new PiedDeBiche(mondeTest);
	}

	@Test
	public void test_estDeplacable(){
		assertThat(pieddebicheTest.estDeplacable(),is(true));
	}


}
