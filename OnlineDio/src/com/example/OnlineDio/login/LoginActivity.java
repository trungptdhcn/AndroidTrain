package com.example.OnlineDio.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.example.OnlineDio.nevigation.NavigationActivity;
import com.example.OnlineDio.R;

public class LoginActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private Button login_btDone;
    private Button login_btBack;
    private EditText login_edEmail;
    private EditText login_edPass;
    private ImageButton login_iv_cancelOfEmail;
    private ImageButton login_iv_cancelOfPass;
    private TextView login_tvForgotPass;
    private boolean typedEmail = false;
    private boolean typedPass = false;
    private String dumEmail = "khang@gmail.com";
    private String dumPass = "khang";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_btDone = (Button) findViewById(R.id.login_btDone);
        login_btBack = (Button) findViewById(R.id.login_btBack);
        login_edEmail = (EditText) findViewById(R.id.login_et_email);
        login_edPass = (EditText) findViewById(R.id.login_et_Pass);

        login_iv_cancelOfEmail = (ImageButton) findViewById(R.id.login_ib_cancelOfEmail);
        login_iv_cancelOfPass = (ImageButton) findViewById(R.id.login_ib_cancelOfPass);

        login_edEmail.addTextChangedListener(textChangeListener);
        login_edPass.addTextChangedListener(textChangeListener);

        login_edEmail.setOnFocusChangeListener(focusChange);
        login_edPass.setOnFocusChangeListener(focusChange);

        login_iv_cancelOfEmail.setOnClickListener(cancelOfBtEmail);
        login_iv_cancelOfPass.setOnClickListener(cancelOfBtPass);

        login_btDone.setOnClickListener(new ClickListener());

    }

    private View.OnFocusChangeListener focusChange = new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            switch (v.getId())
            {
                case R.id.login_et_email:
                    if (!hasFocus)
                    {
                        login_iv_cancelOfEmail.setVisibility(View.INVISIBLE);
                        if (!login_edEmail.getText().toString().isEmpty() && new EmailValidator().validate(login_edEmail.getText().toString()) == false)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                    else
                    {
                        if (!login_edEmail.getText().toString().equals(""))
                        {
                            login_iv_cancelOfEmail.setVisibility(View.VISIBLE);

                        }
                    }
                    break;

                case R.id.login_et_Pass:
                    if (!hasFocus)
                    {
                        login_iv_cancelOfPass.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        if (!login_edPass.getText().toString().isEmpty())
                        {
                            login_iv_cancelOfPass.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
            }
        }
    };
    private View.OnClickListener cancelOfBtEmail = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            login_edEmail.setText("");
        }
    };
    private View.OnClickListener cancelOfBtPass = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            login_edPass.setText("");
        }
    };
    private TextWatcher textChangeListener = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (!login_edEmail.getText().toString().equals("") && login_edEmail.isFocused() == true)
            {
                typedEmail = true;
                login_iv_cancelOfEmail.setVisibility(View.VISIBLE);
            }
            else
            {
                login_iv_cancelOfEmail.setVisibility(View.INVISIBLE);

            }
            if (!login_edPass.getText().toString().isEmpty() && login_edPass.isFocused() == true)
            {
                login_iv_cancelOfPass.setVisibility(View.VISIBLE);
                typedPass = true;
            }
            else
            {
                login_iv_cancelOfPass.setVisibility(View.INVISIBLE);

            }

            if (new EmailValidator().validate(login_edEmail.getText().toString()) && !login_edEmail.getText().toString().isEmpty() && !login_edPass.getText().toString().isEmpty())
            {
                login_btDone.setEnabled(true);
                login_btDone.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button_done));
            }
            else
            {
                login_btDone.setEnabled(false);
                login_btDone.setBackgroundDrawable(getResources().getDrawable(R.drawable.soundboard_btn_done_selected));
            }
        }
    };

    public void validateAccount()
    {

    }

    class ClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.login_btDone:
                    if (new EmailValidator().validate(login_edEmail.getText().toString()) && login_edEmail.getText().toString().equals(dumEmail) && login_edPass.getText().toString().equals(dumPass))
                    {
                        Intent i = new Intent(LoginActivity.this, NavigationActivity.class);
                        startActivity(i);
                    }

                    else
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                        // Setting Dialog Title
                        alertDialog.setTitle("Error Singing In");

                        // Setting Dialog Message
                        alertDialog.setMessage("There is no connection  to the internet ");

                        // Setting OK Button
                        alertDialog.setButton("OK", (DialogInterface.OnClickListener) null);
                        alertDialog.show();
                    }

            }
        }
    }
}

