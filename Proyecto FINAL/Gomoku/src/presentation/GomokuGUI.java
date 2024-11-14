package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of GomokuGUI
 * @author Camilo Murcia Jeisson Casallas
 * Version 1.0 (18/11/23)
 */
public class GomokuGUI extends JFrame {
    //Gomoku
    private Gomoku gomoku;

    //Principal
    private JPanel mainMenu;
    private JLabel jLabel1;
    //MENU
    private JLabel creacion;
    private JButton nuevoJuego;
    private JButton reanudarP;
    private JButton configuracion;
    //Modo de juego
    private JPanel newGame;
    //Jugador vs IA
    private JPanel aiConfigPanel;
    //Jugador vs Jugador
    private JPanel playerConfigPanel;
    //configuracion
    private JPanel settings;
    //Jugadores
    private JTextField textField1= new JTextField("Jugador 1",10);
    private JTextField textField2 = new JTextField("Maquina",10);
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JButton colorFicha;
    private JButton colorFicha2;
    private ArrayList<Color> selectedColors;
    //tipo de juego
    private JPanel gameMode;
    //Panel del tablero
    private JPanel game;
    private int tamañoTablero = 15;
    private JPanel gomokuGame;
    private JPanel gameOptions;
    
    //botones tablero
    private JButton save;
    private JButton finish;
    private JButton reset;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private List<Piedra> listPiedra;
    
    //REANUDAR
    private JFileChooser load;
    private JFileChooser openFile;
    private JFileChooser saveFile;

    //Turnos
    private Color color1 = new Color(255,255,255);
    private Color color2 = new Color(0,0,0);
    private String nombre1;
    private String nombre2;
    private Color colorVer;
    private int contador = 1;
    private String colorTurnoJug = "White";
    private JLabel turnLabel;
    
    private GomokuGUI(){
        setVisible(true);
        prepareElements();
        prepareActions();
    }

    //ARREGLAR TAMAÑO PANTALLA TABLERO E INFORMACION NO CABE
    private void prepareElements(){
        setSize(560, 600);
        getContentPane().setBackground(new Color(255,0,0));
        setLocationRelativeTo(null);
        setResizable(false);
        prepareElementsMenu();
        //prepareLogo();
        getContentPane().add(mainMenu); 
    }
    private void prepareLogo(){
        setTitle("Gomoku");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
        ImageIcon imagen = new ImageIcon("Presentation/images/logo.png");
        jLabel1 = new JLabel(imagen);
        jLabel1.setBounds(125,15,300,150);
        mainMenu.add(jLabel1);
    }
    private void prepareElementsMenu(){
        mainMenu = new JPanel();
        mainMenu.setLayout(null);
        nuevoJuego = new JButton("Nuevo juego");
        reanudarP = new JButton("Reanudar partida");
        configuracion = new JButton("Configuracion");
        mainMenu.setBackground(new Color(255,128,0));
        nuevoJuego.setBounds(125,180,300,40);
        mainMenu.add(nuevoJuego);
        reanudarP.setBounds(125,250,300,40);
        mainMenu.add(reanudarP);
        configuracion.setBounds(125, 320, 300, 40);
        mainMenu.add(configuracion);
        creacion = new JLabel("Hecho por: Jeisson Casallas & Camilo Murcia");
        creacion.setFont(new Font("Andale Mono",1,10));
        creacion.setBounds(85,375,300,30);
        mainMenu.add(creacion);
    }

    private void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitOptions();
            }
        });
        nuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                showGameModeDialog();
            }
        });
        reanudarP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //resumeOption(); REVISAR DESPUES FUNCIONAMIENTO. 
            }
        });
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                GameSettingsDialog();
            }
        });
        
    }

    private void showGameModeDialog() {
        gameMode = new JPanel();
        JLabel label = new JLabel("Selecciona el tipo de juego:");
        JComboBox<String> GameModeBox = new JComboBox<>(new String[]{"Modo Normal","Modo limite de tiempo","Modo Piedras limitadas"});
        gameMode.add(label);
        gameMode.add(GameModeBox);
        int result = JOptionPane.showOptionDialog(this, gameMode, "Modo de Juego",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            String typeSelected = (String) GameModeBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Modo seleccionado: " + typeSelected);
            if(typeSelected.equals("Modo Normal")){
                normalModeGomoku();
                newGameModeDialog();
            }else if(typeSelected.equals("Modo limite de tiempo")){
                quickModeGomoku();
                newGameModeDialog();
            }else if(typeSelected.equals("Modo Piedras limitadas")){
                limitModeGomoku();
                newGameModeDialog();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Operación cancelada");
        }
    }
    
    private void normalModeGomoku(){
        this.gomoku = new Gomoku(tamañoTablero);
        prepareElementsBoard();
    }
    
    private void quickModeGomoku(){
        this.gomoku = new Gomoku(tamañoTablero);
        prepareElementsBoard();
    }
    
    private void limitModeGomoku(){
        this.gomoku = new Gomoku(tamañoTablero);
        prepareElementsBoard();
    }

    private void newGameModeDialog() {
        // Crear un panel con componentes personalizados
        newGame = new JPanel();
        JLabel label = new JLabel("Selecciona el modo de juego:");
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Jugador vs IA", "Jugador vs Jugador"});
        newGame.add(label);
        newGame.add(comboBox);

        int result = JOptionPane.showOptionDialog(this, newGame, "Modo de Juego",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (result == JOptionPane.OK_OPTION) {
            String selectedMode = (String) comboBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Modo seleccionado: " + selectedMode);
            
            if(selectedMode.equals("Jugador vs IA")){
                showAIConfiguration();
            }else if(selectedMode.equals("Jugador vs Jugador")){
                showPlayerConfiguration();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada");
        }
    }
    
    private void showAIConfiguration() {
        aiConfigPanel = new JPanel();
        //aiConfigPanel.add(new JLabel("Nuevo juego"));
        textField1 = new JTextField("Jugador 1",10);
        colorFicha= new JButton("     ");
        aiConfigPanel.add(new JLabel("Jugador 1: "));
        this.nombre1 = textField1.getText();
        this.nombre2 = "Jugador 2";
        aiConfigPanel.add(textField1);
        aiConfigPanel.add(new JLabel("Color ficha: "));
        aiConfigPanel.add(colorFicha);
        aiConfigPanel.add(new JLabel("Dificultad Maquina: "));
        JComboBox<String> DificultBox = new JComboBox<>(new String[]{"Agresiva", "Experta","Miedosa"});
        aiConfigPanel.add(DificultBox);
        aiConfigPanel.add(new JLabel("Color Ficha:"));
        colorFicha2= new JButton("     ");
        aiConfigPanel.add(colorFicha2);
        colorFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               changeColor();
            }
        });
        colorFicha2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               changeColor();
            }
        });
        int result = JOptionPane.showOptionDialog(this, aiConfigPanel, "Configuración jugador",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        this.nombre2 = "Maquina";
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Configuración Exitosa");
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada");
        }
    }
    
    private void showPlayerConfiguration() {
        playerConfigPanel = new JPanel();
        //playerConfigPanel.add(new JLabel("Nuevo juego"));
        textField1 = new JTextField("Jugador 1",10);        
        colorFicha= new JButton();
        playerConfigPanel.add(new JLabel("Jugador 1: "));
        this.nombre1 = textField1.getText();
        playerConfigPanel.add(textField1);
        playerConfigPanel.add(new JLabel("Color ficha: "));
        playerConfigPanel.add(colorFicha);
        
        textField2 = new JTextField("Jugador 2",10);
        colorFicha2= new JButton();
        playerConfigPanel.add(new JLabel("Jugador 2: "));
        this.nombre2 = textField2.getText();
        playerConfigPanel.add(textField2);
        playerConfigPanel.add(new JLabel("Color ficha: "));
        playerConfigPanel.add(colorFicha2);
        
        colorFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               changeColor();
            }
        });
        colorFicha2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               changeColor();
            }
        });
        int result = JOptionPane.showOptionDialog(this, playerConfigPanel, "Configuración jugador",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Configuración Exitosa");
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada");
        }
    }

    private void GameSettingsDialog() {
        settings = new JPanel();
        JTextField textField3 = new JTextField(10);
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"10%", "20%","30%","40%","50%","60%","70%","80%","90%","100%"});
        settings.add(new JLabel("Tamaño tablero:"));
        settings.add(textField3);
        settings.add(new JLabel("Casillas especiales:"));
        settings.add(comboBox);
        int result = JOptionPane.showOptionDialog(this,settings, "Configuración",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int newSize = Integer.parseInt(textField3.getText());
                if (newSize >= 10) {
                    tamañoTablero = newSize;
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un tamaño de tablero válido (mayor que 10).",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido para el tamaño del tablero.",
                         "Error", JOptionPane.ERROR_MESSAGE);
            }
            String value1 = textField3.getText();
            String value2 = (String) comboBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Tamaño tablero: " + value1 + "\n Casillas especiales: " + value2);
        }
    }
    
    public void changeColor(){
        HashMap<String, Color> jugadores = gomoku.getJugadores();
        selectedColors = new ArrayList<>();
        if(jugadores.get(nombre1) == Color.BLACK){
            Color selectedColor = selectColor();
            gomoku.setColor(nombre1, selectedColor);
            this.color1 = selectedColor;
            selectedColors.add(selectedColor);
        }else if(jugadores.get(nombre2) == Color.WHITE){
            Color selectedColor = selectColor();
            gomoku.setColor(nombre2, selectedColor);
            this.color2 = selectedColor;
            selectedColors.add(selectedColor);
        }
    }

    private Color selectColor() {
        Color selectedColor = null;
        while (selectedColor == null || selectedColors.contains(selectedColor)) {
            selectedColor = JColorChooser.showDialog(null, 
                "Elige el color para tus piedras: ", null);
            if (selectedColor == null) {
                JOptionPane.showMessageDialog(this, "No puedes seleccionar un color nulo", "Alerta", JOptionPane.ERROR_MESSAGE);
            }else if(selectedColors.contains(selectedColor)) {
                JOptionPane.showMessageDialog(this, "No puedes seleccionar un color ya en uso", "Alerta", JOptionPane.WARNING_MESSAGE);
            }            
        }
        return selectedColor;
    }
    
    public void resumeOption(){
        File file = null;
        load = new JFileChooser();
        load.setFileSelectionMode(JFileChooser.FILES_ONLY);
        showFileName("Se abrió el archivo ", file);
    }
    
     private void showFileName(String m, File file) {
        int result = openFile.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = openFile.getSelectedFile();
            JOptionPane.showMessageDialog(null, m + file.getName(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void exitOptions() {
        int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea cerrar la aplicación?",
                "Confirmacion de cierre", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    
    //TABLERO
    /* 
    private JPanel drawBoard() {
        listPiedra = gomoku.getListPiedras();
        game = new JPanel();
        game.setBounds(10, 20, (8 * tamañoTablero) + 100, (8 * tamañoTablero) + 100);
        game.setBackground(Color.BLACK);
        game.setLayout(new GridLayout(tamañoTablero, tamañoTablero));
        game.setPreferredSize(new Dimension(300, 300));
        for(Piedra p :listPiedra){
            game.add(p);
            p.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickOnButton(p);
                }
            });
        }
        return game;
    }
    */
    
    private List<Casilla> listCasillas;
    private JPanel drawBoard(){
        listCasillas = gomoku.getListCasillas();
        this.game = new JPanel();
        this.game.setBounds(10, 20, (8 * tamañoTablero) + 100, (8 * tamañoTablero) + 100);
        this.game.setBackground(Color.BLACK);
        this.game.setLayout(new GridLayout(tamañoTablero, tamañoTablero));
        this.game.setPreferredSize(new Dimension(300, 300));
        for(Casilla c : listCasillas){
            this.game.add(c);
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickOnButton2(c);
                }
            });
        }
        return game;
    }
    
    private void clickOnButton2(Casilla boton) { 
        boolean movValido = false;
        movValido = gomoku.movimientoCasillaValido(boton.getPositionX(), boton.getPositionY());
        if(movValido){
            if(contador%2 == 1){
                refreshTurno();
                gomoku.place(boton.getPositionX(), boton.getPositionY(),this.colorTurnoJug, color1);
                boton.setOcupada(true);boton.setFondo(color1);
                this.colorTurnoJug = "White";
            }
            else{   
                refreshTurno();        
                gomoku.place(boton.getPositionX(), boton.getPositionY(),this.colorTurnoJug, color2);
                boton.setOcupada(true);boton.setFondo(color2);
                this.colorTurnoJug = "Black";
            }
        }else{
            JOptionPane.showMessageDialog(null, "Movimiento invalido, intente nuevamente", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
        if(movValido){
            ++contador;
        }
        if(colorTurnoJug.equals("White")){
            colorVer = color1;
        }
        else{
            colorVer = color2;
        }
        if(gomoku.getWinner(colorVer)){
            JOptionPane.showMessageDialog(null, String.format("El ganador es: %s ", colorTurnoJug),"Informacion", JOptionPane.INFORMATION_MESSAGE);
            restartGame();
        }
        if (gomoku.finalizado()){
            JOptionPane.showMessageDialog(null, String.format("El juego queda empatado! \n No mas movimientos disponibles"), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            restartGame();
        }
    }

    /* 
    private void clickOnButton(Piedra boton) { 
        boolean movValido = false;
        movValido = gomoku.movimientoValido(boton.getPositionX(), boton.getPositionY());
        if(movValido){
            if(contador%2 == 1){
                refreshTurno();
                this.colorTurnoJug = "White";
                gomoku.place(boton.getPositionX(), boton.getPositionY(),this.colorTurnoJug, color1);
                //this.colorTurnoJug = nombre1;//CORREGIR LABEL TURNOS
            }
            else{
                refreshTurno();
                this.colorTurnoJug = "Black";                   
                gomoku.place(boton.getPositionX(), boton.getPositionY(),this.colorTurnoJug, color2);
                //this.colorTurnoJug = nombre2;//CORREGIR LABEL TURNOS

            }
        }else{
            JOptionPane.showMessageDialog(null, "Movimiento invalido, intente nuevamente", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
        if(movValido){
            ++contador;
        }
        if(colorTurnoJug.equals("White")){
            colorVer = color1;
        }
        else{
            colorVer = color2;
        }
        if(gomoku.getWinner(colorVer)){
            JOptionPane.showMessageDialog(null, String.format("El ganador es: %s ", colorTurnoJug),"Informacion", JOptionPane.INFORMATION_MESSAGE);
            restartGame();
        }
        if (gomoku.isFinished()){
            JOptionPane.showMessageDialog(null, String.format("El juego queda empatado! \n No mas movimientos disponibles"), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            restartGame();
        }
    }
    */
    private void restartGame(){
        int resp = JOptionPane.showConfirmDialog(null,"Desea jugar nuevamente?", "Informacion", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
            gomoku.nuevoJuego();
            this.contador = 1;
            this.colorTurnoJug = "White";
            refreshTurno();
        }
        else{
            this.dispose();
        }
    }

    private void refreshTurno(){
        turnLabel.setText("Turno: " + this.colorTurnoJug);
        player1ScoreLabel.setText(this.nombre1);
        player2ScoreLabel.setText(this.nombre2);
        //return turnLabel;
    }
    
    public void prepareElementsBoard() {
        mainMenu.setVisible(false);
        gomokuGame = new JPanel();
        gameOptions = new JPanel();
        prepareLogo();
        this.add(gameOptions);
        this.add(gomokuGame);
        prepareElementsGame();
        gameOptions.setVisible(true);
        gomokuGame.setVisible(true);
        //prepareActionsBoardGame(); FUNCIONALIDAD BOTONES
    }
    
    public void prepareElementsGame() {
        gomokuGame.setSize(this.getWidth() - gameOptions.getWidth(), this.getHeight());
        gomokuGame.setBackground(new Color(255,128,0));
        game = drawBoard();
        JPanel buttonContainer= addGameButtons();
        JPanel informationContainer=addGameInformation();
        gomokuGame.add(jLabel1);
        gomokuGame.add(informationContainer);
        gomokuGame.add(game);
        gomokuGame.add(buttonContainer);
    }
    
    
    private JPanel addGameInformation(){
        infoPanel = new JPanel();
        JLabel timeLabel = new JLabel("Tiempo: pendiente");
        player1ScoreLabel = new JLabel("Jugador 1: ");
        player2ScoreLabel = new JLabel("Jugador 2:" );
        turnLabel = new JLabel("Turno: " + this.colorTurnoJug);
    
        //ARREGLAR POSICIÓN DE LOS COMENTARIOS
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(timeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(player1ScoreLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(player2ScoreLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(turnLabel);

        return infoPanel;
    }
    
    private JPanel addGameButtons(){
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255,128,0));
        finish= new JButton("Terminar juego");
        reset = new JButton("Reiniciar juego");
        save = new JButton("Guardar juego");
        
        //ARREGLAR BOUNDS
        finish.setBounds(125, 180, 300, 40);
        reset.setBounds(125, 230, 300, 40); // Ajusta las coordenadas para evitar superposición
        save.setBounds(125, 280, 300, 40);    // Ajusta las coordenadas para evitar superposición
        
        buttonPanel.add(finish);
        buttonPanel.add(reset);
        buttonPanel.add(save);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                saveGame();
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                restartGame();
            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                finishGame();
            }
        });
        
        return buttonPanel;
    }
    
    public void finishGame(){
        int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea finalizar el juego?",
                "Confirmacion de cierre", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            this.remove(gomokuGame);
            this.remove(gameOptions);
            mainMenu.setVisible(true);
        }           
    }
    
    public void saveGame(){
        File file = null;
        saveFile = new JFileChooser();
        saveFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFile.showSaveDialog(this);
        showFileName("Se guardo el archivo ", file);
    }

    //MAIN
    public static void main(String args[]) {
        new GomokuGUI();
    }
}

