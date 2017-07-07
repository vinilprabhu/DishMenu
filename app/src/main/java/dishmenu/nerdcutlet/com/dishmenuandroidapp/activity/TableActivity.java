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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    private DatabaseReference starters,drinks,maincourse,dessert;

    private String mUserId;
    private FirebaseUser user;


    public static String table,FirstName,rest;


    RecyclerView RVstarters;
    Button bill;
    List<Dish> list = new ArrayList<>();
    private Adapter mAdapter;



    int z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        table=i.getStringExtra("table");

        bill=(Button)findViewById(R.id.button3);


//        RVmaincourse = (RecyclerView) findViewById(R.id.RVmiancourse);
//
//        RVdrinks = (RecyclerView) findViewById(R.id.RVdrinks);
//
//        RVdessert = (RecyclerView) findViewById(R.id.RVdessert);

        RVstarters = (RecyclerView) findViewById(R.id.RVstarters);


//        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
//        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getApplicationContext());
//        RVdrinks.setLayoutManager(mLayoutManager1);
//        RVmaincourse.setLayoutManager(mLayoutManager2);
//        RVdessert.setLayoutManager(mLayoutManager3);
        RVstarters.setLayoutManager(mLayoutManager4);


        mAdapter = new Adapter(list);
        RVstarters.setAdapter(mAdapter);
//        RVdessert.setAdapter(mAdapter);
//        RVmaincourse.setAdapter(mAdapter);
//        RVdrinks.setAdapter(mAdapter);



        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        mUserId = user.getUid();


        final ArrayList<String> users=new ArrayList<>();//stores name of other members on the table

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirstName =dataSnapshot.child("users").child(mUserId).child("Details").child("firstname").getValue().toString().trim();


                order=database.child("order").child(rest).child(table).child(FirstName);

                starters=order.child("Starters");
                dessert=order.child("Dessert");
                drinks=order.child("Drinks");
                maincourse=order.child("Main Course");



                dessert.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        HashMap hashMap = (HashMap) dataSnapshot.getValue();
                        String a = (String) hashMap.get("name");
                        //String c=().getText().toString();

                        String b = (String) hashMap.get("price");

                        String c = (String) hashMap.get("quantity");
                        String d = "Dessert";
                        Dish f = new Dish(d,a, b,c);

                        list.add(f);
                        mAdapter = new Adapter(list);
                        RVstarters.setAdapter(mAdapter);
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


                drinks.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        HashMap hashMap = (HashMap) dataSnapshot.getValue();
                        String a = (String) hashMap.get("name");
                        //String c=().getText().toString();

                        String b = (String) hashMap.get("price");

                        String c = (String) hashMap.get("quantity");
                        String d="Drinks";
                        Dish f = new Dish(d,a, b,c);

                        list.add(f);
                        mAdapter = new Adapter(list);
                        RVstarters.setAdapter(mAdapter);
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


                maincourse.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        HashMap hashMap = (HashMap) dataSnapshot.getValue();
                        String a = (String) hashMap.get("name");
                        //String c=().getText().toString();

                        String b = (String) hashMap.get("price");

                        String c = (String) hashMap.get("quantity");
                        String d="Main Course";
                        Dish f = new Dish(d,a, b,c);

                        list.add(f);
                        mAdapter = new Adapter(list);
                        RVstarters.setAdapter(mAdapter);
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

                starters.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        HashMap hashMap = (HashMap) dataSnapshot.getValue();
                        String a = (String) hashMap.get("name");
                        //String c=().getText().toString();

                        String b = (String) hashMap.get("price");

                        String c = (String) hashMap.get("quantity");
                        String d="Starters";
                        Dish f = new Dish(d,a, b,c);

                        list.add(f);
                        mAdapter = new Adapter(list);
                        RVstarters.setAdapter(mAdapter);
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        final ListView userList=(ListView)findViewById(R.id.listViewMembers);

        final FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>
                (this,String.class,android.R.layout.simple_list_item_1,database.child("vt").child(rest).child(table).child("users")) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);
                if(model.equals(FirstName))z =position;

                System.out.println("position " + z);




            }
        };





        userList.setAdapter(firebaseListAdapter);




        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = userList.getItemAtPosition(position).toString();

                Intent intent =   new Intent(getApplicationContext(), UserOrders.class);
                intent.putExtra("rest",rest);
                intent.putExtra("table",table);
                intent.putExtra("user",selected);
                startActivity(intent);
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(getApplicationContext(), Bill.class);
                intent.putExtra("rest",rest);
                intent.putExtra("table",table);
                intent.putExtra("user",FirstName);
                startActivity(intent);
            }
        });





    }
}
