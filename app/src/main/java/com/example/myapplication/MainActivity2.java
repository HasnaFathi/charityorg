package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private TextView name, cat, des, email, mobile, deadline, goal, min;
    private Button editDetails, removeProject;
    Project p;
    Project pr = new Project();
    long PID;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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


                p = getIntent().getParcelableExtra("project");



        name.setText(String.valueOf(p.getName()));
        cat.setText(p.getCatergory());
        des.setText(p.getDescription());
        email.setText(p.getEmail());
        mobile.setText(p.getContactNo());
        deadline.setText(String.valueOf(p.getDate()));
        goal.setText(String.valueOf(p.getDonationGoal()));
        min.setText(String.valueOf(p.getMinDonation()));

        PID = p.getProjectID();

        //Update project

        DialogPlus dialogPlus = DialogPlus.newDialog(this).setGravity(Gravity.CENTER).setMargin(50,0,50,0)
                .setContentHolder(new ViewHolder(R.layout.update_project)).setExpanded(false).create();
        p = getIntent().getParcelableExtra("project");

        View holderView = (LinearLayout) dialogPlus.getHolderView();

        EditText name1 = holderView.findViewById(R.id.et_projectName);
        Spinner catergory = holderView.findViewById(R.id.sp_selectCat);
        EditText email1 = holderView.findViewById(R.id.et_projectEmail);
        EditText contactNo1 = holderView.findViewById(R.id.et_projectContactNo);
        EditText deadline1 = holderView.findViewById(R.id.et_ProjectDonDeadline);
        EditText donationGoal1 = holderView.findViewById(R.id.et_projectDonationGoal);
        EditText doantionMin1 = holderView.findViewById(R.id.et_projectMinDonation);
        EditText Description1 = holderView.findViewById(R.id.et_projectDescription);
        Button btnAddProject1 = holderView.findViewById(R.id.btn_update_project);


        btnAddProject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<>();

                map.put("contactNo", contactNo1.getText().toString());
                map.put("date", deadline1.getText().toString());
                map.put("description", Description1.getText().toString());
                map.put("donationGoal", Double.parseDouble(donationGoal1.getText().toString()));
                map.put("email", email1.getText().toString());
                map.put("minDonation", Double.parseDouble(doantionMin1.getText().toString()));
                map.put("name", name1.getText().toString());



                pr.setName(name1.getText().toString().trim());
                pr.setContactNo(contactNo1.getText().toString().trim());
                pr.setCatergory(p.getCatergory());
                pr.setEmail(email1.getText().toString().trim());
                pr.setDonationGoal(Double.parseDouble(donationGoal1.getText().toString()));
                pr.setMinDonation(Double.parseDouble(doantionMin1.getText().toString()));
                pr.setDate(deadline1.getText().toString());
                pr.setDescription(Description1.getText().toString().trim());
                pr.setProjectID(p.getProjectID());




                FirebaseDatabase.getInstance().getReference("project").child(String.valueOf(p.getProjectID())).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {


                        name.setText(String.valueOf(pr.getName()));
                        cat.setText(pr.getCatergory());
                        des.setText(pr.getDescription());
                        email.setText(pr.getEmail());
                        mobile.setText(pr.getContactNo());
                        deadline.setText(String.valueOf(pr.getDate()));
                        goal.setText(String.valueOf(pr.getDonationGoal()));
                        min.setText(String.valueOf(pr.getMinDonation()));

                        p = pr;

                        dialogPlus.dismiss();

                    }
                });


//
//
//                reff.child(String.valueOf(PID)).setValue(p);
//
//                Toast.makeText(MainActivity2.this, "Project added", Toast.LENGTH_SHORT).show();
//
//
////                DatabaseReference reff = FirebaseDatabase.getInstance().getReference("project");
////                reff.chi

            }
        });

        name1.setText(p.getName());
        email1.setText(p.getEmail());
        contactNo1.setText(p.getContactNo());
        deadline1.setText(p.getDate());
        donationGoal1.setText(String.valueOf(p.getDonationGoal()));
        doantionMin1.setText(String.valueOf(p.getMinDonation()));
        Description1.setText(p.getDescription());

        Button btn_update = holderView.findViewById(R.id.btn_update_project);

        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AlertDialog.Builder builder = new AlertDialog.Builder(this);

                dialogPlus.show();



            }
        });

        removeProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteProject(p);

            }
        });

        }

    private void deleteProject(Project project) {

        DatabaseReference r1 = FirebaseDatabase.getInstance().getReference("project").child(String.valueOf(project.getProjectID()));
        DatabaseReference r2 = FirebaseDatabase.getInstance().getReference().child("prevProjects");

        r1.removeValue();

        r2.child(String.valueOf(project.getProjectID())).setValue(project);


    }

}