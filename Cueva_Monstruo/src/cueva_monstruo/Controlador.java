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
    private JButton monstruo;
    private JButton precipicio;
    private JButton tesoro;
    private JButton rapido;
    private JButton lento;
    
    public Controlador(){
        iniciar = new JButton("iniciar");
        iniciar.setBounds(20, 20, 40, 40);
        
        reset = new JButton("reset");
        reset.setBounds(20, 50, 40, 70);
        
        monstruo = new JButton("monstruo");
        monstruo.setBounds(20, 80, 40, 100);
        
        precipicio = new JButton("precipicio");
        precipicio.setBounds(20, 110, 40, 130);
        
        tesoro = new JButton("tesoro");
        tesoro.setBounds(20, 140, 40, 160);
        
        rapido = new JButton("rapido");
        rapido.setBounds(20, 170, 40, 190);

        lento = new JButton("lento");
        lento.setBounds(20, 200, 40, 220);
        
        initComponents();
    }
    
    public void initComponents(){
        
        iniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(Cueva_Monstruo.iniciar()){
                    iniciar.setText("pausar");
                } else {
                    iniciar.setText("iniciar");
                }
            }
        });
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciar.setText("iniciar");
                Cueva_Monstruo.reset();
            }
        });
        monstruo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.monstruo();
            }
        });
        precipicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.precipicio();
            }
        });
        tesoro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.tesoro();
            }
        });
        rapido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.acelerar_robot();
            }
        });
        lento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cueva_Monstruo.decelerar_robot();
            }
        });
        
        this.add(iniciar);
        this.add(reset);
        this.add(monstruo);
        this.add(precipicio);
        this.add(tesoro);
        this.add(rapido);
        this.add(lento);
    }
}
