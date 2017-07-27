package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mhigh on 7/27/2017.
 */

public class NewUser extends AppCompatActivity {

    Button parentSelection;
    Button childSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_setup);
        setUpSelectionButtons();
    }

    public void setUpSelectionButtons(){
        parentSelection = (Button) findViewById(R.id.newUserParentSelection);
        childSelection = (Button) findViewById(R.id.newUserChildSelection);

        parentSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Send intent to parent setup
                Intent parentIntent = new Intent(NewUser.this, NewUserParent.class);
                startActivity(parentIntent);
            }
        });

        childSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Send intent to parent setup
                Intent childIntent = new Intent(NewUser.this, NewUserChild.class);
                startActivity(childIntent);
            }
        });
    }
}
