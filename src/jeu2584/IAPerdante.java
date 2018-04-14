/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2584;

/**
 *
 * @author Alexduqeb
 */
public class IAPerdante extends Grille{
    
    public IAPerdante(){
        super();
    }
    
    public void tourIA(){}{
        Grille fils1 = this;
        Grille fils2 = this;
        Grille fils3 = this;
        Grille fils4 = this;
        fils1.lanceurDeplacerCases(HAUT);
        fils2.lanceurDeplacerCases(BAS);
        fils3.lanceurDeplacerCases(GAUCHE);
        fils4.lanceurDeplacerCases(DROITE);
        int min=this.comparer(fils1.getScore(),fils2.getScore(),fils3.getScore(),fils4.getScore());
        boolean b2 = true;
        if(min==fils1.getScore()){
            b2=this.lanceurDeplacerCases(HAUT);
        }
        else if(min==fils2.getScore()){
            b2=this.lanceurDeplacerCases(BAS);
        }
        else if(min==fils3.getScore()){
            b2=this.lanceurDeplacerCases(GAUCHE);
        }
        else if(min==fils4.getScore()){
            b2=this.lanceurDeplacerCases(DROITE);
        }
        else if (b2) {
            boolean b = this.nouvelleCase();
                //if (!b) this.gameOver();
            }
        System.out.println(this);
        //if (this.getValeurMax()>=OBJECTIF) this.victory();
        //if (this.partieFinie()) this.gameOver();
    
    
    }
    public int comparer(int int1,int int2,int int3,int int4){
        int min=int1;
        if(int2<min){
            min=int2;
        }
        if(int3<min){
            min=int3;
        }
        if(int4<min){
            min=int4;
        }
        return min;
    }
}
