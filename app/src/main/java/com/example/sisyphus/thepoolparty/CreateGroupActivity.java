package com.example.sisyphus.thepoolparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                String name = mGroupName.getText().toString().trim();
                String password = mGroupPassword.getText().toString().trim();
                //Get the userId (to be added to the group)
                String userId = user.getUid();

                //TODO: Stop different users from being able to overwrite group data if they enter the same group name.
                //Add the group name to the list of groups in the database, and add its password as a child to it
                mDatabase.child("groups").child(name).child("password").setValue(password);
                //Add the user to the group members
                mDatabase.child("groups").child(name).child("members").setValue(userId);

                //In the user's tab add this as a group they are in
                mDatabase.child("users").child(userId).child("groups").child(name).setValue(name);
            }
        });
    }
}
