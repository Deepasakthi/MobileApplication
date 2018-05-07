package com.example.deepaganesan.texoprinters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Final extends AppCompatActivity implements View.OnClickListener{

    TextView et;
    Button b;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        et=(TextView)findViewById(R.id.textView6);

        StringBuilder sb = new StringBuilder();
        sb.append("Name :"+(getIntent().getExtras().getString("Name")));
        sb.append("\nAddress :"+(getIntent().getExtras().getString("Address")));
        sb.append("\nModel :"+(getIntent().getExtras().getString("Model")));
        sb.append("\nColor :"+(getIntent().getExtras().getString("Color")));
        sb.append("\nSize :"+(getIntent().getExtras().getString("Size")));
        sb.append("\nDate of Delivery :"+(getIntent().getExtras().getString("Date")));
        sb.append("\nQuantity :"+(getIntent().getExtras().getString("Quantity")));

        str=sb.toString();
        et.setText(str);

        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(),TexoPage.class));
    }
}
