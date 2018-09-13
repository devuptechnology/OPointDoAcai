package com.devup.opointdoacai.opointdoacai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    EditText email,password;
    CheckBox checkBox;
    ImageButton signin;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        checkBox = findViewById(R.id.checkbox);

        signin = findViewById(R.id.signin);

        signup = findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this,"Sign In Button Clicked",Toast.LENGTH_LONG).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(Login.this,SignUpActivity.class);
                //startActivity(i);
            }
        });

    }
}
