package cat.dam.andy.AWS_RDS_mysql.database;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cat.dam.andy.AWS_RDS_mysql.MainActivity;

public class DatabaseAdaptor {
    private static final String TAG="DatabaseAdaptor";
    private static final AWS_KEYS keys = new AWS_KEYS();
    private static final String SERVER = keys.getServer();
    private static final String PORT = keys.getPort();
    private static final String DATABASE = keys.getDatabase();
    private static final String USER = keys.getUser(); // WARNING! DO NOT CHECK IN YOUR CREDENTIALS INTO PUBLIC SOURCE CONTROL
    private static final String PASS = keys.getPass(); // WARNING! DO NOT CHECK IN YOUR CREDENTIALS INTO PUBLIC SOURCE CONTROL
    private static final String URL = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASE;
    private static String res;

    public static void getResults(MainActivity mainActivity, String... params) {
        //obrim un thread per a fer la consulta a la base de dades
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            res=getValues(params);
            handler.post(() -> {
                //quan acabi la consulta, cridem a processFinish de MainActivity
                Log.d(TAG, "onPostExecute");
                Log.d(TAG, res);
                mainActivity.processFinish(res);
            });
        });
    }


    private static String getValues(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database Connection success "  + Arrays.toString(params));
            StringBuilder result = new StringBuilder("RESULTS");
            result.append(System.lineSeparator()).append("=".repeat(result.length()-1));
            result.append(System.lineSeparator());
            String searchPartial = params[0] + "%";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM POKEMON" +
                    " WHERE " +
                    "name LIKE '" + searchPartial + "'");
            //També es pot fer amb PreparedStatement
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pokemon" +
//                    " WHERE " +
//                    "name LIKE ?");
//            ps.setString(1, searchPartial);
//            ResultSet rs = ps.executeQuery();

            int nResults=0;
            while (rs.next()) {
                try {
                    result.append(rs.getString("name")).append(" (").append(
                            rs.getInt("pokedex_number")).append(")");
                    nResults++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.append(System.lineSeparator());
            }
            result.append(System.lineSeparator());
            result.append("We found ").append(nResults).append(" results that matches your query '").append(params[0]).append("'");
            res = result.toString();
            if (res.length() > 502) {
                Log.d(TAG, "Database Result success " + result.substring(0, 500));
            } else {
                Log.d(TAG, "Database Result success " + result);
            }
            connection.close(); //tanca la connexió
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }
}