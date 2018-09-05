package com.mask.pressboxlaundry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfilePage extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ProfilePage";
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference mDatabase;
    String user_id;

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference();
        FirebaseUser us = mAuth.getCurrentUser();
        user_id = us.getUid();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        findViewById(R.id.edit).setOnClickListener(this);

    }

    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserInformation ui = new UserInformation();
            ui.setName(ds.child(user_id).getValue(UserInformation.class).getName());
            ui.setCardNo(ds.child(user_id).getValue(UserInformation.class).getCardNo());
            ui.setContact(ds.child(user_id).getValue(UserInformation.class).getContact());
            ui.setHostel(ds.child(user_id).getValue(UserInformation.class).getHostel());

            //Display all info
            Log.d(TAG, "Name: " + ui.getName());
            Log.d(TAG, "Card No: " + ui.getCardNo());
            Log.d(TAG, "Contact: " + ui.getContact());
            Log.d(TAG, "Hostel: " + ui.getHostel());

            ArrayList<String> array = new ArrayList<>();
            array.add(ui.getName());
            array.add(ui.getCardNo());
            array.add(ui.getContact());
            array.add(ui.getHostel());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mList.setAdapter(adapter);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit:
                Intent intent = new Intent(ProfilePage.this, EditPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}

