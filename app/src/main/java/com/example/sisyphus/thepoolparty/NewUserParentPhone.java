package com.example.sisyphus.thepoolparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mhigh on 7/27/2017.
 */

public class NewUserParentPhone extends AppCompatActivity {

    Button phoneOk;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_parent_setup_phone);
        phoneOk();
    }

    private void phoneOk(){
        phoneOk = (Button) findViewById(R.id.newParentPhoneOk);

        phoneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewUserParentPhone.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
