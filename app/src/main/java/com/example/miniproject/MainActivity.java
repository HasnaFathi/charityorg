package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnproject,btnevent,btnOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnproject=(Button)findViewById(R.id.btnproject);
        btnevent=(Button)findViewById(R.id.btnevent);
        btnOrg=(Button)findViewById(R.id.btnOrg);


        btnproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProductLandingActivity.class));
                finish();
            }
        });

        btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EventMainActivity.class));
                finish();
            }
        });

        btnOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Org_MainActivity.class));
                finish();
            }
        });
    }
}