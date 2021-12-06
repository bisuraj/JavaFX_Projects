/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stock_manage;

/**
 *
 * @author Bisuraj Sharma
 */
public class users {
    int pid ;
    String pname;
	int  stock, min_stock , price;

    public void setId(int pid) {
        this.pid = pid;
    }

    public void setpname(String pname) {
        this.pname = pname;
    }

    public void setstock(int stock) {
        this.stock = stock;
    }

    public void setmin_stock(int min_stock) {
        this.min_stock = min_stock;
    }

    public void setType(int type) {
        this.price = price;
    }

    public int getId() {
        return pid;
    }

    public String getpname() {
        return pname;
    }

    public int getstock() {
        return stock;
    }

    public int getmin_stock() {
        return min_stock;
    }

    public int getprice() {
        return price;
    }

    public users(int id ,String pname, int stock, int min_stock, int type) {
        this.pid = pid;
        this.pname = pname;
        this.stock = stock;
        this.min_stock = min_stock;
        this.price = price;
    }
    
}
