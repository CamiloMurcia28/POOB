import java.awt.Polygon;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
import javax.swing.JOptionPane;

/**
 * Replicate Replicate
 * 
 * @author Camilo Murcia Jeisson Casallas
 * @version 2.0(9 september 2023)
 */
public class SelfAssembly
{
    private int rows;
    private int columns;
    private int posXActual;
    private int posYActual;
    private int posXNueva;
    private int posYNueva;
    private int xPositionCell;
    private int yPositionCell;
    private String color = "magenta";
    private String color_2;
    private static int[][] gameState;
    private static int[][] newGameState;
    
    /**
     * Ciclo 1, implementacion metodos para la creacion del tablero y su funcionamiento
     */
    
    /**
     * Set dimensions for the cell board
     * @param nRows number of rows of the cell board
     * @param nColumns number of columns of the cell board
     */
    
    public void setDimension(int nRows, int nColumns){
        this.rows = nRows;
        this.columns = nColumns;
        this.color_2 = color;
        this.color = "black";
        MatrizDeCeros(rows + 1, columns + 1);
        for (int i = 0; i <= rows; i++){
            for(int j = 0; j <= columns; j++){
                createHexagonCells(i , j);
            }
        }
        
        this.color = color_2;
    }
    
    private void MatrizDeCeros(int filas, int columnas) {
        // Inicializar la matriz con ceros en el constructor
        gameState = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                gameState[i][j] = 0;
            }
        }
    }
    
    private int getValorMatriz(int fila, int columna) {
        if (fila >= 0 && fila < gameState.length && columna >= 0 && columna < gameState[0].length) {
            return gameState[fila][columna];
        } else {
            return -1;
        }
    }
    
    public void Replicate(){
        int filas = rows;
        int columnas = columns;
        filas += 1;
        columnas += 1;
        int suma;
        suma = 0;
        
        newGameState = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                newGameState[i][j] = gameState[i][j];
            }
        }
        for (int i = 0; i <= rows; i++){
            for(int j = 0; j <= columns; j++){
                createHexagonCells(i , j);
            }
        }
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                suma = getValorMatriz(x-1,y-1) + 
                       getValorMatriz(x  ,y-1) +
                       getValorMatriz(x+1,y-1) + 
                       getValorMatriz(x-1,y  ) +
                       getValorMatriz(x+1,y  ) +
                       getValorMatriz(x-1,y+1) +
                       getValorMatriz(x  ,y+1) +
                       getValorMatriz(x+1,y+1);
                //REGLA 1: UNA CELULA MUERTA CON 3 VECINAS VIVAS, "REVIVE"
                if (getValorMatriz(x,y) == 0 && suma == 3){
                    newGameState[x][y] = 1;
                }
                //REGLA 2: UNA CELULA VIVA CON MENOS DE 2 O MAS DE 3 VECINAS VIVAS,"MUERE"
                else if(getValorMatriz(x,y) == 1 && (suma < 2 || suma > 3)){
                    newGameState[x][y] = 0;
                }
                
                if(newGameState[x][y] == 0){
                    createCellDied(x,y);
                } else{
                    createCellAlive(x,y);
                }
            }
        }
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                gameState[i][j] = newGameState[i][j];
            }
        }
        
        
    }
    
    
    private void createCellDied(int row, int col) {
        Canvas canvas = Canvas.getCanvas();
        int cellSize = 15;
        int x = col * (int) (cellSize * Math.sqrt(3)) + (row % 2) * (int) (cellSize * Math.sqrt(3)) / 2;
        int y = row * (int) (1.5 * cellSize);
        
        if( col > columns){
            setCoordinates(row, columns);
            startingPosition(row, getYCoordinates());
        } else if(row < 0){
            setCoordinates(rows, columns);
            setValorMatriz(rows, columns, 1);
        } else if(row > rows){
            setCoordinates(rows, col);
            startingPosition(getXCoordinates(), col);
        } else{
            Hexagon hexagon = new Hexagon();
            hexagon.changeSize(cellSize);
            hexagon.moveHorizontal(x);
            hexagon.moveVertical(y);
            hexagon.changeColor("black");
            hexagon.makeVisible();
            setCoordinates(row, col);
        }
        
    }
    
    private void createCellAlive(int row, int col) {
        Canvas canvas = Canvas.getCanvas();
        int cellSize = 15;
        int x = col * (int) (cellSize * Math.sqrt(3)) + (row % 2) * (int) (cellSize * Math.sqrt(3)) / 2;
        int y = row * (int) (1.5 * cellSize);
        
        if( col > columns){
            setCoordinates(row, columns);
            startingPosition(row, getYCoordinates());
        } else if(row < 0){
            setCoordinates(rows, columns);
            setValorMatriz(rows, columns, 1);
            setValorMatriz(rows, columns, 0);
        } else if(row > rows){
            setCoordinates(rows, col);
            startingPosition(getXCoordinates(), col);
        } else{
            Hexagon hexagon = new Hexagon();
            hexagon.changeSize(cellSize);
            hexagon.moveHorizontal(x);
            hexagon.moveVertical(y);
            hexagon.changeColor(color);
            hexagon.makeVisible();
            setCoordinates(row, col);
        }
        
    }

    private void setValorMatriz(int fila, int columna, int valor) {
        this.gameState[fila][columna] = valor;
    }
    
    private void createHexagonCells(int row, int col) {
        Canvas canvas = Canvas.getCanvas();
        int cellSize = 15;
        int x = col * (int) (cellSize * Math.sqrt(3)) + (row % 2) * (int) (cellSize * Math.sqrt(3)) / 2;
        int y = row * (int) (1.5 * cellSize);
        
        if( col > columns){
            setCoordinates(row, columns);
            startingPosition(row, getYCoordinates());
        } else if(row < 0){
            setCoordinates(rows, columns);
            setValorMatriz(rows, columns, 1);
        } else if(row > rows){
            setCoordinates(rows, col);
            startingPosition(getXCoordinates(), col);
        } else{
            Hexagon hexagon = new Hexagon();
            hexagon.changeSize(cellSize);
            hexagon.moveHorizontal(x);
            hexagon.moveVertical(y);
            hexagon.changeColor(color);
            hexagon.makeVisible();
            setCoordinates(row, col);
        }
        
    }
    
    /*
     * Set coordinates for the replicate
     * @param position of the row in the board
     * @param position of the column in the board
     */
    
    private void setCoordinates(int row, int col)
    {
        posXActual = row;
        posYActual = col;
    }
    
    /**
     * Get the x coord. value
     */
    
    private int getXCoordinates()
    {
        return posXActual;
    }
    
    /**
     * Get the y coord. value
     */
    
    private int getYCoordinates()
    {
        return posYActual;
    }
    
    /**
     * Ciclo 2, creacion del hexagono de forma manual y aleatoria
     */
    
    /**
     * Choose an starting position, and draw an hexagon on the board's cell
     * @param row position of the hexagon
     * @param column position of the hexagon
     */
    public void startingPosition(int row, int col)
    {
        Canvas canvas = Canvas.getCanvas();
        int tamano_hex = 15;
        int x = col * (int)(tamano_hex * Math.sqrt(3)) + (row %2) * (int)(tamano_hex * Math.sqrt(3))/2;
        int y = row * (int)(1.5 * tamano_hex);
        
        if(col > columns){
            setCoordinates(row, columns);
            startingPosition(row, getYCoordinates());
            setValorMatriz(row, getYCoordinates(), 1);
        }else if(row < 0){
            setCoordinates(rows, columns);
            setValorMatriz(rows, columns, 1);
        }else if(row > rows){
            setCoordinates(rows, columns);
            startingPosition(getXCoordinates(), col);
            setValorMatriz(getXCoordinates(), col, 1);
        }else{
            Hexagon hexagono = new Hexagon();
            hexagono.changeSize(tamano_hex);
            hexagono.moveHorizontal(x);
            hexagono.moveVertical(y);
            hexagono.changeColor("magenta");
            hexagono.makeVisible();
            setCoordinates(row, col);
            setValorMatriz(row, col,1);
        }
    }
    
    /**
     * Choose a random starting position, and draw an hexagon on the board's cell
     */
    
    public void randomStartingPosition()
    {
        Canvas canvas = Canvas.getCanvas();
        int tamano_hex = 15;

        int row = (int) (Math.random() * rows);
        int col = (int) (Math.random() * columns);
    
        int x = col * (int)(tamano_hex * Math.sqrt(3)) + (row %2) * (int)(tamano_hex * Math.sqrt(3))/2;
        int y = row * (int)(1.5 * tamano_hex);
    
        Hexagon hexagono = new Hexagon();
        
        hexagono.changeSize(tamano_hex);
        hexagono.moveHorizontal(x);
        hexagono.moveVertical(y);
        hexagono.changeColor("green");
        hexagono.makeVisible();
        setCoordinates(row, col);
        setValorMatriz(row, col,1);
        
    }
    
    /**
     * Ciclo 3, implementacion del replicate y sus reflejos
     */

    /**
     * Reflect the replicate vertical 
     */
    
    public void reflectVertical(){
        int filas = gameState.length;
        int columnas = gameState[0].length;
        
        int[][] matrizReflejadaVertical = new int[filas][columnas];
            
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizReflejadaVertical[i][j] = gameState[filas - 1 - i][j];
            }
        }
        
        //limpie
        for (int i = 0; i <= rows; i++){
                for(int j = 0; j <= columns; j++){
                    createHexagonCells(i , j);
                }
            }
        
        //cree reflejado vertical
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                if(matrizReflejadaVertical[x][y] == 0){
                        createCellDied(x,y);
                    } else{
                        createCellAlive(x,y);
                    }
            }
        }
        
        for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    gameState[i][j] = matrizReflejadaVertical[i][j];
                }
        }
    }
    
    /**
     * Reflect the replicate horizontal
     */
    public void reflectHorizontal(){
        int filas = gameState.length;
        int columnas = gameState[0].length;
        
        int[][] matrizReflejadaVertical = new int[filas][columnas];
        
        
            
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizReflejadaVertical[i][j] = gameState[i][columnas - 1 -j];
            }
        }
        
        //limpie
        for (int i = 0; i <= rows; i++){
                for(int j = 0; j <= columns; j++){
                    createHexagonCells(i , j);
                }
            }
        
        //cree reflejado horizontal
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                if(matrizReflejadaVertical[x][y] == 0){
                        createCellDied(x,y);
                    } else{
                        createCellAlive(x,y);
                    }
            }
        }
        
        //Guarde nuevo estado
        for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    gameState[i][j] = matrizReflejadaVertical[i][j];
                }
        }
    }
}
    

