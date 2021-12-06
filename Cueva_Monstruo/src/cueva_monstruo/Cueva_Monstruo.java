/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pujol
 */
public class Cueva_Monstruo extends JFrame implements MouseListener{

    /**
     * @param args the command line arguments
     */
    
    static Tablero tablero; //instancia de la clase tablero
    
    static int n;
    
    public Cueva_Monstruo(){
        // TODO code application logic here
        this.setTitle("Monstruo");
        initComponents();
        this.setDefaultCloseOperation(Cueva_Monstruo.EXIT_ON_CLOSE);
    }
    
    public void initComponents() { //se inicializan los componentes de la ventana
        //se inicializa el tablero
        n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de casillas"));
        tablero = new Tablero(n);
        tablero.addMouseListener(this);
        tablero.setLocation(50, 50);
        
        //se especifican las caracterísitcas de la ventana
        this.getContentPane().add(new Controlador(), BorderLayout.NORTH);
        this.getContentPane().add(tablero, BorderLayout.CENTER);
        this.setResizable(false);
        this.setSize(new Dimension(650, 650));
        this.pack();
        this.setLocationRelativeTo(null);

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Cueva_Monstruo().setVisible(true);
    }
    
    public static boolean iniciar(){
        return tablero.iniciarRobot();
    }
    
    public static void percepciones(){
        if(tablero.hay_robot()){
            tablero.informar();
        }
        tablero.repaint();
    }
    
    public static void moverRobot(int y, int x, Direccion dir){
        tablero.moverRobot(y,x,dir);
    }
    
    public static void moverRobot(int yNuevo, int xNuevo, int y, int x){
        tablero.moverRobot(yNuevo,xNuevo,y,x);
    }
    
    public static void acelerar_robot(){
        tablero.acelerar_robot();
    }
    
    public static void decelerar_robot(){
        tablero.decelerar_robot();
    }
    
    public static float get_velocidad_robot(){
        return tablero.get_velocidad_robot();
    }
    
    public static void reset() {
        tablero.resetTablero();
    }
    
    public static void monstruo(){
        tablero.setCasillaTipo(CasillaTipo.Monstruo);
    }
    
    public static void precipicio(){
        tablero.setCasillaTipo(CasillaTipo.Precipicio);
    }
    
    public static void tesoro(){
        tablero.setCasillaTipo(CasillaTipo.Tesoro);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            tablero.Colorear(me.getX(), me.getY()); //se colorea la casilla clickeada
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
