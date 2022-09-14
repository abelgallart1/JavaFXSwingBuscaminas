/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXSwingMain.java to edit this template
 */
package javafxswingbuscaminas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Abel Gallart Bonome
 */
public class JavaFXSwingBuscaminas extends JApplet {
    
    int JFXPANEL_WIDTH_INT = 1000;
    int JFXPANEL_HEIGHT_INT = 1000;
    JFXPanel fxContainer;
    static int ancho=30;
    static int largo=30;
    static int bombas=30;
    static int puntos=0;
    static int nivel=0;
    Random ran;
    String [][]matris;
    JButton b[][];
    JLabel l[][];
   
    static JFrame frame;
    static JApplet applet;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e){}
               
                frame = new JFrame("Buscaminas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                frame.setOpacity((float) 1.0);
                
                applet = new JavaFXSwingBuscaminas();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                applet.start();
            }
        });
    }
    
    @Override
    public void init() {
        
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jPanel3 = new JPanel();
       
       
        jPanel2.setSize(new java.awt.Dimension(720, 720));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bombas "+bombas);
        
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nivel "+nivel);
        
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Puntos "+puntos);
       
        jPanel3.setBackground(new java.awt.Color(255, 153, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscaminas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        
        
        ran=new Random();
        matris=new String[ancho][largo];
        for(int x=0;x<ancho;x++)
        for(int y=0;y<largo;y++)
        {matris[x][y]="0";}          
     
           int c=0;
        while(c<bombas){
        int x=(int)(ancho*ran.nextFloat());
        int y=(int)(largo*ran.nextFloat());
        
        if (!matris[x][y].equals("B")) { matris[x][y]="B";c++;}
        }
        
        
        for(int x=0;x<ancho;x++)
        for(int y=0;y<largo;y++)
        {     
          c=0;
          try{if (matris[x-1][y].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x-1][y-1].equals("B"))c++;}catch(Exception e){}
          try{ if (matris[x][y-1].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x+1][y-1].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x+1][y].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x+1][y+1].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x][y+1].equals("B"))c++;}catch(Exception e){}
          try{if (matris[x-1][y+1].equals("B"))c++;}catch(Exception e){}
          
         if (!matris[x][y].equals("B")) matris[x][y]=c+"";    
         }
    
          b=new JButton[ancho][largo];
          l=new JLabel[ancho][largo];

        for(int x=0;x<ancho;x++)for(int y=0;y<largo;y++)
        {     
             l[x][y]=new JLabel();
             l[x][y].setForeground(Color.WHITE);
             l[x][y].setHorizontalAlignment(JLabel.CENTER);
             if (!matris[x][y].equals("0")) l[x][y].setText(" "+matris[x][y]);
       
             b[x][y]=new JButton();
             b[x][y].setBackground(Color.WHITE);
             
             b[x][y].addMouseListener(new java.awt.event.MouseAdapter() {
           
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    if(evt.getButton()==MouseEvent.BUTTON3) Button3(evt);
                    if(evt.getButton()==MouseEvent.BUTTON1) Button1(evt);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(JavaFXSwingBuscaminas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             });
            
            jPanel2.add(b[x][y], new org.netbeans.lib.awtextra.AbsoluteConstraints(x*largo,y*ancho,29,29));
            jPanel2.add(l[x][y], new org.netbeans.lib.awtextra.AbsoluteConstraints(x*largo,y*ancho,29,29));
        }
         
        
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setSize(new java.awt.Dimension(JFXPANEL_WIDTH_INT,JFXPANEL_WIDTH_INT));
        jPanel1.setLayout(new AbsoluteLayout());
        jPanel1.add(jLabel1, new AbsoluteConstraints(110, 10,270, 60));
        jPanel1.add(jLabel2, new AbsoluteConstraints(330, 10, 190, 60));
        jPanel1.add(jLabel3, new AbsoluteConstraints(540, 10, 240, 60));
        jPanel1.add(jPanel3, new AbsoluteConstraints(100, 10, 670, 60));
        jPanel1.add(jPanel2, new AbsoluteConstraints(50, 80, -1, -1));
        setLayout(new AbsoluteLayout());
        jPanel1.setOpaque(false);

        add(jPanel1,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0, -1,-1));
   
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT,JFXPANEL_HEIGHT_INT));
        add(fxContainer,new AbsoluteConstraints(0,0,-1,-1));
           
       
       // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    createScene();
                    File filefondo=new File("./fondo.mp4");
                    Media mediafondo=new Media(filefondo.toURI().toString());
                    MediaPlayer fondo=new MediaPlayer(mediafondo);
                    MediaView mv=new MediaView(fondo);
                    
                    fxContainer.setScene(new Scene(new Group(mv)));
                    
                    fondo.setCycleCount(MediaPlayer.INDEFINITE);
                    fondo.setVolume(0.7);
                    
                    mv.setFitHeight(2000);
                    mv.setFitWidth(2000);
                    fondo.play();
                } catch (Exception ex) {}
            }
        });
       
        
    }
    
   
    public void analiza(JButton bot){
    if (!bot.isVisible()) return ;
         
    int x=bot.getX()/30;
    int y=bot.getY()/30;
    bot.setVisible(false);
                
    if (matris[x][y].equals("0")){ 
     puntos++;
     jLabel3.setText("Puntos "+puntos);
    try {analiza(b[x-1][y]);}catch(Exception e){}
    try {analiza(b[x-1][y-1]);}catch(Exception e){}
    try {analiza(b[x][y-1]);}catch(Exception e){}
    try {analiza(b[x+1][y-1]);}catch(Exception e){}
    try {analiza(b[x+1][y]);}catch(Exception e){}
    try {analiza(b[x+1][y+1]);}catch(Exception e){}
    try {analiza(b[x][y+1]);}catch(Exception e){}
    try { analiza(b[x-1][y+1]);}catch(Exception e){}
            
    }
    }
    
    
    private void Button1(MouseEvent  evt) throws URISyntaxException {                                         
        
        Component com=((Component) evt.getSource());
   
        int x=com.getX()/30;
        int y=com.getY()/30;
        
        if (matris[x][y].equals("B"))  
        {
                File fileperder=new File("./perder.mp3");
                Media mediaperder=new Media(fileperder.toURI().toString());
                MediaPlayer efecto=new MediaPlayer(mediaperder);
                efecto.setVolume(0.7);
                efecto.play();
                   
                javax.swing.JOptionPane.showMessageDialog
                         (null,"Has Perdido","Fin del juego....",javax.swing.JOptionPane.OK_OPTION); 
       
                applet.stop();
                frame.setVisible(false);
                frame = new JFrame("Buscaminas");
                frame.setUndecorated(true);
                frame.setOpacity((float) 1.0);
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                applet = new JavaFXSwingBuscaminas();
                puntos=0;nivel=0;bombas=40;
                
                applet.init();
                frame.setContentPane(applet.getContentPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
              
                frame.setVisible(true);
                applet.start(); 
                

               }
   
        else{
            analiza(b[x][y]);
            puntos++;
            jLabel3.setText("Puntos "+puntos);
            File fileclick=new File("./click.mp3");
            Media mediaclick=new Media(fileclick.toURI().toString());
            MediaPlayer efectoclick=new MediaPlayer(mediaclick);
            efectoclick.setVolume(0.7);
            efectoclick.play();
            
            int c=0;
            for(int i=0;i<ancho;i++)for(int j=0;j<largo;j++) 
             if ( b[i][j].isVisible())  c++;   

             if (c==bombas)
             {  File fileganar=new File("./ganar.mp3");
                Media mediaganar=new Media(fileganar.toURI().toString());
                MediaPlayer efectoganar=new MediaPlayer(mediaganar);
                efectoganar.setVolume(0.7);
                efectoganar.play();
                
                javax.swing.JOptionPane.showMessageDialog
                         (null,"Has Ganado","Fin del juego....",javax.swing.JOptionPane.OK_OPTION);
              
              
               applet.stop();
               frame.setVisible(false);
               frame = new JFrame("Buscaminas");
               frame.setUndecorated(true);
               frame.setOpacity((float) 1.0);
                    
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               applet = new JavaFXSwingBuscaminas();
               bombas=(bombas+1>ancho*largo/2)?bombas:bombas+1; 
               nivel++;
               puntos+=200;
               applet.init();
               
               frame.setContentPane(applet.getContentPane());
               frame.pack();
               frame.setLocationRelativeTo(null);
                     
               frame.setVisible(true);
               
               applet.start();
              }
            
           }
       }                                        

 private void Button3(MouseEvent evt) {                                      
       Component com=((Component) evt.getSource());
       if (com.getBackground().equals(Color.red)) {com.setBackground(Color.WHITE);return;}
       if (com.getBackground().equals(Color.WHITE)) com.setBackground(Color.red);
       File filemarcar=new File("./marcar.mp3");
       Media mediamarcar=new Media(filemarcar.toURI().toString());
       MediaPlayer efectomarcar=new MediaPlayer(mediamarcar);
       efectomarcar.setVolume(0.7);
       efectomarcar.play();
    }    
    
    
    private void createScene() {
        StackPane root = new StackPane();
        fxContainer.setScene(new Scene(root));
    }
    
}
