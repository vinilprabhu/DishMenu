package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        Intent i = getIntent();
        final String j =i.getStringExtra("selected");
        String table=i.getStringExtra("table");
        Toast.makeText(getApplicationContext(), table, Toast.LENGTH_LONG).show();

    }
}
