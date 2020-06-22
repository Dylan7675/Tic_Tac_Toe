/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.TEXT_CURSOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.time.Clock.system;

import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import tic_tac_toe.Difficulty;
import tic_tac_toe.Game;
import userInterface.Player;
import static userInterface.Player.getGrid;
import userInterface.tictacUI;

public class TicTacClient 
{
   //declares
   private static tictacUI parent ;
   public static int currentPlayer = 0;
   private static Player player;
   public static int rowClick;
   public static int colClick;
   public static int playerScore = 0;
   public static int compScore = 0;
   public static int playedCount = 1;
   
   
   public TicTacClient(Player inPlayer,tictacUI inParent)
   {
      //sets parent to tictacUI class and player to Player class
       parent = inParent;
       player = inPlayer;
   }
   
   static class Move
   {
	   int row, col;
   }

public static void setWinStatus()
{
	Game.setVertWin();
    Game.setHorzWin();
    Game.setDiagWin();
}
   
public static boolean checkForWin()
{
    setWinStatus();
	
    if(Game.isGameOver()==true){
        if(currentPlayer == 0){   
            JOptionPane.showMessageDialog(parent,"You won the game!");
        }
        else if(currentPlayer == 1){
            JOptionPane.showMessageDialog(parent,"Computer won the game!");
        }
        endGame();
    }
    else{ return(false);}
    return(true);
}

public static void endGame()
{
    if(Game.setStalemate() != true){
        if(currentPlayer == 0){
            playerScore += 1;
        }
        else{
            compScore += 1;
        }
    } 
    tictacUI.playerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1),
            "     Score:                        Player-"+playerScore+"                  Computer-"+compScore, TEXT_CURSOR, ICONIFIED,tictacUI.playFont,Color.WHITE));
    
    reset();
    
}

public static void reset()
{
    //reset everything
    for(int row = 0; row < 3; row++){
        for(int col = 0; col < 3; col++){
          Player.getGrid()[row][col].setText("");  
        }
    }
    
    Game.resetWinStatus();
    Game.stalemate = false;
    Game.turnCount = 1;
    playedCount = 1;
    
}

private static Move findBestMove()
{ 
    int bestScore = -1000;
    Move bestMove = new Move();
    bestMove.row = 0;
    bestMove.col = 0;
    
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
        	if (getGrid()[i][j].getText().equals(""))
        	{
        		getGrid()[i][j].setText(tictacUI.compLetter);        		
        		int score = minimax(0, false);
        		getGrid()[i][j].setText("");
        		
        		if (score > bestScore)
        		{
        			bestScore = score;
        			bestMove.row = i;
        			bestMove.col = j;
        		}
        	}
        }
    }
    return bestMove ;
}

private static int minimax(int depth, Boolean isMax)
{
	currentPlayer = (isMax) ? 0 : 1;
	setWinStatus();
	
	if (Game.isGameOver() == true && currentPlayer == 1)
	{
		   Game.resetWinStatus();
	       return (10);
	}
	
	if (Game.isGameOver() == true && currentPlayer == 0)
   {
	   Game.resetWinStatus();
       return (-10);
   }
   
   if(Game.setStalemate() == true)
   {
	  Game.resetWinStatus();
      return (0);
   }
   
   if (isMax)
   {
	   int bestScore = -1000;
	   
	   for (int i = 0; i < 3; i++)
       {
           for (int j = 0; j < 3; j++)
           {        	   
               if (getGrid()[i][j].getText().equals(""))
               {
            	   getGrid()[i][j].setText(tictacUI.compLetter);
                   int score = minimax(depth + 1, false);
                   getGrid()[i][j].setText("");
                   bestScore = Math.max(score, bestScore);
               }
           }
       }
       return bestScore;
   }
   
   else
   {
	   int bestScore = 1000;
	   
	   for (int i = 0; i < 3; i++)
       {
           for (int j = 0; j < 3; j++)
           {
               if (getGrid()[i][j].getText().equals(""))
               {
            	   getGrid()[i][j].setText(tictacUI.playerLetter);
            	   int score = minimax(depth + 1, true);
            	   getGrid()[i][j].setText("");
            	   bestScore = Math.min(score, bestScore);
               }
           }
       } 
       return bestScore;
   }
}

//Computer move selection
	
public static void computerPick()
{
	
	Random random = new Random();
	
    if(Difficulty.hard == true){
    	currentPlayer = 1;
    	Move optimalMove = findBestMove();
    	currentPlayer = 1;
    	getGrid()[optimalMove.row][optimalMove.col].setText(tictacUI.compLetter);
        getGrid()[optimalMove.row][optimalMove.col].setForeground(Color.red);
        checkForWin();
        stalemateEnd();
        
    }
    
    if(Difficulty.intermediate == true){
    rowClick = random.nextInt(Player.getRows());
    colClick = random.nextInt(Player.getCols());
    currentPlayer = 1;
    
    if(getGrid()[1][1].getText().isEmpty())
    {
    	getGrid()[1][1].setText(tictacUI.compLetter);
        getGrid()[1][1].setForeground(Color.red);
        checkForWin();
        stalemateEnd();
        return;
    }
    
    //Blocks middle edge selection between 2 corners
    for(int i=0; i<=2; i+=2)
    {
    	for(int j=2; j>=0; j-=2)
    	{
    		if(i != j)
    		{
    			if(getGrid()[i][i].getText() != ""
    					&& getGrid()[i][i].getText() == getGrid()[j][i].getText()
    					&& getGrid()[1][i].getText() == "")
    			{
    				getGrid()[1][i].setText(tictacUI.compLetter);
    		        getGrid()[1][i].setForeground(Color.red);
    		        checkForWin();
    		        stalemateEnd();
    		        return;
    			}
    			
    			else if(getGrid()[i][i].getText() != ""
    					&& getGrid()[i][i].getText() == getGrid()[i][j].getText()
    					&& getGrid()[i][1].getText() == "")
    			{
    				getGrid()[i][1].setText(tictacUI.compLetter);
    		        getGrid()[i][1].setForeground(Color.red);
    		        checkForWin();
    		        stalemateEnd();
    		        return;
    			}
    		}
    	}
    }
    
    //Blocks corner win selection
    for (int i=0; i<3; i++)
    {
    	if(getGrid()[i][2].getText() == ""
    	   && getGrid()[i][0].getText() == getGrid()[i][1].getText()
    	   && getGrid()[i][0].getText() != "")
    	{
    		getGrid()[i][2].setText(tictacUI.compLetter);
	        getGrid()[i][2].setForeground(Color.red);
	        checkForWin();
	        stalemateEnd();
	        return;
    	}
    	else if(getGrid()[i][0].getText() == ""
    	        && getGrid()[i][1].getText() == getGrid()[i][2].getText()
    	    	&& getGrid()[i][1].getText() != "")
    	    {
    	            getGrid()[i][0].setText(tictacUI.compLetter);
    		        getGrid()[i][0].setForeground(Color.red);
    		        checkForWin();
    		        stalemateEnd();
    		        return;
    	    }
    	else if(getGrid()[2][i].getText() == ""
    	        && getGrid()[0][i].getText() == getGrid()[1][i].getText()
    	    	&& getGrid()[0][i].getText() != "")
    	    {
    	            getGrid()[2][i].setText(tictacUI.compLetter);
    		        getGrid()[2][i].setForeground(Color.red);
    		        checkForWin();
    		        stalemateEnd();
    		        return;
    	    }
    	else if(getGrid()[0][i].getText() == ""
    	        && getGrid()[1][i].getText() == getGrid()[2][i].getText()
    	    	&& getGrid()[1][i].getText() != "")
    	    {
    	            getGrid()[0][i].setText(tictacUI.compLetter);
    		        getGrid()[0][i].setForeground(Color.red);
    		        checkForWin();
    		        stalemateEnd();
    		        return;
    	    }
    }
    
     // choosing corners
    if(getGrid()[0][0].getText().isEmpty()||
        getGrid()[0][2].getText().isEmpty()||
        getGrid()[1][1].getText().isEmpty()||
        getGrid()[2][0].getText().isEmpty()||
        getGrid()[2][2].getText().isEmpty())   
     {  
        if(getGrid()[rowClick][colClick].equals(getGrid()[0][0])||
           getGrid()[rowClick][colClick].equals(getGrid()[0][2])||
           getGrid()[rowClick][colClick].equals(getGrid()[1][1])||
           getGrid()[rowClick][colClick].equals(getGrid()[2][0])||
           getGrid()[rowClick][colClick].equals(getGrid()[2][2]))
        {
            //checking if said corner is empty
            if((getGrid()[rowClick][colClick].getText()).equals(tictacUI.playerLetter)||
               (getGrid()[rowClick][colClick].getText()).equals((tictacUI.compLetter)))
                {
                    computerPick(); 
                }
     
            else
               {
                getGrid()[rowClick][colClick].setText(tictacUI.compLetter);
                getGrid()[rowClick][colClick].setForeground(Color.red);
                checkForWin();
                stalemateEnd(); 
            }
         }
        else
        {
            computerPick();
        }
     }
        // if corners are no longer empty, continues on to regular selection
        else if((getGrid()[rowClick][colClick].getText()).equals(tictacUI.playerLetter)||
        (getGrid()[rowClick][colClick].getText()).equals((tictacUI.compLetter)))
        
            {
                computerPick(); 
            }
     
     else
    {
        getGrid()[rowClick][colClick].setText(tictacUI.compLetter);
        getGrid()[rowClick][colClick].setForeground(Color.red);
        checkForWin();
        stalemateEnd();          
    }
      
  
  }
  if(Difficulty.beginner == true){
    
    rowClick = random.nextInt(Player.getRows());
    colClick = random.nextInt(Player.getCols());  
      
    if((getGrid()[rowClick][colClick].getText()).equals(tictacUI.playerLetter)||
        (getGrid()[rowClick][colClick].getText()).equals((tictacUI.compLetter)))
    {
       computerPick(); 
    }
    
    else
    {
        currentPlayer = 1;
        getGrid()[rowClick][colClick].setText(tictacUI.compLetter);
        getGrid()[rowClick][colClick].setForeground(Color.red);
        checkForWin();
        stalemateEnd();          
    }
}
  
} 

public static void stalemateEnd()
{
	if(Game.setStalemate() == true)
    {
        JOptionPane.showMessageDialog(parent,"There has been a Stalemate!\n Game Over!");
        endGame();
    } 	
}

}