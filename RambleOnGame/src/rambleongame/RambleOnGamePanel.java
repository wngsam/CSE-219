/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rambleongame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import static rambleongame.RambleOnGame.*;

/**
 *
 * @author Sam
 */
public class RambleOnGamePanel extends JPanel implements MouseListener{
    
    private MiniGame game;
    private RambleOnGameDataModel data;
    public static List<subRegion> regions;
    
    public RambleOnGamePanel(MiniGame initGame, RambleOnGameDataModel initData){
		game = initGame;
		data = initData;
                addMouseListener(this);
        }
    
    public void paintComponent(Graphics g){
        
            renderToGraphicsContext(g);
            
        }
    
    public void renderToGraphicsContext(Graphics g){
		renderBackground(g);
                renderMap(g);
                
                if(gameMode.equals("SRM")){
                renderStats(g);
                renderSR(g);
                }
                
                if(gameMode.equals("CM")){
                renderStats(g);
                renderCap(g);
                }
                
                if(gameMode.equals("FM")||gameMode.equals("LM")){
                    renderStats(g);
                    renderLeader(g);
                }
                
                renderModes(g);
               
                
                if(gameMode.equals("Welcome")||gameMode.equals("Accounts")){renderWelcome(g);}
                if(gameMode.equals("Accounts")){renderAcctSprites(g);}
                if(gameMode.equals("Create")){renderCreate(g);}
        }
    public void renderLeader(Graphics g){
        if(!playList.isEmpty()){
        regions = playList;
        Sprite s;
            int b=50;
            if(gameMode.equals("LM")){s = new Sprite(regions.get(0).getLDR(), 900,715-b,0,0,"LCurrent");}
            else{
                s = new Sprite(regions.get(0).getFLG(), 900,715-b,0,0,"FCurrent");
            }
            renderSprite(g, s);
            for(int i=1;i<regions.size();i++){
                if(gameMode.equals("LM")){s = new Sprite(regions.get(i).getLDR(), 900,665-b,0,0,"LCurrent");}
                else{
                    s = new Sprite(regions.get(i).getFLG(), 900,665-b,0,0,"FCurrent");
                }
                b+=150;
                renderSprite(g, s);
            }
            s = game.getGUIDecor().get("Logo");
               renderSprite(g, s);
        }
    }
    public void renderCreate(Graphics g){
        Sprite bg = game.getGUIDecor().get("BACKGROUND");
                renderSprite(g, bg);
                bg = game.getGUIButtons().get("OK");
                renderSprite(g, bg);
                bg =game.getGUIButtons().get("CANCEL");
                renderSprite(g, bg);
                bg=game.getGUIDecor().get("ENTER NAME");
                renderSprite(g,bg);
    }
    public void renderAcctSprites(Graphics g){
        for(int i=0;i<sprites.size();i++){
        renderSprite(g,sprites.get(i));
        }
    }
    public void renderWelcome(Graphics g){
        Sprite bg = game.getGUIButtons().get("Welcome");
                renderSprite(g, bg);
                bg = game.getGUIDecor().get("BACKGROUND");
                renderSprite(g, bg);
                bg = game.getGUIButtons().get("NEW ACCT");
                renderSprite(g, bg);
    }
    
    public void renderSR(Graphics g){
        if(!playList.isEmpty()){
        regions = playList;
        Sprite s;
            int b=50;
            s = new Sprite(regions.get(0).getST(), 900,715-b,0,1.5f,"Current");
            renderSprite(g, s);
            for(int i=1;i<regions.size();i++){
                s = new Sprite(regions.get(i).getST(), 900,665-b,0,1.5f,"Default");
                b+=50;
                renderSprite(g, s);
            }
        }
    }
    public void renderModes(Graphics g){
        Sprite bg = game.getGUIDecor().get("Logo");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("SRM");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("FM");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("CM");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("LM");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("START");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("EXIT");
               renderSprite(g, bg);
               bg = game.getGUIButtons().get("ACCT2");
               renderSprite(g, bg);
    }
    public void renderBackground(Graphics g){
		Sprite bg = game.getGUIDecor().get("Main Background");
                renderSprite(g, bg);
        }
    public void renderMap(Graphics g){
                //Game Map
                SpriteType sT = new SpriteType("Game Map");
                BufferedImage img = loader.getMap().getMap();
                sT.addState("Current", img);
                //img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
                //sT.addState("Default", img);
                Sprite bg = new Sprite(sT, 0, 0, 0, 0, "Current");
                renderSprite(g, bg);
                
                //TITLE
                sT = new SpriteType("Title");
                img = new BufferedImage(700, 50, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2d = img.createGraphics();
                g2d.setColor ( new Color ( 120, 25, 20 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 50));
                String str = loader.getMap().getName();
                g2d.drawString(str,165,40);
                sT.addState("Default", img);
                int x =80;
                int y =10;
		sT.addState("Default", img);
                Sprite s = new Sprite(sT, x, y, 0, 0, "Default");
		renderSprite(g, s);
                
                renderSprite(g, game.getGUIDecor().get("WIN"));
                
    }
    public void renderSprite(Graphics g, Sprite s){
		SpriteType bgST = s.getSpriteType();
		Image img = bgST.getStateImage(s.getState());
		g.drawImage(img, (int)s.getX(), (int)s.getY(), bgST.getWidth(), bgST.getHeight(), null);
	}
    public void renderCap(Graphics g){
        if(!playList.isEmpty()){
        regions = playList;
        Sprite s;
            int b=50;
            s = new Sprite(regions.get(0).getST(), 900,715-b,0,1.5f,"CCurrent");
            renderSprite(g, s);
            for(int i=1;i<regions.size();i++){
                s = new Sprite(regions.get(i).getST(), 900,665-b,0,1.5f,"CDefault");
                b+=50;
                renderSprite(g, s);
            }
        }
    }
    public void renderStats(Graphics g){
            String str;
            
            str = "Game Score: "+data.currentGAME_SCORE;
            g.setColor ( new Color ( 248, 248, 255 ) );
            g.setFont(new Font(null, Font.PLAIN, 16));
            g.drawString(str, 75,650);
            
            str = "Regions Found: "+data.currentREGIONS_FOUND;
            g.setColor ( new Color ( 248, 248, 255 ) );
            g.setFont(new Font(null, Font.PLAIN, 16));
            g.drawString(str, 230,650);
            
            str = "Regions Left: "+data.currentREGIONS_LEFT;
            g.setColor ( new Color ( 248, 248, 255 ) );
            g.setFont(new Font(null, Font.PLAIN, 16));
            g.drawString(str, 370,650);
            
            str = "Incorrect Guesses: "+data.currentINCORRECT_GUESSES;
            g.setColor ( new Color ( 248, 248, 255 ) );
            g.setFont(new Font(null, Font.PLAIN, 16));
            g.drawString(str, 500,650);
             
            str = "Time: "+data.getGameTimeText();
            g.setColor ( new Color ( 248, 248, 255 ) );
            g.setFont(new Font(null, Font.PLAIN, 16));
            g.drawString(str, 660,650);        
            
        }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    if(e.getButton() == 3 && !gameMode.equals("Welcome")&&!gameMode.equals("Accounts")&&!gameMode.equals("Create")){
        gameMode="Navigation";
        game.reset();
        if(stk.size()!=0){
        String temp = stk.pop(); 
        currentPath=stk.pop();
        loadData(temp);
        }
        }
    if(e.getButton()==2&&(gameMode.equals("FM")||gameMode.equals("CM")||gameMode.equals("LM")||gameMode.equals("SRM"))){
        data.endGameAsWin();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
