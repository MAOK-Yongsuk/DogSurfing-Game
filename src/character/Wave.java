package character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave {

     public int x,y,width,height,speed;
     private int xStart;
     
     public  Wave(int x,int y,int w,int h,int speed,JPanel game) {
          this.x = x;
          this.xStart = x;
          this.y = y;
          this.width = w;
          this.height = h;
          this.speed = speed;
          move(game);

     }

     public void move(JPanel game) {
          Timer timer = new Timer(50,new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    x -= speed;
                    game.repaint();
                    if(x<0) {
                         x = xStart;
                    }
               }
          });
          timer.start();
     }
     
     public BufferedImage getImage() {
          BufferedImage image = null;
          try {
               image = ImageIO.read(new File("C:\\Users\\HP\\OneDrive\\[-{MAOK}-]\\[-Workings\\JAVA_Game01\\img\\wave.png"));
               return image;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return image;
     }
}