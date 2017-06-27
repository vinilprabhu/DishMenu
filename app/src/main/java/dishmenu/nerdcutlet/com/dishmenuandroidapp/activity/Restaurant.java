package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class Restaurant extends AppCompatActivity {

    private DatabaseReference database,restdb,selectdb;
    private FirebaseUser user;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        listView = (ListView) findViewById(R.id.listView);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();

        restdb=database.child("restaurant").child("rest_name");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,restdb) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };

        listView.setAdapter(firebaseListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = listView.getItemAtPosition(i).toString();

                Intent intent =   new Intent(getApplicationContext(), RestaurantDetails.class);
                intent.putExtra("selected",selected);
                startActivity(intent);
            }
        });
    }
}
