package application.tool;

import javafx.stage.Stage;

public class StageManager {

    private static Stage stage;

    public static void setStage(Stage oldStage){
        stage = oldStage;
    }

    public static Stage getStage(){
        return stage;
    }

    public static void switchStage(Stage newStage){
        Stage oldStage = getStage();
        oldStage.close();
        setStage(newStage);
    }

    public static void configStage(String title, boolean isResizable, boolean isMaximized, boolean isCenterOnScreen){
        stage.setResizable(isResizable);
        stage.setMaximized(isMaximized);
        stage.setTitle(title);
        if (isCenterOnScreen) {
            stage.centerOnScreen();
        }
        stage.show();
    }



}
