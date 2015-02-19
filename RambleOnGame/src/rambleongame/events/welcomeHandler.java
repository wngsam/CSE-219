package rambleongame.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rambleongame.RambleOnGame;
import static rambleongame.RambleOnGame.*;
/**
 * TheRegionPickerGame - Exit Game
 * @author Sam Wang
 */
public class welcomeHandler implements ActionListener{
    private RambleOnGame game;
    
    public welcomeHandler(RambleOnGame initGame)
	{
		game = initGame;
	}
    public void actionPerformed(ActionEvent ae)
	{
		if(!gameMode.equals("Accounts")){gameMode="Accounts";}
	}
}
