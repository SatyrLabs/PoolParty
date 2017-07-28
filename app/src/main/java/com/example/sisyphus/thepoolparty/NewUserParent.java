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

public class NewUserParent extends AppCompatActivity {

    private DatabaseReference mDatabase;

    Button imageAndTextOk;
    EditText nameEditText;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_parent_setup);
        imageAndTextOk();
    }

    private void imageAndTextOk(){
        imageAndTextOk = (Button) findViewById(R.id.parentNameAndImageOk);
        nameEditText = (EditText) findViewById(R.id.parentNameEditText);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();

        imageAndTextOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Launch the car setup screen
                Intent intent = new Intent(NewUserParent.this, NewUserParentCar.class);
                startActivity(intent);

                //store the edit text
                name = nameEditText.getText().toString().trim();

                //Add the edit text to the user's data
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDatabase.child("users").child(userId).child("FullName").setValue(name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
        });
    }
}
