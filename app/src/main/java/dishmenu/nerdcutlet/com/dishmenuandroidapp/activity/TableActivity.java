package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class TableActivity extends AppCompatActivity {
    private DatabaseReference database;

    private String mUserId;
    private FirebaseUser user;

    String table,FirstName,rest;

    int z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        table=i.getStringExtra("table");
        Toast.makeText(getApplicationContext(), rest+" "+table, Toast.LENGTH_SHORT).show();


        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        mUserId = user.getUid();


        final ArrayList<String> users=new ArrayList<>();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirstName =dataSnapshot.child("users").child(mUserId).child("Details").child("firstname").getValue().toString().trim();

                long x = (long) dataSnapshot.child("vt").child(rest).child(table).child("count").getValue();
                z= (int) x;

                for(int i=1;i<=z;i++){
                    users.add(dataSnapshot.child("vt").child(rest).child(table).child("users").child(String.valueOf(i)).getValue().toString().trim());

                    Toast.makeText(getApplicationContext(), users.get(i-1), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//FirstName


    }
}
