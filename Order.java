package com.example.deepaganesan.texoprinters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private TextView oDetails;
    private EditText namee,addre,nose;
    private Button button;
    private Spinner s1,s2;
    private DatePicker dp;
    public String names,addr,model,colour,msize,date,nos;
    private String[] colors = { "Green" , "Red" , "Blue" , "Teal" , "Same as Model"  };
    private String[] msizes = { "Mat-34x48" , "Mat-24x72" , "Mat-30x45" , "Bed-39'x75'" , "Bed-60'x80'" , "Bed-78'x80'" ,"Towel-12'x12'" , "Towel-20'x30'" , "Towel-30'x56'"};

    private DatabaseReference dbref;
    private FirebaseAuth fba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        oDetails=(TextView)findViewById(R.id.textView2);
        namee=(EditText)findViewById(R.id.editText4);
        addre=(EditText)findViewById(R.id.editText5);
        nose=(EditText)findViewById(R.id.editText6);
        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner1);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        dp=(DatePicker)findViewById(R.id.datePicker);

        ArrayAdapter cc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,colors);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter ss = new ArrayAdapter(this,android.R.layout.simple_spinner_item,msizes);
        ss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(cc);
        s2.setAdapter(ss);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        oDetails.setText("Model is :"+(getIntent().getExtras().getString("key")));
        model=getIntent().getExtras().getString("key");

        fba=FirebaseAuth.getInstance();
        dbref= FirebaseDatabase.getInstance().getReference();
    }

    private void saveOrder(){
        OrderInfo orderInfo=new OrderInfo(names,addr,model,colour,msize,date,nos);
        FirebaseUser user= fba.getCurrentUser();
        dbref.child(user.getUid()).setValue(orderInfo);
        //Toast.makeText(getApplicationContext(),"Order Placed...!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

        names=namee.getText().toString().trim();
        //Toast.makeText(getApplicationContext(),names,Toast.LENGTH_SHORT).show();
        addr=addre.getText().toString().trim();
        nos=nose.getText().toString().trim();
        //Toast.makeText(getApplicationContext(),names,Toast.LENGTH_SHORT).show();

        StringBuilder builder=new StringBuilder();
        builder.append(dp.getDayOfMonth()+"/");
        builder.append((dp.getMonth() + 1)+"/");
        builder.append(dp.getYear());
        date = builder.toString();

        saveOrder();
        Intent i=new Intent(getApplicationContext(),Final.class);
        i.putExtra("Name",names);
        i.putExtra("Address",addr);
        i.putExtra("Color",colour);
        i.putExtra("Model",model);
        i.putExtra("Size",msize);
        i.putExtra("Quantity",nos);
        i.putExtra("Date",date);
        finish();
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            colour=colors[i];
            //Toast.makeText(getApplicationContext(),colour,Toast.LENGTH_SHORT).show();

            msize=msizes[i];
            //Toast.makeText(getApplicationContext(),msize,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}
