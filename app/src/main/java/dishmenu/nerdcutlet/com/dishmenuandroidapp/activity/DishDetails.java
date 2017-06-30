package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class DishDetails extends AppCompatActivity {

    private DatabaseReference database,dishdb;
    private String mUserId;
    private FirebaseUser user;

    String j,k,l,priceS,FirstName,table;

    private TextView name,discription,price;
    private EditText quantity;
    private Button adddish;
    private ImageButton ibp,ibm;

    private static final String TAG="DishDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);

        Intent i = getIntent();
        j =i.getStringExtra("rest");
        k =i.getStringExtra("menu");
        l =i.getStringExtra("dish");
        table=i.getStringExtra("table");

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        mUserId = user.getUid();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("dish").child(j).child(k).child(l);

        ImageView image=(ImageView)findViewById(R.id.imageView2);

        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(pathReference)
                .into(image);



        name =(TextView) findViewById(R.id.textView9);
        discription=(TextView)findViewById(R.id.textView15);
        price=(TextView)findViewById(R.id.textView17);

        quantity=(EditText)findViewById(R.id.editText);



        dishdb=database;

        dishdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String Discription =(String)dataSnapshot.child("menuitem").child(j).child(k).child(l).child("isselected").getValue();
                long Price =(long)dataSnapshot.child("menuitem").child(j).child(k).child(l).child("Price").getValue();
                String Name =(String)dataSnapshot.child("menuitem").child(j).child(k).child(l).child("Name").getValue();
                FirstName =(String)dataSnapshot.child("users").child(mUserId).child("Details").child("firstname").getValue();

                priceS=Long.toString(Price);

                name.setText(Name);
                discription.setText(Discription);
                price.setText(priceS);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        adddish=(Button)findViewById(R.id.button);
        ibp=(ImageButton)findViewById(R.id.imageButton);
        ibm=(ImageButton)findViewById(R.id.imageButton2);

        adddish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int x=Integer.valueOf(String.valueOf(quantity.getText()));

                if(x<1)Toast.makeText(DishDetails.this,"Quantity Cannot be '0'", Toast.LENGTH_LONG).show();
                else
                {
                    database.child("order").child(j).child(table).child(FirstName).child(k).child(l).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                           if(dataSnapshot.hasChild("quantity")) {
                               Long y = (Long) dataSnapshot.child("quantity").getValue();

                               y=y+x;
                               database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("quantity").setValue(y);
                               database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("price").setValue(priceS);
                           }
                           else {
                               database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("quantity").setValue(1);
                               database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("price").setValue(priceS);}




                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("quantity").setValue(1);
                            database.child("order").child(j).child(table).child(FirstName).child(k).child(l).child("price").setValue(priceS);
                        }
                    });




                }


            }
        });

        ibp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.valueOf(String.valueOf(quantity.getText()));
                x++;
                quantity.setText(""+x);
            }
        });

        ibm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.valueOf(String.valueOf(quantity.getText()));
                if(x==0){x=1;
                    quantity.setText(""+x);}
                else if (x>1){x--;
                quantity.setText(""+x);}
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_t, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_table:
                Intent intent =   new Intent(getApplicationContext(), TableActivity.class);
                intent.putExtra("table",table);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
