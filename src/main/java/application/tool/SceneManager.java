package application.tool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {

    public static Scene createScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/fxml/" + fxmlFile));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch(IOException e){
            throw new IOException ("The file was not found");
        }
        return scene;
    }

    public static void switchScene(ActionEvent e, String fxmlFile) throws IOException {
        Parent page = FXMLLoader.load(SceneManager.class.getResource("/fxml/" + fxmlFile));
        Scene scene = ((Node) e.getSource()).getScene();
        scene.setRoot(page);
    }

}
