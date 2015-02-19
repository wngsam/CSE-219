package theregionpickergame;
import java.awt.image.BufferedImage;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import theregionpickergame.events.NewGameHandler;
import theregionpickergame.events.ExitGameHandler;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Arrays;
/**
 * TheRegionPickerGame - Bhutan Edition
 * CSE 219 HW3
 * @author Sam Wang ID:108107971
 */
public class TheRegionPickerGame extends MiniGame{

        public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 768;
	public static final float BOUNDARY_TOP = 0.05f;
	public static final float BOUNDARY_BOTTOM = 0.05f;
	public static final float BOUNDARY_LEFT = 0.01f;
	public static final float BOUNDARY_RIGHT = 0.01f;
        
        public static final int REGIONS_FOUND =0;
        public static final int REGIONS_LEFT =20;
        public static final int INCORRECT_GUESSES=0;
        public static final int GAME_SCORE=1000;
        
        public static final String MAP_TYPE = "MAP_TYPE";
        public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
        public static final String LOGO_TYPE ="LOGO_TYPE";
        public static final String START_TYPE="START_TYPE";
        public static final String EXIT_TYPE="EXIT_TYPE";
        public static final String WIN_DISPLAY_TYPE="WIN_DISPLAY_TYPE";
        
        public static final String DEFAULT_STATE = "DEFAULT_STATE";
        public static final String PLAY_STATE = "PLAY_STATE";
        
        public static final int FRAME_RATE = 30;
	public static final String APP_TITLE = "TheRegionPickerGame";
       
        private boolean timerflag = false;
        private Timer time;
        
        public static Map map = new Map();
        
        public TheRegionPickerGame(){
		super(APP_TITLE, FRAME_RATE);
	}
    
    public void initData(){
                data = new TheRegionPickerGameDataModel();
		data.setGameDimensions(GAME_WIDTH, GAME_HEIGHT);
		boundaryLeft 	= BOUNDARY_LEFT	* GAME_WIDTH;
		boundaryRight 	= GAME_WIDTH 	- (GAME_WIDTH * BOUNDARY_RIGHT);
		boundaryTop 	= BOUNDARY_TOP 	* GAME_HEIGHT;
		boundaryBottom 	= GAME_HEIGHT 	- (GAME_HEIGHT * BOUNDARY_BOTTOM);
        }
    public void initGUIControls(){
            canvas = new TheRegionPickerGamePanel(this, (TheRegionPickerGameDataModel)data);
            
                BufferedImage img;
		float x, y;
		SpriteType sT;
		Sprite s;
                Graphics2D g2d;
                String str;
                
                //Main Screen BG
                sT = new SpriteType(BACKGROUND_TYPE);
		img = loadImage("./data/images/Background.png");
		sT.addState(DEFAULT_STATE, img);
		x = 0;
		y = 0;
		s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
		guiDecor.put(BACKGROUND_TYPE, s);
                
                //Win Screen
                sT = new SpriteType(WIN_DISPLAY_TYPE);
                img = loadImage("./data/images/victory.png");
                sT.addState(PLAY_STATE,img);
                img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
                sT.addState(DEFAULT_STATE, img);
                x = 50;
                y = 7;
                s = new Sprite(sT,x,y,0,0,DEFAULT_STATE);
                guiDecor.put(WIN_DISPLAY_TYPE,s);
                
                //MAP
                s = map.getSprite();
		guiDecor.put(MAP_TYPE, s);

                //Top Right Icon
                sT = new SpriteType(LOGO_TYPE);
		img = loadImage("./data/images/Logo.png");
		sT.addState(DEFAULT_STATE, img);
		x = 1008;
		y = 0;
		s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
		guiDecor.put(LOGO_TYPE, s);
                
                //TITLE
                sT = new SpriteType("Title");
                img = new BufferedImage(700, 50, BufferedImage.TYPE_4BYTE_ABGR);
                g2d = img.createGraphics();
                g2d.setColor ( new Color ( 240, 240, 220 ) );
                g2d.setFont(new Font(null, Font.PLAIN, 50));
                g2d.drawString("Kingdom Of Bhutan",165,40);
                sT.addState(PLAY_STATE, img);
                x =100;
                y =50;
                img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		sT.addState(DEFAULT_STATE, img);
                s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
		guiDecor.put("Title", s);
                
                //START Button
                sT = new SpriteType(START_TYPE);
		img = loadImage("./data/images/START.png");
		sT.addState(DEFAULT_STATE, img);
		x = 1008;
		y = 124;
		s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
		guiButtons.put(START_TYPE, s);
                
                //EXIT Button
                sT = new SpriteType(EXIT_TYPE);
		img = loadImage("./data/images/EXIT.png");
		sT.addState(DEFAULT_STATE, img);
		x = 1144;
		y = 124;
		s = new Sprite(sT, x, y, 0, 0, DEFAULT_STATE);
		guiButtons.put(EXIT_TYPE, s);
        }
    public void initGUIHandlers() 
	{
            NewGameHandler ngh = new NewGameHandler(this);
            guiButtons.get(START_TYPE).setActionListener(ngh);
            ExitGameHandler egh = new ExitGameHandler(this);
            guiButtons.get(EXIT_TYPE).setActionListener(egh);
        }   
    public void reset()
	{   guiDecor.get(WIN_DISPLAY_TYPE).setState(DEFAULT_STATE);
            map.initArray();
            map.resetColor();
            Collections.shuffle(Arrays.asList(map.getRegionArray()));
                time = new Timer((TheRegionPickerGameDataModel)data);
                time.start();
            guiDecor.get(MAP_TYPE).setState(PLAY_STATE);
            guiDecor.get("Title").setState(PLAY_STATE);
            data.reset(this);
        }
    public void updateGUI()
	{   
            if(data.won()){
                guiDecor.get(WIN_DISPLAY_TYPE).setState(PLAY_STATE);
            }
        }
    public static void main(String[] args) {
        TheRegionPickerGame game = new TheRegionPickerGame();
        game.startGame();
    }
}
