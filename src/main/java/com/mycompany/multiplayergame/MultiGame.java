package com.mycompany.multiplayergame;

import bowling.Frame;
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;

/**
 *
 * @author pedago
 */
public class MultiGame implements MultiPlayerGame
{   
    int nombreTotalJoueurs, numJoueur;
    boolean gameStarted;
    
    String[] nomJoueur;    
    HashMap <String,SinglePlayerGame> joueurs;
    
    public MultiGame()                              //Constructeur de notre class. Il contient toutes les variables a modifier pour le deroulement de la partie.
    {
        this.nombreTotalJoueurs=0;                  //Va servir pour l'exception aucun joueur.
        this.numJoueur=0;                           //Numero du joueur jouant actuellement
        this.gameStarted=false;                     //Booleen verifiant l'etat du jeu (terminé ou pas)
        this.nomJoueur= new String[0];              //Liste des noms des joueurs. Utilisé comme clé dans la table.
        this.joueurs= new HashMap<>();              //Table formé des noms des joueurs auquel on associe la partie solo.
    }
    
    /**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 * @param playerName un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1"
	 * @throws java.lang.Exception si le tableau est vide ou null
	 */
    @Override
    public String startNewGame(String[] playerName) throws Exception 
    {
        this.nombreTotalJoueurs=playerName.length;              //Affectation des variables, nombre de joueurs, partie commencée, liste des joueurs.
        this.gameStarted=true;
        this.nomJoueur=playerName;
        
        if (nombreTotalJoueurs==0)
        {
            throw new java.lang.Exception("Il n'y a aucun joueur.");        //Dans le cas ou la liste est vide, aucun joueur présent.
        }
        
        for (int i=0; i<playerName.length; i++)
        {
            this.joueurs.put(playerName[i], new SinglePlayerGame());        //Ajoute tout les joueurs dans la table de joueurs en y associant leur partie.
        }
        
        return "Prochain tir : joueur " + playerName[numJoueur] +                                               //Retourne la formule indiqué au dessus si tout se passe correctement.
                ", tour n° " + this.joueurs.get(playerName[numJoueur]).getCurrentFrame().getFrameNumber() + 
                ", boule n° " + this.joueurs.get(playerName[numJoueur]).getCurrentFrame().getBallsThrown()+1;
    }
    
    /**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le frame courant, pour la boule courante
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.Exception si la partie n'est pas démarrée, ou si elle est terminée.
	 */
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception 
    {
        if (!this.gameStarted)
        {
            throw new java.lang.Exception("La partie n'est pas démarrée ou elle est terminée.");        //On verifie l'etat de la partie. Si terminé ou non commencé on lance l'exception
        }
        
        this.joueurs.get(nomJoueur[numJoueur]).lancer(nombreDeQuillesAbattues);                         //On effectue le lancer pour le joueur courant.
        
        if (this.joueurs.get(nomJoueur[numJoueur]).getCurrentFrame().isFinished())
        {
            this.numJoueur++;                                                                           //Si le lancer etait le dernier du tour alors il passera automatiquement au tour suivant(Verifier la classe SinglePlayerGame).
        }                                                                                               //Pour ce tour ci c'est au joueur suivant de jouer.
        
        if (this.numJoueur==this.nomJoueur.length)                                                      //Plusieurs choses doivent etre verifié.
        {                                                                                               //Premierement si on arrive au dernier joueur de la liste.
            if (this.joueurs.get(this.nomJoueur[this.numJoueur-1]).getCurrentFrame()==null)             //Si le tour du dernier joueur est terminé.
            {
                this.gameStarted=false;                                                                 //Dans ce cas on arrete la partie. Retournant la formule demandé.
                return "Partie terminée";
            }
            this.numJoueur=0;                                                                           //Si ce n'est pas le dernier tour, dans ce cas on retourne au premier joueur.
        }
        
        return "Prochain tir : joueur " + this.nomJoueur[this.numJoueur] + 
                ", tour n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getFrameNumber() + 
                ", boule n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getBallsThrown()+1;      //Par defaut on affiche la formule du prochain joueur.
    }

    /**
	 * Donne le score pour le joueur playerName
	 * @param playerName le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws Exception si le playerName ne joue pas dans cette partie
	 */
    @Override
    public int scoreFor(String playerName) throws Exception
    {
        if (!this.joueurs.containsKey(playerName))                                          //Simple fonction, on verifie la presence du joueur, lançant l'exception si le nom ne figure pas dans la liste.
        {
            throw new java.lang.Exception("Ce joueur ne joue pas dans cette partie.");
        }
        return this.joueurs.get(playerName).score();                                        //Sinon on recupere par defaut le score dans la table enregistré dans l'objet associé.
    }  
}