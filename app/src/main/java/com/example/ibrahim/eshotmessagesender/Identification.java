package com.example.ibrahim.eshotmessagesender;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Identification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        init();
    }

    private void init() {

        et_IdNo = findViewById(R.id.et_IdNo);
        et_Name = findViewById(R.id.et_Name);
        et_Surname = findViewById(R.id.et_Surname);
        et_Email = findViewById(R.id.et_Email);
        et_Gsm = findViewById(R.id.et_Gsm);
        et_Phone = findViewById(R.id.et_Phone);
        tv_error = findViewById(R.id.tv_Error);
    }

    EditText et_IdNo, et_Name, et_Surname, et_Email, et_Gsm, et_Phone;
    TextView tv_error;

    public void bt_onclick_Write(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        if(wrongChecker(et_IdNo.getText().toString(),et_Name.getText().toString(),et_Surname.getText().toString(),et_Email.getText().toString(),et_Gsm.getText().toString(),et_Phone.getText().toString())){

            HashMap map = new HashMap();

            map.put("Id",et_IdNo.getText().toString());
            map.put("Name",et_Name.getText().toString());
            map.put("Name2",et_Surname.getText().toString());
            map.put("Mail",et_Email.getText().toString());
            map.put("PhoneGsm",et_Gsm.getText().toString());
            map.put("Phone",et_Phone.getText().toString());

            intent.putExtra("id",map);
            startActivity(intent);
        }
    }

    private boolean wrongChecker(String id, String name, String surname, String email, String gsm, String phone){

        if(wrongId(id)) {
            tv_error.setText("Lütfen geçerli bir T.C Kimlik numarası giriniz..");
            return false;
        }
        else{
            if(wrongName(name)) {
                tv_error.setText("Lütfen geçerli bir ad giriniz..");
                return false;
            }
            else{
                if(wrongName(surname)) {
                    tv_error.setText("Lütfen geçerli bir soyad giriniz..");
                    return false;
                }
                else{
                    if(wrongMail(email)) {
                        tv_error.setText("Lütfen geçerli bir e-mail giriniz..");
                        return false;
                    }
                    else{
                        if(wrongGsm(gsm)) {
                            tv_error.setText("Lütfen geçerli bir gsm numarası giriniz..");
                            return false;
                        }
                        else{
                            if(wrongGsm(phone)) {
                                tv_error.setText("Lütfen geçerli bir telefon numarası giriniz..");
                                return false;
                            }
                            else{

                                if(email.length() == 0 && gsm.length() == 0 && phone.length() == 0) {
                                    tv_error.setText("Lütfen iletişim bilgilerinden en az birini doldurunuz..");
                                    return false;
                                }
                                else
                                    return true;

                            }
                        }
                    }
                }
            }
        }
    }

    private boolean wrongId(String id){

        char[] id_numbers = id.toCharArray();
        byte sum = 0;

        if(id_numbers.length != 11)
            return true;
        else{

            for(int i=0; i<10; i++)
                sum += Byte.parseByte(String.valueOf(id_numbers[i]));

            return sum % 10 != Byte.parseByte(String.valueOf(id_numbers[10]));
        }
    }

    private boolean wrongName(String name){

        return name.length() <= 1;
    }

    private boolean wrongMail(String mail){

        return mail.length() > 0 && !mail.contains("@");
    }

    private boolean wrongGsm(String gsm){

        return gsm.length() > 0 && gsm.length() != 10;
    }
}
