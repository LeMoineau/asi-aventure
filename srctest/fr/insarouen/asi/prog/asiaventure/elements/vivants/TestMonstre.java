package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Monstre;

import java.util.HashMap;
import java.lang.Throwable;

public class TestMonstre {

  public Monde monde;
  public Piece piece;

  class ObjetTest extends Objet {

    boolean deplacable;

    public ObjetTest(String nom, Monde monde, boolean deplacable)
    throws NomDEntiteDejaUtiliseDansLeMondeException {
      super(nom, monde);
      this.deplacable = deplacable;
    }

    public boolean estDeplacable() {
      return this.deplacable;
    }

  }

  @Before
  public void init() throws Exception{
    monde = new Monde("un monde");
    piece = new Piece("une piece", monde);
  }

  @Test
  public void test_executer()
  throws Throwable {
    Piece piece2 = new Piece("une autre piece", monde);
    Porte porte = new Porte("une porte", monde, piece, piece2);
    Objet obj_A = new ObjetTest("un objet A", monde, true);
    Objet obj_B = new ObjetTest("un objet B", monde, true);
    Objet obj_C = new ObjetTest("un objet C", monde, true);
    piece.deposer(obj_A);
    piece2.deposer(obj_B);
    piece2.deposer(obj_C);
    Monstre monstre = new Monstre("un monstre", monde, 10, 1, piece);
    HashMap<String,Objet> contient = new HashMap<String,Objet>();
    assertThat(monstre.getObjets(), equalTo(contient));
    monstre.executer();
    contient.put(obj_B.getNom(), obj_B);
    contient.put(obj_C.getNom(), obj_C);
    assertThat(monstre.getPointVie(), equalTo(9));
    assertThat(monstre.getObjets(), equalTo(contient));
    monstre.executer();
    contient.remove(obj_B.getNom());
    contient.remove(obj_C.getNom());
    contient.put(obj_A.getNom(), obj_A);
    assertThat(monstre.getPointVie(), equalTo(8));
    assertThat(monstre.getObjets(), equalTo(contient));
  }
}
