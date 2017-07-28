package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mhigh on 7/27/2017.
 */

public class NewUserParentCar extends AppCompatActivity {

    Button carImageOk;
    EditText carEditText;

    String car;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_parent_setup_car);
        carImageOk();
    }

    private void carImageOk(){
        carImageOk = (Button) findViewById(R.id.carImageAndOk);
        carEditText = (EditText) findViewById(R.id.carEditText);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();

        carImageOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Launch the phone setup screen
                Intent intent = new Intent(NewUserParentCar.this, NewUserParentPhone.class);
                startActivity(intent);

                //store the edit text
                car = carEditText.getText().toString().trim();

                //Add the edit text to the user's data
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDatabase.child("users").child(userId).child("Car").setValue(car);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
        });

    }
}
