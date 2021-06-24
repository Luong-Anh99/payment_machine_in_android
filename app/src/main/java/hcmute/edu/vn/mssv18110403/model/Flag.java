package hcmute.edu.vn.mssv18110403.model;

public class Flag {
    int _id ;
    String _name;

    public Flag(){}
    public Flag (int id, String name){
        this._id  = id;
        this._name = name;
    }

    public Flag (  String name){
        this._name = name;

    }

    public  int get_id(){return  this._id;}
    public void set_id(int id){this._id = id;}

    public String get_name(){return this._name;}
    public  void set_name(String name){this._name = name;}
}
