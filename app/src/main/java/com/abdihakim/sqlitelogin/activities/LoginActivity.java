package com.abdihakim.sqlitelogin.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.abdihakim.sqlitelogin.R;
import com.abdihakim.sqlitelogin.helper.InputValidation;
import com.abdihakim.sqlitelogin.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){

        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);

    }

    private void initListeners(){

        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    public void initObjects(){

        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    @Override

    public  void onClick (View v){

        switch (v.getId()){

            case R.id.appCompatButtonLogin:
                verifyFromSQlite();
                break;

            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);

                break;
                }


    }
    private void verifyFromSQlite (){

 if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email_password_miss))){
     return;
 }
 if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email_password_miss))){
     return;
 }
if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email_empty))){

     return;
}

if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())){

     Intent accountIntent = new Intent(activity, UsersActivity.class);
     accountIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
     emptyInputEditText();
     startActivity(accountIntent);
} else{

    Snackbar.make(nestedScrollView,getString(R.string.error_invalid_email_password),Snackbar.LENGTH_LONG).show();
     }
    }


    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
