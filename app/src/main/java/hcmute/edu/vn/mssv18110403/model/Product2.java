package hcmute.edu.vn.mssv18110403.model;

public class Product2 {
    int _id ;
    String _name;
    int quantity;
    String description;
    double price;
    byte[] image;
    String note;
    int category;


    public Product2(){}
    public Product2(int id, String name, int quantity, String description, double price, byte[] image, String note, int category){
        this._id  = id;
        this._name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.image = image;
        this.note = note;
        this.category = category;
    }

    public Product2( String name, int quantity, String description, double price, byte[] image, String note, int category){
        this._name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.image = image;
        this.note = note;
        this.category = category;
    }


    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    //public Date get_birthday(){return this._birthday;}
   // public  void set_birthday(Date birthday){this._birthday = birthday;}

}
