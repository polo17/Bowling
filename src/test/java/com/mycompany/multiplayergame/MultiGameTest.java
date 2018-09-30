/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multiplayergame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedago
 */
public class MultiGameTest
{    
    private MultiGame multiGame;
    String[] empty;
    String[] partie;
    
    @Before
    public void setUp() 
    {
        multiGame=new MultiGame();
        empty=new String[0];
        partie=new String[] {"Jacke", "Jean", "Emilie", "Marc"};
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testAucunJoueur() throws Exception 
    {
        multiGame.startNewGame(empty);
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testPartieNonCommencé() throws Exception
    {
        multiGame.lancer(5);
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testScoreJoueurInnexistant() throws Exception
    {
        multiGame.startNewGame(partie);
        assertEquals(0, multiGame.scoreFor("Michel"));
    }
    
    @Test
    public void testCreationPartie() throws Exception
    {
        assertEquals("Prochain tir : joueur Jacke, tour n° 1, boule n° 01", multiGame.startNewGame(partie));
    }
    
    @Test
    public void testScore() throws Exception
    {
        multiGame.startNewGame(partie);
        assertEquals(0, multiGame.scoreFor("Jacke"));
    }
    
    @Test
    public void testRandomGame() throws Exception
    {
        multiGame.startNewGame(partie);
        multiGame.lancer(5);
        assertEquals(5, multiGame.scoreFor("Jacke"));
    }
    
}