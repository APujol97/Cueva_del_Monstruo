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
public class Conocimiento {
    
    private boolean visitada = false;
    
    private boolean ok = false;
    private boolean hedor = false;
    private boolean brisa = false;
    private boolean posibleMonstruo = false;
    private boolean posiblePrecipicio = false;
    
    public Conocimiento(){
        
    }
    
    public boolean isVisitada(){
        return visitada;
    }
    
    public boolean isOk(){
        return ok;
    }
    
    public boolean isHedor(){
        return hedor;
    }
    
    public boolean isBrisa(){
        return brisa;
    }
    
    public boolean isPosibleMonstruo(){
        return posibleMonstruo;
    }
    
    public boolean isPosiblePrecipicio(){
        return posiblePrecipicio;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setHedor(boolean hedor) {
        this.hedor = hedor;
    }

    public void setBrisa(boolean brisa) {
        this.brisa = brisa;
    }

    public void setPosibleMonstruo(boolean posibleMonstruo) {
        this.posibleMonstruo = posibleMonstruo;
    }

    public void setPosiblePrecipicio(boolean posiblePrecipicio) {
        this.posiblePrecipicio = posiblePrecipicio;
    }
}
