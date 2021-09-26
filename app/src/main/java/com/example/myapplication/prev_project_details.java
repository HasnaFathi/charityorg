package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class prev_project_details extends AppCompatActivity {

    private TextView name, cat, des, email, mobile, deadline, goal, min;
    private Button editDetails, removeProject;
    Project p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_project_details);


        name = findViewById(R.id.tv_display_name);
        cat = findViewById(R.id.tv_display_cat);
        des = findViewById(R.id.tv_display_description);
        email = findViewById(R.id.tv_display_contactEmail);
        mobile = findViewById(R.id.tv_display_contactNo);
        deadline = findViewById(R.id.tv_display_deadline);
        goal = findViewById(R.id.tv_display_goal);
        min = findViewById(R.id.tv_display_MinDonation);
        editDetails = findViewById(R.id.btn_updatePage);
        removeProject = findViewById(R.id.btn_delete);
        getSupportActionBar().setTitle("Project Details");


        p = getIntent().getParcelableExtra("project");



        name.setText(String.valueOf(p.getName()));
        cat.setText(p.getCatergory());
        des.setText(p.getDescription());
        email.setText(p.getEmail());
        mobile.setText(p.getContactNo());
        deadline.setText(String.valueOf(p.getDate()));
        goal.setText(String.valueOf(p.getDonationGoal()));
        min.setText(String.valueOf(p.getMinDonation()));

    }
}