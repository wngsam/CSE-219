package theregionpickergame;
/**
 * TheRegionPickerGame - Timer Class
 * @author Sam
 */
public class Timer extends Thread{
    private TheRegionPickerGameDataModel data;
    public Timer(TheRegionPickerGameDataModel initData){
        data = initData;
    }
    public void run(){
        while(data.inProgress()){
            data.incSeconds();
            
            if(data.getCurrentGameScore()>0){
            data.modGameScore(-1);
            }
            try { Thread.sleep(1000); }
            catch(InterruptedException ie) {}  
            }
                }
}
