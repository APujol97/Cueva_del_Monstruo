/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author pujol
 */
public class Tablero extends JPanel{
    
    private Robot robot;
    private static int elementos; //número de casillas
    private static final int dimension = 600; //dimensión de un lado del tablero
    private Casilla tablero[][];
    private static int lado;
    private boolean borrar;
    private boolean muro = true;
    
    public Tablero(int n){
        initComponents(n);
    }
    
    public void initComponents(int n){
        tablero = new Casilla[n][n];
        elementos = n;
        lado = dimension/elementos;
        borrar = false;
        
        int eje_y = 0;
        for(int i = 0; i < n; i++){
            int eje_x = 0;
            for(int j = 0; j < n; j++){
                Rectangle2D.Float r = new Rectangle2D.Float(eje_x, eje_y, 
                    lado,lado);
                tablero[i][j] = new Casilla(r);
                eje_x += lado;
            }
            eje_y += lado;
        }
    }
    
    public void Colorear(int x, int y) { //método para colorear una casilla
        x = x / lado; //calculamos la columna de la casilla clickeada
        y = y / lado; //calculamos la fila de la casilla clickeada
        if(muro){
            tablero[y][x].ColorearCasilla(borrar);
        }else{
            if(robot == null || !robot.getMovimiento()){
                ponerRobot(x,y);
            }
        }
    }
    
    public void ponerRobot(int x, int y) {
        if(!tablero[y][x].hayMuro()){
            if(!tablero[y][x].hayRobot()){
                if(robot != null){
                    tablero[robot.getY()][robot.getX()].pintaRobot();
                }
                robot = new Robot(y, x);
                tablero[y][x].pintaRobot();
            }else{
                robot = null;
                tablero[y][x].pintaRobot();
            }
            
        }
    }
    
    public void acelerar_robot(){
        if(robot != null){
            robot.acelerar();
        }
    }
    
    public void decelerar_robot(){
        if(robot != null){
            robot.decelerar();
        }
    }
    
    public float get_velocidad_robot(){
        float vel = 0;
        if(robot != null){
            vel =  robot.getVelocidad();
        }
        return vel;
    }
    
    public void resetTablero(){
        if(robot != null){
            robot.setMovimiento(false);
            robot.muere();
            robot = null;
        }
        initComponents(elementos);
        repaint();
    }
    
    public void informar(){
        int[] percepciones = new int[8];
        
        //percepcion para bordes
        if(robot.getX() == 0){
            percepciones[0] = 1;
            percepciones[6] = 1;
            percepciones[7] = 1;
        } else if(robot.getX() == (elementos-1)){
            percepciones[2] = 1;
            percepciones[3] = 1;
            percepciones[4] = 1;
        }
        
        if(robot.getY() == 0){
            percepciones[0] = 1;
            percepciones[1] = 1;
            percepciones[2] = 1;
        } else if(robot.getY() == (elementos-1)){
            percepciones[4] = 1;
            percepciones[5] = 1;
            percepciones[6] = 1;
        }
        
        int initX = robot.getX()-1;
        int initY = robot.getY()-1;
        int iter = 0;
        //percepcion muros
        for(int i = 0; i < 3; i++){
            if((initX >=0) && (initY+i >= 0) && (initY+i < elementos) && (tablero[initY+i][initX].hayMuro())){
                if(i == 0){
                    percepciones[0] = 1;
                } else if(i == 1){
                    percepciones[7] = 1;
                } else {
                    percepciones[6] = 1;
                }
            }
            
            if((initY+i >= 0) && (i != 1) && (initY+i < elementos) && (tablero[initY+i][initX+1].hayMuro())){
                if(i == 0){
                    percepciones[1] = 1;
                } else {
                    percepciones[5] = 1;
                }
            }
            
            if((initX+2 < elementos) && (initY+i < elementos) && (initY+i >= 0) && (tablero[initY+i][initX+2].hayMuro())){
                if(i == 0){
                    percepciones[2] = 1;
                } else if(i == 1){
                    percepciones[3] = 1;
                } else {
                    percepciones[4] = 1;
                }
            }
        }
        robot.percibir(percepciones);
    }
    
    public boolean iniciarRobot(){
        boolean iniciado = false;
        if(robot != null){
            robot.setMovimiento(!robot.getMovimiento());
            iniciado = robot.getMovimiento();
        }
        return iniciado;
    }
    
    public boolean hay_robot(){
        return robot != null;
    }
    
    public void moverRobot(int y, int x, Direccion dir){
        switch(dir){
            case NORTE:
                tablero[y+1][x].pintaRobot();
                break;
            case SUR:
                tablero[y-1][x].pintaRobot();
                break;
            case ESTE:
                tablero[y][x-1].pintaRobot();
                break;
            case OESTE:
                tablero[y][x+1].pintaRobot();
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        
        tablero[y][x].pintaRobot();
    }
    
    @Override
    public Dimension getPreferredSize() { //método que devuelve las dimensiones del tablero
        return new Dimension(dimension, dimension);
    }

    @Override
    public void paintComponent(Graphics g) { //método que pinta el tablero en la ventana
        for (int i = 0; i < elementos; i++) {
            for (int j = 0; j < elementos; j++) {
                tablero[i][j].paintComponent(g);
            }
        }
    }

    void setMuro(boolean b) {
        this.muro = b;
    }
    
}
