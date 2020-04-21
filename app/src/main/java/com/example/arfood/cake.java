package com.example.arfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class cake extends AppCompatActivity {

    ImageButton imageButton,imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);

        imageButton = (ImageButton) findViewById(R.id.cake1);
        imageButton1 = (ImageButton) findViewById(R.id.cupcake);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cake.this, cake_ar.class);
                startActivity(intent);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cake.this, cupcake_ar.class);
                startActivity(intent);
            }
        });




    }
}
