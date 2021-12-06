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
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bisuraj Sharma
 */
public class SaleController implements Initializable {


    @FXML
    private Button btnback;

    @FXML
    private Button btncheck;

    @FXML
    private Button btnsale;

    @FXML
    private TextField txtpid;

    @FXML
    private TextField txtpname;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtquantity;

    @FXML
    void goback(ActionEvent event) throws IOException {
                Parent part = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();

    }
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
       Connection conn;
    PreparedStatement pst2;
    PreparedStatement pst3;
    
      @FXML
    void check(ActionEvent event) throws SQLException {
        Connect();
        String p_id=txtpid.getText();
        String query = "select pname,price from products WHERE pid=(?)";
  
            try {
                pst2 = con.prepareStatement(query);
                pst2.setString(1, p_id);
                ResultSet rs = pst2.executeQuery();
                 while (rs.next()) {
                 String p_name=rs.getString("pname");
                 String p_price=rs.getString("price");
                 txtpname.setText(p_name);
                 txtprice.setText(p_price);
                 
             }
            }
         catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    
    @FXML
    void sale(ActionEvent event) throws SQLException {
     try {
         Connect();
        String pid=txtpid.getText();
        String query2="update products SET stock=? where pid=?";
        String query1 = "select stock from products WHERE pid=(?)";
        pst = con.prepareStatement(query1);
        pst.setString(1, pid);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
        String st_ck=rs.getString("stock");
        String qn=txtquantity.getText();
        int stck=Integer.parseInt(st_ck);
        int quan=Integer.parseInt(qn);
       int remain =stck-quan;
       String rem =String.valueOf(remain);
       PreparedStatement statement =con.prepareStatement(query2);
       statement.setString(1,rem);
       statement.setString(2,pid);
        int rows = statement.executeUpdate();
        if(rows>0){
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Transaction");
                alert.setContentText("Sale Completed Sucessfully");
                alert.showAndWait();
              
                txtpname.setText("");
                txtquantity.setText("");
                txtpid.setText("");
                txtprice.setText("");


                txtpid.requestFocus();
                
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
        Connect();
    }    
    
}
