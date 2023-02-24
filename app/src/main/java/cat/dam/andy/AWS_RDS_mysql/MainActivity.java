package cat.dam.andy.AWS_RDS_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cat.dam.andy.AWS_RDS_mysql.database.DatabaseAdaptor;

public class MainActivity extends AppCompatActivity  {
    //Member variables
    private EditText et_name;
    private Button btn_search;
    private final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        et_name = (EditText)findViewById(R.id.et_name);
        btn_search = (Button)this.findViewById(R.id.btn_search);
    }

    private void initListeners() {
        btn_search.setOnClickListener(v -> searchByName(et_name.getText().toString()));
    }

    public void searchByName(String searchText) {
        Log.d(TAG, "searchByName " + searchText);
        if (searchText.length() >= 1) {
            DatabaseAdaptor mySQLAsyncTask = new DatabaseAdaptor();
            DatabaseAdaptor.getResults(this,  searchText);
        } else {
            displayResults(String.valueOf(R.string.no_valid_search));
        }
    }

    public void processFinish(String result) {
        if (result.length() > 502) {
            Log.d("MainActivity:", "processFinish " + result.substring(0, 500));
        } else {
            Log.d("MainActivity:", "processFinish " + result);
        }
        displayResults(result);
    }

    private void displayResults(String res) {
        TextView tvResults = (TextView)findViewById(R.id.tv_results);
        tvResults.setText(res);
    }
}