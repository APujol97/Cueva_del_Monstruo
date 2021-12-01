/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pujol
 */
public class Controlador extends JPanel {
    
    private JButton iniciar;
    private JButton reset;
    private JButton rapido;
    private JButton lento;
    private JButton muro;
    private JButton robot;
    private JLabel vel;
    private int velocidad = 0;
    
    public Controlador(){
        iniciar = new JButton("iniciar");
        iniciar.setBounds(20, 20, 40, 40);
        
        reset = new JButton("reset");
        reset.setBounds(20, 50, 40, 70);
        
        rapido = new JButton("rapido");
        rapido.setBounds(20, 80, 40, 100);
        
        lento = new JButton("lento");
        lento.setBounds(20, 110, 40, 130);
        
        muro = new JButton("muro");
        muro.setBounds(20, 140, 40, 160);
        
        robot = new JButton("robot");
        robot.setBounds(20, 170, 40, 190);
        
        initComponents();
    }
    
    public void initComponents(){
        
        iniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(Cueva_Monstruo.iniciar()){
                    iniciar.setText("pausar");
                    vel.setText("Velocidad: " + Cueva_Monstruo.get_velocidad_robot() + "%");
                } else {
                    iniciar.setText("iniciar");
                    vel.setText("Velocidad: " + Cueva_Monstruo.get_velocidad_robot() + "%");
                }
            }
        });
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciar.setText("iniciar");
                Cueva_Monstruo.reset();
                vel.setText("Velocidad: 0%");
            }
        });
        rapido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.acelerar_robot();
                vel.setText("Velocidad: " + Cueva_Monstruo.get_velocidad_robot() + "%");
            }
        });
        lento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.decelerar_robot();
                vel.setText("Velocidad: " + Cueva_Monstruo.get_velocidad_robot() + "%");
            }
        });
        muro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.muro();
            }
        });
        robot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.robot();
            }
        });
        
        vel = new JLabel("Velocidad: " + Cueva_Monstruo.get_velocidad_robot() + "%");
        
        this.add(iniciar);
        this.add(reset);
        this.add(rapido);
        this.add(lento);
        this.add(muro);
        this.add(robot);
        this.add(vel);
    }
}
