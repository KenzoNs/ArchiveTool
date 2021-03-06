package application;

import application.model.SpringFXMLLoader;
import application.tool.StageManager;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.io.IOException;

@Configuration
@EnableJpaRepositories("application.repository")
public class JavafxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent root;

    @Override
    public void init() throws IOException {

        String[] args = getParameters().getRaw().toArray(new String [0]);

        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
            ac.registerBean(Application.class, () -> JavafxApplication.this);
            ac.registerBean(Parameters.class, this::getParameters);
            ac.registerBean(HostServices.class, this::getHostServices);
        };
        this.applicationContext = new SpringApplicationBuilder().sources(ArchiveToolApplication.class).initializers(initializer).run(args);
        this.root = this.applicationContext.getBean(SpringFXMLLoader.class).load("loginMenu.fxml");
    }

    @Override
    public void start(Stage primaryStage){
        StageManager.setStage(primaryStage);
        StageManager.getStage().setScene(new Scene(this.root));
        StageManager.configStage("Login Menu", false, false, true);
    }

    @Override
    public void stop(){
        System.out.println("stop");
        this.applicationContext.close();
        Platform.exit();
    }
}
