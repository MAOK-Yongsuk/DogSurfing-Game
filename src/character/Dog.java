package character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Dog {

     public int x,y,dogSize,dog,health;
     private int jumpHigh = 100;

     public  Dog(int x,int y,int dogSize,int health) {
          this.x = x;
          this.y = y;
          this.dogSize = dogSize;
          this.health = health;

     }
     public void jump(JPanel game) {
          this.y -= jumpHigh;
          game.repaint();
          Timer timer = new Timer(450,new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    y += jumpHigh;
                    game.repaint();
               }
          });
          timer.setRepeats(false);
          timer.start();

     }

     public BufferedImage getImage() {
          BufferedImage image = null;
          try {
               image = ImageIO.read(new File("C:\\Users\\HP\\OneDrive\\[-{MAOK}-]\\[-Workings\\JAVA_Game01\\img\\dog.png"));
               return image;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return image;
     }
}
