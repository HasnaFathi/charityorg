package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addEvent extends AppCompatActivity {

    EditText name,date,location,email ;
    Button addev;
    DatabaseReference reff;
    Event ev;
    Button back;

    private void clearControls(){

        name.setText("");
        date.setText("");
        location.setText("");
        email.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        name= findViewById(R.id.add_name);
        date=  findViewById(R.id.add_date);
        location= findViewById(R.id.add_location);
        email= findViewById(R.id.add_email);
        addev =  findViewById(R.id.add_submit);

        ev = new Event();

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventMainActivity.class);
                startActivity(intent);
            }
        });

        addev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("Event");
                try{

                    if(TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Event name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(location.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Location", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the email", Toast.LENGTH_SHORT).show();
                    else {

                        ev.setName(name.getText().toString().trim());
                        ev.setDate(date.getText().toString().trim());
                        ev.setLocation(location.getText().toString().trim());
                        ev.setEmail(email.getText().toString().trim());



                        reff.push().setValue(ev);

                        Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                        clearControls();
                    }

                }catch (Exception e){

                    Toast.makeText(getApplicationContext(), "Data Inserted Unsuccessful", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}