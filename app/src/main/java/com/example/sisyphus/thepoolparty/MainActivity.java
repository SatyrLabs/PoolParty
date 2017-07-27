package com.example.sisyphus.thepoolparty;


import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.sisyphus.thepoolparty.R.styleable.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    Button parentLoginButton;
    Button childLoginButton;
    Button newUserButton;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static final int RC_SIGN_IN = 1;  //request code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        parentLoginButton = (Button) findViewById(R.id.loggedInParent);
        childLoginButton = (Button) findViewById(R.id.loggedInChild);
        newUserButton = (Button) findViewById(R.id.newUser);

        // Capture button clicks (simulating initial login for each user type)
        parentLoginButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        ParentLoggedIn.class);
                startActivity(myIntent);
            }
        });
        childLoginButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick (View view){
                Intent childIntent = new Intent(MainActivity.this, ChildLoggedIn.class);
                startActivity(childIntent);
            }
        });
        newUserButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick (View view){
                Intent newUserIntent = new Intent(MainActivity.this, NewUser.class);
                startActivity(newUserIntent);
            }
        });

        //Initialize the authentication object and state listener
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Check if user is logged in or not.  If not, show login screen
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());

                } else{
                    //user is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN); //Request code is a flag for when we start activity
                }

            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListener != null){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }

    }

    private void onSignedInInitialize(String username){
        //Set the username variable (set so that future messages can have a username attached to them)
        mUsername = username;
    }

    private void onSignedOutCleanup(){
        mUsername = ANONYMOUS;
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
