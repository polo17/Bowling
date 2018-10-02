package com.mycompany.multiplayergame;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;

public class MultiGame implements MultiPlayerGame
{  
    String[] nomJoueur;
    boolean partieCommencee;
    int totalJoueurs;
    int numJoueur;     
    HashMap <String,SinglePlayerGame> joueurs;
    
    public MultiGame() {
        this.partieCommencee=false;
        this.numJoueur=0;
        this.totalJoueurs=0;                                                            
        this.nomJoueur= new String[0];             
        this.joueurs= new HashMap<>();             
    }

    @Override
    public String startNewGame(String[] nomJoueur) throws Exception {
        this.totalJoueurs=nomJoueur.length;   
        this.partieCommencee=true;
        this.nomJoueur=nomJoueur;      
        if (totalJoueurs==0) {
            throw new java.lang.Exception("Il n'y a aucun joueur.");
        }       
        for (int i=0; i<nomJoueur.length; i++) {
            this.joueurs.put(nomJoueur[i], new SinglePlayerGame()); 
        }       
        return "Prochain tir : joueur " + nomJoueur[numJoueur] + ", tour n° " + this.joueurs.get(nomJoueur[numJoueur]).getCurrentFrame().getFrameNumber() + 
        ", boule n° " + this.joueurs.get(nomJoueur[numJoueur]).getCurrentFrame().getBallsThrown()+1;
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (!this.partieCommencee){
            throw new java.lang.Exception("La partie non démarrée ou terminée.");
        } 
        this.joueurs.get(nomJoueur[numJoueur]).lancer(nombreDeQuillesAbattues);   
        if (this.joueurs.get(nomJoueur[numJoueur]).getCurrentFrame().isFinished()){
            this.numJoueur++;
        }                                                                                        
        if (this.numJoueur==this.nomJoueur.length){                                       
            if (this.joueurs.get(this.nomJoueur[this.numJoueur-1]).getCurrentFrame()==null){
                this.partieCommencee=false;
                return "Partie terminée";
            }
            this.numJoueur=0;
        } 
        return "Prochain tir : joueur " + this.nomJoueur[this.numJoueur] + ", tour n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getFrameNumber() + 
        ", boule n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getBallsThrown()+1;
    }

    @Override
    public int scoreFor(String nomJoueur) throws Exception {
        if (!this.joueurs.containsKey(nomJoueur)){
            throw new java.lang.Exception("Le nom de ce joueur est inconnu.");
        }
        return this.joueurs.get(nomJoueur).score();
    }  
}