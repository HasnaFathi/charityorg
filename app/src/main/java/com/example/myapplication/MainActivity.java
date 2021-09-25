package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reff, reff2;
    long maxid;
    int pCount;
    ArrayList<String> catList;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        EditText name = findViewById(R.id.et_projectName);
        Spinner catergory = findViewById(R.id.sp_selectCat);
        EditText email = findViewById(R.id.et_projectEmail);
        EditText contactNo = findViewById(R.id.et_projectContactNo);
        EditText deadline = findViewById(R.id.et_ProjectDonDeadline);
        EditText donationGoal = findViewById(R.id.et_projectDonationGoal);
        EditText doantionMin = findViewById(R.id.et_projectMinDonation);
        EditText Description = findViewById(R.id.et_projectDescription);
        Button btnAddProject = findViewById(R.id.btn_update_project);
        Button nextBtn = findViewById(R.id.btn_next);
        Button nextBtn2 = findViewById(R.id.btn_next2);

        catList = new ArrayList<>();
        DatabaseReference r = FirebaseDatabase.getInstance().getReference();
        r.child("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.child("name").getValue(String.class);
                    catList.add(name);

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, catList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                catergory.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        // getting the no of child nodes to generate the ID
//        reff = FirebaseDatabase.getInstance().getReference().child("project");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()){
//                    maxid = (dataSnapshot.getChildrenCount());
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //Adding a count value and retrieving Id from it.

        reff = FirebaseDatabase.getInstance().getReference().child("projectCount");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    pCount = (snapshot.getValue(Integer.class));
                    pCount = pCount + 1;
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff2 = FirebaseDatabase.getInstance().getReference().child("project");

                if (TextUtils.isEmpty(name.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a Project name", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(email.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a contact email address", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(contactNo.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a contact number", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(deadline.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a donations deadline", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(donationGoal.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a donation goal", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(doantionMin.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a minimum donation amount", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(Description.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter a project description", Toast.LENGTH_SHORT).show();

                } else {

                    reff.setValue(pCount);

                    Project p = new Project();
                    int i;

                    p.setName(name.getText().toString().trim());
                    String cat = catergory.getSelectedItem().toString();
                    p.setCatergory(cat);
                    p.setContactNo(contactNo.getText().toString().trim());
                    p.setEmail(email.getText().toString().trim());
                    p.setDonationGoal(Double.parseDouble(donationGoal.getText().toString()));
                    p.setMinDonation(Double.parseDouble(doantionMin.getText().toString()));
                    p.setDate(deadline.getText().toString());
                    p.setDescription(Description.getText().toString().trim());
                    p.setProjectID(Integer.parseInt(String.valueOf(pCount)));


                    reff2.child(String.valueOf(pCount)).setValue(p);

                    Toast.makeText(MainActivity.this, "Project added", Toast.LENGTH_SHORT).show();

                }

            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SearchProjects.class);
                startActivity(i);

            }
        });

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);

            }
        });

    }
}