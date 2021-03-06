/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2584;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;


public class Grille implements Parametres {

    private final HashSet<Case> grille;
    private int valeurMax = 0;
    private int score = 0;
    private boolean deplacement;
    private boolean grille_terminee = false;
    private boolean joue = false;

    public Grille() {
        this.grille = new HashSet<>();
    }
    
    public boolean joue() {
        return this.joue;
    }
    
    public void setJoue(boolean b) {
        this.joue = b;
    }

    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }
    
    public String toHTML() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "<html>";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "<br/>";
        }
        result += "</html>";
        return result;
    }
    
    public int[][] toTab() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
       return tableau;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }
    
    public int getScore() {
        return score;
    }

    public boolean partieFinie() {
        if (this.grille.size() < TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.voisinFibo(c.getVoisinDirect(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void terminerGrille() {
        this.grille_terminee = true;
    }
    
    public boolean grilleTerminee() {
        return this.grille_terminee;
    }

    public boolean lanceurDeplacerCases(int direction) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }
        return deplacement;
    }
    
    private int fib(int index){
        int result;
        if(index == 0 || index == -1){
            result = 1;
        }
        else {
            result = fib(index - 1) + fib(index-2);
        }
        return result;
    }

    private void fusion(Case c, Case c2) {
        this.score += c.getValeur() + c2.getValeur();
        c.setFibo_index(c.getFibo_index() + 1);
        c.setValeur(fib(c.getFibo_index()));
        
        
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        
        deplacement = true;
    }

    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if(!grilleTerminee()){
            if (extremites[rangee] != null) {
                if ((direction == HAUT && extremites[rangee].getY() != compteur)
                        || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                        || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                        || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                    this.grille.remove(extremites[rangee]);
                    switch (direction) {
                        case HAUT:
                            extremites[rangee].setY(compteur);
                            break;
                        case BAS:
                            extremites[rangee].setY(TAILLE - 1 - compteur);
                            break;
                        case GAUCHE:
                            extremites[rangee].setX(compteur);
                            break;
                        default:
                            extremites[rangee].setX(TAILLE - 1 - compteur);
                            break;
                    }
                    this.grille.add(extremites[rangee]);
                    deplacement = true;
                }
                Case voisin = extremites[rangee].getVoisinDirect(-direction);
                if (voisin != null) {
                    if (extremites[rangee].voisinFibo(voisin)) {
                        if(extremites[rangee].getValeur() < voisin.getValeur()){
                            extremites[rangee].setValeur(voisin.getValeur());
                            extremites[rangee].setFibo_index(voisin.getFibo_index());
                        }
                        this.fusion(extremites[rangee], voisin);
                        extremites[rangee] = voisin.getVoisinDirect(-direction);
                        this.grille.remove(voisin);
                        this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                    } else {
                        extremites[rangee] = voisin;
                        this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                    }
                }
            }
        }
    }

    /*
    * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (une pour chaque colonne)
    * Si direction = DROITE : retourne les 4 cases qui sont le plus à droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 4 cases qui sont le plus à gauche (une pour chaque ligne)
    * Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                        result[c.getX()] = c;
                    }
                    break;
                case BAS:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;
                    }
                    break;
                case GAUCHE:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
                default:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
            }
        }
        return result;
    }


    public boolean nouvelleCase() {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int[] possibiliteValeur = {1,1,1,2};
            int valeur = possibiliteValeur[ra.nextInt(4)];
            int fibo = valeur-1;
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur, fibo);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 1 && ajout.getValeur() == 2)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }


}
