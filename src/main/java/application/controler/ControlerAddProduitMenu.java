package application.controler;

import application.entity.Produit;
import application.exception.AlreadyDisconnectException;
import application.exception.NotConnectException;
import application.exception.ToShortException;
import application.model.UtilisateurSession;
import application.service.ProduitService;
import application.tool.Utils;
import application.view.EditElement;
import application.view.ErrorMessages;
import application.view.FxmlView;
import application.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class ControlerAddProduitMenu implements Initializable {

    private UtilisateurSession utilisateurSession = UtilisateurSession.getInstance();

    private StageManager stageManager = StageManager.getInstance();

    private EditElement editElement = EditElement.getInstance();

    private ArrayList<Button> aButton;

    @Autowired
    private ProduitService produitService;

    @FXML
    private Text title;

    @FXML
    private Button homeButton;

    @FXML
    private Button validateButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button adminPanelButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Text errorText;

    @FXML
    private Button userButton;

    @FXML
    private ImageView genderImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.aButton = new ArrayList<>();
        this.aButton.add(this.validateButton);
        this.aButton.add(this.adminPanelButton);

        this.editElement.updateTitle(this.title);

        if (this.editElement.isConnected()){
            try{
                if (this.editElement.hasPermission()){
                    this.editElement.updateProfilButton(this.userButton, this.genderImage);
                    this.editElement.displayButton(this.aButton);
                }
                else{
                    this.editElement.hideButton(this.aButton);
                    this.editElement.disconnect();
                }
            }catch(NotConnectException | AlreadyDisconnectException e){
                this.editElement.forceDisconnection();
            }
        }
        else{
            this.editElement.forceDisconnection();
        }
    }

    @FXML
    public void displayMainMenu(ActionEvent event) throws IOException {
        this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
    }

    @FXML
    void submit(ActionEvent event) {
        if (this.priceTextField.getText().equals("") || this.productNameTextField.getText().equals("")){
            Utils.displayErrorMessage(this.errorText, ErrorMessages.MISS_FIELD_ERROR);
        }
        else{
            float fprice;
            String price = this.priceTextField.getText();

            try{
                fprice = Float.parseFloat(price);
                String productName = this.productNameTextField.getText();
                try {
                    if (Utils.testSizeText(productName)) {
                        Produit p = new Produit(productName, Float.parseFloat(price));
                        this.produitService.addOrUpdateProduct(p);
                        this.stageManager.switchScene(event, FxmlView.MAIN_MENU);
                        try{
                            Utils.displayInfoWindows("Produit crée avec succès");
                        }catch(IOException e){
                            e.getMessage();
                        }
                    }
                }catch(ToShortException e){
                    Utils.displayErrorMessage(this.errorText, ErrorMessages.TO_SHORT);
                }

            }catch(NumberFormatException e){
                Utils.displayErrorMessage(this.errorText, ErrorMessages.INCORRECT_FLOAT_TYPE);
            }
        }
    }

    @FXML
    public void disconnect(ActionEvent event) {
        try{
            this.editElement.disconnect();
        }catch (AlreadyDisconnectException e){
            this.editElement.forceDisconnection();
        }
    }
}

