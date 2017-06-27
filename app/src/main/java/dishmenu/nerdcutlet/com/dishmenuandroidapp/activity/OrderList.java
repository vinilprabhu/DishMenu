package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class OrderList extends AppCompatActivity {

    private DatabaseReference database,restdb;
    private FirebaseUser user;
    private String mUserId;

    private ListView listView;
    private Button confirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();

        mUserId = user.getUid();

        listView = (ListView) findViewById(R.id.listViewo);
        confirm=(Button)findViewById(R.id.buttonco);


        restdb=database.child("order").child(mUserId).child("orders").child("order1");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,restdb) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };

        listView.setAdapter(firebaseListAdapter);


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                alertDialogBuilder.setMessage("Confirm order ?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(OrderList.this,"Order Confirmed!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Home.class));

                    }
                });



                alertDialogBuilder.setNegativeButton("Cancle",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(OrderList.this,"Cancled",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();






            }
        });

    }
}
