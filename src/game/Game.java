package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import event.Event;
import character.*;

public class Game extends JPanel implements KeyListener{

     int xStart = 1000;
     int gamespeed = 30;
     long lastPress = 0;
     Dog dog = new Dog(100,268,65,100);
     Wave[] waveSet = makeWaveSet(4);
     Environment[] envSet = makeEnv(2,Environment.CLOUD);

     //--- font ---
     Font myFont = new Font ("FC Lamoon", 1, 35);
     

     public Game() {
          this.setBounds(0, 0, 1000, 600);
          this.addKeyListener(this);
          this.setFocusable(true);
          this.setLayout(null);                   
     }

     @Override
     public void paint(Graphics g) {         
          super.paint(g);
          Graphics2D g2 = (Graphics2D) g;
          try {
               this.drawBackground(g2);
          } catch (IOException e) {
               e.printStackTrace();
          }

          //---- dog -----
          g2.setColor(Color.RED);
          //g2.drawRect(dog.x, dog.y, dog.dogSize, dog.dogSize);
          g2.drawImage(dog.getImage(), dog.x, dog.y,dog.dogSize,dog.dogSize,null);
          g2.setFont (myFont);
          g2.drawString("HP --> "+dog.health+" %", 40, 40);
          g2.setColor(Color.black);

          //---- wave ----
          for(Wave wave : waveSet) {         
               g2.drawImage(wave.getImage(),wave.x, (wave.y-18), (wave.width+35), (wave.height+10),null);
               if(Event.checkHit(dog, wave)) {
                    g2.setStroke(new BasicStroke(10.0f));
                    g2.setColor(Color.RED);                   
                    g2.drawRect(0, 0, 1000, 900);
                    dog.health -= 1;
               }              
          }                                  
     }

     private void drawBackground(Graphics2D g2) throws IOException {
          g2.drawImage(ImageIO.read(new File("C:\\Users\\HP\\OneDrive\\[-{MAOK}-]\\[-Workings\\JAVA_Game01\\img\\sky.jpg"))
          ,0,0,985,350, null);
          
          g2.drawImage(ImageIO.read(new File("C:\\Users\\HP\\OneDrive\\[-{MAOK}-]\\[-Workings\\JAVA_Game01\\img\\bgg.jpg"))
          ,0,308,985,300, null);

          //---- cloud ----
          for(Environment item:envSet) {
               g2.drawImage(item.getImage(),item.x,item.y,400,160, null);
          }
     }



     private Wave[] makeWaveSet(int waveNumber) {
          Wave[] waveSet = new Wave[waveNumber];
          for(int i=0;i<waveNumber;i++) {
               double waveLocation = 1000+Math.floor(Math.random()*1000);
               waveSet[i] = new Wave((int)waveLocation, 300, 30, 40, 20, this);               
          }
          return waveSet;
     }

     private Environment[] makeEnv(int size,int eType){
		Environment[] envSet = new Environment[size];
		int far = 0;
		for(int i=0;i<size;i++) {
			envSet[i] = new Environment(xStart+far,30,this,eType,8);
			far+=600;
		}
		return envSet;
	}

     

     @Override
     public void keyTyped(KeyEvent e) {

     }

     @Override
     public void keyPressed(KeyEvent e) {
          if(System.currentTimeMillis()-lastPress>600) {
               if(e.getKeyCode()==38 || e.getKeyCode()==32) {
                    dog.jump(this);
                    this.repaint();
               }
               lastPress = System.currentTimeMillis();
          }
          
     }

     @Override
     public void keyReleased(KeyEvent e) {

     }

          
     
}