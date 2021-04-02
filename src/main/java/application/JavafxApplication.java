package application;

import application.view.FxmlView;
import application.view.StageManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.io.IOException;

@Configuration

@EnableJpaRepositories("application.repository")
public class JavafxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent root;
    private StageManager sm = StageManager.getInstance();

    @Override
    public void init() throws IOException {
        this.applicationContext = SpringApplication.run(ArchiveToolApplication.class);
        sm.setApplicationContexte(this.applicationContext);
        sm.setFxmlView(FxmlView.LOGIN_MENU);
        this.root = sm.load();
    }

    @Override
    public void start(Stage primaryStage){
        sm.setStage(primaryStage);
        sm.getStage().setScene(new Scene(this.root));
        sm.configStage(false, false, false);
    }

    @Override
    public void stop(){
        System.out.println("Application close");
        this.applicationContext.close();
        Platform.exit();
    }
}
