package com.example.arfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class Category extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginPrefs";
    GridLayout gridLayout;
    ImageButton btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        btnLogout = (ImageButton) findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent logout = new Intent(Category.this, MainActivity.class);
                startActivity(logout);
            }
        });



        gridLayout = (GridLayout)findViewById(R.id.grid);



        setSingleEvent(gridLayout);
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i=0; i<gridLayout.getChildCount(); i++){

            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0){
                        Intent intent = new Intent(Category.this, cake.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1){
                        Intent intent = new Intent(Category.this, doughnut.class);
                        startActivity(intent);
                    }


                }
            });

        }
    }
}
