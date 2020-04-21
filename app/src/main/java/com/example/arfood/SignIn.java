package com.example.arfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    public static final String PREFS_NAME = "LoginPrefs";

    EditText edtPhn, edtPass;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhn = (EditText) findViewById(R.id.edtPhone);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(SignIn.this, Category.class);
            startActivity(intent);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("Please Wait");
                dialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtPhn.getText().toString()).exists()) {
                            dialog.dismiss();
                            User user = dataSnapshot.child(edtPhn.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPass.getText().toString())) {
                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("logged", "logged");
                                editor.commit();
                                Intent category = new Intent(SignIn.this, Category.class);
                                startActivity(category);

                            } else {
                                Toast.makeText(SignIn.this, "Sign In Not Successfully!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "User Don't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
