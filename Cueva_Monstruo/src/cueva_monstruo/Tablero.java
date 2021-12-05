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
public class Tablero extends JPanel {

    private Robot robot;
    private static int elementos; //número de casillas
    private static final int dimension = 600; //dimensión de un lado del tablero
    private Casilla tablero[][];
    private static int lado;
    private CasillaTipo casilla = CasillaTipo.Monstruo;

    public Tablero(int n) {
        initComponents(n);
    }

    public void initComponents(int n) {
        tablero = new Casilla[n][n];
        elementos = n;
        lado = dimension / elementos;

        int eje_y = 0;
        for (int i = 0; i < n; i++) {
            int eje_x = 0;
            for (int j = 0; j < n; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(eje_x, eje_y,
                        lado, lado);
                tablero[i][j] = new Casilla(r);
                eje_x += lado;
            }
            eje_y += lado;
        }
    }

    public void iniciarMapa() {
        for (int i = 0; i < elementos; i++) {
            for (int j = 0; j < elementos; j++) {
                if (tablero[i][j].hayMonstruo()) {
                    if (i > 0) {
                        tablero[i - 1][j].setHedor(true);
                    }
                    if (i < elementos-1) {
                        tablero[i + 1][j].setHedor(true);
                    }
                    if (j > 0) {
                        tablero[i][j - 1].setHedor(true);
                    }
                    if (j < elementos-1) {
                        tablero[i][j + 1].setHedor(true);
                    }
                }
                
                if (tablero[i][j].hayPrecipicio()) {
                    if (i > 0) {
                        tablero[i - 1][j].setBrisa(true);
                    }
                    if (i < elementos-1) {
                        tablero[i + 1][j].setBrisa(true);
                    }
                    if (j > 0) {
                        tablero[i][j - 1].setBrisa(true);
                    }
                    if (j < elementos-1) {
                        tablero[i][j + 1].setBrisa(true);
                    }
                }
                
                if (tablero[i][j].hayTesoro()) {
                    tablero[i][j].setResplandor(true);
                }
                
            }
        }
    }

    public void Colorear(int x, int y) { //método para colorear una casilla
        x = x / lado; //calculamos la columna de la casilla clickeada
        y = y / lado; //calculamos la fila de la casilla clickeada
        tablero[y][x].ColorearCasilla(casilla);
    }

    public void acelerar_robot() {
        if (robot != null) {
            robot.acelerar();
        }
    }

    public void decelerar_robot() {
        if (robot != null) {
            robot.decelerar();
        }
    }

    public float get_velocidad_robot() {
        float vel = 0;
        if (robot != null) {
            vel = robot.getVelocidad();
        }
        return vel;
    }

    public void resetTablero() {
        if (robot != null) {
            robot.setMovimiento(false);
            robot.muere();
            robot = null;
        }
        initComponents(elementos);
        repaint();
    }

    public void informar() {
        Casilla c = tablero[robot.getY()][robot.getX()];
        robot.percibir(c.getObservaciones());
    }

    public boolean iniciarRobot() {
        iniciarMapa();
        boolean iniciado = false;
        robot = new Robot(elementos - 1, 0, elementos);
        tablero[elementos - 1][0].pintaRobot();
        if (robot != null) {
            robot.setMovimiento(!robot.getMovimiento());
            iniciado = robot.getMovimiento();
        }
        return iniciado;
    }

    public boolean hay_robot() {
        return robot != null;
    }

    public void moverRobot(int y, int x, Direccion dir) {
        switch (dir) {
            case NORTE:
                tablero[y + 1][x].pintaRobot();
                break;
            case SUR:
                tablero[y - 1][x].pintaRobot();
                break;
            case ESTE:
                tablero[y][x - 1].pintaRobot();
                break;
            case OESTE:
                tablero[y][x + 1].pintaRobot();
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

    public void setCasillaTipo(CasillaTipo c) {
        this.casilla = c;
    }

}
