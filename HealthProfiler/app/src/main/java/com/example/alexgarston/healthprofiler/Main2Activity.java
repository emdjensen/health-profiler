package com.example.alexgarston.healthprofiler;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    double height, weight, age, calcbmi, calcmaxheart, calcmin, calcmax;
    String dec;
    EditText input1,input2,input3;
    Button apply, next;
    TextView bmi,health,heart,min,max;
    DB_controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        input1=(EditText)findViewById(R.id.input1);
        input2=(EditText)findViewById(R.id.input2);
        input3=(EditText)findViewById(R.id.input3);

        apply=(Button)findViewById(R.id.apply);
        next=(Button)findViewById(R.id.next);

        bmi=(TextView)findViewById(R.id.bmi);
        health=(TextView)findViewById(R.id.health);
        heart=(TextView)findViewById(R.id.heartrate);
        min=(TextView)findViewById(R.id.min);
        max=(TextView)findViewById(R.id.max);

        controller = new DB_controller(this, "", null, 1);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height= Double.parseDouble(input1.getText().toString());
                weight= Double.parseDouble(input2.getText().toString());
                age= Double.parseDouble(input3.getText().toString());

                calcbmi= (weight*703)/(height*height);
                calcmaxheart=220-age;
                calcmin=.50*calcmaxheart;
                calcmax=.80*calcmaxheart;

                if(calcbmi>19)
                    if(calcbmi<24)
                    {
                        dec= "You are Normal";
                    }
                if(calcbmi>25)
                    if(calcbmi<29)
                    {
                        dec= "You are Overweight";
                    }
                if(calcbmi>30)
                    if(calcbmi<39)
                    {
                        dec= "You are Obese";
                    }
                if(calcbmi>40)
                {
                    dec ="You are Extremely Obese";
                }

                bmi.setText(String.valueOf(calcbmi));
                health.setText(dec);
                heart.setText(String.valueOf(calcmaxheart));
                min.setText(String.valueOf(calcmin));
                max.setText(String.valueOf(calcmax));


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    controller.insert_student(bmi.getText().toString(), health.getText().toString(), heart.getText().toString(), min.getText().toString(), max.getText().toString());
                }

                catch(SQLiteException e)
                {
                    Toast.makeText(Main2Activity.this, "ALREADY EXISTS", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });




    }
}
