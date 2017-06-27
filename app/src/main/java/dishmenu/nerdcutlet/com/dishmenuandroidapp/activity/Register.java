package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;



import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class Register extends AppCompatActivity{

    private EditText editTextEmail;
    private EditText editTextPassword,editTextPassword1;
    private Button buttonSignup,buttonSignin;
    private ProgressDialog progressDialog;


//    private SignInButton gsb;
//
//    private static final int RC_SIGN_IN=1;
//    private static final String TAG="Register";
//
//    private GoogleApiClient mGoogleApiClient;
//

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    finish();

                    //and open profile activity
                    // User is signed in

                    startActivity(new Intent(getApplicationContext(), UserDetails.class));
                }
            }
        };

//        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//
//
//        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//            @Override
//            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                Toast.makeText(Register.this,"Connection Error!",Toast.LENGTH_SHORT).show();
//            }
//        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();


        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword1 = (EditText) findViewById(R.id.editTextConfirmPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonSignin = (Button) findViewById(R.id.buttonSignin);

//        gsb=(SignInButton)findViewById(R.id.gsb);

        progressDialog = new ProgressDialog(this);


//        gsb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String email = editTextEmail.getText().toString();




                if(email.length()==0)
                    editTextEmail.setError("Email ID cannot be empty");
                else registerUser();

            }
        });


        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }


    private void registerUser() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password1 = editTextPassword1.getText().toString().trim();

        if(password.length()==0)
            editTextPassword.setError("Password cannot empty!");
        else if (password!=password1)
        {
            editTextPassword1.setError("Password do not match!");
            editTextPassword.setError("Password do not match!");
        }
        else {

            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), UserDetails.class));
                            } else {
                                Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_LONG).show();
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    // thrown if there already exists an account with the given email address
                                    editTextEmail.setError("Email ID is already used!\nSignIn now");
                                } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                    // thrown if the password is not strong enough
                                    editTextPassword.setError("password is not strong enough!");
                                } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // thrown if the email address is malformed
                                    editTextEmail.setError("Email ID is invalid!");
                                }
                            }
                            progressDialog.dismiss();
                        }
                    });
        }

    }

}
