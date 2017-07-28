package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

public class NewUser extends AppCompatActivity {

    Button parentSelection;
    Button childSelection;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_setup);
        setUpSelectionButtons();
    }

    public void setUpSelectionButtons(){
        parentSelection = (Button) findViewById(R.id.newUserParentSelection);
        childSelection = (Button) findViewById(R.id.newUserChildSelection);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();

        //When the parent button is clicked, set the userType data value to equal "parent"
        parentSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Send intent to parent setup
                Intent parentIntent = new Intent(NewUser.this, NewUserParent.class);
                startActivity(parentIntent);

                //Set the userType value to "parent"
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDatabase.child("users").child(userId).child("userType").setValue("Parent");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });

            }
        });

        //When the parent button is clicked, set the userType data value to equal "child"
        childSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Send intent to child setup
                Intent childIntent = new Intent(NewUser.this, NewUserChild.class);
                startActivity(childIntent);

                //Set the userType value to "child"
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDatabase.child("users").child(userId).child("userType").setValue("Child");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
        });
    }
}
