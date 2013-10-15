package com.example.OnlineDio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private Button login_btDone;
    private Button login_btBack;
    private EditText login_edtEmail;
    private EditText login_edtPass;
    private TextView login_tvForgotPass;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_btDone = (Button) findViewById(R.id.login_btDone);
        login_btBack = (Button) findViewById(R.id.login_btBack);
        login_edtEmail = (EditText) findViewById(R.id.login_edtEmail);
        login_edtPass = (EditText) findViewById(R.id.login_edtPass);

        login_btDone.setOnClickListener(new ClickListener());

    }

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
                    Intent i = new Intent(LoginActivity.this,NavigationActivity.class);
                    startActivity(i);
            }
        }
    }
}

