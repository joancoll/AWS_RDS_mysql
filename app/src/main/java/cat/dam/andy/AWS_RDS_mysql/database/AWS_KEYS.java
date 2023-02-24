package cat.dam.andy.AWS_RDS_mysql.database;

public class AWS_KEYS {
    // WARNING! DO NOT CHECK IN YOUR CREDENTIALS INTO PUBLIC SOURCE CONTROL
    private static final String SERVER = "YOUR SERVER URL";
    private static final String PORT = "3306";
    private static final String DATABASE = "YOUR DATABASE NAME";
    private static final String USER = "YOUR USERNAME"; // WARNING! DO NOT CHECK IN YOUR CREDENTIALS INTO PUBLIC SOURCE CONTROL
    private static final String PASS = "YOUR PASSWORD"; // WARNING! DO NOT CHECK IN YOUR CREDENTIALS INTO PUBLIC SOURCE CONTROL
    public String getServer() {
        return SERVER;
    }
    public String getPort() {
        return PORT;
    }
    public String getDatabase() {
        return DATABASE;
    }
    public String getUser() {
        return USER;
    }
    public String getPass() {
        return PASS;
    }
}

