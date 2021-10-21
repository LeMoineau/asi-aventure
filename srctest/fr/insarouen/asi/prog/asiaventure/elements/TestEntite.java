
package fr.insarouen.asi.prog.asiaventure.elements;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class TestEntite {

	public Monde monde;
	public Entite entite;

	@Before
	public void init() throws Exception {
    monde = new Monde("MondeTest");
    entite = new Entite("NomEntiteTest", monde) {};
	}

	@Test
	public void test_getNom() {
		assertThat(entite.getNom(), equalTo("NomEntiteTest"));
	}

	@Test
	public void test_getMonde() {
		assertThat(entite.getMonde(), equalTo(monde));
	}

	@Test
	public void test_hashCode() throws NomDEntiteDejaUtiliseDansLeMondeException {
		Entite entite2 = new Entite("EntiteTmp", monde) {};
    assertThat(entite.hashCode(), equalTo(entite.hashCode()));
    assertThat(entite.hashCode(), not(equalTo(entite2.hashCode())));
	}

  @Test
  public void test_equals() throws NomDEntiteDejaUtiliseDansLeMondeException {
		Entite entite2 = new Entite("EntiteTmp", monde) {};
    assertThat(entite.equals(entite), is(true));
    assertThat(entite.equals(entite2), is(false));
  }
}
