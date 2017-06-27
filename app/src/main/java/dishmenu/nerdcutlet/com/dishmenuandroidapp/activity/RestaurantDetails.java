package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;


//work with menus from lyssane


public class RestaurantDetails extends AppCompatActivity {

    private static final String TAG="RestaurantDetails";

    private DatabaseReference database,restdb,selectdb;

    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        Intent i = getIntent();
        final String j =i.getStringExtra("selected");



        final TextView name,address,delivery,foodtype,timings;
        final Button menu;

        name =(TextView)findViewById(R.id.textView0) ;


        address =(TextView)findViewById(R.id.textView1) ;
        delivery =(TextView)findViewById(R.id.textView2) ;
        foodtype =(TextView)findViewById(R.id.textView3) ;
        timings =(TextView)findViewById(R.id.textView4) ;

        menu=(Button)findViewById(R.id.menu);


        name.setText(j);
        database = FirebaseDatabase.getInstance().getReference();
        restdb=database.child("restaurant").child("rest_details");

        selectdb=restdb.child(j);



        selectdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String Address =(String)dataSnapshot.child("Address").getValue();

                address.setText(Address);

                String Delivery =(String)dataSnapshot.child("Delivery").getValue();
                delivery.setText(Delivery);

                String Foodtype =(String)dataSnapshot.child("Food Type").getValue();
                foodtype.setText(Foodtype);

                String Timings =(String)dataSnapshot.child("Timings").getValue();
                timings.setText(Timings);




//                tvFirstName.setText(FirstNameS);
//                tvSurname.setText(SurnameS);
//                tvCountry.setText(CountryS);
//                tvDOB.setText(DOBS);
//                tvPhone.setText(PhoneS);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogBuilder.setMessage("Set table number.");

                alertDialogBuilder.setView(input);

                alertDialogBuilder.setPositiveButton("Set table number?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(RestaurantDetails.this,"Table is set.",Toast.LENGTH_LONG).show();
                        table= input.getText().toString();

                        Intent intent =   new Intent(getApplicationContext(), Menus.class);
                        intent.putExtra("selected",j);
                        intent.putExtra("table",table);
                        startActivity(intent);

                    }
                });



                alertDialogBuilder.setNegativeButton("Cancle",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RestaurantDetails.this,"Cancled",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }
}
