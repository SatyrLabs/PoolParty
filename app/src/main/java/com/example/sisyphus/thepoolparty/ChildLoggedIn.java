package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;

/**
 * Created by mhigh on 7/27/2017.
 */

public class ChildLoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_logged_in);
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
                Intent signOutIntent = new Intent(ChildLoggedIn.this, MainActivity.class);
                startActivity(signOutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
