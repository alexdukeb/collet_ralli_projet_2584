/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu2584;

import java.lang.reflect.Array;
import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static jeu2584.Parametres.BAS;
import static jeu2584.Parametres.DROITE;
import static jeu2584.Parametres.GAUCHE;
import static jeu2584.Parametres.HAUT;
import static jeu2584.Parametres.OBJECTIF;

/**
 *
 * @author Alexduqeb
 */
public class JeuFXMain extends Application {
    
    private Grille g, g2;
    IAPerdante iaperdante;
    boolean j1iaperdante = false;;
    IAAleatoire iaaleatoire;
    boolean j2iaaleatoire = false;;
    Label[] panelsJ2, panelsJ1;
    Label resultat, scoreJ1, scoreJ2;
    
    @Override
    public void start(Stage primaryStage) {
         
        Button btnJ1 = new Button();
        btnJ1.setText("Joueur 1'");
        btnJ1.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        
        
        
        
        
        
        
        GridPane gridpaneJ1 = new GridPane();
        //gridpaneJ1.setPadding(new Insets(5));
        gridpaneJ1.setStyle( "-fx-background-color:blue;" );
        gridpaneJ1.setHgap(50);
        gridpaneJ1.setVgap(5);
        
        panelsJ1 = new Label[16];        
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                Label label =  new Label("1");
                int index = x + 4*y;
                label.setFont(new Font(45.0));
                panelsJ1[index] = label;
                gridpaneJ1.add(panelsJ1[index], x, y);
            }
        }
                
        panelsJ2 = new Label[16];
        GridPane gridpaneJ2 = new GridPane();
        gridpaneJ2.setStyle( "-fx-background-color:yellow;" );
        
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                Label label =  new Label("1");
                int index = x + 4*y;
                label.setFont(new Font(45.0));
                panelsJ2[index] = label;
                gridpaneJ2.add(panelsJ2[index], x, y);
            }
        }
        Button btn, btn2, btn3, btn4;
        btn = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();
        btn3.setVisible(false);
        btn4.setVisible(false);
        
        btn.setText("Joueur 1");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("test");
                g = new Grille();
                boolean bg = g.nouvelleCase();
                bg = g.nouvelleCase();
                btn.setVisible(false);
                btn2.setVisible(false);
                btn3.setVisible(true);
                btn4.setVisible(true);
            }
        });
        
        
        btn2.setText("IA Perdante");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                iaperdante = new IAPerdante();
                j1iaperdante = true;
                boolean bg = iaperdante.nouvelleCase();
                bg = iaperdante.nouvelleCase();
                btn.setVisible(false);
                btn2.setVisible(false);
                btn3.setVisible(true);
                btn4.setVisible(true);
            }
        });
        
        
        btn3.setText("Joueur 2");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                g2 = new Grille();
                boolean bg = g2.nouvelleCase();
                bg = g2.nouvelleCase();
                btn3.setVisible(false);
                btn4.setVisible(false);
                rafraichir();
            }
        });
        
        btn4.setText("IA Aleatoire");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                iaaleatoire = new IAAleatoire();
                j2iaaleatoire = true;
                boolean bg = iaaleatoire.nouvelleCase();
                bg = iaaleatoire.nouvelleCase();
                btn3.setVisible(false);
                btn4.setVisible(false);
                rafraichir();
            }
        });
     
        GridPane choix = new GridPane();
        choix.add(btn, 0, 0);
        choix.add(btn2, 1, 0);
        choix.add(btn3, 2, 0);
        choix.add(btn4, 3, 0);
        
        gridpaneJ2.setHgap(50);
        gridpaneJ2.setVgap(5);
        
        GridPane grilles = new GridPane();
        grilles.add(gridpaneJ1, 0, 0);
        grilles.add(gridpaneJ2, 1, 0);
        
        GridPane root = new GridPane();
        root.setHgap(10); 
        root.setVgap(10);
        root.add(grilles, 0, 0);
        root.add(choix, 0, 4);
        resultat = new Label();
        root.add(resultat, 0, 3);
        
        scoreJ1 = new Label("0");
        
        root.add(scoreJ1, 0, 1);
        
        scoreJ2 = new Label("0");
        
        root.add(scoreJ2, 0, 2);
        
        
        

        
        Scene scene = new Scene(root, 800, 500);
        
        
        //key pressed
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                if(!g.grilleTerminee() && g.joue()){
                    boolean b2 = g.lanceurDeplacerCases(HAUT);
                    if (b2) {
                        boolean b = g.nouvelleCase();
                        if (!b) {
                            this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                        }
                    }

                    this.rafraichir();
                    if (g.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ1("Gagné Joueur 1! Score = "+g.getValeurMax());
                    }
                    if (g.partieFinie()) this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                }
            }
            
            if (e.getCode() == KeyCode.Q) {
                if(!g.grilleTerminee() && g.joue()){
                    boolean b2 = g.lanceurDeplacerCases(GAUCHE);
                    if (b2) {
                        boolean b = g.nouvelleCase();
                        if (!b) {
                            this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                        }
                    }

                    this.rafraichir();
                    if (g.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ1("Gagné Joueur 1! Score = "+g.getValeurMax());
                    }
                    if (g.partieFinie()) this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                }
            }
            
            if (e.getCode() == KeyCode.S) {
                if(!g.grilleTerminee() && g.joue()){
                    boolean b2 = g.lanceurDeplacerCases(BAS);
                    if (b2) {
                        boolean b = g.nouvelleCase();
                        if (!b) {
                            this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                        }
                    }

                    this.rafraichir();
                    if (g.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ1("Gagné Joueur 1! Score = "+g.getValeurMax());
                    }
                    if (g.partieFinie()) this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                }
            }
            
            if (e.getCode() == KeyCode.D) {
                if(!g.grilleTerminee() && g.joue()){
                    boolean b2 = g.lanceurDeplacerCases(DROITE);
                    if (b2) {
                        boolean b = g.nouvelleCase();
                        if (!b) {
                            this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                        }
                    }

                    this.rafraichir();
                    if (g.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ1("Gagné Joueur 1! Score = "+g.getValeurMax());
                    }
                    if (g.partieFinie()) this.finPartieJ1("Perdu Joueur 1! Score = "+g.getValeurMax());
                }
            }
            
            if (e.getCode() == KeyCode.UP) {
                if(!g2.grilleTerminee() && g2.joue()){
                    boolean b2 = g2.lanceurDeplacerCases(HAUT);
                    if (b2) {
                        boolean b = g2.nouvelleCase();
                        if (!b) {
                            this.finPartieJ2("Perdu Joueur 2! Score = "+g2.getValeurMax());
                        }
                    }
                    this.rafraichir();
                    if (g2.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ2("Gagné Joueur 2 ! Score = "+g2.getValeurMax());
                    }
                    if (g2.partieFinie()) this.finPartieJ2("Perdu Joueur 2 ! Score = "+g2.getValeurMax());
                }
            }
            if (e.getCode() == KeyCode.LEFT) {
                if(!g2.grilleTerminee() && g2.joue()){
                    boolean b2 = g2.lanceurDeplacerCases(GAUCHE);
                    if (b2) {
                        boolean b = g2.nouvelleCase();
                        if (!b) {
                            this.finPartieJ2("Perdu Joueur 2! Score = "+g2.getValeurMax());
                        }
                    }
                    this.rafraichir();
                    if (g2.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ2("Gagné Joueur 2 ! Score = "+g2.getValeurMax());
                    }
                    if (g2.partieFinie()) this.finPartieJ2("Perdu Joueur 2 ! Score = "+g2.getValeurMax());
                }
            }
            if (e.getCode() == KeyCode.DOWN) {
                if(!g2.grilleTerminee() && g2.joue()){
                    boolean b2 = g2.lanceurDeplacerCases(BAS);
                    if (b2) {
                        boolean b = g2.nouvelleCase();
                        if (!b) {
                            this.finPartieJ2("Perdu Joueur 2! Score = "+g2.getValeurMax());
                        }
                    }
                    this.rafraichir();
                    if (g2.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ2("Gagné Joueur 2 ! Score = "+g2.getValeurMax());
                    }
                    if (g2.partieFinie()) this.finPartieJ2("Perdu Joueur 2 ! Score = "+g2.getValeurMax());
                }
            }
            if (e.getCode() == KeyCode.RIGHT) {
                if(!g2.grilleTerminee() && g2.joue()){
                    boolean b2 = g2.lanceurDeplacerCases(DROITE);
                    if (b2) {
                        boolean b = g2.nouvelleCase();
                        if (!b) {
                            this.finPartieJ2("Perdu Joueur 2! Score = "+g2.getValeurMax());
                        }
                    }
                    this.rafraichir();
                    if (g2.getValeurMax() >= OBJECTIF) {
                        this.finPartieJ2("Gagné Joueur 2 ! Score = "+g2.getValeurMax());
                    }
                    if (g2.partieFinie()) this.finPartieJ2("Perdu Joueur 2 ! Score = "+g2.getValeurMax());
                }
            }
        });
      
        
        primaryStage.setTitle("2584");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private boolean j1IsIAperdante(){
        return this.j1iaperdante;
    }
    
    private boolean j2IsIAaleatoire(){
        return this.j2iaaleatoire;
    }
            
    
    private void rafraichir() {
        System.out.print(g);
        System.out.print(g2);
        int[][] tabG1;
        int[][] tabG2;
        if(j1iaperdante){
            tabG1 = iaperdante.toTab();
            scoreJ1.setText("Score J1 : " + Integer.toString(iaperdante.getScore()));
        } else{
            tabG1 = g.toTab();
            scoreJ1.setText("Score J1 : " + Integer.toString(g.getScore()));
        }
        
        if(j2iaaleatoire){
            tabG2 = iaaleatoire.toTab();
            scoreJ2.setText("Score J2 : " + Integer.toString(iaaleatoire.getScore()));
        } else{
            tabG2 = g2.toTab();
            scoreJ2.setText("Score J2 : " + Integer.toString(g2.getScore()));
        }
        
        for (int y = 0; y < tabG1.length; y++) {
            for (int x = 0; x < tabG1.length; x++) {
               panelsJ1[x + y*4].setText(Integer.toString(tabG1[y][x]));
            }    
        }
        
        
        for (int y = 0; y < tabG2.length; y++) {
            for (int x = 0; x < tabG2.length; x++) {
               panelsJ2[x + y*4].setText(Integer.toString(tabG2[y][x]));
            }    
        }
        
               
        
    }
    
    private void finPartieJ1(String message) {
        resultat.setText(resultat.getText() + "\n" + message + "\n");
        g.terminerGrille();
    }
    
    private void finPartieJ2(String message) {
        resultat.setText(resultat.getText() + "\n" + message + "\n");
        g2.terminerGrille();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        
        launch(args);
        
        
        
        
    }
    
}
