package com.example.sisyphus.thepoolparty;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class JoinTeamActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    Button joinGroup;
    private EditText mGroupName;
    private EditText mGroupPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);
        joinGroup();
    }

    private void joinGroup(){
        joinGroup = (Button) findViewById(R.id.joinGroupButton);
        mGroupName = (EditText) findViewById(R.id.joinGroupName);
        mGroupPassword = (EditText) findViewById(R.id.joinGroupPassword);

        //Get an instance of DatabaseReference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Set the values of name and password from the EditTexts
                final String name = mGroupName.getText().toString().trim();
                final String password = mGroupPassword.getText().toString().trim();
                //get the user Id
                final String userId = user.getUid();

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //make sure the group exists
                        if(snapshot.child("groups").child(name).exists()){
                            String val = (String) snapshot.child("groups").child(name).child("password").getValue();
                            //If the password matches the current groups, add the user to the group's children
                            if(password.equals(val)){
                                //password was correct: add user to group members child
                                mDatabase.child("groups").child(name).child("members").child(userId).setValue(true);
                                //Add group to user's list of groups
                                mDatabase.child("users").child(userId).child("groups").child(name).setValue(true);
                            } else {
                                Toast.makeText(JoinTeamActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //Tell the user the group doesn't exist
                            Toast.makeText(JoinTeamActivity.this, "Group currently doesn't exist. You can make a group in the Create a group tab!", Toast.LENGTH_LONG).show();
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
