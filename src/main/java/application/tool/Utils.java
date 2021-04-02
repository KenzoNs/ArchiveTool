package application.tool;

import application.exception.AlreadyDisconnectException;
import application.exception.PhoneNumberException;
import application.exception.ToShortException;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean testPhoneNumberFormat(String phoneNumber) throws PhoneNumberException {
        Pattern pattern = Pattern.compile("^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        }
        throw new PhoneNumberException();
    }

    public static boolean testSizeText(String text) throws ToShortException {
        if (text.length() >= 3){
            return true;
        }
        throw new ToShortException();
    }

    public static void displayErrorMessage(Text text, ErrorMessages message){
        text.setText(message.getText());
        text.setVisible(true);
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(4000);
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
