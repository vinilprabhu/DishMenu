package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class Menus extends AppCompatActivity {

    private DatabaseReference database,restdb,selectdb;
    private FirebaseUser user;
    private Button menu1,menu2,menu3,menu4;


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        Intent i = getIntent();
        final String j =i.getStringExtra("selected");
        final String table=i.getStringExtra("table");

        menu1=(Button)findViewById(R.id.menu1);
        menu2=(Button)findViewById(R.id.menu2);
        menu3=(Button)findViewById(R.id.menu3);
        menu4=(Button)findViewById(R.id.menu4);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(getApplicationContext(), MenuItems.class);
                intent.putExtra("selected","Drinks");
                intent.putExtra("menu",j);
                intent.putExtra("table",table);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(getApplicationContext(), MenuItems.class);
                intent.putExtra("selected","Starters");
                intent.putExtra("menu",j);
                intent.putExtra("table",table);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(getApplicationContext(), MenuItems.class);
                intent.putExtra("selected","Main Course");
                intent.putExtra("menu",j);
                intent.putExtra("table",table);
                startActivity(intent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(getApplicationContext(), MenuItems.class);
                intent.putExtra("selected","Desserts");
                intent.putExtra("menu",j);
                intent.putExtra("table",table);
                startActivity(intent);
            }
        });




//        listView = (ListView) findViewById(R.id.listView2);
//
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        database = FirebaseDatabase.getInstance().getReference();
//
//        restdb=database.child("menu").child(j);
//
//        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,restdb) {
//            @Override
//            protected void populateView(View v, String model, int position) {
//                TextView textView = (TextView)v.findViewById(android.R.id.text1);
//                textView.setText(model);
//
//            }
//        };
//
//        listView.setAdapter(firebaseListAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selected = listView.getItemAtPosition(i).toString();
//
//                Intent intent =   new Intent(getApplicationContext(), MenuItems.class);
//                intent.putExtra("selected",selected);
//                intent.putExtra("menu",j);
//                startActivity(intent);
//            }
//        });
    }
}
