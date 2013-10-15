package com.example.OnlineDio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private Button login_btDone;
    private Button login_btBack;
    private EditText login_edEmail;
    private EditText login_edPass;
    private TextView login_tvForgotPass;
    private boolean typedEmail = false;
    private boolean typedPass = false;
    private String dumEmail = "khang";
    private String dumPass = "khang";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_btDone = (Button) findViewById(R.id.login_btDone);
        login_btBack = (Button) findViewById(R.id.login_btBack);
        login_edEmail = (EditText) findViewById(R.id.login_edtEmail);
        login_edPass = (EditText) findViewById(R.id.login_edtPass);

        login_edEmail.addTextChangedListener(textChangeListener);
        login_edPass.addTextChangedListener(textChangeListener);

        login_btDone.setOnClickListener(new ClickListener());

    }

    private TextWatcher textChangeListener = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (!login_edEmail.getText().toString().equals(""))
            {
                typedEmail = true;
            }
            else
            {
                typedEmail = false;
            }
            if (!login_edPass.getText().toString().isEmpty())
            {
                typedPass = true;
            }
            else
            {
                typedPass = false;
            }

            if (typedPass == true && typedEmail == true)
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
                    if (login_edEmail.getText().toString().equals(dumEmail) && login_edPass.getText().toString().equals(dumPass))
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
                        // Setting Icon to Dialog
//                        alertDialog.setIcon(R.drawable.tick);
                        // Setting OK Button
                        alertDialog.setButton("OK", (DialogInterface.OnClickListener) null);
                        alertDialog.show();
                    }

            }
        }
    }
}

