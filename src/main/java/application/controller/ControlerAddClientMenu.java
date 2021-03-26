package application.controller;

import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControlerAddClientMenu implements Initializable {

    @FXML
    private Button home;

    private StageManager sm = StageManager.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void displayMainMenu(ActionEvent event) throws IOException {
        this.sm.switchScene(event, FxmlView.MAIN_MENU);
    }

}


