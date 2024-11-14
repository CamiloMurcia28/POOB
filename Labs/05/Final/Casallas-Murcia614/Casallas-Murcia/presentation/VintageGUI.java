package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import domain.*;

/**
 * Class of VintageGUI 
 * @author Camilo Murcia Jeisson Casallas
 * Version 4.0 (18/11/23)
 */
public class VintageGUI extends JFrame {

    //Menu
    private JFileChooser saveFile;
    private JFileChooser openFile;
    
    //Interfaz
    private JPanel vintageGame;
    private JPanel game;
    private JButton[][] boardButtons;
    private int tamañoTablero = 8;
    private Color buttonBorderColor = new Color(128,64, 0);
    private JButton saveVintage;
    private JButton restartButton;
    private JButton finishButton;
    
    private JPanel informationPanel;
    private JPanel optionsPanel;
    private JButton selectedButton;
    private JLabel turnoLabel;
    private JLabel puntuacionJugador1Label;
    private JLabel puntuacionJugador2Label;
    private JPanel buttonContainerPanel;
    private JPanel buttonPanel;
    private JPanel titlePanel;
    private JPanel infoPanel;
    
    
    //Vintage
    private Vintage vintage;
    
    private VintageGUI() {
        super("Vintage Jewel");
        this.vintage = new Vintage(this.tamañoTablero);
        prepareElements();
        prepareActions();
        prepareElementsBoard();
    }

    private void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = screenSize.width / 2;
        int heightScreen = screenSize.height / 2;
        setSize(widthScreen, heightScreen);
        setLocationRelativeTo(null);
        prepareElementsMenu();
    }
    
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitOptions();
            }
        });
    }
    
    /**
     * Refreshes the entire GUI, removing everything and redrawing.
     */
    public void refresh() {
        getContentPane().removeAll();
        prepareElements();
        prepareActions();
        prepareElementsBoard();
        this.revalidate();
        this.repaint();
        validateWinCondition(); 
    }

     //prepareElementsGameO
    private void addGameTitleLabel2() {
        Color backGroundColor = new Color(200, 200, 200);
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Vintage Jewel");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
    
        add(titlePanel, BorderLayout.NORTH);
    }
    
    private JPanel addGameInformation(){
        buttonContainerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel scoresLabel = new JLabel("Puntajes:");
        JLabel player1ScoreLabel = new JLabel("Jugador 1: "+ vintage.getPlayers().get((byte) 1).getPoints());
        JLabel player2ScoreLabel = new JLabel("Jugador 2: "+ vintage.getPlayers().get((byte) 2).getPoints());
        JLabel turnLabel = new JLabel("Turno: "+ vintage.getTurn() );

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(scoresLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(player1ScoreLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(player2ScoreLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(turnLabel);
    
        buttonContainerPanel.add(infoPanel);
        add(buttonContainerPanel, BorderLayout.WEST);
        
        return infoPanel;
    }
    
    private void addGameButtons(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel buttPanel = new JPanel();
        buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.Y_AXIS));
    
        finishButton = new JButton("Terminar juego");
        restartButton = new JButton("Reiniciar juego");
    
        buttPanel.add(finishButton);
        buttPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttPanel.add(restartButton);
        
        buttonPanel.add(buttPanel);
        add(buttonPanel, BorderLayout.EAST);
    }

    private void addGameTitleLabel() {
        Color backGroundColor = new Color(200, 200, 200);
        JPanel titlePanel = createTitlePanel();
        JPanel buttonContainerPanel = createButtonContainerPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(buttonContainerPanel, BorderLayout.EAST);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Vintage Jewel");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        
        titlePanel.add(titleLabel);

        finishButton = new JButton("Terminar juego");
        restartButton = new JButton("Reiniciar juego");
        titlePanel.add(finishButton);
        titlePanel.add(restartButton);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetOptionItem();
            }
        });
        
        return titlePanel;
        }
    
    private JPanel createButtonContainerPanel() {
        JPanel buttonContainerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel scoreAndTurnPanel = createScoreAndTurnPanel();
        
        buttonContainerPanel.add(scoreAndTurnPanel);
        return buttonContainerPanel;
    }
    
    private JPanel createScoreAndTurnPanel() {
        JPanel scoreAndTurnPanel = new JPanel();
        scoreAndTurnPanel.setLayout(new BoxLayout(scoreAndTurnPanel, BoxLayout.Y_AXIS));
    
        // Agregar las etiquetas para puntajes y turno
        JLabel scoresLabel = new JLabel("Puntajes:");
        JLabel player1ScoreLabel = new JLabel("Jugador1: " + vintage.getPlayers().get((byte) 1).getPoints());
        JLabel player2ScoreLabel = new JLabel("Jugador2: " + vintage.getPlayers().get((byte) 2).getPoints());
        JLabel turnLabel = new JLabel("Turno: " + vintage.getTurn());
    
        // Agregar las etiquetas al panel de puntajes y turno
        scoreAndTurnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scoreAndTurnPanel.add(scoresLabel);
        scoreAndTurnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scoreAndTurnPanel.add(player1ScoreLabel);
        scoreAndTurnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scoreAndTurnPanel.add(player2ScoreLabel);
        scoreAndTurnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scoreAndTurnPanel.add(turnLabel);
    
        return scoreAndTurnPanel;
    }


    private void prepareElementsBoard() {
        vintageGame = new JPanel();
        this.setLayout(new BorderLayout());
        this.add(vintageGame, BorderLayout.CENTER);
        prepareElementsGame();
        addGameTitleLabel2();
        addGameInformation();
        addGameButtons();
        prepareActionsBoardGame();
    }

    /**
     * Prepares the elements of the game
     *  */
    public void prepareElementsGame() {
        game = drawBoard();
        vintageGame.add(game, BorderLayout.SOUTH);
    }
    
    private JPanel drawBoard() {
        game = new JPanel();
        game.setBounds(10, 20, (8 * tamañoTablero) + 100, (8 * tamañoTablero) + 100);
        game.setBackground(Color.BLACK);
        game.setLayout(new GridLayout(tamañoTablero, tamañoTablero));
        game.setPreferredSize(new Dimension(300, 300));
        boardButtons = new JButton[tamañoTablero][tamañoTablero];
    
        for (int fila = 0; fila < tamañoTablero; fila++) {
            for (int columna = 0; columna < tamañoTablero; columna++) {
                Color color = vintageColor(vintage.returnBoard()[fila][columna]);
                JButton boton = createButton(fila, columna);
                boton.setPreferredSize(new Dimension(30, 30));
                boton.setBackground(color);
                boardButtons[fila][columna] = boton;
                game.add(boton);
            }
        }
    
        return game;
    }

    private Color vintageColor(char color){
        if (color == 'p')
            return Color.PINK;
        else if(color == 'r')
            return Color.RED;
        else if(color == 'a')
            return Color.BLUE;
        else if(color == 'v')
            return Color.GREEN;
        else if(color == 'b')
            return Color.WHITE;
        else if(color == 'y')
            return Color.YELLOW;
        else if(color == 'n')
            return Color.ORANGE;
        else
            return Color.BLACK;
    }
    
    //NUEVO
    private JButton createButton(int fila, int columna) {
        JButton boton = new JButton();
        boton.setPreferredSize(new Dimension(30, 30));
        boton.setBackground(new Color(255, 255, 255));
        boton.setBorder(BorderFactory.createLineBorder(returnNewBorderColorWhenMoved(fila, columna), 3)); // Aplicar el nuevo borde aquí
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            clickOnButton(fila, columna, boton);
            }
        });
        return boton;
    }
    
    //NUEVO
    
    /**
     * Valida la condición de victoria y muestra un mensaje si es necesario.
     */
    public void validateWinCondition() {
        if (vintage.returnVintageFinished()) {
                String winnerMessage = "";
                int winner = vintage.returnWinner();
                if (winner == 0) {
                    winnerMessage = "Empate";
                } else if (winner == 1) {
                    winnerMessage = "El ganador fue el Jugador 1";
                } else if (winner == 2) {
                    winnerMessage = "El ganador fue el Jugador 2";
                }
                JOptionPane.showMessageDialog(this, "Juego Terminado\n" + winnerMessage, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    //NUEVO
    /**
     * Returns the color for the line of the button
     * @param fila
     * @param columna
     * @return borderButtonColor
     */
    private Color returnNewBorderColorWhenMoved(int fila, int columna){
        if(vintage.returnBinaryVintageBoard()[fila][columna] == (byte) 1){
            return Color.BLACK;
        }
        return buttonBorderColor;
    }
    
    
    public void clickOnButton(int fila, int columna, JButton boton) {
        if (selectedButton == null) {
            selectedButton = boton;
        } else {
            JButton clickedButton = boton;
            int[] selectedButtonLocation = getGridButtonPosition(selectedButton);
            int[] clickedButtonLocation = getGridButtonPosition(clickedButton);
            
            vintage.move(selectedButtonLocation[0],selectedButtonLocation[1], clickedButtonLocation[0], clickedButtonLocation[1]);
            refresh();
            selectedButton = null;
        }
    }

    /**
     * Returns an array with the x, y position of a button in the game's board
     * @param button
     * @return
     */
    private int[] getGridButtonPosition(JButton button){
        Point punto = button.getLocation();
        int fila = punto.y / button.getHeight();
        int columna = punto.x / button.getWidth();
        return new int[]{fila, columna};
    }
    
    //BOTONES MENU
    
    private void prepareElementsMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);

        JMenu fileMenu = new JMenu("Menu");
        JMenu fileOption = new JMenu("Configuración");
        menuBar.add(fileMenu);
        menuBar.add(fileOption);
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        JMenuItem saveMenuItem = new JMenuItem("Guardar");
        JMenuItem colorOptionItem = new JMenuItem("Color tablero");
        JMenuItem sizeOptionItem = new JMenuItem("Tamaño tablero");
        fileMenu.add(exitMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileOption.add(colorOptionItem);
        fileOption.add(sizeOptionItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitOptions();
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileOption();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileOption();
            }
        });
        
        colorOptionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorOptionItem();
            }
        });
        
        sizeOptionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizeOptionItem();
            }
        });

    }
    
    public void finishOption(){
        this.remove(vintageGame);
    }
    
    public void saveFileOption() {
        File file = null;
        saveFile = new JFileChooser();
        saveFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFile.showSaveDialog(this);
        showFileName("El archivo se guardo ", file);
    }

    public void openFileOption() {
        File file = null;
        openFile = new JFileChooser();
        openFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        openFile.showOpenDialog(this);
        showFileName("Se abrio el archivo ", file);
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
        } else if (respuesta == JOptionPane.NO_OPTION) {
            // No hace nada
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void colorOptionItem() {
        JColorChooser chooser = new JColorChooser();
        Color newColor = chooser.showDialog(null, "Color del tablero", buttonBorderColor);
    
        if (newColor != null) {
            buttonBorderColor = newColor;
            game.setBackground(buttonBorderColor);
            for (int fila = 0; fila < tamañoTablero; fila++) {
                for (int columna = 0; columna < tamañoTablero; columna++) {
                    JButton boton = boardButtons[fila][columna];
                    boton.setBorder(BorderFactory.createLineBorder(buttonBorderColor));
                }
            }
        }
    }
    
    public void sizeOptionItem() {
        tamañoTablero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo tamaño"));
        if(tamañoTablero>=3){
            game.setVisible(false);
            prepareElementsGame();
            game.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se puede crear el tablero con menos de 3 casillas","Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void resetOptionItem(){
        int answer = JOptionPane.showConfirmDialog(null, "¿Desea reiniciar el juego?", "Reiniciar Juego", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                this.remove(vintageGame);
                vintage.setTurno();
                vintage.restoreGame();
                prepareElementsBoard();
                refresh();
            }
            else if(answer == JOptionPane.NO_OPTION){
                //No hace nada
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
    }
    
    public void prepareActionsBoardGame(){
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                resetOptionItem(); 
            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                finishOption();
            }
        });
    }
    
    //MAIN
    public static void main(String args[]) {
        new VintageGUI().setVisible(true);
    }
    

    
}