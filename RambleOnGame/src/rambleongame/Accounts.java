package rambleongame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import mini_game.SpriteType;

/**
 *
 * @author Sam
 */
public class Accounts {
    
    String name;
    BufferedImage img;
    Graphics2D g2d;
    private static int a;
    private static int b;
    private static int c;
    private static final int width=225;
    private static final int height=50;
    private SpriteType sT;
    
    Accounts(String name){
        this.name=name;
        a=20;
        b=144;
        c=200;
                a+=5;
                b+=3;
                c+=1;
                sT = new SpriteType(name);
                this.name=name;
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(a,b,c));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 255, 255, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(name,7,28);
                sT.addState("DEFAULT", img);
    }
    
    public SpriteType getST(){
        return sT;
    }
    public String returnName(){
        return name;
    }
}
