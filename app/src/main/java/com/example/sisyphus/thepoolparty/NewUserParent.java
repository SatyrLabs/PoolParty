package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mhigh on 7/27/2017.
 */

public class NewUserParent extends AppCompatActivity {

    Button imageAndTextOk;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_parent_setup);
        imageAndTextOk();
    }

    private void imageAndTextOk(){
        imageAndTextOk = (Button) findViewById(R.id.parentNameAndImageOk);

        imageAndTextOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(NewUserParent.this, NewUserParentCar.class);
                startActivity(intent);
            }
        });

    }
}
