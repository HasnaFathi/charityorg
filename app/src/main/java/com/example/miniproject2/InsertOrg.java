package com.example.miniproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertOrg extends AppCompatActivity {

    EditText etId, etname, etcontact, etlocation, etemail, etdes, etdate;
    Button btnadd;

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

        btnadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //check empty fields
                if (TextUtils.isEmpty(etId.getText().toString())) {

                    Toast.makeText(InsertOrg.this, "Empty ID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etname.getText().toString())) {
                    Toast.makeText(InsertOrg.this, "Empty Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etcontact.getText().toString())) {
                    Toast.makeText(InsertOrg.this, "Empty Contact Field", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etlocation.getText().toString())) {
                    Toast.makeText(InsertOrg.this, "Empty Location Field", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etdate.getText().toString())) {
                    Toast.makeText(InsertOrg.this, "Empty Date Field", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(InsertOrg.this, "Successfully Added Organization", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}