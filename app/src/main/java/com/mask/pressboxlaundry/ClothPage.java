package com.mask.pressboxlaundry;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mask.pressboxlaundry.R;

public class ClothPage extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView shirt,tshirt,jeans,under,bedsht,other;

    public  static  final String fname="Cloth_count";
    public  static final String Shirt="shirt_key";
    public  static final String Jeans="jeans_key";
    public  static final String TShirt="t_shirt_key";
    public  static final String Under="under_key";
    public  static final String Bedsht="bed_key";
    public  static final String Other="other_key";

    String s,t,j,u,b,o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_page);

        shirt=(TextView) findViewById(R.id.editText);
        jeans=(TextView) findViewById(R.id.editText2);
        tshirt=(TextView) findViewById(R.id.editText3);
        under=(TextView) findViewById(R.id.editText4);
        bedsht=(TextView) findViewById(R.id.editText5);
        other=(TextView) findViewById(R.id.editText6);

        sharedpreferences=getSharedPreferences(fname, Context.MODE_PRIVATE);

        SharedPreferences spref=getSharedPreferences(fname,Context.MODE_PRIVATE);
        s=spref.getString(Shirt,"");
        t=spref.getString(TShirt,"");
        j=spref.getString(Jeans,"");
        u=spref.getString(Under,"");
        b=spref.getString(Bedsht,"");
        o=spref.getString(Other,"");

        shirt.setText(s);
        tshirt.setText(t);
        jeans.setText(j);
        under.setText(u);
        bedsht.setText(b);
        other.setText(o);

        /*
        if(sharedpreferences.contains(Shirt))
            shirt.setText(sharedpreferences.getString(Shirt,""));
        if(sharedpreferences.contains(TShirt))
            shirt.setText(sharedpreferences.getString(TShirt,""));
        if(sharedpreferences.contains(Jeans))
            shirt.setText(sharedpreferences.getString(Jeans,""));
        if(sharedpreferences.contains(Under))
            shirt.setText(sharedpreferences.getString(Under,""));
        if(sharedpreferences.contains(Bedsht))
            shirt.setText(sharedpreferences.getString(Bedsht,""));
        if(sharedpreferences.contains(Other))
            shirt.setText(sharedpreferences.getString(Other,""));
    */
    }

    public void Clear_func(View view) {
        Toast.makeText(this,"Data Cleared",Toast.LENGTH_SHORT).show();

        shirt.setText("");
        tshirt.setText("");
        jeans.setText("");
        under.setText("");
        bedsht.setText("");
        other.setText("");

        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void Save_func(View view) {
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();

        s=shirt.getText().toString();
        t=tshirt.getText().toString();
        j=jeans.getText().toString();
        u=under.getText().toString();
        b=bedsht.getText().toString();
        o=other.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(Shirt,s);
        editor.putString(TShirt,t);
        editor.putString(Jeans,j);
        editor.putString(Bedsht,b);
        editor.putString(Under,u);
        editor.putString(Other,o);

        editor.apply();

        finish();
    }
}
