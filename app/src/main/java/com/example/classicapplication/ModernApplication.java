package com.example.classicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModernApplication extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextCity;
    RadioGroup radioGroup;
    EditText mMobileNumber;
    EditText mTextBloodGroup;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    Spinner spinner_d;
    String names[] ={"Donor", "Reciever"};
    ArrayAdapter<String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_application);

        db = new DatabaseHelper(this);

        spinner_d=(Spinner)findViewById(R.id.spinner_dropdown);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        spinner_d.setAdapter(arrayAdapter);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextCity = (EditText)findViewById(R.id.edittext_city);
        mMobileNumber = (EditText)findViewById(R.id.edittext_mobilenumber);
        mTextBloodGroup = (EditText)findViewById(R.id.edittext_blood);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        radioGroup = (RadioGroup)findViewById(R.id.gender);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        spinner_d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Form: "+names[i],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(ModernApplication.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String city = mTextCity.getText().toString().trim();
                String blood = mTextBloodGroup.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                 RadioButton checkedBtn = findViewById(radioGroup.getCheckedRadioButtonId());
                String gender = checkedBtn.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                   long val = db.adduser(user,pwd);
                   if(val>0){
                       Toast.makeText(ModernApplication.this,"You have registered",Toast.LENGTH_SHORT).show();
                       Intent moveToMain = new Intent(ModernApplication.this,MainActivity.class);
                       startActivity(moveToMain);
                   }
                   else{
                       Toast.makeText(ModernApplication.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                   }
                }
                else{
                    Toast.makeText(ModernApplication.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
