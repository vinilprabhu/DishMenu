package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

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

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter.Adapter2;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;
import model.Dish;

public class UserOrders extends AppCompatActivity {

    private DatabaseReference database,order;

    private DatabaseReference starters,drinks,maincourse,dessert;

    private String mUserId;
    private FirebaseUser user;

    private ListView listView;
    TextView username;

    public static String table,FirstName,rest;


    RecyclerView RVdrinks,RVdessert,RVmaincourse,RVstarters;
    List<Dish> list = new ArrayList<>();
    private Adapter2 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        table=i.getStringExtra("table");
        FirstName=i.getStringExtra("user");


        RVstarters = (RecyclerView) findViewById(R.id.RVstarters);
        username=(TextView)findViewById(R.id.username);
        username.setText(FirstName);

        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getApplicationContext());


        RVstarters.setLayoutManager(mLayoutManager4);


        mAdapter = new Adapter2(list);
        RVstarters.setAdapter(mAdapter);

        database = FirebaseDatabase.getInstance().getReference();


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
                        mAdapter = new Adapter2(list);
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
                        mAdapter = new Adapter2(list);
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
                        mAdapter = new Adapter2(list);
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
                        mAdapter = new Adapter2(list);
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
    }
}
