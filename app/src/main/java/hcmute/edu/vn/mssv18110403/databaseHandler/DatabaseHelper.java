package hcmute.edu.vn.mssv18110403.databaseHandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110403.model.Account;
import hcmute.edu.vn.mssv18110403.model.Category2;
import hcmute.edu.vn.mssv18110403.model.Customer;
import hcmute.edu.vn.mssv18110403.model.Employee;
import hcmute.edu.vn.mssv18110403.model.Flag;
import hcmute.edu.vn.mssv18110403.model.OrderDetail;
import hcmute.edu.vn.mssv18110403.model.Product2;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cuhangManage";
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SEX = "sex";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_ADDRESS = "address";

    private static final String TABLE_CUSTOMER = "customer";
    private static final String KEY_ID1 = "id";
    private static final String KEY_NAME1 = "name";
    private static final String KEY_SEX1 = "sex";
    private static final String KEY_PH_NO1 = "phone_number";
    private static final String KEY_ADDRESS1 = "address";

    private static final String TABLE_CUSTOMER3 = "category2";
    private static final String KEY_ID3 = "id";
    private static final String KEY_NAME3 = "name";
    private static final String KEY_IMAGE3 = "image";

    private  static  final  String TABLE_PRODUCT = "product2";
    private  static  final  String KEY_ID_PRODUCT = "id";
    private  static  final  String KEY_NAME_PRODUCT = "name";
    private  static  final  String KEY_QUAN_PRODUCT = "quantity";
    private  static  final  String KEY_DES_PRODUCT = "description";
    private  static  final  String KEY_PRICE_PRODUCT = "price";
    private  static  final  String KEY_IMAGE_PRODUCT = "image";
    private  static  final  String KEY_NOTE_PRODUCT = "note";
    private  static  final  String KEY_CATEGORY_PRODUCT = "category";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT, "
                + KEY_SEX + " TEXT, "
                + KEY_PH_NO + " TEXT, "
                + KEY_ADDRESS + " TEXT "
                + ")";

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER3 + "("
                + KEY_ID3 + " INTEGER PRIMARY KEY,"
                + KEY_NAME3 + " TEXT, "
                + KEY_IMAGE3 + " BLOB"
                + ")";
        String CREATE_CUSTOMER_TABLE3 = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY,"
                + KEY_NAME1 + " TEXT, "
                + KEY_SEX1 + " TEXT, "
                + KEY_PH_NO1 + " TEXT, "
                + KEY_ADDRESS1 + " TEXT "
                + ")";

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + KEY_ID_PRODUCT + " INTEGER PRIMARY KEY,"
                + KEY_NAME_PRODUCT + " TEXT, "
                + KEY_QUAN_PRODUCT + " INTEGER, "
                + KEY_DES_PRODUCT + " TEXT, "
                + KEY_PRICE_PRODUCT + " REAL, "
                + KEY_IMAGE_PRODUCT + " BLOB, "
                + KEY_NOTE_PRODUCT + " TEXT, "
                + KEY_CATEGORY_PRODUCT + " INTEGER,"
                + " FOREIGN KEY " + "("+ KEY_CATEGORY_PRODUCT +")" + "  REFERENCES " + " category2" + "("+"id"+")" + ")";



        String CREATE_ORDERDETAIL_TABLE = "CREATE TABLE " + "orderDetail" + "("
                + "id" + " INTEGER PRIMARY KEY,"
                + "customer" + " INTEGER,"
                + "product" + " INTEGER,"
                + "quantity" + " INTEGER,"
                + "price" + " REAL,"
                + " FOREIGN KEY" + "("+ "customer"+")" + " REFERENCES" + " customer" + "("+"id"+"),"
                + " FOREIGN KEY" + "("+ "product"+")" + " REFERENCES" + " product" + "("+"id"+")" + ")";

        String CREATE_CART_TABLE = "CREATE TABLE" + " cart" + "("
                + "id" + " INTEGER PRIMARY KEY,"
                + "customer" + " INTEGER,"
                + "product" + " INTERGER,"
                + "price" + " REAL,"
                + "quantity" + " INTERGER,"
                + "sub_total" + " REAL,"
                + " FOREIGN KEY" + "("+ "customer"+")" + " REFERENCES" + " customer" + "("+"id"+"),"
                + " FOREIGN KEY" + "("+ "product"+")" + " REFERENCES" + " product" + "("+"id"+")" + ")";

        String CREATE_HERO_TABLE = "CREATE TABLE " + "flag" + "("
                + "id" + " INTEGER PRIMARY KEY,"
                + "name" + " TEXT"
                + ")";

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + "account" + "("
                + "id" + " INTEGER PRIMARY KEY,"
                + "name" + " TEXT,"
                + "password" + " TEXT,"
                + "email" + " TEXT"
                + ")";

        db.execSQL(CREATE_CUSTOMER_TABLE3);
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_ORDERDETAIL_TABLE);
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_HERO_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }


    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", account.get_name());
        values.put("password", account.getPassword());
        values.put("email", account.getEmail());
        // Inserting Row
        db.insert("account", null, values);
        db.close(); // Closing database connection
    }
    public List<Account> getAllAccount() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "account";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.set_id(Integer.parseInt(cursor.getString(0)));
                account.set_name(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                // Adding contact to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }
        // return contact list
        return accountList;
    }

    public void addFlag(Flag flag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", flag.get_name()); // Contact Name
        // Inserting Row
        db.insert("flag", null, values);
        db.close(); // Closing database connection
    }
    public List<Flag> getAllFlag() {
        List<Flag> employeeList = new ArrayList<Flag>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "flag";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Flag employee = new Flag();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        // return contact list
        return employeeList;
    }



    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        // Create tables again
        onCreate(db);
    }



    //OrderDetail
    public void addOrderDetail(OrderDetail employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("customer", employee.getCustomer_id()); // Contact Name
        values.put("product", employee.getProduct_id());
        values.put("quantity", employee.getQuantity());
        values.put("price", employee.getPrice());
        // Inserting Row
        db.insert("orderDetail", null, values);
        db.close(); // Closing database connection
    }


    //region Category2
    public void addCategory2(Category2 employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME3, employee.get_name()); // Contact Name
        values.put(KEY_IMAGE3, employee.getImage()); // Contact Phone


        // Inserting Row
        db.insert(TABLE_CUSTOMER3, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Category2 getCategory2(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CUSTOMER3, new String[]{KEY_ID3,
                        KEY_NAME3,KEY_IMAGE3}, KEY_ID3 + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Category2 employee = new Category2(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                (cursor.getString(2)).getBytes());

        // return contact
        return employee;
    }

    // code to get all contacts in a list view
    public List<Category2> getAllCategory2() {
        List<Category2> employeeList = new ArrayList<Category2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER3;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category2 employee = new Category2();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                employee.setImage(cursor.getBlob(2));

                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        // return contact list
        return employeeList;
    }

    // code to update the single contact
    public int updateCategory2(Category2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME3, contact.get_name());
        values.put(KEY_IMAGE3, contact.getImage());

        // updating row
        return db.update(TABLE_CUSTOMER3, values, KEY_ID3 + " = ?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // Deleting single employee
    public void deleteCategory2(Category2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER3, KEY_ID3 + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

    // Getting contacts Count
    public int getCategoryCount2() {
        String countQuery = "SELECT  * FROM " + TABLE_CUSTOMER3;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    //endregion

    //region Employee
    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employee.get_name()); // Contact Name
        values.put(KEY_SEX, employee.get_sex()); // Contact Phone
        values.put(KEY_PH_NO, employee.get_phone_number());
        values.put(KEY_ADDRESS, employee.get_address());

        // Inserting Row
        db.insert(TABLE_EMPLOYEE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[]{KEY_ID,
                        KEY_NAME,KEY_SEX, KEY_PH_NO,KEY_ADDRESS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee employee = new Employee(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        // return contact
        return employee;
    }

    // code to get all contacts in a list view
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                employee.set_sex(cursor.getString(2));
                employee.set_phone_number(cursor.getString(3));
                employee.set_address(cursor.getString(4));
                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return contact list
        return employeeList;
    }

    // code to update the single contact
    public int updateEmployee(Employee contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_SEX, contact.get_sex());
        values.put(KEY_PH_NO, contact.get_phone_number());
        values.put(KEY_ADDRESS, contact.get_address());

        // updating row
        return db.update(TABLE_EMPLOYEE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // Deleting single employee
    public void deleteEmployee(Employee contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

    // Getting contacts Count
    public int getEmployeesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    //endregion

    //region Customer
    public void addCustomer(Customer employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME1, employee.get_name()); // Contact Name
        values.put(KEY_SEX1, employee.get_sex()); // Contact Phone
        values.put(KEY_PH_NO1, employee.get_phone_number());
        values.put(KEY_ADDRESS1, employee.get_address());

        // Inserting Row
        db.insert(TABLE_CUSTOMER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[]{KEY_ID,
                        KEY_NAME,KEY_SEX, KEY_PH_NO,KEY_ADDRESS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Customer employee = new Customer(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        // return contact
        return employee;
    }

    // code to get all contacts in a list view
    public List<Customer> getAllCustomer() {
        List<Customer> employeeList = new ArrayList<Customer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer employee = new Customer();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                employee.set_sex(cursor.getString(2));
                employee.set_phone_number(cursor.getString(3));
                employee.set_address(cursor.getString(4));
                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return contact list
        return employeeList;
    }

    // code to update the single contact
    public int updateCustomer(Customer contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME1, contact.get_name());
        values.put(KEY_SEX1, contact.get_sex());
        values.put(KEY_PH_NO1, contact.get_phone_number());
        values.put(KEY_ADDRESS1, contact.get_address());

        // updating row
        return db.update(TABLE_CUSTOMER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // Deleting single employee
    public void deleteCustomer(Customer contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

    // Getting contacts Count
    public int getCustomersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CUSTOMER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    //endregion


    //region Product
    public void addProduct(Product2 employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_PRODUCT, employee.get_name()); // Contact Name
        values.put(KEY_QUAN_PRODUCT, employee.getQuantity());
        values.put(KEY_DES_PRODUCT, employee.getDescription());
        values.put(KEY_PRICE_PRODUCT, employee.getPrice());
        values.put(KEY_IMAGE_PRODUCT, employee.getImage());
        values.put(KEY_NOTE_PRODUCT, employee.getNote());
        values.put(KEY_CATEGORY_PRODUCT, employee.getCategory());




        // Inserting Row
        db.insert(TABLE_PRODUCT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
   public Product2 getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT, new String[]{KEY_ID_PRODUCT,KEY_NAME_PRODUCT,
                KEY_QUAN_PRODUCT,KEY_DES_PRODUCT,KEY_PRICE_PRODUCT,KEY_IMAGE_PRODUCT,KEY_NOTE_PRODUCT,KEY_CATEGORY_PRODUCT}, KEY_ID_PRODUCT + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product2 employee = new Product2(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3),
                cursor.getDouble(4),
                cursor.getBlob(5),
                cursor.getString(6),
                Integer.parseInt(cursor.getString(7)));

        // return contact
        return employee;
    }

    // code to get all contacts in a list view
    public List<Product2> getAllProduct() {
        List<Product2> employeeList = new ArrayList<Product2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product2 employee = new Product2();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                employee.setQuantity(Integer.parseInt(cursor.getString(2)));
                employee.setDescription(cursor.getString(3));
                employee.setPrice(Double.parseDouble(cursor.getString(4)));
                employee.setImage(cursor.getBlob(5));
                employee.setNote(cursor.getString(6));
                employee.setQuantity(Integer.parseInt(cursor.getString(7)));

                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return contact list
        return employeeList;
    }

    //Get all product in a category
    public List<Product2> getAllProductInCategry(int id) {
        List<Product2> employeeList = new ArrayList<Product2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE category = " + id ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product2 employee = new Product2();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.set_name(cursor.getString(1));
                employee.setQuantity(Integer.parseInt(cursor.getString(2)));
                employee.setDescription(cursor.getString(3));
                employee.setPrice(Double.parseDouble(cursor.getString(4)));
                employee.setImage(cursor.getBlob(5));
                employee.setNote(cursor.getString(6));
                employee.setQuantity(Integer.parseInt(cursor.getString(7)));

                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return contact list
        return employeeList;
    }

    // code to update the single contact
    public int updateProduct(Product2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_PRODUCT, contact.get_name());
        values.put(KEY_QUAN_PRODUCT, contact.getQuantity());
        values.put(KEY_DES_PRODUCT, contact.getDescription());
        values.put(KEY_PRICE_PRODUCT, contact.getPrice());
        values.put(KEY_IMAGE_PRODUCT, contact.getImage());
        values.put(KEY_NOTE_PRODUCT, contact.getNote());
        values.put(KEY_CATEGORY_PRODUCT, contact.getCategory());

        // updating row
        return db.update(TABLE_PRODUCT, values, KEY_ID_PRODUCT + " = ?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // Deleting single employee
    public void deleteProduct(Product2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, KEY_ID_PRODUCT + " = ?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

    // Getting contacts Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    //endregion

}