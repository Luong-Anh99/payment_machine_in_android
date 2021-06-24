package hcmute.edu.vn.mssv18110403.model;

public class Account {
    int _id ;
    String _name;
    String password;
    String email;

    public Account(){}
    public Account(int id, String name, String password, String email){
        this._id  = id;
        this._name = name;
        this.password = password;
        this.email = email;
    }
    public Account( String name, String password, String email){
        this._name = name;
        this.password = password;
        this.email = email;
    }
    public  int get_id(){return  this._id;}
    public void set_id(int id){this._id = id;}

    public String get_name(){return this._name;}
    public  void set_name(String name){this._name = name;}

    public String getPassword(){return this.password;}
    public  void setPassword(String password){this.password = password;}

    public String getEmail(){return this.email;}
    public  void setEmail(String email){this.email = email;}
}
