package application.tool;

import application.exception.AlreadyDisconnectException;
import application.exception.PhoneNumberException;
import application.model.UtilisateurSession;
import application.view.ErrorMessages;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean testPhoneNumberFormat(String phoneNumber) throws PhoneNumberException {
        Pattern pattern = Pattern.compile("^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            return true;
        }
        throw new PhoneNumberException();
    }



    public static void displayErrorMessage(Text text, ErrorMessages message){
        text.setText(message.getText());
        text.setVisible(true);
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                text.setText("None");
                text.setVisible(false);
            }
        });
        new Thread(sleeper).start();
    }

    public static void displayInfoWindows(String text) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");

        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

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
