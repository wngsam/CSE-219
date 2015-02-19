package rambleongame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import static rambleongame.RambleOnGame.*;

/**
 *
 * @author Sam
 */
public class RambleOnGameDataModel extends MiniGameDataModel{
    private long seconds;
    public static int currentREGIONS_FOUND=0;
    public static int currentREGIONS_LEFT;
    public static int currentINCORRECT_GUESSES=0;
    public static int currentGAME_SCORE=1000;
    private GregorianCalendar startTime;
    
    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
        
            if((gameMode.equals("SRM")||gameMode.equals("CM")||gameMode.equals("FM")||gameMode.equals("LM"))&&!won()){
            try{
                if(x>0 && x<900 && y>0 && y<700){
                    int c = loader.getMap().getMap().getRGB(x,y);
                    if(c==playList.get(0).getColor().getRGB()){
                        for(int i=0;i<playList.get(0).xcord().size();i++){
                            int a = playList.get(0).xcord().get(i);
                            int b = playList.get(0).ycord().get(i);
                            loader.getMap().getMap().setRGB(a, b, Color.GREEN.getRGB());
                        }
                        
                    playList=reduceList(playList);
                    correct();
                    }else{
                        
                        for(int i=1;i<playList.size();i++){
                            if(c==playList.get(i).getColor().getRGB()){
                                wrong();
                                for(int k=0;i<playList.get(0).xcord().size();k++){
                                int a = playList.get(i).xcord().get(k);
                                int b = playList.get(i).ycord().get(k);
                                loader.getMap().getMap().setRGB(a, b, Color.RED.getRGB());
                                }
                                i=playList.size();
                            }
                        }
                    }
                }
        
            }catch(Exception e){
            
            }
        }
        
        if(gameMode.equals("Navigation")){
        try{
        if(x>0 && x<900 && y>0 && y<700){
            if(loader.getMap().getMap().getRGB(x,y)!=loader.getMap().getSea().getRGB() && loader.getMap().getMap().getRGB(x,y)!=loader.getMap().getBG().getRGB()){
                for(int i=0;i<loader.getSR().size();i++){
                    for(int k=0;k<loader.getSR().get(i).xcord().size();k++){
                    if(loader.getSR().get(i).xcord().get(k)==x && loader.getSR().get(i).ycord().get(k)==y){
                        stk.push(currentPath);
                        stk.push(loader.getMap().getName());
                        currentPath=currentPath+loader.getSR().get(i).getName()+"/";
                        loadData(loader.getSR().get(i).getName());
                        k=loader.getSR().get(i).xcord().size();
                        i=loader.getSR().size();
                    }
                    }
                }
            }
        }
        }catch(Exception e){}
        }
        
        
    }

    @Override
    public void reset(MiniGame game) {
    startTime = new GregorianCalendar();
    
    currentREGIONS_FOUND=0;
    currentREGIONS_LEFT=loader.getSR().size();;
    currentINCORRECT_GUESSES=0;
    currentGAME_SCORE=1000;
        beginGame();
    }

    @Override
    public void updateAll(MiniGame game) {
        if(loader.getSR()!=null){
        currentREGIONS_LEFT=loader.getSR().size();
        }
        GregorianCalendar now = new GregorianCalendar();
        long diff = now.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = diff/1000L;
        if(!won()){seconds=numSeconds;}
        currentGAME_SCORE=1000-((currentINCORRECT_GUESSES*10)+(int)numSeconds);
    }

    @Override
    public void updateDebugText(MiniGame game) {
        
    }
    public ArrayList<subRegion> reduceList(ArrayList<subRegion> list){
        ArrayList<subRegion> temp = new ArrayList();
        
        for(int i=1;i<list.size();i++){
            temp.add(list.get(i));
        }
        return temp;
    }
    public void correct(){
        currentREGIONS_LEFT--;
        currentREGIONS_FOUND++;
        for(int k=0;k<playList.size();k++){
        for(int i=0;i<playList.get(k).xcord().size();i++){
           int a = playList.get(k).xcord().get(i);
           int b = playList.get(k).ycord().get(i);
           loader.getMap().getMap().setRGB(a, b, playList.get(k).getColor().getRGB());
           }
        }
        if(playList.size()==0){
            endGameAsWin();
        }
    }
    public void wrong(){
        currentINCORRECT_GUESSES++;
        if(currentGAME_SCORE-10>=0){currentGAME_SCORE-=10;}else{currentGAME_SCORE=0;}
    }
    public String getGameTimeText()
    {   if(won()){
        return getSecondsAsTimeText(seconds);
        }
        if (startTime == null)
        {
            return "";
        }
        GregorianCalendar now = new GregorianCalendar();
        long diff = now.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = diff/1000L;

        return getSecondsAsTimeText(numSeconds);
    }
    public String getSecondsAsTimeText(long numSeconds)
    {
        long numHours = numSeconds/3600;
        numSeconds = numSeconds - (numHours * 3600);
        long numMinutes = numSeconds/60;
        numSeconds = numSeconds - (numMinutes * 60);
        
        String timeText = "";
        if (numHours > 0)
        {
            timeText += numHours + ":";
        }
        timeText += numMinutes + ":";
        if (numSeconds < 10)
        {
            timeText += "0" + numSeconds;
        }
        else
        {
            timeText += numSeconds;
        }
        return timeText;
    }
}
