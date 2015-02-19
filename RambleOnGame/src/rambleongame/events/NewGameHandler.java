package rambleongame.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rambleongame.RambleOnGame;
/**
 * TheRegionPickerGame - Exit Game
 * @author Sam Wang
 */
public class NewGameHandler implements ActionListener{
    private RambleOnGame game;
    
    public NewGameHandler(RambleOnGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		game.reset();
	}
}
