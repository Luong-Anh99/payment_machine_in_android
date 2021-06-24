package hcmute.edu.vn.mssv18110403.model;

public class Cart {
    int id_product;
    String name;
    int quantity;
    double price;
    byte[] image;


    public Cart(){}
    public Cart(int id_product ,String name, int quantity, double price, byte[] image){
        this.id_product = id_product;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
