/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package stock_manage;

import helpers.DBconnection;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Bisuraj Sharma
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtuname;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
      String uname= txtuname.getText();
      String pass= txtpass.getText();
       if(uname.equals("")&&pass.equals("")){
           JOptionPane.showMessageDialog(null,"Your Username  and password can Not be empty");
       }
       else{
           con = DBconnection.getConnect();
           pst = con.prepareStatement("select*from users where username=? and password=?" );
           pst.setString(1,uname);
           pst.setString(2,pass);
           rs=pst.executeQuery();
           if(rs.next()){
               Parent part = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
           }
           else{
               JOptionPane.showMessageDialog(null,"Please Enter Valid Credential");
               txtuname.setText("");txtpass.setText("");
               txtuname.requestFocus();;
               
           }
       }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
