package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ParentLoggedIn extends AppCompatActivity {
    Button createGroupButton;
    Button joinGroupButton;
    Button viewGroupButton;
    Button myKidsButton;

    public void setUpAdultButton(){

        createGroupButton = (Button) findViewById(R.id.createPoolGroup);
        joinGroupButton = (Button) findViewById(R.id.joingPoolGroup);
        viewGroupButton = (Button) findViewById(R.id.viewPoolGroup);
        myKidsButton = (Button) findViewById(R.id.myKids);

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ParentLoggedIn.this,
                        CreateGroupActivity.class);
                startActivity(myIntent);
            }
        });


        joinGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ParentLoggedIn.this,
                        JoinTeamActivity.class);
                startActivity(myIntent);
            }
        });


        viewGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ParentLoggedIn.this,
                        dummyActivity.class);
                startActivity(myIntent);
            }
        });


        myKidsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ParentLoggedIn.this,
                        dummyActivity.class);
                startActivity(myIntent);
            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_logged_in);
        setUpAdultButton();


    }
}
