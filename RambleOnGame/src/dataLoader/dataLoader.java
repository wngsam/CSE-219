package dataLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import rambleongame.Region;
import rambleongame.subRegion;
import xml_utilities.XMLUtilities;
import static rambleongame.RambleOnGame.*;
import static rambleongame.subRegion.*;


/**
 *
 * @author Sam
 */
public class dataLoader {
    
    XMLUtilities XMLUtil = new XMLUtilities();
    Document doc;
    Node region;
    Element e;
    ArrayList<Node> subRegion;
    ArrayList<subRegion> subRegionObj;
    static Region main;
    String data;
    String cPath;
    
    public dataLoader(String cP, String name){
        data=cP+name;
        cPath=cP;
        try{
        XMLUtil.validateXMLDoc(data, "./data/The World/RegionData.xsd");
        doc = XMLUtil.loadXMLDocument(data,"./data/The World/RegionData.xsd");
        }catch(Exception e){}
        region = XMLUtil.getNodeWithName(doc,"region");
        e = (Element)region;
        
    }
    
    public  Region getMap(){
        return main;
    }
    
    public void loadRegion(){
        main = new Region(e.getAttribute("name"),e.getAttribute("leader"),e.getAttribute("capital"));
        
    }
    public boolean loadSubRegion(){
        
        subRegion = XMLUtil.getChildNodesWithName(region,"sub_region");
        a=50;
        subRegionObj = new ArrayList();
        boolean cap = true;
        for (int i=0;i<subRegion.size();i++){
            Element temp =(Element)subRegion.get(i);
            subRegion sR = new subRegion(temp.getAttribute("name"),temp.getAttribute("capital"),temp.getAttribute("red"));
            subRegionObj.add(sR);
            if(temp.getAttribute("capital").equals("") || temp.getAttribute("capital")==null){
                cap = false;
            }
        }
        return cap;
    }
    
    public void loadSR(){
        main.loadSR(subRegionObj);
    }
    public ArrayList<subRegion> getSR(){
        return subRegionObj;
    }
    public void loadMorePics(){
        String Name = main.getName();
        if(Name.equals("Africa") || Name.equals("Asia") || Name.equals("Europe") || Name.equals("North America") 
                || Name.equals("Oceania") || Name.equals("South America")){
        for(int i=0;i<subRegionObj.size();i++){
            String name = subRegionObj.get(i).getName();
            BufferedImage img1;
            BufferedImage img2;
            
            try{
                img1 = ImageIO.read(new File(cPath+name+" Flag.jpg"));
                main.loadFlags(img1);
                subRegionObj.get(i).addFlag(img1);
            }catch(Exception e){
                try{
                img1 = ImageIO.read(new File(cPath+name+" Flag.png"));
                subRegionObj.get(i).addFlag(img1);
                main.loadFlags(img1);
            }catch(Exception e2){
                System.out.println("Error loading "+name+" flag");
                //fm=0;
            }
            }
            
            try{
                img2 = ImageIO.read(new File(cPath+name+" Leader.jpg"));
                subRegionObj.get(i).addLeader(img2);
                main.loadLeaders(img2);
            }catch(Exception e){
                try{
                img2 = ImageIO.read(new File(cPath+name+" Leader.png"));
                main.loadLeaders(img2);
                subRegionObj.get(i).addLeader(img2);
            }catch(Exception e2){
                System.out.println("Error loading "+name+" leader");
                //lm=0;
            }
            }
            
        }
    }
        for(int i=0;i<subRegionObj.size();i++){
        subRegionObj.get(i).createRegions();
        }
    }
    
}
