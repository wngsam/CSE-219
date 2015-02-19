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
public class FlagMode implements ActionListener{
    private RambleOnGame game;
    
    public FlagMode(RambleOnGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		if(game.getState("FM").equals("On")){gameMode="FM";}
                playList=loader.getSR();
                game.reset();
	}
}