package com.abdihakim.sqlitelogin.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Pattern;

public class InputValidation {
    // This is where we try to validate the form

    private Context context; // set of circumstances that is around a particular event
    // We should create a constractor
    public InputValidation(Context context){
        this.context = context;
    }
    // This method is to check the edit text field

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout,String message){
        // This method takes in text input layout and the edit text
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
          textInputEditText.setError(message);
          hideKeyboardFrom(textInputEditText);
        }else{

            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){

        String value = textInputEditText.getText().toString().trim();

        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){

            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }else{

            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2,TextInputLayout textInputLayout , String message){
String value1 = textInputEditText1.getText().toString().trim();
String value2 = textInputEditText2.getText().toString().trim();

  if(!value1.contentEquals(value2)){

      textInputLayout.setError(message);
      hideKeyboardFrom(textInputEditText2);

      return false;
  }else{

      textInputLayout.setErrorEnabled(false);
  }
return true;
    }

    private void hideKeyboardFrom(View view){
// hides keyboard whenver this methods show
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
