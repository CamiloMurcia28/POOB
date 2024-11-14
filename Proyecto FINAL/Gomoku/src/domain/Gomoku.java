package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;

import java.awt.Color;
import java.util.HashMap;

/**
 * Class for Gomoku
 *
 * @author Jeisson Casallas y Camilo Murcia
 * @version 1.0
 */
public class Gomoku{
    private HashMap<String, Color> jugadores;
    
    //Tablero
    private List<Piedra> listPiedras;
    private List<Piedra> listBlackStones;
    private List<Piedra> listWhiteStones;
    private int numberOfCols;
    private int numberOfRows;
    private boolean isThereAWinner;
    private List<Integer> chainsFound;
    private Color emptyColor = new Color(153,76,0);
    

    public Gomoku(int tamTablero){
        jugadores = new HashMap<>();
        jugadores.put("Jugador 1",Color.BLACK);
        jugadores.put("Jugador 2",Color.WHITE);
        this.isThereAWinner = false;
        this.listPiedras = new ArrayList<Piedra>();
        this.listCasillas = new ArrayList<Casilla>();
        this.listBlackStones = new ArrayList<Piedra>();
        this.listWhiteStones = new ArrayList<Piedra>();
        this.chainsFound = new ArrayList<Integer>();
        this.numberOfCols = tamTablero;this.numberOfRows = tamTablero;
        for (int row = 1; row <= tamTablero; row++) {
            for (int col = 1; col <= tamTablero; col++) {
                this.listPiedras.add(new PiedraNormal(row, col));
            }
        }

        for (int row = 1; row <= tamTablero; row++) {
            for (int col = 1; col <= tamTablero; col++) {
                this.listCasillas.add(new Casilla(row, col));
            }
        }
    }

    public HashMap<String, Color> getJugadores(){
        return this.jugadores;
    }

    public void setColor(String name, Color selectedColor){
        if(jugadores.get(name) != null){
            jugadores.replace(name, selectedColor);
        }
    }
    
    public boolean isThereAFiveChain(Color color){
        this.collectHorizontalChains(color);
        this.collectVerticalChains(color);
        this.collectDiagonallyRightChains(color);
        this.collectDiagonallyLeftChains(color);
            
        if(this.chainsFound.size()>0){
            if (Collections.max(this.chainsFound) == 5){
                this.isThereAWinner = true;

            }else{
                //
            }
        }
        this.chainsFound.clear();
        return this.isThereAWinner;
    }
    
    private void collectHorizontalChains(Color color){
        int count = 0;
        for (int row = 1; row <= this.numberOfRows; row++) {
            for (int column = 1; column <= this.numberOfCols+1 ; column++){
                Piedra p = this.getPiedraRowCol(row,column);
                if (p != null && p.getStoneColor().equals(color) && count < 5)
                    count += p.getPuntuacion();
                else if(count >= 3){
                    this.chainsFound.add(count);
                    count = 0;
                }else{
                    count = 0;
                }
            }
        }
    }
    
    private void collectVerticalChains(Color color){
        int count = 0;
        for (int row = 1; row <= this.numberOfRows; row++) {
            for (int column = 0; column <= this.numberOfCols +1; column++){
                Piedra p = this.getPiedraRowCol(column,row);
                if (p != null && p.getStoneColor().equals(color) && count < 5){
                    count += p.getPuntuacion();
                }
                else if(count >= 3){
                    this.chainsFound.add(count);
                    count = 0;
                }else{
                    count = 0;
                }
            }
        }
    }

    /*
     * *
     *  *
     *   *
     *    *
     *     *
     */
    private void collectDiagonallyRightChains(Color color){
        int count = 1;
        for (int row = 1; row < this.numberOfRows; row++) {
            int rowAux = row;
            for (int column = 0; column < this.numberOfCols; column++) {
                Piedra p = this.getPiedraRowCol(column,row);
                if (rowAux <= this.numberOfRows){
                    if (p != null && this.getPiedraRowCol(rowAux,column).getStoneColor().equals(color)&& count < 5){
                        count += p.getPuntuacion();
                        rowAux++;
                    }else if(count >= 3){
                        this.chainsFound.add(count);
                        count = 0;
                    }else{
                        rowAux = row;
                        count = 0;
                    }
                }
            }
            for (int column = this.numberOfCols; column >= 0; column--) {
                Piedra p = this.getPiedraRowCol(column,row);
                if (rowAux <= this.numberOfRows){
                    if (p != null && this.getPiedraRowCol(rowAux,column).getStoneColor().equals(color) && count < 5){
                        count += p.getPuntuacion();
                        rowAux++;
                    }else if(count >= 3){
                        this.chainsFound.add(count);
                        count = 0;
                    }else{
                        rowAux = row;
                        count = 0;
                    }
                }
            }
        }
    }
    
/*
     *      *
     *     *
     *    *
     *   *
     *  *
*/

    private void collectDiagonallyLeftChains(Color color){
        int count = 1;
        int rowAux = 0;
        for (int row = 1; row <= this.numberOfRows; row++){
            rowAux = row;
            for (int column = 0; column < this.numberOfCols; column++) {
                Piedra p = this.getPiedraRowCol(column,row);
                if (rowAux <= this.numberOfRows){
                    if (p!= null && this.getPiedraRowCol(rowAux,column).getStoneColor().equals(color) && count < 5){
                        count += p.getPuntuacion();
                        rowAux++;
                    }else if(count >= 3){
                        chainsFound.add(count);
                        count = 0;
                    }else{
                        isThereAWinner = false;
                        rowAux = row;
                        count = 0;
                    }
                }
            }
            for (int column = this.numberOfCols; column >= 0; column--) {
                Piedra p = this.getPiedraRowCol(column,row);
                if (rowAux <= this.numberOfRows){
                    if (p != null && this.getPiedraRowCol(rowAux,column).getStoneColor().equals(color) && count < 5){
                        count += p.getPuntuacion();
                        rowAux++;
                    }else if(count >= 3){
                        this.chainsFound.add(count);
                        count = 0;
                    }else{
                        rowAux = row;
                        count = 0;
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param row
     * @param col
     * @return
     */

    private Piedra getPiedraRowCol(int row, int col){
        for (Piedra p : listPiedras) {
            if (p.getPositionX() == row && p.getPositionY() == col)
                return p;
            }
        return null;
    }
    
    public void place(int row, int col, String turno, Color color){
        Piedra p = this.getPiedraRowCol(row,col);
        p.ponerPiedraEncima(color);
        if(turno.equals("Black")){
            this.listBlackStones.add(p);
        }
        if(turno.equals("White")){
            this.listWhiteStones.add(p);
        }
    }

    public JButton ponerPiedra(int row, int col, String turno, Color color){
        Piedra p = this.getPiedraRowCol(row,col);
        p.ponerPiedraEncima(color);
        if(turno.equals("Black")){
            this.listBlackStones.add(p);
        }
        if(turno.equals("White")){
            this.listWhiteStones.add(p);
        }
        return p;
    }
    
    public void cleanTheBoard() {
        for (Piedra p : this.listPiedras){
            p.limpiarPiedra();
        }       
        for(Casilla c : this.listCasillas){
            c.limpiarCasilla();
        }
        this.listBlackStones.clear();
        this.listWhiteStones.clear();
        this.chainsFound.clear();
        this.isThereAWinner = false;
    }
     
    //Conexion con piedra
    public boolean almenosUnaPiedraEmpty(){
        boolean ban = false;
        for (Piedra p : this.listPiedras){
            if(p.getStoneColor().equals(emptyColor)){
                ban = true;
            }
        }
        return ban;
    }
    
    public void cleanOnePiedra(int row, int col) {
        Piedra p = getPiedraRowCol(row, col);
        p.limpiarPiedra();
    }
    
    public boolean movimientoValido(int row, int col){
        return (this.getPiedraRowCol(row,col).getStoneColor().equals(emptyColor));
    }

    public List<Piedra>getListPiedras() {
        return listPiedras;
    }


    //Casillas
    private List<Casilla> listCasillas;
    /**
     * 
     * @return
     */
    public boolean alMenosUnaCasillaVacia(){
        boolean bandera = false;
        for(Casilla c : this.listCasillas){
            if(c.getOcupada()){
                bandera = true;
            }
        }
        return bandera;
    }
    /**
     * 
     * @return
     */
    public List<Casilla> getListCasillas(){
        return this.listCasillas;
    }

    private Casilla getCasillaRowCol(int row, int col){
        for (Casilla c : listCasillas) {
            if (c.getPositionX() == row && c.getPositionY() == col)
                return c;
            }
        return null;
    }

    public boolean movimientoCasillaValido(int row, int col){
        return (!this.getCasillaRowCol(row,col).getOcupada());
    }

    public boolean almenosUnaCasillaVacia(){
        boolean ban = false;
        for (Casilla c : this.listCasillas){
            if(c.getOcupada()){
                ban = true;
            }
        }
        return ban;
    }
    public boolean finalizado(){
        return !almenosUnaCasillaVacia();
    }
    
    //Juego
    
    public boolean isFinished(){
        return !almenosUnaPiedraEmpty();
    }
    
    public void nuevoJuego(){
        cleanTheBoard();
    }
    
    public boolean getWinner(Color color){
        return isThereAFiveChain(color);
    }
    

}
