package theregionpickergame.sprites;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import mini_game.SpriteType;
/**
 * TheRegionPickerGame - Region Class
 * @author Sam Wang
 * Stores each sub-region data with color, sprite, etc
 */
public class Region {
    
    private String name;
    private Color c;
    private ArrayList<Integer> xcord= new ArrayList();
    private ArrayList<Integer> ycord= new ArrayList();
    
    BufferedImage img;
    Graphics2D g2d;
    private static int a=50;
    private static int b=0;
    private static final int width=271;
    private static final int height=50;
    private static final float VELOCITY=1.5f;

    SpriteType sT;
    
    public Region(String name, Color c){
        this.c=c;
        this.name=name;
        createRegions();
    }
           
    public void addCoord(int x, int y){
        xcord.add(x);
        ycord.add(y);
    }
    
    public String getName(){
        return name;
    }
    public void createRegions(){
        sT= new SpriteType(name);
        a+=10;
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(a,a,a));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(name,7,28);
                sT.addState("DEFAULT_STATE", img);
                img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setPaint( new Color(200,10,30));
                g2d.fillRect(0,0,width,height);
                g2d.setColor ( new Color ( 0, 191, 255 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 18));
                g2d.drawString(name,7,28);
                sT.addState("CURRENT_STATE", img);
    }
    public SpriteType getSpriteType(){
        return sT;
    }
    public ArrayList getXcord(){
        return xcord;
    }
    public ArrayList getYcord(){
        return ycord;
    }
    public Color getColor(){
        return c;
    }
}
