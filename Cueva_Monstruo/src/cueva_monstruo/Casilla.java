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
    
    private CasillaTipo tipo;
    private Color color; // cambiar color por imagen
    private final Rectangle2D.Float rectang;
    private boolean[] observaciones = {false, false, false}; //[Hedor, Brisa, Resplandor]
    
    
    public Casilla(Rectangle2D.Float rectang){
        tipo = CasillaTipo.Nada;
        this.rectang = rectang;
        this.color = Color.WHITE;
    }
    
    public void ColorearCasilla(CasillaTipo casillaTipo) { //método para colorear la casilla
        if ((!CasillaTipo.Nada.equals(tipo) && casillaTipo.equals(tipo)) || (casillaTipo.equals(tipo))) {
            color = Color.WHITE;
            tipo = CasillaTipo.Nada;
        } else {
            //switch pintar precipicio, monstruo, tesoro
            switch(casillaTipo){
                case Monstruo:
                    color = Color.RED;
                    tipo = CasillaTipo.Monstruo;
                    break;
                case Precipicio:
                    color = Color.BLACK;
                    tipo = CasillaTipo.Precipicio;
                    break;
                case Tesoro:
                    color = Color.YELLOW;
                    tipo = CasillaTipo.Tesoro;
                    break;
                default:
                    break;
            }
        }
    }
    
    public void pintaRobot(){
        if (null == tipo) {
            color = Color.WHITE;
            tipo = CasillaTipo.Nada;
        } else switch (tipo) {
            case Nada:
                //si el color de la casilla es blanco, se pinta de azul
                color = Color.BLUE;
                tipo = CasillaTipo.Robot;
                break;
            case Tesoro:
                color = Color.BLUE;
                tipo = CasillaTipo.Robot;
                break;
            default:
                color = Color.WHITE;
                tipo = CasillaTipo.Nada;
                break;
        }
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); //asignamos el color actual de la casilla
        g2d.fill(rectang); //pintamos la casilla del color que tiene actualmente
        g2d.setColor(Color.BLACK); //asignamos el color negro
        g2d.draw(rectang); //dibujamos los contornos de la casilla de negro
    }
    
    public void setHedor(boolean h){
        this.observaciones[0] = h;
    }
    
    public void setBrisa(boolean b){
        this.observaciones[1] = b;
    }
    
    public void setResplandor(boolean r){
        this.observaciones[2] = r;
    }
    
    public boolean[] getObservaciones(){
        return this.observaciones;
    }
    
    public boolean hayMonstruo(){
        return CasillaTipo.Monstruo.equals(tipo);
    }
    
    public boolean hayPrecipicio(){
        return CasillaTipo.Precipicio.equals(tipo);
    }
    
    public boolean hayTesoro(){
        return CasillaTipo.Tesoro.equals(tipo);
    }
    
    public boolean hayRobot() {
        return true;
    }
    
}
