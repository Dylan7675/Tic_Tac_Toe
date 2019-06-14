/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import userInterface.Player;

/**
 *
 * @author Dylan
 */
public class Game {

    //win check bools
    public static boolean vertWin = false;
    public static boolean horzWin = false;
    public static boolean diagWin = false;
    public static boolean stalemate = false;
    public static boolean gameOver = false;

    //stalemate counter
    public static int staleCount = 0;
    
    //move counter
    public static int turnCount = 1;

    //check and set win to true 
    public static boolean setVertWin() {
        if ((Player.buttonGrid[0][0].getText()).equals(Player.buttonGrid[1][0].getText())
                && (Player.buttonGrid[1][0].getText()).equals((Player.buttonGrid[2][0].getText()))
                &&!(Player.buttonGrid[0][0].getText()).equals("")) 
        {
            return(vertWin=true);
        } 
        
        else if ((Player.buttonGrid[0][1].getText()).equals(Player.buttonGrid[1][1].getText())
                && (Player.buttonGrid[1][1].getText()).equals((Player.buttonGrid[2][1].getText()))
                &&!(Player.buttonGrid[0][1].getText()).equals(""))
        {
            return(vertWin=true);
        } 
        
        else if ((Player.buttonGrid[0][2].getText()).equals(Player.buttonGrid[1][2].getText())
                && (Player.buttonGrid[1][2].getText()).equals((Player.buttonGrid[2][2].getText()))
                &&!(Player.buttonGrid[0][2].getText()).equals("")) 
        {
            return(vertWin=true);
        }
        
        else return (vertWin=false);
        
    }

    public static boolean setHorzWin() {
        if ((Player.buttonGrid[0][0].getText()).equals(Player.buttonGrid[0][1].getText())
                && (Player.buttonGrid[0][1].getText()).equals((Player.buttonGrid[0][2].getText()))
                &&!(Player.buttonGrid[0][0].getText()).equals("")) 
        {
            return(horzWin=true);
        }
        
        else if ((Player.buttonGrid[1][0].getText()).equals(Player.buttonGrid[1][1].getText())
                && (Player.buttonGrid[1][1].getText()).equals((Player.buttonGrid[1][2].getText()))
                &&!(Player.buttonGrid[1][0].getText()).equals("")) 
        {
            return(horzWin=true);
        }
        
        else if ((Player.buttonGrid[2][0].getText()).equals(Player.buttonGrid[2][1].getText())
                && (Player.buttonGrid[2][1].getText()).equals((Player.buttonGrid[2][2].getText()))
                &&!(Player.buttonGrid[2][0].getText()).equals("")) 
        {
            return(horzWin=true);
        }
        
        else return(horzWin=false);
    }

    public static boolean setDiagWin() {
        if ((Player.buttonGrid[0][0].getText()).equals(Player.buttonGrid[1][1].getText())
                && (Player.buttonGrid[1][1].getText()).equals((Player.buttonGrid[2][2].getText()))
                &&!(Player.buttonGrid[0][0].getText()).equals("")) 
        {
            return(diagWin=true);
        }
        
        else if ((Player.buttonGrid[0][2].getText()).equals(Player.buttonGrid[1][1].getText())
                && (Player.buttonGrid[1][1].getText()).equals((Player.buttonGrid[2][0].getText()))
                &&!(Player.buttonGrid[0][2].getText()).equals("")) 
        {
            return(diagWin=true);
        }
        
        else return(diagWin=false);
    }
    
    //sets stalemate to true if stalemate happens
    public static boolean setStalemate(){
        if(staleCount == 9){
            return(stalemate=true);
        }
        else return(stalemate=false);
    }
    
    //checks for a win, if true returns game over
    public static boolean isGameOver() {
        if ((vertWin || horzWin || diagWin) == true) {
            gameOver = true;
        }

        return gameOver;
    }
}
