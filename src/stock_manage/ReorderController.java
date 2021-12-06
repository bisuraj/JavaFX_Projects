/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package stock_manage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bisuraj Sharma
 */
public class ReorderController implements Initializable {

   @FXML
    private Button back;

    @FXML
    private Button btnreorder;

    @FXML
    private Button tbndetails;

    @FXML
    private TextField txtmin;

    @FXML
    private TextField txtpid;

    @FXML
    private TextField txtpname;

    @FXML
    private TextField txtprice;
    Connection con;
    PreparedStatement pst;
        
 
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4306/usernames", "root","");
        }
        catch (ClassNotFoundException ex)
        {
                    ex.printStackTrace();
        }
        catch (SQLException ex)
        {
                     ex.printStackTrace();
                }
            }
    

    @FXML
    void details(ActionEvent event) {
        Connect();
        String p_id=txtpid.getText();
        String query = "select pname,price,minstock from products WHERE pid=(?)";
  
            try {
                pst = con.prepareStatement(query);
                pst.setString(1, p_id);
                ResultSet rs = pst.executeQuery();
                 while (rs.next()) {
                 String p_name=rs.getString("pname");
                 String p_price=rs.getString("price");
                 String p_mine=rs.getString("minstock");
                 txtpname.setText(p_name);
                 txtprice.setText(p_price);
                 txtmin.setText(p_mine);
             }
            }
         catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }

    @FXML
    void goback(ActionEvent event) throws IOException {
        
        Parent part = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void reorder(ActionEvent event) {
         Connect();
        String p_id=txtpid.getText();
        String query = "select stock,minstock from products WHERE pid=(?)";
  
            try {
                pst = con.prepareStatement(query);
                pst.setString(1, p_id);
                ResultSet rs = pst.executeQuery();
                 while (rs.next()) {
                 String p_stock=rs.getString("stock");
                 String p_min=rs.getString("minstock");
                int stck = Integer.parseInt(p_stock);
                int min = Integer.parseInt(p_min);
                if(stck<min){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reorder");
                alert.setHeaderText("Reorder");
                alert.setContentText("Reordered Successfully");
                alert.showAndWait();
                 }
                if(stck>min){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reorder");
                alert.setHeaderText("Reorder");
                alert.setContentText("You have Enough Stock");
                alert.showAndWait();
                }
             }
            }
         catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
