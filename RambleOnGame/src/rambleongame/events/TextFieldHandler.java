package rambleongame.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rambleongame.RambleOnGame;
import static rambleongame.RambleOnGame.*;

/**
 *
 * @author Sam
 */
public class TextFieldHandler implements ActionListener{
       
    private RambleOnGame game;
    public TextFieldHandler(RambleOnGame initGame)
	{
            game = initGame;
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameMode.equals("Create")){
        String text = game.text.getText();
        game.text.selectAll();
        boolean boo = false;
        if(text.trim().length()==0){
            boo=true;
        }
        ArrayList<String> str = new ArrayList();
        try{    
		BufferedReader reader = new BufferedReader(new FileReader("./data/accounts/accounts.txt"));
                String line =reader.readLine();

                while(line.length()!=0 && !line.equals("") && line!=null && boo==false){
                        if(line.equals(text)){
                            boo = true;
                        }
                        str.add(line);
			line = reader.readLine();
			}

                reader.close();
            }catch (Exception ex){}
        
                if(boo==false){
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/accounts/accounts.txt"));
                        for(int i=0;i<str.size();i++){
                            writer.write(str.get(i));
                            writer.newLine();
                        }
                        writer.write(text);
                        writer.close();
                    }catch (Exception ex){}
                    gameMode="Navigation";
                    
                    File dir = new File(new String("./data/accounts/")+text);
                    dir.mkdir();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Name already exist or is empty/invalid please try a different name.");
                }
        }
    }
}
