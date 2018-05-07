package com.example.deepaganesan.texoprinters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPswd;
    private TextView signin;
    private ProgressDialog pgd;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgd=new ProgressDialog(this);
        buttonRegister=(Button)findViewById(R.id.buttons);
        editTextEmail=(EditText)findViewById(R.id.edittextEmail);
        editTextPswd=(EditText)findViewById(R.id.edittextpswd);
        signin=(TextView)findViewById(R.id.signin);
        fba=FirebaseAuth.getInstance();
        buttonRegister.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String pswd = editTextPswd.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pswd)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        pgd.setMessage("Registering.....");
        pgd.show();
        fba.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pgd.dismiss();
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==buttonRegister){
            registerUser();
        }

        if(v==signin){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
