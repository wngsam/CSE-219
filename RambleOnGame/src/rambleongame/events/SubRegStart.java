package rambleongame.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import rambleongame.RambleOnGame;
import static rambleongame.RambleOnGame.*;
/**
 * TheRegionPickerGame - Exit Game
 * @author Sam Wang
 */
public class SubRegStart implements ActionListener{
    private RambleOnGame game;
    
    public SubRegStart(RambleOnGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		if(game.getState("SRM").equals("On")){gameMode="SRM";}
                playList=loader.getSR();
                game.reset();
	}
}
