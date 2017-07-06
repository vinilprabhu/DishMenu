package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter.Adapter;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;
import model.Dish;

public class TableActivity extends AppCompatActivity {
    private DatabaseReference database,order;

    private String mUserId;
    private FirebaseUser user;

    private ListView listView;

    public static String table,FirstName,rest;


    RecyclerView recyclerView;
    List<Dish> list = new ArrayList<>();
    private Adapter mAdapter;


    private EditText Quantity;

    int z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        table=i.getStringExtra("table");
        Toast.makeText(getApplicationContext(), rest+" at "+table, Toast.LENGTH_SHORT).show();



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new Adapter(list);
        recyclerView.setAdapter(mAdapter);



        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        mUserId = user.getUid();


        final ArrayList<String> users=new ArrayList<>();//stores name of other members on the table

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirstName =dataSnapshot.child("users").child(mUserId).child("Details").child("firstname").getValue().toString().trim();

                long x = (long) dataSnapshot.child("vt").child(rest).child(table).child("count").getValue();
                z= (int) x;

                for(int i=1;i<=z;i++){
                    if(!FirstName.equals(dataSnapshot.child("vt").child(rest).child(table).child("users").child(String.valueOf(i)).getValue().toString().trim())) {
                        users.add(dataSnapshot.child("vt").child(rest).child(table).child("users").child(String.valueOf(i)).getValue().toString().trim());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        order=database.child("order").child(rest).child(table).child("Vinil").child("Dessert");


        order.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Quantity=(EditText) findViewById(R.id.quantity);
                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                String a = (String) hashMap.get("name");
                //String c=().getText().toString();

                String b = (String) hashMap.get("price");

                String c = (String) hashMap.get("quantity");
                Dish f = new Dish(a, b,c);

                list.add(f);
                mAdapter = new Adapter(list);
                recyclerView.setAdapter(mAdapter);
                System.out.println("woks" + a);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
