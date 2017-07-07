package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter.Adapter3;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;
import model.Dish;

public class Bill extends AppCompatActivity {

    private DatabaseReference database,order;

    private DatabaseReference starters,drinks,maincourse,dessert;

    private String mUserId;
    private FirebaseUser user;

    private ListView listView;

    public static String table,FirstName,rest;


    RecyclerView RVdrinks,RVdessert,RVmaincourse,RVstarters;
    List<Dish> list = new ArrayList<>();
    private Adapter3 mAdapter;

    TextView Total,Tax,TTotal;
    Button ConfirmOrder;

    double GST=0.18;

    int GTotal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        table=i.getStringExtra("table");
        FirstName=i.getStringExtra("user");

        Total=(TextView)findViewById(R.id.Total);
        Tax=(TextView)findViewById(R.id.Tax);
        TTotal=(TextView)findViewById(R.id.TTotal);
        RVstarters = (RecyclerView) findViewById(R.id.RVstarters);

        ConfirmOrder=(Button)findViewById(R.id.ConfirmOrder);

        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getApplicationContext());


        RVstarters.setLayoutManager(mLayoutManager4);


        mAdapter = new Adapter3(list);
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


                        int x=Integer.parseInt(b),y=Integer.parseInt(c);
                        int z=x*y;
                        GTotal=GTotal+z;

                        Total.setText(GTotal+"");


                        double l,m;
                        l=GTotal*GST;
                        m=GTotal+l;

                        Tax.setText(String.format( "%.2f",l));
                        TTotal.setText(String.format( "%.2f",m));



                        list.add(f);
                        mAdapter = new Adapter3(list);
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


                        int x=Integer.parseInt(b),y=Integer.parseInt(c);
                        int z=x*y;
                        GTotal=GTotal+z;

                        Total.setText(GTotal+"");
                        double l,m;
                        l=GTotal*GST;
                        m=GTotal+l;

                        Tax.setText(String.format( "%.2f",l));
                        TTotal.setText(String.format( "%.2f",m));

                        list.add(f);
                        mAdapter = new Adapter3(list);
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

                        int x=Integer.parseInt(b),y=Integer.parseInt(c);
                        int z=x*y;
                        GTotal=GTotal+z;

                        Total.setText(GTotal+"");
                        double l,m;
                        l=GTotal*GST;
                        m=GTotal+l;

                        Tax.setText(String.format( "%.2f",l));
                        TTotal.setText(String.format( "%.2f",m));

                        list.add(f);
                        mAdapter = new Adapter3(list);
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


                        int x=Integer.parseInt(b),y=Integer.parseInt(c);
                        int z=x*y;
                        GTotal=GTotal+z;

                        Total.setText(GTotal+"");
                        double l,m;
                        l=GTotal*GST;
                        m=GTotal+l;

                        Tax.setText(String.format( "%.2f",l));
                        TTotal.setText(String.format( "%.2f",m));

                        list.add(f);
                        mAdapter = new Adapter3(list);
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

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialogBuilder.setMessage("Confirm Order?\nOnce ordered it cannot be cancled.");


                alertDialogBuilder.setPositiveButton("Yes.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(Bill.this,"Order is confirmed.",Toast.LENGTH_SHORT).show();





                        Intent intent =   new Intent(getApplicationContext(), Payments.class);
                        startActivity(intent);

                    }
                });



                alertDialogBuilder.setNegativeButton("Review Your order?",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Bill.this,"Cancled",Toast.LENGTH_SHORT).show();


                        onBackPressed();
//                        Intent intent =   new Intent(getApplicationContext(), TableActivity.class);
//                        intent.putExtra("rest",rest);
//                        intent.putExtra("table",table);
//                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }
        });
    }
}
