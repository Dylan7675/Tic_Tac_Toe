/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.*;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.TEXT_CURSOR;
import static tic_tac_toe.TicTacClient.compScore;
import static tic_tac_toe.TicTacClient.playerScore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import tic_tac_toe.Difficulty;
import tic_tac_toe.TicTacClient;

public class tictacUI extends JFrame{
    
    //menu components
    private static JMenuBar menuBar ;
    private static JMenu optionMenu;
    private static JMenu difficultyMenu;
    private static JMenuItem reset;
    private static JMenuItem exit;
    private static JMenuItem beginner;
    private static JMenuItem intermediate;
    private static JMenuItem hard;
    
    //buttons
    public static JButton x;
    public static JButton o;
    public static JButton start;
    
    //UI layout
    private JPanel xoPanel;
    public static JPanel playerPanel;
    private static JPanel startPanel;
    
    //font
    public static Font playFont;
    
    //player
    public static String playerLetter = "X";
    public static String compLetter = "O";
    
    //objects
    private Player playGame;
    
    
    
    public tictacUI()
    {
        initObjects();
        initComponents();
        
    }
    
    private void initComponents()
    {
        //setting the JFrame
        this.setTitle("TicTacToe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        this.setBackground(Color.DARK_GRAY);
        
        Font titleFont = new Font("Verdana",Font.BOLD, 30);
        Font textFont = new Font("Verdana",Font.PLAIN, 18);
 
        //startPanel setup
        startPanel = new JPanel(new BorderLayout());
        startPanel.setBackground(Color.WHITE);
        start = new JButton("Start");
        start.setPreferredSize(new Dimension(90,60));
        start.setMaximumSize(new Dimension(90,60));
        start.setFont(titleFont);
        startPanel.add(start, BorderLayout.SOUTH);
        start.addActionListener(new startButtonListener());
        
        //adds title in startPanel
        JLabel title = new JLabel("Tic Tac Toe!", SwingConstants.CENTER);
        title.setFont(titleFont);
        startPanel.add(title, BorderLayout.NORTH);
        
        //adds image to startPanel
        java.net.URL imageUrl = tictacUI.class.getResource("pic.png");
        ImageIcon image = new ImageIcon(imageUrl);
        JLabel label = new JLabel(image);
        startPanel.add(label, BorderLayout.CENTER);

        //setting up the menuBar
        menuBar = new JMenuBar();
        optionMenu = new JMenu("Options");
        optionMenu.setMnemonic('O');
        menuBar.add(optionMenu);
        difficultyMenu = new JMenu("Difficulty");
        menuBar.add(difficultyMenu);
        difficultyMenu.setMnemonic('D');
        this.setJMenuBar(menuBar);
        
        // option menu
        reset = new JMenuItem("Reset");
        reset.addActionListener(new resetListener());
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        optionMenu.add(reset);
        optionMenu.add(exit);
        
        //Difficulty menu
        beginner = new JMenuItem("Beginner");
        beginner.addActionListener(new beginnerButtonListener());
        intermediate = new JMenuItem("intermediate");
        intermediate.addActionListener(new intermediateButtonListener());
        hard = new JMenuItem("Hard");
        hard.addActionListener(new hardButtonListener());
        difficultyMenu.add(beginner);
        difficultyMenu.add(intermediate);
        //difficultyMenu.add(hard);
        
        //setup XO option panel
        xoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,90,0));
        xoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),"Select either X or O",TitledBorder.CENTER,0,textFont,Color.WHITE));
        
        //buttons
        x = new JButton("X");
        x.addActionListener(new xOptionListener());
        o = new JButton("O");
        o.addActionListener(new oOptionListener());
        xoPanel.add(x);
        xoPanel.add(o);
        x.setEnabled(true);
        o.setEnabled(true);
        
        setupPlayerPanel();
       
        //add panels
        this.add(xoPanel,BorderLayout.NORTH);
        this.add(startPanel,BorderLayout.CENTER);
        this.setVisible(true);
        playerPanel.setVisible(false);
        xoPanel.setVisible(false);
        startPanel.setVisible(true);
        
     }
    
    //sets up playerPanel and instanciates playerArray
    private void setupPlayerPanel()
    {
        Font playFont = new Font("Seriff",Font.PLAIN,12);
        playerPanel = new JPanel(new GridLayout(3,3));
        playerPanel.setMinimumSize(new Dimension(150,150));
        playerPanel.setMaximumSize(new Dimension(150,150));
        playerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1),
            "     Score:                        Player-"+playerScore+"                  Computer-"+compScore, TEXT_CURSOR, ICONIFIED,tictacUI.playFont,Color.WHITE)); 
        Font myFont = new Font("Verdana",Font.BOLD, 60);
        JButton[][] playerArray = playGame.getGrid();
        
        for(int row = 0; row < playGame.getRows(); row++)
        {
            for(int col = 0; col < playGame.getCols(); col++)
            {
                playerPanel.add(playerArray[row][col]);
                playerArray[row][col].setFont(myFont);
            }
        }
    }
    
    private void initObjects()
    {
        playGame = new Player("Play Game", this);
    }
    
    private class resetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            TicTacClient.reset();
        }
    
    }
    
    
    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int response = JOptionPane.showConfirmDialog(null, "Are you Sure You Want To Exit?","Exit?", JOptionPane.YES_NO_OPTION);
            if (response == (JOptionPane.YES_OPTION)){
                    System.exit(0);}
        }
    
    }
    
    private class xOptionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           playerLetter = "X";
           compLetter = "O" ;
           x.setEnabled(false);
           o.setEnabled(false);
           playerPanel.setVisible(true);
        }
    
    }
    
    private class oOptionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playerLetter = "O";
            compLetter="X";
            o.setEnabled(false);
            x.setEnabled(false);
            //playerPanel.setVisible(true);
        }
    
    }
    
    private class startButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            remove(startPanel);
            invalidate();
            validate();
            repaint();
            add(playerPanel, BorderLayout.CENTER);
            xoPanel.setVisible(true);
            playerPanel.setVisible(true);
            xoPanel.setBackground(Color.DARK_GRAY);
            playerPanel.setBackground(Color.DARK_GRAY);
        }
    
    }
    
    private class beginnerButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Difficulty.beginner = true;
            Difficulty.intermediate = false;
            Difficulty.hard = false;    
            JOptionPane.showMessageDialog(null, "Easy Mode has Been Selected");
        }
    }
    
    private class intermediateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Difficulty.beginner = false;
            Difficulty.intermediate = true;
            Difficulty.hard = false;
            JOptionPane.showMessageDialog(null, "intermediate Mode has Been Selected");
        }
    }
    
    private class hardButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Difficulty.beginner = false;
            Difficulty.intermediate = false;
            Difficulty.hard = true;
            JOptionPane.showMessageDialog(null, "Hard Mode has Been Selected");
        }
    }
    
      
}
