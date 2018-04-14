/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2584;

import java.util.HashSet;
import java.util.Random;
import static jeu2584.Parametres.BAS;
import static jeu2584.Parametres.DROITE;
import static jeu2584.Parametres.GAUCHE;
import static jeu2584.Parametres.HAUT;
import static jeu2584.Parametres.OBJECTIF;


/**
 *
 * @author Alexduqeb
 */
public class IAAleatoire extends Grille{
    
    public IAAleatoire(){
        super();
    }
    
    public boolean tourIA(){
        boolean deplacement = true;
        Random ra = new Random();
        int valeur = ra.nextInt(4);
        int direction = 0;
        
        if(valeur==0){
            direction= DROITE;
        }
        if(valeur==1){
            direction= GAUCHE;
        }
        if(valeur==2){
            direction= BAS;
        }
        if (valeur==3){
            direction= HAUT;
        }
        else{
        }
        
        while(!this.lanceurDeplacerCases(direction)){
            valeur = ra.nextInt(4);
            if(valeur==0){
                direction= DROITE;
            }
            if(valeur==1){
                direction= GAUCHE;
            }
            if(valeur==2){
                direction= BAS;
            }
            if (valeur==3){
                direction= HAUT;
            }
            else{
            }
        }
        
        boolean b = this.nouvelleCase();
        if (!b) deplacement = false;
        
        
        return deplacement;
    
    }
    
    
}
