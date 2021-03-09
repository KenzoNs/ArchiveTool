package application.tool;

import javafx.stage.Stage;

public class StageManager {

    private static Stage stage;

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

    public void setStage(Stage newStage){
        stage = newStage;
    }

    public Stage getStage(){
        return stage;
    }

    public void switchStage(Stage newStage){
        Stage oldStage = getStage();
        oldStage.close();
        setStage(newStage);
    }

    public void configStage(String title, boolean isResizable, boolean isMaximized, boolean isCenterOnScreen){
        stage.setResizable(isResizable);
        stage.setMaximized(isMaximized);
        stage.setTitle(title);
        if (isCenterOnScreen) {
            stage.centerOnScreen();
        }
        stage.show();
    }
}
