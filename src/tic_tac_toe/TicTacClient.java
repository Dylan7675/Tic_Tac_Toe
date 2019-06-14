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
import static tic_tac_toe.Game.staleCount;

import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import tic_tac_toe.Difficulty;
import tic_tac_toe.Game;
import userInterface.MyThread;
import userInterface.Player;
import static userInterface.Player.getGrid;
import userInterface.tictacUI;
import userInterface.MyThread;
/**
 *
 * @author dylan laptop
 */
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

public static boolean checkForWin()
{
    Game.setVertWin();
    Game.setHorzWin();
    Game.setDiagWin();
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
    //score tracker  
    System.out.println(staleCount);
    if(staleCount != 10){
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
    
    currentPlayer = 0;
    Game.vertWin = false;
    Game.horzWin = false;
    Game.diagWin = false;
    Game.gameOver = false;
    Game.staleCount = 0;
    Game.turnCount = 1;
    playedCount = 1;
    
}

//Computer move selection
public static void computerPick()
{
	
	
	Random random = new Random();
    if(Difficulty.hard == true){
        currentPlayer = 1;
        
   
        if((Game.turnCount == 1 && playedCount == 1)&&
            (getGrid()[0][0].getText().equals(tictacUI.playerLetter)||
            getGrid()[0][2].getText().equals(tictacUI.playerLetter)||
            getGrid()[2][0].getText().equals(tictacUI.playerLetter)||
            getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
        {
            getGrid()[1][1].setText(tictacUI.compLetter);
            playedCount += 1;
        }
        
        if((Game.turnCount == 1&& playedCount == 1)&& 
            (getGrid()[0][1].getText().equals(tictacUI.playerLetter)||
            getGrid()[1][0].getText().equals(tictacUI.playerLetter)||
            getGrid()[1][2].getText().equals(tictacUI.playerLetter)||
            getGrid()[2][1].getText().equals(tictacUI.playerLetter)))
        {
            if(getGrid()[0][1].getText().equals(tictacUI.playerLetter)){
                getGrid()[2][1].setText(tictacUI.compLetter);
                playedCount+=1;
            }
            if(getGrid()[1][0].getText().equals(tictacUI.playerLetter)){
                getGrid()[1][2].setText(tictacUI.compLetter);
                playedCount +=1 ;
            }
            
            if(getGrid()[1][2].getText().equals(tictacUI.playerLetter)){
                getGrid()[1][0].setText(tictacUI.compLetter);
                playedCount+=1;
            }
           
            if(getGrid()[2][1].getText().equals(tictacUI.playerLetter)){
                getGrid()[0][1].setText(tictacUI.compLetter);
                playedCount +=1;
            }
        }
        
        // pressure to either play [0][0] or [2][2]
        if((Game.turnCount == 1&& playedCount == 1) && 
          getGrid()[1][1].getText().equals(tictacUI.playerLetter))
        {
            getGrid()[0][2].setText(tictacUI.compLetter);
            playedCount += 1;
        }
        
        
        
        //edge decisions
        if((Game.turnCount == 2&& playedCount == 2)&&
          (getGrid()[0][1].getText().equals(tictacUI.playerLetter)||
          getGrid()[1][0].getText().equals(tictacUI.playerLetter)||
          getGrid()[1][2].getText().equals(tictacUI.playerLetter)||
          getGrid()[2][1].getText().equals(tictacUI.playerLetter)))
        {
            if(getGrid()[0][1].getText().equals(tictacUI.playerLetter)&&getGrid()[0][2].getText().equals(tictacUI.playerLetter)){
                    getGrid()[0][0].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[0][1].getText().equals(tictacUI.playerLetter)&&getGrid()[0][0].getText().equals(tictacUI.playerLetter)){
                    getGrid()[0][2].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[1][0].getText().equals(tictacUI.playerLetter)&&getGrid()[2][0].getText().equals(tictacUI.playerLetter)){
                    getGrid()[0][0].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[1][0].getText().equals(tictacUI.playerLetter)&&getGrid()[0][0].getText().equals(tictacUI.playerLetter)){
                    getGrid()[2][0].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[1][2].getText().equals(tictacUI.playerLetter)&&getGrid()[2][2].getText().equals(tictacUI.playerLetter)){
                    getGrid()[0][2].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[1][2].getText().equals(tictacUI.playerLetter)&&getGrid()[0][2].getText().equals(tictacUI.playerLetter)){
                    getGrid()[2][2].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[2][1].getText().equals(tictacUI.playerLetter)&&getGrid()[2][2].getText().equals(tictacUI.playerLetter)){
                    getGrid()[2][0].setText(tictacUI.compLetter);
                    playedCount +=1;}
            if(getGrid()[2][1].getText().equals(tictacUI.playerLetter)&&getGrid()[2][0].getText().equals(tictacUI.playerLetter)){
                    getGrid()[2][2].setText(tictacUI.compLetter);
                    playedCount +=1;}
                    
        }
        
        //choosing edge between two of players selected corners
        if((Game.turnCount == 2&& playedCount ==2)&&
        ((getGrid()[0][0].getText().equals(tictacUI.playerLetter)&&(getGrid()[0][2].getText().equals(tictacUI.playerLetter))||getGrid()[2][0].getText().equals(tictacUI.playerLetter))||
        (getGrid()[0][2].getText().equals(tictacUI.playerLetter)&&(getGrid()[0][0].getText().equals(tictacUI.playerLetter))||getGrid()[2][2].getText().equals(tictacUI.playerLetter))||
        (getGrid()[2][0].getText().equals(tictacUI.playerLetter)&&(getGrid()[0][0].getText().equals(tictacUI.playerLetter))||getGrid()[2][2].getText().equals(tictacUI.playerLetter))||
        (getGrid()[2][2].getText().equals(tictacUI.playerLetter)&&(getGrid()[2][0].getText().equals(tictacUI.playerLetter))||getGrid()[0][2].getText().equals(tictacUI.playerLetter))))
        {
            if((getGrid()[0][0].getText().equals(tictacUI.playerLetter)&&(getGrid()[0][2].getText().equals(tictacUI.playerLetter))))
            {
                getGrid()[0][1].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((getGrid()[0][0].getText().equals(tictacUI.playerLetter)&&(getGrid()[2][0].getText().equals(tictacUI.playerLetter))))
            {
                getGrid()[1][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((getGrid()[2][0].getText().equals(tictacUI.playerLetter)&&(getGrid()[2][2].getText().equals(tictacUI.playerLetter))))
            {
                getGrid()[2][1].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((getGrid()[0][2].getText().equals(tictacUI.playerLetter)&&(getGrid()[2][2].getText().equals(tictacUI.playerLetter))))
            {
                getGrid()[1][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
                
            
        }
        
        //choosing a corner if [0][0] was start play
        if((Game.turnCount == 2 && playedCount == 2)&&
          (getGrid()[0][0].getText().equals(tictacUI.playerLetter)||
           getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
        {
            if(getGrid()[0][0].getText().equals(tictacUI.playerLetter)){
               getGrid()[2][2].setText(tictacUI.compLetter);
               playedCount +=1;
            }
            
            if(getGrid()[2][2].getText().equals(tictacUI.playerLetter)){
               getGrid()[0][0].setText(tictacUI.compLetter);
               playedCount+=1;
            }
        
        }
       // handle [1][1] move and [2][0] move in 2nd turn
      
       //turn 3 if edge then corner pick
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[0][1].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[0][0].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
        //top
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[0][1].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[0][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
        
        //left
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[1][0].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[0][0].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[1][0].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
        //right
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[1][2].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[0][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[1][2].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
        //bottom
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[2][1].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[2][0].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            
            if((Game.turnCount == 3 && playedCount == 3)&&(getGrid()[2][1].getText().equals(tictacUI.playerLetter))&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter))
            &&(getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
        

        //Play3 in according to starting with a corner
       if((Game.turnCount == 3&& playedCount ==3)&&(getGrid()[0][0].getText().equals(tictacUI.playerLetter))
         &&(getGrid()[2][0].getText().equals(tictacUI.playerLetter)))
        {
            if((getGrid()[1][2].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            else{
                getGrid()[1][2].setText(tictacUI.compLetter);
                playedCount+=1;
                checkForWin();
            }
        }
           
        if((Game.turnCount == 3&& playedCount == 3)&&(getGrid()[0][0].getText().equals(tictacUI.playerLetter))
         &&(getGrid()[0][2].getText().equals(tictacUI.playerLetter)))
        {
            if((getGrid()[2][1].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[2][2].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            else{
                getGrid()[2][1].setText(tictacUI.compLetter);
                playedCount+=1;
                checkForWin();
            }
        }
        
        if((Game.turnCount == 3&& playedCount == 3)&&(getGrid()[0][2].getText().equals(tictacUI.playerLetter))
         &&(getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
        {
            if((getGrid()[1][0].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            else{
                getGrid()[1][0].setText(tictacUI.compLetter);
                playedCount+=1;
                checkForWin();
            }
        }
        
        if((Game.turnCount == 3&& playedCount == 3)&&(getGrid()[2][0].getText().equals(tictacUI.playerLetter))
         &&(getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
        {
            if((getGrid()[0][1].getText().equals(tictacUI.playerLetter)))
            {
                getGrid()[0][0].setText(tictacUI.compLetter);
                playedCount += 1;
            }
            else{
                getGrid()[0][1].setText(tictacUI.compLetter);
                playedCount+=1;
                checkForWin();
            }
        }
             
       // player in defense of losing on turn 3 from starting [1][1]
       if((Game.turnCount == 3&& playedCount == 3)&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter)
          &&getGrid()[2][2].getText().equals(tictacUI.playerLetter)))
       {
           if(getGrid()[0][1].getText().isEmpty())
           {
               getGrid()[0][1].setText(tictacUI.compLetter);
               playedCount+=1;
               checkForWin();
           }
           else{
               getGrid()[2][1].setText(tictacUI.compLetter);
               playedCount += 1;
           }
       }
       
       if((Game.turnCount == 3&& playedCount == 3)&&(getGrid()[1][1].getText().equals(tictacUI.playerLetter)
          &&getGrid()[0][0].getText().equals(tictacUI.playerLetter)))
       {
           if(getGrid()[1][2].getText().isEmpty())
           {
               getGrid()[1][2].setText(tictacUI.compLetter);
               playedCount+=1;
               checkForWin();
           }
           else{
               getGrid()[1][0].setText(tictacUI.compLetter);
               playedCount += 1;
           }
       }
        
       //turn 4, if empty take winning space, random selection should finish the stalemate 
       //if first move was center
       
       
        Game.turnCount += 1;
        System.out.println("turn count = " + Game.turnCount);
    }
    
    
    
    if(Difficulty.intermediate == true){
    rowClick = random.nextInt(Player.getRows());
    colClick = random.nextInt(Player.getCols());
    currentPlayer = 1;
     
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
                Game.staleCount +=1;
                System.out.println(staleCount);
                checkForWin();
               
                if(Game.staleCount >= 9)
                {
                    staleCount += 1; // can still win on last move must, add to be able to compare if there is a win or stalemate
                    JOptionPane.showMessageDialog(parent,"There has been a Stalemate!\n Game Over!");
                    endGame();
                } 
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
        Game.staleCount +=1;
        System.out.println(staleCount);
        checkForWin();
        
        if(Game.staleCount >= 9)
        {
            staleCount += 1; // can still win on last move must, add to be able to compare if there is a win or stalemate
            JOptionPane.showMessageDialog(parent,"There has been a Stalemate!\n Game Over!");
            endGame();
        }          
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
        Game.staleCount +=1;
        System.out.println(staleCount);
        checkForWin();
        
        if(Game.staleCount >= 9)
        {
            staleCount += 1; // can still win on last move must, add to be able to compare if there is a win or stalemate
            JOptionPane.showMessageDialog(parent,"There has been a Stalemate!\n Game Over!");
            endGame();
        }          
    }
}
  
}  
}