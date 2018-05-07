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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonsi;
    private EditText editTextEmail;
    private EditText editTextpswd;
    private TextView signup;
    private ProgressDialog pgd;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=(EditText)findViewById(R.id.edittextEmail);
        editTextpswd=(EditText)findViewById(R.id.edittextpswd);
        buttonsi=(Button)findViewById(R.id.buttonsi);
        signup=(TextView)findViewById(R.id.signup);
        pgd=new ProgressDialog(this);

        fba=FirebaseAuth.getInstance();
        /*if(fba.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),TexoPage.class));
        }*/

        buttonsi.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String pswd = editTextpswd.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pswd)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        pgd.setMessage("Logging in.....");
        pgd.show();

        fba.signInWithEmailAndPassword(email,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pgd.dismiss();
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(),TexoPage.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v==buttonsi){
            userLogin();
        }

        if(v==signup){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
