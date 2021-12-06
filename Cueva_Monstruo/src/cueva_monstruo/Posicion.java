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
public class Posicion {
    
    private int x;
    private int y;
    
    public Posicion(int y, int x){
        this.y = y;
        this.x = x;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}
