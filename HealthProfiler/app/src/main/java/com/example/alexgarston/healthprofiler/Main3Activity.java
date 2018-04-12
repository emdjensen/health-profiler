package com.example.alexgarston.healthprofiler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    DB_controller controller;
    TextView textView;
    Button display, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        display = (Button) findViewById(R.id.display);
        back = (Button) findViewById(R.id.back);
        textView=(TextView)findViewById(R.id.textView);

        controller = new DB_controller(this, "", null, 1);





        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.list_all_students(textView);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}


