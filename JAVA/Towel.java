package com.example.deepaganesan.texoprinters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Towel extends AppCompatActivity implements View.OnClickListener {

    private TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towel);

        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        //t6=(TextView)findViewById(R.id.t6);

        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        //t6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==t1){
            Intent i=new Intent(getApplicationContext(),Order.class);
            i.putExtra("key","towel1");
            startActivity(i);
        }
        if(view==t2){
            Intent i=new Intent(getApplicationContext(),Order.class);
            i.putExtra("key","towel2");
            startActivity(i);
        }
        if(view==t3){
            Intent i=new Intent(getApplicationContext(),Order.class);
            i.putExtra("key","towel3");
            startActivity(i);
        }
        if(view==t4){
            Intent i=new Intent(getApplicationContext(),Order.class);
            i.putExtra("key","towel4");
            startActivity(i);
        }
        if(view==t5){
            Intent i=new Intent(getApplicationContext(),Order.class);
            i.putExtra("key","towel5");
            startActivity(i);
        }

    }
}
