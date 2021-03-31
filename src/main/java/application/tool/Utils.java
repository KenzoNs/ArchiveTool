package application.tool;

import application.exception.AlreadyDisconnectException;
import application.model.UtilisateurSession;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.scene.control.Button;

import java.io.IOException;
import java.security.MessageDigest;

public class Utils {

    public static boolean hasPermission(){
        return UtilisateurSession.getInstance().getUtilisateur().getPrivilege_utilisateur() == 3;
    }

    public static boolean isConnected(){
        return UtilisateurSession.getInstance().getUtilisateur() != null;
    }


    public static void updateButton(Button target){
        if (hasPermission()){
            target.setVisible(true);
            target.setManaged(true);
            target.setDisable(false);
        }
        else{
            target.setVisible(false);
            target.setManaged(false);
            target.setDisable(true);
        }
    }

    public static void disconnect() throws AlreadyDisconnectException {
        UtilisateurSession us = UtilisateurSession.getInstance();
        StageManager sm = StageManager.getInstance();
        if (us.getUtilisateur() != null){
            us.cleanUserSession();
            try{
                sm.createStage(FxmlView.LOGIN_MENU, false, false, true);
            }catch(IOException e){
                e.getCause();
            }
        }
        else {
            forceDisconnection();
            throw new AlreadyDisconnectException();
        }
    }

    public static void forceDisconnection(){
        try{
            StageManager.getInstance().createStage(FxmlView.LOGIN_MENU, false, false, true);
        }catch(IOException e){
            e.getCause();
        }
    }

    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
