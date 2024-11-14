package presentation;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ColonyGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=31;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoColony photo;
    private Colony colony;

    private ColonyGUI() {
        colony=new Colony();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Colony");
        photo=new PhotoColony(this);
        buttonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(buttonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE,SIDE*SIZE+50)); 
        setResizable(false);
        photo.repaint();
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        buttonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonTicTacAction();
                }
            });

    }

    private void buttonTicTacAction() {
        colony.ticTac();
        photo.repaint();
    }

    public Colony getColony(){
        return colony;
    }
    
    public static void main(String[] args) {
        ColonyGUI cg=new ColonyGUI();
        cg.setVisible(true);
    }  
}

class PhotoColony extends JPanel{
    private ColonyGUI gui;

    public PhotoColony(ColonyGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE, gui.SIDE*gui.SIZE));         
    }


    public void paintComponent(Graphics g){
        Colony Colony=gui.getColony();
        super.paintComponent(g);
         
        for (int f=0;f<=Colony.getLength();f++){
            g.drawLine(f*gui.SIDE,0,f*gui.SIDE,Colony.getLength()*gui.SIDE);
        }
        for (int c=0;c<=Colony.getLength();c++){
            g.drawLine(0,c*gui.SIDE,Colony.getLength()*gui.SIDE,c*gui.SIDE);
        }       
        for (int f=0;f<Colony.getLength();f++){
            for(int c=0;c<Colony.getLength();c++){
                if (Colony.getEntity(f,c)!=null){
                    g.setColor(Colony.getEntity(f,c).getColor());
                    if (Colony.getEntity(f,c).shape()==Entity.INSECT){
                        g.drawOval(gui.SIDE*c+1,gui.SIDE*f+5,gui.SIDE-12,gui.SIDE-12);
                        g.drawOval(gui.SIDE*c+gui.SIDE-15,gui.SIDE*f+gui.SIDE-10,gui.SIDE-5,gui.SIDE-12);
                        if (Colony.getEntity(f,c).is()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+5,gui.SIDE-12,gui.SIDE-12);
                            g.fillOval(gui.SIDE*c+gui.SIDE-15,gui.SIDE*f+gui.SIDE-10,gui.SIDE-5,gui.SIDE-12);
                        }    
                    }else if (Colony.getEntity(f,c).shape()==Entity.SQUARE){  
                        g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2); 
                        if (Colony.getEntity(f,c).is()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }
                    }else{
                        g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        if (Colony.getEntity(f,c).is()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } 
                    }
                }
            }
        }
    }
}