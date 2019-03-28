package com.abdihakim.sqlitelogin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.abdihakim.sqlitelogin.R;
import com.abdihakim.sqlitelogin.helper.InputValidation;
import com.abdihakim.sqlitelogin.model.User;
import com.abdihakim.sqlitelogin.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout  textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton  appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override

    protected void onCreate (Bundle savedInstanceState){

   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_register);


   initViews();
   initListeners();
   initObjects();

    }

    private void initViews(){

        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListeners(){
    appCompatButtonRegister.setOnClickListener(this);
    appCompatTextViewLoginLink.setOnClickListener(this);

    }

    private void initObjects(){

        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    public void postDataToSQLite(){ // The problem is here

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName,getString(R.string.error_message_name))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword,getString(R.string.error_message_password))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email))){
            return ;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword,getString(R.string.error_message_password))){
            return;
        }

        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snackbar shows the success that the record has been saved successfully
            Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else {

            Snackbar.make(nestedScrollView,getString(R.string.error_email_exist),Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){

        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
