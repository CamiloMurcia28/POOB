package presentation;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;

/**
 * The GUI class ColonyGUI.
 *
 * @author  Jeisson Casallas & Camilo Murcia
 * @version 1.0
 */
public class ColonyGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=31;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoColony photo;
    private Colony colony;
    private JMenuItem optionOpen;
    private JMenuItem optionSave;
    private JMenuItem optionImport;
    private JMenuItem optionExport;
    private JMenuItem optionNew;
    private JMenuItem optionExit;

    private ColonyGUI() {
        colony=new Colony();
        prepareElements();
        prepareActions();
        prepareElementsMenu();
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
    
    private void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);

        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);
        optionOpen  = new JMenuItem("Abrir");
        optionSave = new JMenuItem("Guardar");
        optionImport = new JMenuItem("Importar");
        optionExport= new JMenuItem("Exportar como");
        optionNew = new JMenuItem("Nuevo");
        optionExit = new JMenuItem("Salir");
        fileMenu.add(optionOpen);
        fileMenu.add(optionSave);
        fileMenu.add(optionImport);
        fileMenu.add(optionExport);
        fileMenu.add(optionNew);
        fileMenu.add(optionExit);
        PrepareActionsMenu();
    }
    
    private void PrepareActionsMenu(){
        optionOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    optionOpen();
                }
                catch (ColonyException ce)
                {
                    JOptionPane.showMessageDialog(ColonyGUI.this, ce.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        optionSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    optionSave();
                }
                catch (ColonyException ce)
                {
                    JOptionPane.showMessageDialog(ColonyGUI.this, ce.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        optionImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    optionImport();
                }
                catch (ColonyException ce)
                {
                    JOptionPane.showMessageDialog(ColonyGUI.this, ce.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        });
        optionExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    optionExport();
                }
                catch (ColonyException ce)
                {
                    JOptionPane.showMessageDialog(ColonyGUI.this, ce.getMessage(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        optionNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionNew();
            }
        });

        optionExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    optionExit();
            }
        });
    }
    
    private void optionOpen() throws ColonyException {
        JFileChooser load = new JFileChooser();
        load.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = load.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = load.getSelectedFile();
            Colony loadedColony = Colony.open01(file);
            if (loadedColony != null) {
                colony = loadedColony;
                photo.repaint();
            }
        }
    }
 
    
   private void optionSave() throws ColonyException {
        JFileChooser save = new JFileChooser();
        save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = save.showSaveDialog(this);
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = save.getSelectedFile();
            colony.save01(file);
        }
    }
    
    private void optionImport() throws ColonyException {
        JFileChooser load = new JFileChooser();
        load.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = load.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = load.getSelectedFile();
            colony.import03(file);
            photo.repaint();
        }
    }

    
    private void optionExport() throws ColonyException {
        JFileChooser save = new JFileChooser();
        save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = save.showSaveDialog(this);
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = save.getSelectedFile();
            colony.export02(file);
        }
    }

    private void optionNew(){
        colony= new Colony();
        photo.repaint();
    }
    
    private void optionExit(){
        System.exit(0);
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