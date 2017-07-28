package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mhigh on 7/27/2017.
 */

public class NewUserParentCar extends AppCompatActivity {

    Button carImageOk;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_parent_setup_car);
        carImageOk();
    }

    private void carImageOk(){
        carImageOk = (Button) findViewById(R.id.carImageAndOk);

        carImageOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(NewUserParentCar.this, NewUserParentPhone.class);
                startActivity(intent);
            }
        });

    }
}
