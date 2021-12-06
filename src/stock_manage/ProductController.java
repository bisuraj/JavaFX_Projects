/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Bisuraj Sharma
 */
public class ProductController implements Initializable {

    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnremove;
    @FXML
    private Button btnback;
    @FXML
    private TableView<products> main_table;

    @FXML
    private TableColumn<products, String> pid;
    @FXML
    private TableColumn<products, String> pname;
    @FXML
    private TableColumn<products, String> stock;
    @FXML
    private TableColumn<products, String> minstock;
    @FXML
    private TableColumn<products, String> price;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtquantity;
    @FXML
    private TextField txtmin;
    @FXML
    private TextField txtprice;
        @FXML
    void back(ActionEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(part);
                Image icon =new Image(getClass().getResourceAsStream("mainlogo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Stock Management");
        stage.setScene(scene);
        stage.show();
    }
     PreparedStatement pst4;
    @FXML
    void update(ActionEvent event) {
                Connect();
        String pid = txtid.getText();
        String pname = txtname.getText();
        String stock = txtquantity.getText();
        String minstock = txtmin.getText();
        String price = txtprice.getText();
 try {
            pst4 = con.prepareStatement("update products SET pname=?,stock=?,minstock=?,price=? where pid=?");
            pst4.setString(5, pid);
            pst4.setString(1, pname);
            pst4.setString(2, stock);
            pst4.setString(3, minstock);
            pst4.setString(4, price);
            int status = pst4.executeUpdate();
             
             if(status==1)
             { 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Products");
                alert.setContentText("products Updated Successfully");
                alert.showAndWait();
                table();
                txtid.setText("");
                txtname.setText("");
                txtquantity.setText("");
                txtmin.setText("");
                txtprice.setText("");

                txtid.requestFocus();
                
             }
             else
             {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Products");
                alert.setContentText("products Updating Failed");
                alert.showAndWait();
             }
            } 
          catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
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
    
    
        @FXML
    void Add(ActionEvent event) {
        Connect();
        String pid = txtid.getText();
        String pname = txtname.getText();
        String stock = txtquantity.getText();
        String minstock = txtmin.getText();
        String price = txtprice.getText();

       try {
            pst = con.prepareStatement("insert into products(pid,pname,stock,minstock,price)values(?,?,?,?,?)");
            pst.setString(1, pid);
            pst.setString(2, pname);
            pst.setString(3, stock);
            pst.setString(4, minstock);
            pst.setString(5, price);
            int status = pst.executeUpdate();
             
             if(status==1)
             { 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Member");
                alert.setContentText("products Addedd Successfully");
                alert.showAndWait();
                table();
                txtid.setText("");
                txtname.setText("");
                txtquantity.setText("");
                txtmin.setText("");
                txtprice.setText("");

                txtid.requestFocus();
                
             }
             else
             {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Products");
                alert.setContentText("products Adding Failed");
                alert.showAndWait();
             }
            } 
          catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void table()
      {
          ObservableList<products> productss = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select pid,pname,stock,minstock,price from products");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            products products = new products();
            products.setId(rs.getString("pid"));
            products.setName(rs.getString("pname"));
            products.setstck(rs.getString("stock"));
            products.setminstock(rs.getString("minstock"));
            products.setprice(rs.getString("price"));
            productss.add(products);
       }
    } 
                main_table.setItems(productss);
                pid.setCellValueFactory(f -> f.getValue().idProperty());
                pname.setCellValueFactory(f -> f.getValue().nameProperty());
                stock.setCellValueFactory(f -> f.getValue().stckProperty());
                minstock.setCellValueFactory(f -> f.getValue().minstockProperty());
                price.setCellValueFactory(f -> f.getValue().priceProperty());
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
}
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Connect();
       table();
    }    
    
    
}