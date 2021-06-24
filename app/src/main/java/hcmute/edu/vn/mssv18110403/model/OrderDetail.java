package hcmute.edu.vn.mssv18110403.model;

public class OrderDetail {
    int id;
    int customer_id ;
    int product_id;
    int quantity;
    double price;

    public OrderDetail(){}
    public OrderDetail(int id, int customer_id, int product_id, int quantity, double price){
       this.id = id;
       this.customer_id = customer_id;
       this.product_id  = product_id;
       this.quantity = quantity;
       this.price = price;
    }

    public OrderDetail(  int customer_id, int product_id, int quantity, double price){
        this.customer_id = customer_id;
        this.product_id  = product_id;
        this.quantity = quantity;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
