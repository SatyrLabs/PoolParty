package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;


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
                Intent myIntent = new Intent(ParentLoggedIn.this, ViewMyGroups.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Banner menu for sign out
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                //sign out
                AuthUI.getInstance().signOut(this);
                //Launch an intent back to the main menu (will lead to the firebase Auth since we are logged out)
                //TODO: Is there a more efficient way to do this without building up the stack?
                Intent signOutIntent = new Intent(ParentLoggedIn.this, MainActivity.class);
                startActivity(signOutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
