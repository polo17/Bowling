package com.mycompany.multiplayergame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MultiGameTest {    
    String[] vide;
    String[] partie;
    private MultiGame multiJeu1;
    
    @Before
    public void setUp() {
        multiJeu1=new MultiGame();
        vide=new String[0];
        partie=new String[] {"Alice", "Marc", "Phillipe", "Amelia"};
    }
    
    @Test (expected=java.lang.Exception.class)
    public void partiePasCommenceeTest() throws Exception {
        multiJeu1.lancer(7);
    }
    
    @Test
    public void scoreTest() throws Exception {
        multiJeu1.startNewGame(partie);
        assertEquals(0, multiJeu1.scoreFor("Alice"));
    }
    
    @Test
    public void partieCreeeTest() throws Exception {
        assertEquals("Prochain tir : joueur Alice, tour n° 1, boule n° 01", multiJeu1.startNewGame(partie));
    }
    
    @Test (expected=java.lang.Exception.class)
    public void pasDeJoueurTest() throws Exception {
        multiJeu1.startNewGame(vide);
    }
        
    @Test (expected=java.lang.Exception.class)
    public void nomJoueurIncorrectScoreTest() throws Exception {
        multiJeu1.startNewGame(partie);
        assertEquals(0, multiJeu1.scoreFor("Lily"));
    }
    
}