package com.example.ibrahim.eshotmessagesender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {

        btn = findViewById(R.id.MsgBtn);
    }

    Button btn;


    public void clickSend(View view) {

        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
        //finish();
    }

    public void clickExit(View view) {

        finish();
    }
}
