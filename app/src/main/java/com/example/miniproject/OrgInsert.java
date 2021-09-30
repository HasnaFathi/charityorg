package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class OrgInsert extends AppCompatActivity {

    EditText etId, etname, etcontact, etlocation, etemail, etdes, etdate;
    Button btnadd;
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_org);

        etId = findViewById(R.id.etId);
        etname = findViewById(R.id.etname);
        etcontact = findViewById(R.id.etcontact);
        etlocation = findViewById(R.id.etLocation);
        etemail = findViewById(R.id.etEmail);
        etdes = findViewById(R.id.etdes);
        etdate = findViewById(R.id.etdate);
        btnadd = findViewById(R.id.btnAdd);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Org_MainActivity.class));
                finish();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //check empty fields
                if (TextUtils.isEmpty(etId.getText().toString())) {

                    Toast.makeText(OrgInsert.this, "Empty ID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etname.getText().toString())) {
                    Toast.makeText(OrgInsert.this, "Empty Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etcontact.getText().toString())) {
                    Toast.makeText(OrgInsert.this, "Empty Contact Field", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etlocation.getText().toString())) {
                    Toast.makeText(OrgInsert.this, "Empty Location Field", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etdate.getText().toString())) {
                    Toast.makeText(OrgInsert.this, "Empty Date Field", Toast.LENGTH_SHORT).show();
                }

                //if not empty,connect to firebase
                else {
                    //create student
                    organization org=new organization(
                            etId.getText().toString().trim(),
                            etname.getText().toString().trim(),
                            etcontact.getText().toString().trim(),
                            etlocation.getText().toString().trim(),
                            etemail.getText().toString().trim(),
                            etdes.getText().toString().trim(),
                            etdate.getText().toString().trim()
                    );
                    //reference
                    FirebaseDatabase.getInstance().getReference().child("Organization/" + org.getId()).setValue(org);
                    //set value
                    //show toast
                    Toast.makeText(OrgInsert.this, "Successfully Added Organization", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}