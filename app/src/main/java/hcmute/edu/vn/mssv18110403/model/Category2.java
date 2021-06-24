package hcmute.edu.vn.mssv18110403.model;

public class Category2 {
    int _id ;
    String _name;
    byte[] image;

    public Category2(){}
    public Category2(int id, String name, byte[] image){
        this._id  = id;
        this._name = name;
        this.image = image;
    }

    public Category2( String name, byte[] image){
        this._name = name;
        this.image = image;
    }

    public  int get_id(){return  this._id;}
    public void set_id(int id){this._id = id;}

    public String get_name(){return this._name;}
    public  void set_name(String name){this._name = name;}

    public byte[] getImage(){return this.image;}
    public  void setImage(byte[] image){this.image = image;}


    //public Date get_birthday(){return this._birthday;}
   // public  void set_birthday(Date birthday){this._birthday = birthday;}

}
