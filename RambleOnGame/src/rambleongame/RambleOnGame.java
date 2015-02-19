package rambleongame;

import dataLoader.dataLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import rambleongame.events.CancelHandler;
import rambleongame.events.ExitGameHandler;
import rambleongame.events.CapStart;
import rambleongame.events.FlagMode;
import rambleongame.events.LeaderMode;
import rambleongame.events.NewAcctHandler;
import rambleongame.events.NewGameHandler;
import rambleongame.events.SubRegStart;
import rambleongame.events.TextFieldHandler;
import rambleongame.events.welcomeHandler;

/**
 *
 * @author Sam
 */
public class RambleOnGame extends MiniGame{

    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 768;
    public static final float BOUNDARY_TOP = 0.05f;
    public static final float BOUNDARY_BOTTOM = 0.05f;
    public static final float BOUNDARY_LEFT = 0.01f;
    public static final float BOUNDARY_RIGHT = 0.01f;
    public static final int FRAME_RATE = 30;
    public static final String APP_TITLE = "Ramble On!";
    
    public JTextField text;
    final static String path = "./data/The World/";
    static String currentPath;
    public static dataLoader loader;
    static Stack<String> stk;
    public static int cm=0;
    public static int fm=0;
    public static int lm=0;
    public static String gameMode = "Welcome";
    public static ArrayList<subRegion> playList;
    public static ArrayList<Accounts> accounts = new ArrayList();
    public static ArrayList<Sprite> sprites = new ArrayList();
    
    public RambleOnGame(){
		super(APP_TITLE, FRAME_RATE);
                stk = new Stack();
	}
    
    public static void main(String[] args) {
        readAccts();
        for(int i=0;i<accounts.size();i++){
            System.out.println(accounts.get(i).returnName());
        }
        currentPath="./data/The World/";
        loadData("The World");
        RambleOnGame game = new RambleOnGame();
        game.startGame();
        
    }
    
    public static void loadData(String region){
        boolean flag = false;
        try{
        loader = new dataLoader(currentPath,region+" Data.xml");
        loader.loadRegion();
        flag = loader.loadSubRegion();
        loader.loadSR();
        loader.getMap().getMapData();
        loader.loadMorePics();
        System.out.println("Successfully Loaded "+region);
        }catch(Exception e){
            if(stk.size()!=0){
            String temp = stk.pop(); 
            currentPath=stk.pop();
            loadData(temp);
            }
            JOptionPane.showMessageDialog(null, "Error Loading "+region+" Map XML not found.");
            System.out.println("Error Loading "+region+", Current: "+loader.getMap().getName());
        }
        
        if(!region.equals("The World")){
            
            if(region.equals("Africa") || region.equals("Asia") || region.equals("Europe") || region.equals("North America") 
                || region.equals("Oceania") || region.equals("South America")){
            if(loader.getMap().getLeaders().size()==loader.getSR().size()){
            lm=1;
            }
            if(loader.getMap().getFlags().size()==loader.getSR().size()){
            fm=1;
            }
            }
            if(flag==true){
            cm=1;
            }
            
            
            if(region.equals("Africa") || region.equals("Asia") || region.equals("Europe") || region.equals("North America") 
                || region.equals("Oceania") || region.equals("South America")){
                cm=0;
            }else{
                fm=0;
                lm=0;
            }
        }
        
        
    }

    @Override
    public void initData() {
        data = new RambleOnGameDataModel();
		data.setGameDimensions(GAME_WIDTH, GAME_HEIGHT);
		boundaryLeft 	= BOUNDARY_LEFT	* GAME_WIDTH;
		boundaryRight 	= GAME_WIDTH 	- (GAME_WIDTH * BOUNDARY_RIGHT);
		boundaryTop 	= BOUNDARY_TOP 	* GAME_HEIGHT;
		boundaryBottom 	= GAME_HEIGHT 	- (GAME_HEIGHT * BOUNDARY_BOTTOM);
    }

    @Override
    public void initGUIControls() {
        canvas = new RambleOnGamePanel(this, (RambleOnGameDataModel)data);
            text = new JTextField(20);
            text.setSize(150,35);
            text.setLocation(560,500);
            text.setVisible(false);
            canvas.add(text);
                BufferedImage img;
		float x, y;
		SpriteType sT;
		Sprite s;
                Graphics2D g2d;
                String str;
                
                //Welcome
                sT = new SpriteType("Welcome");
		img = loadImage("./data/images/welcome.png");
		sT.addState("Default", img);
		x = 0;
		y = 0;
                img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
                sT.addState("Off", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("Welcome", s);
                
                //Main Screen BG
                sT = new SpriteType("Main Background");
		img = loadImage("./data/images/mbg.png");
		sT.addState("Default", img);
		x = 0;
		y = 0;
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiDecor.put("Main Background", s);
                
                //Game Map
                //sT = new SpriteType("Game Map");
                //img = loader.getMap().getMap();
                //sT.addState("Current", img);
                //img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
                //sT.addState("Default", img);
                //s = new Sprite(sT, x, y, 0, 0, "Current");
                //guiDecor.put("Game Map", s);
                
                //Logo
                sT = new SpriteType("Logo");
		img = loadImage("./data/images/logo.png");
		sT.addState("Default", img);
		x = 900;
		y = 0;
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiDecor.put("Logo", s);
                
                //START/EXIT/ACCT
                sT = new SpriteType("START");
		img = loadImage("./data/images/START.png");
		x = 901;
		y = 350;
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("START", s);
                
                sT = new SpriteType("EXIT");
		img = loadImage("./data/images/EXIT.png");
		x = 1027;
		y = 350;
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("EXIT", s);
                
                sT = new SpriteType("ACCT2");
		img = loadImage("./data/images/ACCT.png");
		x = 1153;
		y = 350;
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("ACCT2", s);
                
                //Modes
                sT = new SpriteType("SRM");
		img = loadImage("./data/images/srm.png");
		sT.addState("On", img);
		x = 900;
		y = 150;
                img = loadImage("./data/images/offsrm.png");
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("SRM", s);
                
                sT = new SpriteType("FM");
		img = loadImage("./data/images/fm.png");
		sT.addState("On", img);
		x = 900;
		y = 200;
                img = loadImage("./data/images/offfm.png");
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("FM", s);
                
                sT = new SpriteType("CM");
		img = loadImage("./data/images/cm.png");
		sT.addState("On", img);
		x = 900;
		y = 250;
                img = loadImage("./data/images/offcm.png");
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("CM", s);
                
                sT = new SpriteType("LM");
		img = loadImage("./data/images/lm.png");
		sT.addState("On", img);
		x = 900;
		y = 300;
                img = loadImage("./data/images/offlm.png");
		sT.addState("Default", img);
		s = new Sprite(sT, x, y, 0, 0, "Default");
		guiButtons.put("LM", s);
                
                //Win Screen
                sT = new SpriteType("WIN");
                img = loadImage("./data/images/victory.png");
                sT.addState("WIN",img);
                img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
                sT.addState("Default", img);
                x = 380;
                y = 250;
                s = new Sprite(sT,x,y,0,0,"Default");
                guiDecor.put("WIN",s);
                ////////////////////////
                //OK Button
        sT = new SpriteType("OK");
	img = loadImage("./data/images/OK.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 400;
	y = 600;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiButtons.put("OK", s);
        
        //Cancel Button
        sT = new SpriteType("CANCEL");
	img = loadImage("./data/images/Cancel.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 650;
	y = 600;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiButtons.put("CANCEL", s);
        
        //GS Button
        sT = new SpriteType("GAMESCREEN");
	img = loadImage("./data/images/gamescreen.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 418;
	y = 410;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiButtons.put("GAMESCREEN", s);
        
        //ACCT Button
        sT = new SpriteType("ACCT");
	img = loadImage("./data/images/gotoacct.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 418;
	y = 530;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiButtons.put("ACCT", s);
        
        
        //NewAccount Button
        sT = new SpriteType("NEW ACCT");
	img = loadImage("./data/images/NewAccount.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 418;
	y = 10;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiButtons.put("NEW ACCT", s);
        
        //Enter Name
        sT = new SpriteType("ENTER NAME");
	img = loadImage("./data/images/EnterName.jpg");
	sT.addState("ON", img);
        img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        sT.addState("OFF", img);
	x = 418;
	y = 380;
	s = new Sprite(sT, x, y, 0, 0, "OFF");
	guiDecor.put("ENTER NAME", s);
        
        //BG
        sT = new SpriteType("BACKGROUND");
	img = loadImage("./data/images/AcctScreen.jpg");
	sT.addState("DEFAULT", img);
	x = 0;
	y = 0;
	s = new Sprite(sT, x, y, 0, 0, "DEFAULT");
	guiDecor.put("BACKGROUND", s);
    }

    @Override
    public void initGUIHandlers() {
        ExitGameHandler egh = new ExitGameHandler(this);
        guiButtons.get("EXIT").setActionListener(egh);
        NewGameHandler ngh = new NewGameHandler(this);
        guiButtons.get("START").setActionListener(ngh);
        SubRegStart srs = new SubRegStart(this);
        guiButtons.get("SRM").setActionListener(srs);
        FlagMode fm = new FlagMode(this);
        guiButtons.get("FM").setActionListener(fm);
        LeaderMode lm = new LeaderMode(this);
        guiButtons.get("LM").setActionListener(lm);
        CapStart ls = new CapStart(this);
        guiButtons.get("CM").setActionListener(ls);
        welcomeHandler wh = new welcomeHandler(this);
        guiButtons.get("Welcome").setActionListener(wh);
        guiButtons.get("ACCT2").setActionListener(wh);
        NewAcctHandler nah = new NewAcctHandler(this);
        guiButtons.get("NEW ACCT").setActionListener(nah);
        TextFieldHandler tfh = new TextFieldHandler(this);
        guiButtons.get("OK").setActionListener(tfh);
        CancelHandler ch = new CancelHandler(this);
        guiButtons.get("CANCEL").setActionListener(ch);
    }

    @Override
    public void reset() {
        
        if(playList!=null){
        Collections.shuffle(playList);
        for(int k=0;k<playList.size();k++){
        for(int i=0;i<playList.get(k).xcord().size();i++){
           int a = playList.get(k).xcord().get(i);
           int b = playList.get(k).ycord().get(i);
           loader.getMap().getMap().setRGB(a, b, playList.get(k).getColor().getRGB());
           }
        }
        }
        
        data.reset(this);
    }

    @Override
    public void updateGUI() {
        
        if(gameMode.equals("Accounts")||gameMode.equals("Create")){
            guiDecor.get("BACKGROUND").setState("DEFAULT");
        }else{
            guiDecor.get("BACKGROUND").setState("OFF");
        }
        
        if(gameMode.equals("Accounts")){
            guiButtons.get("NEW ACCT").setState("ON");
            makeSprite();
        }else{
            guiButtons.get("NEW ACCT").setState("OFF");
        }
        
        if(gameMode.equals("Create")){
            text.setVisible(true);
            guiButtons.get("OK").setState("ON");
            guiButtons.get("CANCEL").setState("ON");
            guiDecor.get("ENTER NAME").setState("ON");
        }else{
            text.setVisible(false);
            guiButtons.get("OK").setState("OFF");
            guiButtons.get("CANCEL").setState("OFF");
            guiDecor.get("ENTER NAME").setState("OFF");
        }
        
        if(!gameMode.equals("Welcome")){
            guiButtons.get("Welcome").setState("Off");
            guiButtons.get("Welcome").removeActionListener();
        }
        
        if(data.won()){
            guiDecor.get("WIN").setState("WIN");
        }else{
            guiDecor.get("WIN").setState("Default");
        }
        
        
        guiButtons.get("SRM").setState("On");
        if(!currentPath.equals("./data/The World/")){
            if(lm==1){
            guiButtons.get("LM").setState("On");
            }
            else{
                guiButtons.get("LM").setState("Default");
            }
            if(fm==1){
            guiButtons.get("FM").setState("On");
            }
            else{
                guiButtons.get("FM").setState("Default");
            }
            if(cm==1){
            guiButtons.get("CM").setState("On");
            }
            else{
                guiButtons.get("CM").setState("Default");
            }
        }else{
            guiButtons.get("CM").setState("Default");
            guiButtons.get("FM").setState("Default");
            guiButtons.get("LM").setState("Default");
        }
    }
    public String getState(String name){
        return guiButtons.get(name).getState();
    }
    public static void readAccts(){
        try{    accounts.clear();
		BufferedReader reader = new BufferedReader(new FileReader("./data/accounts/accounts.txt"));
                String line =reader.readLine();
                
                while(line.length()!=0 && !line.equals("")){
                        accounts.add(new Accounts(line));
			line = reader.readLine();
			}
                reader.close();
            }catch (Exception e){}
    }
    public void makeSprite(){
        sprites.clear();
        SpriteType sT;
        int x;
	int y = 80;
        
        for(int i=0;i<accounts.size();i++){
        sT=accounts.get(i).getST();
        
        if(i%2==0){
        x = 418;
        y+=50;
        }
        else{
        x = 643;
        }
        
	Sprite s = new Sprite(sT, x, y, 0, 0, "DEFAULT");
        sprites.add(s);
            
        }
        
    }
}
