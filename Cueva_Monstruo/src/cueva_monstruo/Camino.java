/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

/**
 *
 * @author Joaquin
 */
public class Camino {
    
    private Posicion casilla;
    private Camino casillaAnterior;
    
    public Camino(int y, int x){
        this.casilla = new Posicion(y,x);
    }
    
    public void addCasillaAnterior(Camino camino){
        this.casillaAnterior = camino;
    }
    
    public int getX(){
        return casilla.getX();
    }
    
    public int getY(){
        return casilla.getY();
    }
    
    public Camino getCasillaAnterior(){
        return casillaAnterior;
    }
}
