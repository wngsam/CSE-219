package rambleongame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import mini_game.SpriteType;

/**
 *
 * @author Sam
 */
public class subRegion {
    
    String name;
    String capital;
    String color;
    ArrayList<Integer> xcord= new ArrayList();
    ArrayList<Integer> ycord= new ArrayList();
    //
    BufferedImage img;
    BufferedImage Flag;
    BufferedImage Leader;
    Graphics2D g2d;
    public static int a;
    private static final int width=380;
    private static final int height=50;

    SpriteType sT;
    SpriteType flg;
    SpriteType ldr;
    //
    
    public subRegion(String n, String c, String cr){
        name=n;
        capital=c;
        color=cr;
    }
    public void addFlag(BufferedImage f){
        Flag=f;
    }
    public BufferedImage getFlag(){
        return Flag;
    }
    public BufferedImage getLeader(){
        return Leader;
    }
    public void addLeader(BufferedImage l){
        Leader=l;
    }
    public void createRegions(){
        sT= new SpriteType(name);
        a+=10;
        if(a>200){
            a=50;
        }
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(a,a,a));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(name,7,28);
                sT.addState("Default", img);
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(200,10,30));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(name,7,28);
                sT.addState("Current", img);
                
                if(capital!=null&&capital!=""){
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(a,a,a));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(capital,7,28);
                sT.addState("CDefault", img);
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(200,10,30));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(capital,7,28);
                sT.addState("CCurrent", img);
                }
                
                flg=new SpriteType(name);
                ldr=new SpriteType(name);
                BufferedImage img2;
                
                if(Leader!=null){
                img2 = Leader;
                ldr.addState("LCurrent", img2);
                }
                
                if(Flag!=null){
                img2 = Flag;
                flg.addState("FCurrent", img2);
                }
    }
    public SpriteType getST(){
        return sT;
    }
    public SpriteType getLDR(){
        return ldr;
    }
    public SpriteType getFLG(){
        return flg;
    }
    public String getName(){
        return name;
    }
    public Color getColor(){
        int clr = Integer.parseInt(color);
        return new Color(clr,clr,clr);
    }
    public String getCapital(){
        return capital;
    }
    public void addCoord(int x, int y){
        xcord.add(x);
        ycord.add(y);
        
    }
    public ArrayList<Integer> xcord(){
        return xcord;
    }
    public ArrayList<Integer> ycord(){
        return ycord;
    }
    
}
