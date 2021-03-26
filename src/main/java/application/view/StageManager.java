package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class StageManager {

    private static Stage stage;
    private static FxmlView fxmlView;
    private static ApplicationContext applicationContext;

    private StageManager(){

    }

    private static class StageManagerHolder
    {
        private final static StageManager instance = new StageManager();
    }

    public static StageManager getInstance()
    {
        return StageManagerHolder.instance;
    }

    public void setApplicationContexte(ApplicationContext ac){
        applicationContext = ac;
    }

    //----------------------------------- Stage -----------------------------------------------------//

    public Stage getStage(){
        return stage;
    }

    public void setStage(Stage newStage){
        stage = newStage;
    }

    public void createStage(FxmlView target, boolean isResizable, boolean isMaximized, boolean isCenterOnScreen) throws IOException {
        switchStage(new Stage());
        fxmlView = target;
        stage.setScene(createScene());
        configStage(isResizable, isMaximized, isCenterOnScreen);
    }

    public void switchStage(Stage newStage){
        Stage oldStage = getStage();
        oldStage.close();
        stage = newStage;
    }

    public void configStage(boolean isResizable, boolean isMaximized, boolean isCenterOnScreen){
        stage.setResizable(isResizable);
        stage.setMaximized(isMaximized);
        stage.setTitle(fxmlView.getTitle());
        if (isCenterOnScreen) {
            stage.centerOnScreen();
        }
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.show();
    }

    //-----------------------------------------------------------------------------------------------//



    //-------------------------------------- Scene --------------------------------------------------//

    public void setFxmlView(FxmlView target) {
        fxmlView = target;
    }

    public Scene createScene() throws IOException {
        return new Scene(this.load());
    }

    public Parent load() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        loader.setLocation(StageManager.class.getResource("/fxml/" + fxmlView.getFxmlFile()));
        return loader.load();
    }

    public void switchScene(ActionEvent e, FxmlView target) throws IOException {
        fxmlView = target;
        Scene scene = ((Node) e.getSource()).getScene();
        stage.setTitle(fxmlView.getTitle());
        scene.setRoot(this.load());
    }

    //-----------------------------------------------------------------------------------------------//

}