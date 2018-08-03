package com.example.ibrahim.eshotmessagesender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private Spinner spinnerType,spinnerSubject;
    private EditText editText;
    private final DatabaseReference messages = FirebaseDatabase.getInstance().getReference("Messages");
    private DatabaseReference ref;

    private void init() {

        spinnerType = findViewById(R.id.spinnerType);
        spinnerSubject = findViewById(R.id.spinnerSubject);
        editText = findViewById(R.id.editText);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
                R.array.listType, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterSubject = ArrayAdapter.createFromResource(this,
                R.array.listSubject, android.R.layout.simple_spinner_item);
        adapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setAdapter(adapterType);
        spinnerSubject.setAdapter(adapterSubject);
    }

    public void clickSend(View view) {

        String key =  messages.child(GetDate.getDate(false,null)).child(spinnerType.getSelectedItem().toString()).child(spinnerSubject.getSelectedItem().toString()).push().getKey();

        assert key != null;
        ref = messages.child(GetDate.getDate(false,null)).child(spinnerType.getSelectedItem().toString()).child(spinnerSubject.getSelectedItem().toString()).child(key);

        HashMap<String,String> eMsg = new HashMap();
        eMsg.put("Message", editText.getText().toString());
        eMsg.put("Local Date", GetDate.getDate(true,null));

        try{

            ref.setValue(eMsg);
            ref.child("Server Timestamp").setValue(ServerValue.TIMESTAMP);

            Toast.makeText(MainActivity.this, "Mesajınız iletildi",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();

        }catch (Exception e){

            Toast.makeText(MainActivity.this, "Bağlantı hatası mesajınız iletilemedi",Toast.LENGTH_LONG).show();
        }
    }
}