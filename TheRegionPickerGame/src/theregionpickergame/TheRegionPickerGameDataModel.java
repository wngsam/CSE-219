package theregionpickergame;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import static theregionpickergame.TheRegionPickerGame.*;
import java.awt.Color;
import theregionpickergame.sprites.Region;
/**
 * TheRegionPickerGame - Data
 * @author Sam Wang
 */
public class TheRegionPickerGameDataModel extends MiniGameDataModel{
    
    private int seconds;
    private int currentREGIONS_FOUND;
    private int currentREGIONS_LEFT;
    private int currentINCORRECT_GUESSES;
    private int currentGAME_SCORE;
    
    public TheRegionPickerGameDataModel(){
        seconds=0;
        currentREGIONS_FOUND=REGIONS_FOUND;
        currentREGIONS_LEFT=REGIONS_LEFT;
        currentINCORRECT_GUESSES=INCORRECT_GUESSES;
        currentGAME_SCORE=GAME_SCORE;
    }
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
        System.out.println("FFS");
        try{
        if(map.getRegionArray()[0]!=null){
        if(x>0 && x<900 && y>0 && y<700){
            Region region = map.getRegionArray()[0];
            if(map.getImg().getRGB(x, y)!=map.getSea().getRGB()){
            if(region.getXcord().contains(x)&&region.getYcord().contains(y)){
                for(int i=0;i<region.getXcord().size();i++){
                       int a = (int) region.getXcord().get(i);
                       int b = (int) region.getYcord().get(i);
                       map.getImg().setRGB(a, b, Color.GREEN.getRGB());
               }
                correct();
            }
            else{
               Region reg = map.analyzeColor(map.getImg().getRGB(x, y));
               for(int i=0;i<reg.getXcord().size();i++){
                       int a = (int) reg.getXcord().get(i);
                       int b = (int) reg.getYcord().get(i);
                       map.getImg().setRGB(a, b, Color.RED.getRGB());
               }
               currentINCORRECT_GUESSES++;
               if(currentGAME_SCORE>0){
                            if(currentGAME_SCORE-100>0){
                           currentGAME_SCORE-=100;}
                            else{
                                currentGAME_SCORE=0;
                            }
                       }
             }
            }
        }
    }
                } catch (Exception e) {
            }
    }
    public void reset(MiniGame game) {
        seconds=0;
        currentREGIONS_FOUND=REGIONS_FOUND;
        currentREGIONS_LEFT=REGIONS_LEFT;
        currentINCORRECT_GUESSES=INCORRECT_GUESSES;
        currentGAME_SCORE=GAME_SCORE;
        beginGame();
    }
    public void updateAll(MiniGame game) {
    }
    public void updateDebugText(MiniGame game) {
    }
    public int getCurrentGameScore(){
        return currentGAME_SCORE;
    }
    public int getCurrentRegionsFound(){
        return currentREGIONS_FOUND;
    }
    public int getCurrentRegionsLeft(){
        return currentREGIONS_LEFT;
    }
    public int getCurrentIncorrectGuesses(){
        return currentINCORRECT_GUESSES;
    }
    public int getSeconds(){
        return seconds;
    }
    public void incSeconds(){
        seconds++;
    }
    public void modGameScore(int gs){
        if(currentGAME_SCORE>0){
            currentGAME_SCORE+=gs;}
    }
    public void correct(){
        map.resetRED();
        currentREGIONS_FOUND++;
        currentREGIONS_LEFT--;
        if(map.getRegionArray().length==1){
            endGameAsWin();
        }
        map.deleteRegion();
    }
}
