/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stock_manage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Bisuraj Sharma
 */
public class products {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty stck;
    private final StringProperty minstock;
    private final StringProperty price;

   public products(){
   id=new SimpleStringProperty(this,"id");
   name=new SimpleStringProperty(this,"name");
   stck=new SimpleStringProperty(this,"stck");
   minstock=new SimpleStringProperty(this,"minstock");
   price=new SimpleStringProperty(this,"price");
   
   }

        public StringProperty idProperty() {
        return id;
    }
    public String getId() {
        return id.get();
    }
    public void setId(String newId){id.set(newId);}

    public StringProperty  nameProperty(){
        return name;
    }
public String getName() {
        return name.get();
    }
public void setName(String newName){name.set(newName);}
    public StringProperty stckProperty() {
        return stck;
    }
public String getstck() {
        return stck.get();
    }
public void setstck(String newstck){stck.set(newstck);}
    public StringProperty minstockProperty() {
        return minstock;
    }
    public String getminstock() {
        return minstock.get();
    }
    public void setminstock(String newminstock){minstock.set(newminstock);}
            
    public StringProperty priceProperty() {
        return price;
    }
    public String getprice() {
        return price.get();
    }
    public void setprice(String newprice){id.set(newprice);}
            
            @Override
            public String toString(){
                return String.format("%s[id=%s,name=%s",getId(),getstck(),getminstock(),getprice());
            }}

