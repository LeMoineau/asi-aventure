package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;


import java.util.HashMap;
import java.lang.Throwable;

public class TestJoueurHumain {

  public Monde monde;
  public Piece piece;
  public JoueurHumain joueur;

  @Before
  public void init() throws Exception{
    monde = new Monde("un monde");
    piece = new Piece("une piece", monde);
    joueur = new JoueurHumain("Michel", monde, 10, 10, piece);
  }

  @Test
  public void test_executer()
  throws Throwable {

    PiedDeBiche pied = new PiedDeBiche(monde);
    piece.deposer(pied);
    joueur.setOrdre("prendre "+pied.getNom());
    joueur.executer();
    assertThat(joueur.possede(pied),is(true));
    System.out.println("salut");
  }
}
