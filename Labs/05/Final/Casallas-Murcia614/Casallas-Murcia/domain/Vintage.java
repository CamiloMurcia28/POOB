package domain;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Class for Vintage
 *
 * @author Jeisson Casallas y Camilo Murcia
 * @version 1.0
 */
public class Vintage
{
    private char[][] vintageBoard;
    private char[] colors = {'p', 'v', 'b','r','a','y','n'};
    private Random random = new Random();
    private byte[][] binaryVintageBoard;
    private int dimension;
    public byte turn = 0;
    private HashMap<Byte, Player> players = new HashMap<>();
    private int filledCells = 0;
    private boolean vintageFinished;
    private char[][] copyVintageBoard;
    private byte[][] copyBinaryVintage;
    /**
     * Constructor for objects of class Vintage
     */
    /**
     * Constructor de la clase vintage
     * @param dim dimensión del tablero
     */
    public Vintage(int dimension){
        this.dimension = dimension;
        getRandomBoard();
        getBinaryVintageBoard();
        addPlayers();
        refreshBoardGeneral();
        this.vintageFinished = false;
        turn = 1;
        copyStateOfGame();
    }

    /**
     * Crea los jugadores del juego
     */
    public void addPlayers(){
        players.put((byte) 1, new Player((byte) 1));
        players.put((byte) 2, new Player((byte) 2));
    }
    
    /**
     * Restora el juego cuando se reinicia
     */
    public void restoreGame(){
        this.vintageBoard = copyVintageBoard;
        this.binaryVintageBoard = copyBinaryVintage;
        players.get((byte)1).setScore();
        players.get((byte)2).setScore();
        filledCells = 0;
        vintageFinished = false;
        copyStateOfGame(); 
    }
    
        
    /**
     * Copiar el juego original
     */
    private void copyStateOfGame(){
        copyVintageBoard = new char[dimension][dimension];
        copyBinaryVintage = new byte[dimension][dimension];
        for(int i = 0; i < (vintageBoard.length) ; i++){
            for(int j = 0; j < (vintageBoard.length); j++){
                copyVintageBoard[i][j] = vintageBoard[i][j];
                copyBinaryVintage[i][j] = (byte) 0;
            }
        }
    }

    /**
     * Mueve las fichas
     * @param xPos mueve en x
     * @param yPos mueve en y
     * @param newXPos da nueva posición a x
     * @param newYPos da nueva posicion a y
     */
    public void move(int xPos, int yPos, int newXPos, int newYPos){
        if(validMove(xPos, yPos, newXPos, newYPos)){
            char firstCharacter = vintageBoard[xPos][yPos];
            char secondCharacter = vintageBoard[newXPos][newYPos];
            vintageBoard[xPos][yPos] = secondCharacter;
            vintageBoard[newXPos][newYPos] = firstCharacter;
            refreshBoardGeneral();
            setTurn_();
            vintageFinished();
        }
        else{
            JOptionPane.showMessageDialog(null, "Movimiento invalido", "Mensaje de información", JOptionPane.INFORMATION_MESSAGE);
        }    
    }

    /**
     * Calcula los puntos ganados en base a las combinaciones de joyas.
     * 
     * @param xResults Lista de resultados en la coordenada x.
     * @param yResults Lista de resultados en la coordenada y.
     * @return Puntos ganados.
     */
    
    public int calculatePoints(ArrayList<int[]> xResults, ArrayList<int[]> yResults){
        int points = 0;
        if(turn > 0){
            for (int[] result : xResults) {
                points += Math.abs(result[3] - result[1]);
            }
            for (int[] result : yResults) {
                points += Math.abs(result[2] - result[0]);
            }
        }
        return points;
    }

    /**
     * Refresca el tablero hasta que no haya combinaciones de joyas.
     */
    public void refreshBoardGeneral(){
        ArrayList<int[]> xResults = validateJewelsX();
        ArrayList<int[]> yResults = validateJewelsY();
        while(xResults.size() > 0 || yResults.size() > 0){
            refreshBoard(xResults, yResults);
            if(turn > 0){
                players.get(turn).addPoints(calculatePoints(xResults, yResults) + 1);
            }
            xResults = validateJewelsX();
            yResults = validateJewelsY();
        }
    }

    /**
     * Obtiene el turno actual.
     * 
     * @return Turno actual.
     */
    public byte getTurn(){
        return turn;
    }

    /**
     * Obtiene el HashMap que contiene a los jugadores del juego Vintage.
     * 
     * @return HashMap de jugadores.
     */
    public HashMap<Byte, Player> getPlayers(){
        return players;
    }
    
    /**
     * Reinicia el turno al jugador 1.
     */
    public void setTurno(){
        turn = 1;
    }

    /**
     * Verifica si un movimiento es válido.
     * 
     * @param xPos Posición x inicial.
     * @param yPos Posición y inicial.
     * @param newXPos Nueva posición x.
     * @param newYPos Nueva posición y.
     * @return true si el movimiento es válido, false de lo contrario.
     */
    public boolean validMove(int xPos, int yPos, int newXPos, int newYPos){
        if (xPos < 0 || xPos >= vintageBoard.length || yPos < 0 || yPos >= vintageBoard[0].length) {
            return false;
        }
        int diferenciaX = Math.abs(newXPos - xPos);
        int diferenciaY = Math.abs(newYPos - yPos);
        if(diferenciaX == 1 && diferenciaY == 1){
            return false;
        }
        return diferenciaX <= 1 && diferenciaY <= 1 ;
    }
    
    /**
     * Obtiene la matriz del tablero Vintage.
     * 
     * @return Matriz del tablero.
     */
    public char[][] returnBoard(){
        return vintageBoard;
    }

    /**
     * Obtiene un carácter aleatorio del conjunto de colores disponibles.
     * 
     * @return Carácter aleatorio.
     */
    private char getRandomChar(){
        return colors[random.nextInt(0, colors.length)];
    }

    /**
     *Inicializa el tablero Vintage con caracteres aleatorios.
     */
    private void getRandomBoard(){
        vintageBoard = new char[dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j< dimension; j++){
                vintageBoard[i][j] = getRandomChar();
            }
        }
    }

    /**
     *Establece el turno del jugador siguiente.
     */
    private void setTurn_(){
        if(turn == 1){
                turn = 2;
            }else{
                turn = 1;
        }
    }
    /**
     * Inicializa el tablero binario lleno de ceros y unos (1 significa que se hizo una combinación de joyas).
     */
    private void getBinaryVintageBoard(){
        binaryVintageBoard = new byte[dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j< dimension; j++){
                binaryVintageBoard[i][j] = 0;
            }
        }
    }
    
    /**
     * Returns the matrix binaryVintageBoard
     * @return binaryVintageBoard
     */
    public byte[][] returnBinaryVintageBoard(){
        return binaryVintageBoard;
    }    

    /**
     * Refresca el tablero en cada coordenada.
     * 
     * @param xResults Lista de resultados en la coordenada x.
     * @param yResults Lista de resultados en la coordenada y.
     */
    public void refreshBoard(ArrayList<int[]> xResults, ArrayList<int[]> yResults){
        refreshXBoard(xResults);
        refreshYBoard(yResults);
    }


    /**
     * Actualiza el tablero en la coordenada x basado en las combinaciones de joyas.
     * 
     * @param xResults Lista de resultados en la coordenada x.
     */
    public void refreshXBoard(ArrayList<int[]> xResults) {
        if (xResults.size() > 0) {
            for (int[] result : xResults) {
                int xStart = result[1];
                int xEnd = result[3];
                int yPosition = result[0];
                for(int i = xStart; i <= xEnd; i++){
                    updateBinaryVintageBoard(yPosition,i);
                    moveElementToTheTop(yPosition, i, 0);
                } 
            }
        }
    }

    /**
     * Refreshes the board based on the contigous jewels 
     * @param yResults
     */
     public void refreshYBoard(ArrayList<int[]> yResults) {
         if (yResults.size() > 0) {
             for (int[] result : yResults) {
                 int start = result[0];
                 int end = result[2];
                 int column = result[1];

                 for (int i = start; i <= end; i++) {
                    if(turn != 0){
                        updateBinaryVintageBoard(i,column);
                    }
                    moveElementToTheTop(i, column, i - start);
                 }  
             }
         }
    }
    
    /**
     * Updates the binaryVintageBoard
     * @param x
     * @param y
     */
    private void updateBinaryVintageBoard(int x, int y){
        if(binaryVintageBoard[x][y] == 0){
            binaryVintageBoard[x][y] = 1;
            filledCells++;
        }
    }

    /**
     * Moves an element of the vintageBoard to the top, and changes it to a random character
     * @param x
     * @param y
     */
    public void moveElementToTheTop(int x, int y, int limit){
        if(limit >= 0 && limit < vintageBoard.length){
            char selectedChar = vintageBoard[x][y];
            for(int i = x - 1; i >= limit; i--){
                char nextChar = vintageBoard[i][y];
                vintageBoard[i][y] = selectedChar;
                vintageBoard[i + 1][y] = nextChar;
            }
            vintageBoard[limit][y] = getRandomChar();
        }
    }
    
    /**
     * Valida las agrupaciones de joyas contiguas en la coordenada X.
     * 
     * @return Una lista de arreglos con las coordenadas iniciales y finales de las agrupaciones de joyas.
     */
    public ArrayList<int[]> validateJewelsX() {
        ArrayList<int[]> results = new ArrayList<>();
        for (int row = 0; row < vintageBoard.length; row++) {
            int consecutiveCount = 1;
            char currentCharacter = vintageBoard[row][0];
            for (int column = 1; column < vintageBoard[row].length; column++) {
                char nextCharacter = vintageBoard[row][column];
                if (currentCharacter == nextCharacter) {
                    consecutiveCount++;
                } else {
                    if (consecutiveCount >= 3) {
                        int[] result = {row, column - consecutiveCount, row, column - 1};
                        results.add(result);
                    }
                    consecutiveCount = 1;
                    currentCharacter = nextCharacter;
                }
            }
            if (consecutiveCount >= 3) {
                int[] result = {row, vintageBoard[row].length - consecutiveCount, row, vintageBoard[row].length - 1};
                results.add(result);
            }
        }
        return results;
    }

    /**
     * Valida las agrupaciones de joyas contiguas en la coordenada Y.
     * 
     * @return Una lista de arreglos con las coordenadas iniciales y finales de las agrupaciones de joyas.
     */
    private ArrayList<int[]> validateJewelsY() {
        ArrayList<int[]> results = new ArrayList<>();
        for (int column = 0; column < vintageBoard[0].length; column++) {
            int consecutiveCount = 1;
            char currentCharacter = vintageBoard[0][column];
            for (int row = 1; row < vintageBoard.length; row++) {
                char nextCharacter = vintageBoard[row][column];
                if (currentCharacter == nextCharacter) {
                    consecutiveCount++;
                } else {
                    if (consecutiveCount >= 3) {
                        int[] result = {row - consecutiveCount, column, row - 1, column};
                        results.add(result);
                    }
                    consecutiveCount = 1;
                    currentCharacter = nextCharacter;
                }
            }
            if (consecutiveCount >= 3) {
                int[] result = {vintageBoard.length - consecutiveCount, column, vintageBoard.length - 1, column};
                results.add(result);
            }
        }
        return results;
    }
    
    /**
     * Imprime la matriz del tablero vintage en la consola.
     */
    
    public void printVintageMatrix(){
        for (int i = 0; i < dimension; i++) {
            // Recorre las columnas
            for (int j = 0; j < dimension; j++) {
                // Imprime el elemento en la posición i, j
                System.out.print(vintageBoard[i][j] + " ");
            }
            // Salto de línea al final de cada fila
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * @return
     */
    public int returnWinner(){
        int winner = -1;
        int pointsPlayer1 = players.get((byte) 1).getPoints();
        int pointsPlayer2 = players.get((byte) 2).getPoints();
        if (pointsPlayer1 >  pointsPlayer2){
            return 1;
        }
        else if (pointsPlayer2 >  pointsPlayer1){
            return  2;
        }
        else if(pointsPlayer1 == pointsPlayer2){
            return 0;
        }
        return winner;
    }
    
    /**
     * Validates if the game has ended.
     */
    public void vintageFinished() {
        int size = dimension * dimension;
        if (filledCells==size) {
            vintageFinished = true;
        } else {
            vintageFinished = false;
        }
    }

    /**
     * Returns vintageFinished
     */
    public boolean returnVintageFinished(){
        return vintageFinished;
    }
    
    
}
