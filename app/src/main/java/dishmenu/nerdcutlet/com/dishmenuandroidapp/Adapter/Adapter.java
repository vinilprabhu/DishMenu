package dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.activity.TableActivity;
import model.Dish;

/**
 * Created by vinil on 6/7/17.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    private List<Dish> dishList;

    private TextView Quantity;
    private ImageButton delete;

    private DatabaseReference database,order;

    Dish dish;


    public Adapter(View view) {
        super();
        //Name = (TextView) view.findViewById(R.id.customText1);
        Quantity=(TextView) view.findViewById(R.id.quantity);
        delete=(ImageButton)view.findViewById(R.id.deleteButton);

    }

    public Adapter(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom, parent, false);

        return new MyView(itemView);

    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        dish=dishList.get(position);
        holder.Name.setText(dish.name);
        holder.Price.setText(dish.price);
        holder.Menu.setText(dish.menu);
        holder.Quantity.setText(String.valueOf(dish.quantity));

        holder.Quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString()!="")
                {


                    dish.quantity=(holder.Quantity.getText().toString());
                }

            }
        });












    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        public TextView Name,Price,Menu;
        public TextView Quantity;
        public TextInputLayout quantityLayout;
        public ImageButton plus,minus,delete;
//
//        String qty =dish.quantity;
//
//
//        int count=Integer.parseInt(qty);

        public MyView(View view){
            super(view);
            this.setIsRecyclable(false);

            Name = (TextView) view.findViewById(R.id.customText1);
            Price = (TextView) view.findViewById(R.id.customText2);
            Menu=(TextView)view.findViewById(R.id.customText3);
            Quantity=(TextView) view.findViewById(R.id.quantity) ;
            plus=(ImageButton) view.findViewById(R.id.imageButton2);
            minus=(ImageButton)view.findViewById(R.id.imageButton);
            delete=(ImageButton)view.findViewById(R.id.deleteButton);


            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference database;
                    database=FirebaseDatabase.getInstance().getReference().child("order").child(TableActivity.rest).child(TableActivity.table).child(TableActivity.FirstName).child(Menu.getText().toString());

                    String qty =Quantity.getText().toString();

                    int count=Integer.parseInt(qty);

                    count = count + 1 ;
                    Quantity.setText("" + count);
                    database.child(Name.getText().toString()).child("quantity").setValue(""+count);
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference database;
                    database=FirebaseDatabase.getInstance().getReference().child("order").child(TableActivity.rest).child(TableActivity.table).child(TableActivity.FirstName).child(Menu.getText().toString());

                    String qty =Quantity.getText().toString();

                    int count=Integer.parseInt(qty);
                    if(count == 0||count==1){
                        count = 1;

                        Quantity.setText("" + count);
                        database.child(Name.getText().toString()).child("quantity").setValue(""+count);

                    }
                    else if(count ==0){
                        Quantity.setText("" + count);
                        database.child(Name.getText().toString()).child("quantity").setValue(""+count);

                    }
                    else{
                        count = count -1;
                        Quantity.setText("" + count);
                        database.child(Name.getText().toString()).child("quantity").setValue(""+count);

                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    database = FirebaseDatabase.getInstance().getReference().child("order").child(TableActivity.rest).child(TableActivity.table).child(TableActivity.FirstName).child(Menu.getText().toString());
                    database.child(Name.getText().toString()).removeValue();

                }
            });

        }



    }



}
