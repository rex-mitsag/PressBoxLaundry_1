package com.mask.pressboxlaundry;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditPage extends AppCompatActivity {

    EditText name, cno, cont, hostel;
    int c, c1;

    ProgressBar ProgB;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        mAuth = FirebaseAuth.getInstance();

        ProgB = (ProgressBar) findViewById(R.id.Prog);
        name = (EditText) findViewById(R.id.PName);
        cno = (EditText) findViewById(R.id.Cardno);
        cont = (EditText) findViewById(R.id.Contact);
        hostel = (EditText) findViewById(R.id.Hostel);

    }

    private int writeInfo() {
        String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        String dname = name.getText().toString().trim();
        String card = cno.getText().toString().trim();
        String contact = cont.getText().toString().trim();
        String host = hostel.getText().toString().trim();

        Map dataMap = new HashMap();
        dataMap.put("Name", dname);
        dataMap.put("CardNo", card);
        dataMap.put("Contact", contact);
        dataMap.put("Hostel", host);

        mDatabase.setValue(dataMap);

        return c = 1;

    }

    public void saveinfo(View view) {

        FirebaseUser user = mAuth.getCurrentUser();

        ProgB.setVisibility(View.VISIBLE);
        if (user != null) {
            UserProfileChangeRequest prof = new UserProfileChangeRequest.Builder()
                    .build();
            c1 = writeInfo();
            user.updateProfile(prof).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    ProgB.setVisibility(View.GONE);
                    if (task.isSuccessful() && c1 == 1) {
                        Toast.makeText(EditPage.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}