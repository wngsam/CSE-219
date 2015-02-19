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
public class CapStart implements ActionListener{
    private RambleOnGame game;
    
    public CapStart(RambleOnGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		if(game.getState("CM").equals("On")){gameMode="CM";}
                playList=loader.getSR();
                game.reset();
	}
}