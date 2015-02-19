package rambleongame.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rambleongame.RambleOnGame;
import static rambleongame.RambleOnGame.*;

/**
 *
 * @author Sam
 */
public class NewAcctHandler implements ActionListener{
    private RambleOnGame game;
    
    public NewAcctHandler(RambleOnGame initGame)
	{
		game = initGame;
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameMode.equals("Accounts")){
        gameMode="Create";
        }
    }
}
