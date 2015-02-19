package theregionpickergame.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import theregionpickergame.TheRegionPickerGame;
/**
 * TheRegionPickerGame - Start Game
 * @author Sam Wang
 */
public class NewGameHandler implements ActionListener{
    private TheRegionPickerGame game;
    
    public NewGameHandler(TheRegionPickerGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		game.reset();
	}
}
