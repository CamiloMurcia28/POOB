package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;

/**
 * Write a description of class VintageGUI here.
 * 
 * @authors Camilo Murcia y Jeisson Casallas
 * @version 1.0
 */
public class VintageGUI extends JFrame{

    private JFileChooser saveFile;
    private JFileChooser openFile;
    
    /**
     * Constructor for objects of class VintageGUI
     */
    private VintageGUI(){
        super("Vintage");
        prepareElements();
        prepareActions();
        prepareElementsMenu();
    }

    private void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = screenSize.width / 2;
        int heightScreen = screenSize.height / 2;
        setSize(widthScreen, heightScreen);
        setLocationRelativeTo(null);   
    }
    
    private void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        
        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        JMenuItem saveMenuItem = new JMenuItem("Guardar");
        
        fileMenu.add(exitMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        
        exitMenuItem.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            //exitOptions();
            if(e.getSource() == exitMenuItem){
                 System.exit(0);
            }
            if(e.getSource() == openMenuItem){
                openFileOption();
            }
            if(e.getSource() ==  saveMenuItem){
                saveFileOption();
            }
        }
        });
    }
    
    public void saveFileOption(){
        File file = null;
        saveFile = new JFileChooser();
        saveFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFile.showSaveDialog(this);
        showFileName("El archivo se guardo ", file);
    }
    public void openFileOption(){
        File file = null;
        openFile = new JFileChooser();
        openFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        openFile.showOpenDialog(this);
        showFileName("Se abrio el archivo ", file);
    }
    
    /*
     * Imprime el nombre del archivo seleccionado
     * @param String m
     * @param File file
    */
    private void showFileName(String m, File file){
        int result = openFile.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            file = openFile.getSelectedFile();
        }
        if(file != null){
            JOptionPane.showMessageDialog(null,m+file.getName(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea cerrar la apliacion?",
                "Confirmacion de cierre", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    
    private void prepareElementsBoard(){
        
    }
    
    public void refresh(){
        
    }
    
    public void prepareElementsGame(){
        
    }
    
    public static void main(String args[]){
        VintageGUI vWindow = new VintageGUI();
        vWindow.setVisible(true);
    }

}
