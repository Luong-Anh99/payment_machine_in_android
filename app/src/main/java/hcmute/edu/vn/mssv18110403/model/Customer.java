package hcmute.edu.vn.mssv18110403.model;

public class Customer {
    int _id ;
    String _name;
    String _sex;
    String _phone_number;
    String _address;

    public Customer(){}
    public Customer(int id, String name, String sex, String phone_number, String address){
        this._id  = id;
        this._name = name;
        this._sex = sex;
        this._phone_number = phone_number;
        this._address = address;
    }

    public Customer(String name, String sex, String phone_number, String address){
        this._name = name;
        this._sex = sex;
        this._phone_number = phone_number;
        this._address = address;
    }

    public  int get_id(){return  this._id;}
    public void set_id(int id){this._id = id;}

    public String get_name(){return this._name;}
    public  void set_name(String name){this._name = name;}

    public String get_sex(){return this._sex;}
    public  void set_sex(String sex){this._sex = sex;}

    public String get_phone_number(){return this._phone_number;}
    public  void set_phone_number(String phone_number){this._phone_number = phone_number;}

    public String get_address(){return this._address;}
    public  void set_address(String address){this._address = address;}

    //public Date get_birthday(){return this._birthday;}
   // public  void set_birthday(Date birthday){this._birthday = birthday;}

}
