package fr.insarouen.asi.prog.asiaventure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.Simulateur;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Monstre;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.JoueurHumain;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class TestSimulateur {

  /*
  Piece "BureauDesNicolas"
  Piece "BureauDuDirecteur"
  Piece "BureauDeRakoto"
  Piece "BureauDeGasso"
  Piece "Couloir"
  PorteSerrure "Porte1" "Couloir" "BureauDesNicolas"
  PorteSerrure "Porte2" "Couloir" "BureauDuDirecteur"
  Porte "Porte3" "Couloir" "BureauDeRakoto"
  Porte "Porte4" "Couloir" "BureauDeGasso"
  Porte "Trappe" "BureauDeRakoto" "BureauDuDirecteur"
  Porte "PassageSecret" "BureauDesNicolas" "BureauDeGasso"
  Clef "Porte1" "BureauDeRakoto"
  Clef "Porte2" "BureauDeGasso"
  JoueurHumain "Etudiant1" 10 10 "Couloir"
  ConditionDeFinVivantDansPiece SUCCES "Etudiant1" "BureauDesNicolas"
  ConditionDeFinVivantDansPiece ECHEC "Etudiant1" "BureauDuDirecteur"
  */

  public Monde monde;
  public Simulateur simu;

	@Before
	public void init() throws Exception {
    Reader reader = new BufferedReader(new FileReader("tmp/testSimulateur.txt"));
    this.simu = new Simulateur(reader);
    this.monde = this.simu.getMonde();
  }

	@Test
	public void test_presentDansMonde()
  throws Exception {
    assertThat(this.monde.getNom(), equalTo("LeMondeimpitoyabled’ASI"));
    assertThat((this.monde.getEntite("BureauDesNicolas").equals(null)), is(false));
    assertThat((this.monde.getEntite("BureauDuDirecteur").equals(null)), is(false));
    assertThat((this.monde.getEntite("BureauDeRakoto").equals(null)), is(false));
    assertThat((this.monde.getEntite("BureauDeGasso").equals(null)), is(false));
    assertThat((this.monde.getEntite("Couloir").equals(null)), is(false));
    assertThat((this.monde.getEntite("Porte1").equals(null)), is(false));
    assertThat((this.monde.getEntite("Porte2").equals(null)), is(false));
    assertThat((this.monde.getEntite("Porte3").equals(null)), is(false));
    assertThat((this.monde.getEntite("Porte4").equals(null)), is(false));
    assertThat((this.monde.getEntite("Trappe").equals(null)), is(false));
    assertThat((this.monde.getEntite("PassageSecret").equals(null)), is(false));
    assertThat((this.monde.getEntite("Etudiant1").equals(null)), is(false));
  }



  @Test
  public void test_portesSerrure()
  throws Exception{
    Porte porte1 = (Porte)this.monde.getEntite("Porte1");
    Porte porte2 = (Porte)this.monde.getEntite("Porte2");
    Porte porte3 = (Porte)this.monde.getEntite("Porte3");
    Porte porte4 = (Porte)this.monde.getEntite("Porte4");
    Porte passageSecret = (Porte)this.monde.getEntite("PassageSecret");
    Porte trappe = (Porte)this.monde.getEntite("Trappe");
    assertThat(porte1.getSerrure(),is(notNullValue()));
    assertThat(porte2.getSerrure(),is(notNullValue()));
    assertThat(porte3.getSerrure(),is(nullValue()));
    assertThat(porte4.getSerrure(),is(nullValue()));
    assertThat(trappe.getSerrure(),is(nullValue()));
    assertThat(passageSecret.getSerrure(),is(nullValue()));



  }

  @Test
  public void test_portePiece()
  throws Exception{
    Porte porte1 = (Porte)this.monde.getEntite("Porte1");
    Porte porte2 = (Porte)this.monde.getEntite("Porte2");
    Porte porte3 = (Porte)this.monde.getEntite("Porte3");
    Porte porte4 = (Porte)this.monde.getEntite("Porte4");
    Piece couloir = (Piece)this.monde.getEntite("Couloir");
    Piece bureauDeGasso = (Piece)this.monde.getEntite("BureauDeGasso");
    Piece bureauDeRakoto = (Piece)this.monde.getEntite("BureauDeRakoto");
    Piece bureauDesNicolas = (Piece)this.monde.getEntite("BureauDesNicolas");
    Piece bureauDuDirecteur = (Piece)this.monde.getEntite("BureauDuDirecteur");
    assertThat(porte1.getPieceAutreCote(couloir),equalTo(bureauDesNicolas));
    assertThat(porte2.getPieceAutreCote(couloir),equalTo(bureauDuDirecteur));
    assertThat(porte3.getPieceAutreCote(couloir),equalTo(bureauDeRakoto));
    assertThat(porte4.getPieceAutreCote(couloir),equalTo(bureauDeGasso));

  }

}
