package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;



import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class Home extends AppCompatActivity {

    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;

    private TextView tvEmail,tvFirstName,tvSurname,tvCountry,tvDOB,tvPhone;
    private Button bLogout,buttonup,buttonrest;

    private static final String TAG="Home";
    private String mUserId;

    //navigation drawer


    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        database = FirebaseDatabase.getInstance().getReference();

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){

            finish();

            startActivity(new Intent(this, Login.class));
        }else{
            FirebaseUser user=firebaseAuth.getCurrentUser();

            tvEmail=(TextView)findViewById(R.id.textViewEmail);
            tvFirstName=(TextView)findViewById(R.id.textViewFirstName);
            tvSurname=(TextView)findViewById(R.id.textViewSurname);
            tvCountry=(TextView)findViewById(R.id.textViewCountry);
            tvDOB=(TextView)findViewById(R.id.textViewDOB);
            tvPhone=(TextView)findViewById(R.id.textViewPhone);
            bLogout=(Button)findViewById(R.id.buttonLogout);
            buttonup=(Button)findViewById(R.id.buttonup);
            buttonrest=(Button)findViewById(R.id.buttonrest);

            tvEmail.setText(user.getEmail());

            mUserId = user.getUid();

            database.child("users").child(mUserId).child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

//                    User user = dataSnapshot.getValue(User.class);


                    String FirstNameS =(String)dataSnapshot.child("firstname").getValue();

                    if(dataSnapshot.child("firstname").getValue()==null){
                        Toast.makeText(Home.this, "Fill all details to continue!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    }

                    String SurnameS =(String)dataSnapshot.child("surname").getValue();

                    if(dataSnapshot.child("surname").getValue()==null){
                        Toast.makeText(Home.this, "Fill all details to continue!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    }

                    String CountryS =(String)dataSnapshot.child("country").getValue();

                    if(dataSnapshot.child("country").getValue()==null){
                        Toast.makeText(Home.this, "Fill all details to continue!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    }

                    String DOBS =(String)dataSnapshot.child("dob").getValue();

                    if(dataSnapshot.child("dob").getValue()==null){
                        Toast.makeText(Home.this, "Fill all details to continue!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    }

                    String PhoneS =(String)dataSnapshot.child("phone").getValue();

                    if(dataSnapshot.child("phone").getValue()==null){
                        Toast.makeText(Home.this, "Fill all details to continue!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    }


                    tvFirstName.setText(FirstNameS);
                    tvSurname.setText(SurnameS);
                    tvCountry.setText(CountryS);
                    tvDOB.setText(DOBS);
                    tvPhone.setText(PhoneS);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });



            bLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    firebaseAuth.signOut();

                    finish();

                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
            });

            buttonup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    startActivity(new Intent(getApplicationContext(), UserDetails.class));
                }
            });

            buttonrest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(getApplicationContext(), Restaurant.class));
                }
            });
        }





    }
}
