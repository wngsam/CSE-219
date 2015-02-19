package rambleongame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static rambleongame.RambleOnGame.currentPath;

/**
 *
 * @author Sam
 */
public class Region {
    
    Color sea = new Color(0,162,232);
    Color bg = new Color(220,110,0);
    
    ArrayList<BufferedImage> leaders;
    ArrayList<BufferedImage> flags;
    static BufferedImage map;
    static String Name;
    String Leader;
    String Capital;
    //ArrayList<Integer> xcord= new ArrayList();
    //ArrayList<Integer> ycord= new ArrayList();
    //ArrayList<Integer> color= new ArrayList();
    ArrayList<subRegion> sR = new ArrayList();
    
    public Region(String n, String l, String c){
        Name=n;
        Leader=l;
        Capital=c;
        loadMap();
        if(Name.equals("Africa") || Name.equals("Asia") || Name.equals("Europe") || Name.equals("North America") 
                || Name.equals("Oceania") || Name.equals("South America")){
            leaders=new ArrayList();
            flags=new ArrayList();
        }
    }
    
    public void loadSR(ArrayList<subRegion> sR){
        this.sR=sR;
    }
    
    public void getMapData(){
        int width = map.getWidth();
        int height = map.getHeight();
        
        for(int i=0;i<height;i++){
            for(int k=0;k<width;k++){
                
                //if(map.getRGB(k,i)!=sea.getRGB() && map.getRGB(k,i)!=bg.getRGB()){
                  //  xcord.add(k);
                   // ycord.add(i);
                   // color.add(map.getRGB(k,i));
                //}
                if(map.getRGB(k,i)!=sea.getRGB() && map.getRGB(k,i)!=bg.getRGB()){
                for(int j=0;j<sR.size();j++){
                        if(map.getRGB(k,i)==sR.get(j).getColor().getRGB()){
                            sR.get(j).addCoord(k, i);
                        }
                    }
                }
                
            }
        }
    }
    
    public BufferedImage getMap(){
        return map;
    }
    public ArrayList<BufferedImage> getLeaders(){
        return leaders;
    }
    public ArrayList<BufferedImage> getFlags(){
        return flags;
    }
    
    public static void loadMap(){
        try{
        map = ImageIO.read(new File(currentPath+Name+" Map.png"));
        }catch(Exception e){
            try{
        map = ImageIO.read(new File(currentPath+Name+" Map.jpg"));
        }catch(Exception e2){
            System.out.println("Problem loading map");
        }
        }
    }
    
    public void loadFlags(BufferedImage img){
        flags.add(img);
    }
    public void loadLeaders(BufferedImage img){
        leaders.add(img);  
    }
    public String getName(){
        return Name;
    }
    public String getLeader(){
        return Leader;
    }
    public String getCapital(){
        return Capital;
    }
    public Color getSea(){
        return sea;
    }
    public Color getBG(){
        return bg;
    }
    
}
