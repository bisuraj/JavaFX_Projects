
package stock_manage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bisuraj Sharma
 */
public class DashboardController implements Initializable {

       @FXML
    private Button logoutbtn;

    @FXML
    private Button productbtn;

    @FXML
    private Button reorderbtn;

    @FXML
    private Button salebtn;

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
        Image icon =new Image(getClass().getResourceAsStream("mainlogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void product(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("product.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
                Image icon =new Image(getClass().getResourceAsStream("mainlogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void reorder(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("reorder.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
                Image icon =new Image(getClass().getResourceAsStream("mainlogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Reorder");
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    void sale(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("sale.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
                Image icon =new Image(getClass().getResourceAsStream("mainlogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Sale");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
