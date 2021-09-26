package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Org_MainActivity extends AppCompatActivity {

    RecyclerView recview;
    adapter_org adapter;
    FloatingActionButton add;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orgactivity_main);



        recview=(RecyclerView)findViewById(R.id.recView);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<organization> options =
                new FirebaseRecyclerOptions.Builder<organization>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Organization"), organization.class)
                        .build();

        adapter=new adapter_org(options);
        recview.setAdapter(adapter);

        add=(FloatingActionButton)findViewById(R.id.btninsert);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InsertOrg.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);


        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                process_search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                process_search(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void process_search(String s) {

        FirebaseRecyclerOptions<organization> options =
                new FirebaseRecyclerOptions.Builder<organization>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Organization").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), organization.class)
                        .build();

        adapter=new adapter_org(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }
}