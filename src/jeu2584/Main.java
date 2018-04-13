/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2584;

import java.util.Random;
import java.util.Scanner;


public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille[] joueurs = new Grille[2];
        
        Grille g = new Grille();
        boolean b = g.nouvelleCase();
        b = g.nouvelleCase();
        System.out.println("Grille joueur 1 :");
        System.out.println(g);
        
       
        Grille g2 = new Grille();
        boolean bg2 = g2.nouvelleCase();
        bg2 = g2.nouvelleCase();
        System.out.println("Grille joueur 2 :");
        System.out.println(g2);
        System.out.println("Voulez-vous que le j1 soit une intelligence artificielle aléatoire O/N");
        Scanner sc0=new Scanner(System.in);
        String ia=sc0.nextLine();
        joueurs[0] = g;
        joueurs[1] = g2;
        Scanner sc = new Scanner(System.in);
        /*System.out.println("X:");
        int x= sc.nextInt();
        System.out.println("Y:");
        int y= sc.nextInt();
        System.out.println("Valeur:");
        int valeur= sc.nextInt();
        Case c = new Case(x,y,valeur);
        g.getGrille().remove(c);
        System.out.println(g);*/
        
        while (!(g.grilleTerminee() && g2.grilleTerminee())) {
            for(int i = 0; i < 2; i++ ){
                if(!joueurs[i].grilleTerminee() && (ia.equals("N") || (i==1))){
                    System.out.println("JOUEUR " + (i+1));
                    System.out.println("Déplacer vers la Droite (d), Gauche (g), Haut (h), ou Bas (b) ?");
                    String s = sc.nextLine();
                    s.toLowerCase();
                    if (!(s.equals("d") || s.equals("droite")
                            || s.equals("g") || s.equals("gauche")
                            || s.equals("h") || s.equals("haut")
                            || s.equals("b") || s.equals("bas"))) {
                        System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
                    } else {
                        int direction;
                        if (s.equals("d") || s.equals("droite")) {
                            direction = DROITE;
                        } else if (s.equals("g") || s.equals("gauche")) {
                            direction = GAUCHE;
                        } else if (s.equals("h") || s.equals("haut")) {
                            direction = HAUT;
                        } else {
                            direction = BAS;
                        }
                        boolean b2 = joueurs[i].lanceurDeplacerCases(direction);
                        if (b2) {
                            b = joueurs[i].nouvelleCase();
                            if (!b) joueurs[i].gameOver();
                        }
                        System.out.println(joueurs[i]);
                        if (joueurs[i].getValeurMax()>=OBJECTIF) joueurs[i].victory();
                        if (joueurs[i].partieFinie()) joueurs[i].gameOver();
                    }
                }
                else if(ia.equals("O")&& i==0){
                    System.out.println("JOUEUR " + (i+1));
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
                    System.out.println(valeur);
                    boolean b2 = joueurs[i].lanceurDeplacerCases(direction);
                    if (b2) {
                        b = joueurs[i].nouvelleCase();
                            if (!b) joueurs[i].gameOver();
                        }
                        System.out.println(joueurs[i]);
                        if (joueurs[i].getValeurMax()>=OBJECTIF) joueurs[i].victory();
                        if (joueurs[i].partieFinie()) joueurs[i].gameOver();
                }
                else
                   System.out.println("Joeuur " + (i+1) + ", votre grille est terminee.");
            }
            
        }
        System.out.println("Score joeuur 1 " + joueurs[0].getValeurMax());
        System.out.println("Score joeuur 2 " + joueurs[1].getValeurMax());
        /*
        // Bout de code pour tester manuellement si une grille contient une case ou pas
        Scanner sc = new Scanner(System.in);
        System.out.println("x :");
        int x = sc.nextInt();
        System.out.println("y :");
        int y = sc.nextInt();
        Case c = new Case(x, y, 2);
        Case c2 = new Case(x, y, 4);
        System.out.println("test1=" + g.getGrille().contains(c));
        System.out.println("test2=" + g.getGrille().contains(c2));
         */
    }

}
