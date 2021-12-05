/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

/**
 *
 * @author pujol
 */
public class Robot extends Thread{
    
    private int x;
    private int y;
    
    static float velocidad = 500; // espera en milisegundos
    static int velocidad_max = 0;
    static int velocidad_min = 900;
    static int velocidad_salto = 100;
    private boolean moverse = false;
    
    private boolean[] percepciones = {false, false, false};
    private Direccion mov_previo= null;
    private boolean vivo;
    
    private Conocimiento[][] bc;
    
    public Robot(int y, int x, int elem){
        this.vivo = true;
        this.x = x;
        this.y = y;
        bc = new Conocimiento[elem][elem];
        for(int i = 0; i < elem; i++){
            for(int j = 0; j < elem; j++){
                bc[i][j] = new Conocimiento();
            }
        }
        this.start();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    @Override
    public void run(){
        while(vivo){
            System.out.println("espero");
            while(moverse){
                percibe();
                actualizaBC();
                avanzar();
                try {
                    Thread.sleep((long) velocidad);
                } catch (InterruptedException ex) {
                    //System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    public void acelerar(){
        if(velocidad > velocidad_max){
            velocidad -= velocidad_salto;
        }
    }
    
    public void decelerar(){
        if(velocidad < velocidad_min){
            velocidad += velocidad_salto;
        }
    }
    
    public void muere(){
        this.vivo = false;
    }
    
    public void setMovimiento(boolean moverse){
        this.moverse = moverse;
    }
    
    public boolean getMovimiento() {
        return this.moverse;
    }
    
    public float getVelocidad() {
        float vel = 0;
        if(moverse){
            vel = 100 - ((velocidad/1000) * 100);
        }
        return vel;
    }
    
    public void percibir(boolean[] percepciones){
        this.percepciones = percepciones;
        
    }
    
    public void percibe(){
        Cueva_Monstruo.percepciones();
    }
    
    public void actualizaBC(){
        bc[y][x].setVisitada(true);
        if(!percepciones[0] && !percepciones[1]){
            if(y > 0){
                bc[y-1][x].setOk(true);
            }
            if(y < bc.length -1){
                bc[y+1][x].setOk(true);
            }
            if(x > 0){
                bc[y][x-1].setOk(true);
            }
            if(x < bc.length -1){
                bc[y][x+1].setOk(true);
            }
        }
        
        if(percepciones[0]){
            bc[y][x].setHedor(true);
            if(y > 0 && !bc[y-1][x].isOk()){
                bc[y-1][x].setPosibleMonstruo(true);
            }
            if(y < bc.length-1 && !bc[y+1][x].isOk()){
                bc[y+1][x].setPosibleMonstruo(true);
            }
            if(x > 0 && !bc[y][x-1].isOk()){
                bc[y][x-1].setPosibleMonstruo(true);
            }
            if(x < bc.length-1 && !bc[y][x+1].isOk()){
                bc[y][x+1].setPosibleMonstruo(true);
            }
        }
        
        if(percepciones[1]){
            bc[y][x].setBrisa(true);
            if(y > 0 && !bc[y-1][x].isOk()){
                bc[y-1][x].setPosiblePrecipicio(true);
            }
            if(y < bc.length-1 && !bc[y+1][x].isOk()){
                bc[y+1][x].setPosiblePrecipicio(true);
            }
            if(x > 0 && !bc[y][x-1].isOk()){
                bc[y][x-1].setPosiblePrecipicio(true);
            }
            if(x < bc.length-1 && !bc[y][x+1].isOk()){
                bc[y][x+1].setPosiblePrecipicio(true);
            }
        }
    }
    
    public void mover(Direccion dir){
        switch(dir){
            case NORTE:
                this.y += 1;
                break;
            case SUR:
                this.y -= 1;
                break;
            case ESTE:
                this.x -= 1;
                break;
            case OESTE:
                this.x += 1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        Cueva_Monstruo.moverRobot(this.y, this.x, dir);
    }
    
    public void moverAtras(Direccion dir){
        Direccion atras = null;
        switch(dir){
            case NORTE:
                atras = Direccion.SUR;
                this.y += 1;
                break;
            case SUR:
                atras = Direccion.NORTE;
                this.y -= 1;
                break;
            case ESTE:
                atras = Direccion.OESTE;
                this.x -= 1;
                break;
            case OESTE:
                atras = Direccion.ESTE;
                this.x += 1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        Cueva_Monstruo.moverRobot(this.y, this.x, atras);
        mov_previo = atras;
    }
    
    public void avanzar(){
        System.out.println("Pos: " + this.y + "," + this.x);
        if(y < 0 && bc[y-1][x].isOk() && !bc[y-1][x].isVisitada()){
            mover(Direccion.NORTE);
        }
    }
}
