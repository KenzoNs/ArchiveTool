package application.view;

import application.exception.AlreadyDisconnectException;
import application.exception.NotConnectException;
import application.model.UtilisateurSession;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

public class EditElement {
    private static UtilisateurSession us = UtilisateurSession.getInstance();
    private static StageManager sm = StageManager.getInstance();

    private EditElement(){
    }

    private static class EditElementHolder
    {
        private final static EditElement instance = new EditElement();
    }

    public static EditElement getInstance()
    {
        return EditElement.EditElementHolder.instance;
    }

    public void updateProfilButton(Button target, ImageView imageGender) {
        target.setText(us.getUtilisateur().getIdentifiant_utilisateur());
        imageGender.setImage(us.getGenderImage());
    }

    public void updateTitle(Text title){
        title.setText(sm.getFxmlView().getTitle());
    }

    public boolean hasPermission() throws NotConnectException{
        if(isConnected()){
            return us.getUtilisateur().getPrivilege_utilisateur() == 3;
        }
        throw new NotConnectException();
    }

    public boolean isConnected(){
        return us.getUtilisateur() != null;
    }

    public void displayButton(ArrayList<Button> aButton){
        for (Button button: aButton) {
            button.setVisible(true);
            button.setManaged(true);
            button.setDisable(false);
        }
    }

    public void hideButton(ArrayList<Button> aButton){
        for (Button button: aButton) {
            button.setVisible(false);
            button.setManaged(false);
            button.setDisable(true);
        }
    }

    public void disconnect() throws AlreadyDisconnectException {
        if (isConnected()){
            us.cleanUserSession();
            try{
                sm.createStage(FxmlView.LOGIN_MENU, false, false, true);
            }catch(IOException e){
                e.getCause();
            }
        }
        else {
            throw new AlreadyDisconnectException();
        }
    }

    public void forceDisconnection(){
        try{
            StageManager.getInstance().createStage(FxmlView.LOGIN_MENU, false, false, true);
        }catch(IOException e){
            e.getCause();
        }
    }
}
