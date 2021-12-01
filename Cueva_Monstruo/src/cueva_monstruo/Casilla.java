/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author pujol
 */
public class Casilla {
    
    private boolean muro;
    private boolean robot;
    private Color color; // cambiar color por imagen
    private final Rectangle2D.Float rectang;
    
    
    public Casilla(Rectangle2D.Float rectang){
        muro = false;
        robot = false;
        this.rectang = rectang;
        this.color = Color.WHITE;
    }
    
    public void ColorearCasilla(boolean borrar) { //método para colorear la casilla
        if(!robot){
            if (borrar) {
                color = Color.WHITE; //si borrar es "true", se pinta de blanco
                muro = false;
            } else {
                if (color == Color.WHITE) { //si el color de la casilla es blanco, se pinta de negro
                    color = Color.BLACK;
                    muro = true;
                } else {
                    color = Color.WHITE; // si el color de la casilla es negro, se pinta de blanco
                    muro = false;
                }
            }
        }
    }
    
    public void pintaRobot(){
        if (color == Color.WHITE) { //si el color de la casilla es blanco, se pinta de azul
            color = Color.BLUE;
            robot = true;
        } else {
            color = Color.WHITE;
            robot = false;
        }
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); //asignamos el color actual de la casilla
        g2d.fill(rectang); //pintamos la casilla del color que tiene actualmente
        g2d.setColor(Color.BLACK); //asignamos el color negro
        g2d.draw(rectang); //dibujamos los contornos de la casilla de negro
    }
    
    public boolean hayMuro() {
        return muro;
    }
    
    public boolean hayRobot() {
        return robot;
    }
    
}
