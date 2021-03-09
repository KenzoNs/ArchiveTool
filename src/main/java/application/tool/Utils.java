package application.tool;

import application.model.UserSession;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {

    public static void disconnect(){
        UserSession us = UserSession.getInstance();
        StageManager sm = StageManager.getInstance();
        if (us.getUser() == null){
            us.setUser(null);
        }
        try{
            sm.switchStage(new Stage());
            sm.getStage().setScene(SceneManager.createScene("loginMenu.fxml"));
            sm.configStage("Login Menu", false, false, true);
        }catch(IOException e){
            e.getCause();
        }

    }

}
