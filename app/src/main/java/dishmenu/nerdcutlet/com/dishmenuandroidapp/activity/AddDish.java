package dishmenu.nerdcutlet.com.dishmenuandroidapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

public class AddDish extends AppCompatActivity {

    private Button selectImage,check;
    private EditText Rest,Menu,Dish,Price,Desc;

    String rest,menu,dish,price,desc;

    private StorageReference mStorage;
    private DatabaseReference database;

    private static final int GALLERY_INTENT =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        Intent i = getIntent();
        rest =i.getStringExtra("rest");
        menu =i.getStringExtra("menu");

        mStorage= FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference();

        selectImage=(Button)findViewById(R.id.buttonSelectImage);

        Rest=(EditText)findViewById(R.id.editTextRestName);
        Rest.setText(rest);

        Menu=(EditText)findViewById(R.id.editTextMenu);
        Menu.setText(menu);

        Dish=(EditText)findViewById(R.id.editTextDishName);
        Price=(EditText)findViewById(R.id.editTextPrice);
        Desc=(EditText)findViewById(R.id.editTextDesc);


        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dish=Dish.getText().toString();
                price=Price.getText().toString();
                desc=Desc.getText().toString();

                if(dish.isEmpty()||price.isEmpty()||desc.isEmpty())
                    Toast.makeText(AddDish.this,"check data",Toast.LENGTH_SHORT).show();
                else {

                    Toast.makeText(AddDish.this,dish+" "+price+" "+desc,Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Intent.ACTION_PICK);

                    intent.setType("image/*");

                    startActivityForResult(intent, GALLERY_INTENT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT&&resultCode==RESULT_OK){


            Uri uri=data.getData();

            StorageReference imagepath=mStorage.child("dish").child(rest).child(menu).child(dish);

            imagepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    database.child("menuitems").child(rest).child(menu).push().setValue(dish);
                    database.child("menuitem").child(rest).child(menu).child(dish).child("Name").setValue(dish);
                    database.child("menuitem").child(rest).child(menu).child(dish).child("Price").setValue(price);
                    database.child("menuitem").child(rest).child(menu).child(dish).child("Description").setValue(desc);

                    Toast.makeText(AddDish.this,"Dish added",Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
