package com.example.sisyphus.thepoolparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGroupActivity extends AppCompatActivity {

    Button createGroup;
    private EditText mGroupName;
    private EditText mGroupPassword;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        createGroup();
    }

    private void createGroup(){
        createGroup = (Button) findViewById(R.id.createNewGroupButton);
        mGroupName = (EditText) findViewById(R.id.createGroupName);
        mGroupPassword = (EditText) findViewById(R.id.createGroupPassword);

        //Get an instance of DatabaseReference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Set the values of name and password from the edit texts
                final String name = mGroupName.getText().toString().trim();
                final String password = mGroupPassword.getText().toString().trim();
                //Get the userId (to be added to the group)
                final String userId = user.getUid();

                //Check if the group name already exists.
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.child("groups").hasChild(name)){
                            //Group name exists.
                            //Tell the user they need a unique group name
                            Toast.makeText(CreateGroupActivity.this, "Sorry, this group name is already taken!", Toast.LENGTH_SHORT).show();

                        } else {
                            //Make sure the user has entered a group name and password
                            if (name.isEmpty()){
                                //TODO: for some reason if the any groups exist in the database and user enters blank group name, it shows the toast for that group name existing already, not for a blank user
                                Toast.makeText(CreateGroupActivity.this, "You need a group name!", Toast.LENGTH_SHORT).show();
                            } else if (password.isEmpty()){
                                Toast.makeText(CreateGroupActivity.this, "You need a password!", Toast.LENGTH_SHORT).show();
                            } else {
                                //Add the group name to the list of groups in the database, and add its password as a child to it
                                mDatabase.child("groups").child(name).child("password").setValue(password);
                                //Add the user to the group members (as a child of members)
                                mDatabase.child("groups").child(name).child("members").child(userId).setValue(true);
                                //In the user's tab add this as a group they are in
                                mDatabase.child("users").child(userId).child("groups").child(name).setValue(true);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
