/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tic_tac_toe.Game;
import tic_tac_toe.TicTacClient;

/**
 *
 * @author dylan laptop
 */
public class Player {

    //declares
    public static JButton buttonGrid[][];
    private tictacUI parent;
    private final static int rows = 3;
    private final static int cols = 3;
    private GridListener gridListener;
    
    //sets parent to tictacUI class and calls init methods
    public Player(String name,tictacUI inParent) {
        parent = inParent;
        initObjects();
        initComponents();
    }

    private void initObjects() {
        gridListener = new GridListener();
    }
    
    private void initComponents() {
        buttonGrid = new JButton[getRows()][getCols()];
        
        for(int row = 0; row < getRows();row++)
        {
            for (int col = 0;col < getCols();col++)
            {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].putClientProperty("row",row);
                buttonGrid[row][col].putClientProperty("col",col);
                setListener(buttonGrid[row][col], gridListener);
            }
        }
        
    }
    
    public void setListener(JButton inButton, ActionListener inListener)
    {
        inButton.addActionListener(inListener);
    }

    public static JButton[][] getGrid()
    {
        return buttonGrid;
    }
    
    public static int getRows() 
    {
        return rows;
    }

    public static int getCols() 
    {
        return cols;
    }
    
    //play selection and control flow
    public class GridListener implements ActionListener
    {	
        public void actionPerformed(ActionEvent e) 
        {
            tictacUI.x.setEnabled(false);
            tictacUI.o.setEnabled(false);
            if( e.getSource() instanceof JButton) 
            {               
                JButton button = (JButton)e.getSource();
                int rowClick = (int)button.getClientProperty("row");
                int colClick = (int)button.getClientProperty("col");
              
                if((getGrid()[rowClick][colClick].getText()).equals(tictacUI.playerLetter)||
                    (getGrid()[rowClick][colClick].getText()).equals((tictacUI.compLetter)))
                {
                   JOptionPane.showMessageDialog(parent,"Selection is not Valid, choose another space!"); 
                }
                else{
                TicTacClient.currentPlayer = 0;
                getGrid()[rowClick][colClick].setText(tictacUI.playerLetter);
                getGrid()[rowClick][colClick].setForeground(Color.blue);
                
             
                
                Game.staleCount +=1;
                System.out.println(Game.staleCount);
                
                
                TicTacClient.checkForWin();
                
                if(TicTacClient.checkForWin() == false){
                    
                    if(Game.staleCount >= 9)
                    {
                        Game.staleCount += 1; // can still win on last move must, add to be able to compare if there is a win or stalemate
                        JOptionPane.showMessageDialog(parent,"There has been a Stalemate!\n Game Over!");
                        TicTacClient.endGame();
                    } 
                    
                    
                    TicTacClient.computerPick();
                    
                    
                	}
                
                }
        }
    } 
}
}