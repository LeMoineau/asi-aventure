package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;

public class TestClef {

	public Monde mondeTest;


	@Before
	public void init() throws Exception {
		mondeTest = new Monde("un monde");
	}

	@Test
	public void test_test() {
		assertThat(true, is(true));
	}

}
