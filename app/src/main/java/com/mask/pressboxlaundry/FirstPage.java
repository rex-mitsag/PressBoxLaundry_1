package com.mask.pressboxlaundry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FirstPage extends AppCompatActivity {

    TextView day,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dformat=new SimpleDateFormat("dd-MM-yyyy");

        Date d=new Date();
        String datedis=dformat.format(calendar.getTime());

        SimpleDateFormat dayfom=new SimpleDateFormat("EEEE");
        String daydis=dayfom.format(d);

        date=(TextView) findViewById(R.id.dateview);
        day=(TextView) findViewById(R.id.Dayview);
        day.setText(daydis);
        date.setText(datedis);

    }

    public void showProfile_func(MenuItem item) {
       Intent intent=new Intent(this,ProfilePage.class);
        startActivity(intent);
    }

    public void showClothes_func(MenuItem item) {
        Intent intent=new Intent(this,ClothPage.class);
        startActivity(intent);
    }

    public void settings_func(MenuItem item) {
        Intent intent=new Intent(this,SettingsPage.class);
        startActivity(intent);

    }

    public void about_func(MenuItem item) {
        Intent intent=new Intent(this,AboutPage.class);
        startActivity(intent);
    }

    public void Logout_func(MenuItem item) {
        Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,LoginPage.class);
        startActivity(intent);
        finish();
    }

    public void Status_func(View view) {
        Toast.makeText(this,"Shows Status",Toast.LENGTH_SHORT).show();
        int c=0;
        if(c==0)
        {
            Intent intent=new Intent(this,Prepared_clothes.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(this,Unfinished_cloth.class);
            startActivity(intent);
        }
    }

    public void Give_func(View view) {
        Toast.makeText(this,"Give clothes",Toast.LENGTH_SHORT).show();
    }

    public void Take_func(View view) {
        Toast.makeText(this,"Taking Clothes",Toast.LENGTH_SHORT).show();
    }
}
