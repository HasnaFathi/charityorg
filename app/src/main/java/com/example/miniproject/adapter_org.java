package com.example.miniproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class adapter_org extends FirebaseRecyclerAdapter <organization, adapter_org.myviewholder>{


    public adapter_org(@NonNull FirebaseRecyclerOptions<organization> options)
    {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final organization organization)
    {

        holder.name.setText(organization.getName());
        holder.contact.setText(organization.getContact());
        holder.location.setText(organization.getLocation());
        holder.email.setText(organization.getEmail());
        holder.description.setText(organization.getDes());
        //holder.date.setText(organization.getDate());



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updateorg))
                        .setExpanded(true,1500)
                        .create();

                View myview=dialogPlus.getHolderView();

                EditText uname=myview.findViewById(R.id.uname);
                EditText ucontact=myview.findViewById(R.id.ucontact);
                EditText ulocation=myview.findViewById(R.id.ulocation);
                EditText uemail=myview.findViewById(R.id.uemail);
                EditText udescription=myview.findViewById(R.id.udescription);
                EditText udate=myview.findViewById(R.id.udate);
                Button submit=myview.findViewById(R.id.usubmit);



                uname.setText(organization.getName());
                ucontact.setText(organization.getContact());
                ulocation.setText(organization.getLocation());
                uemail.setText(organization.getEmail());
                udescription.setText(organization.getDes());
                udate.setText(organization.getDate());

                dialogPlus.show();


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();

                        map.put("name",uname.getText().toString());
                        map.put("contact",ucontact.getText().toString());
                        map.put("location",ulocation.getText().toString());
                        map.put("email",uemail.getText().toString());
                        map.put("des",udescription.getText().toString());
                        map.put("date",udate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Organization")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void avoid) {
                                        Toast.makeText(holder.name.getContext(),"Details Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(),"Failed to Update Details",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });




                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Organization")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.org_singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        //ImageView img;
        TextView name,contact,location,email,description,date;
        ImageView edit,delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            //img=(ImageView)itemView.findViewbyId.(R.id.img1)
            name=(TextView)itemView.findViewById(R.id.nametext);
            contact=(TextView)itemView.findViewById(R.id.contacttext);
            location=(TextView)itemView.findViewById(R.id.locationtext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
            description=(TextView)itemView.findViewById(R.id.destext);


            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);

        }
    }
}
