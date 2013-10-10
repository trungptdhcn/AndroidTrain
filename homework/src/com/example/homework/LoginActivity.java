package com.example.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    private Button loginButton;
    private EditText edtUserName;
    private EditText edtPassword;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginButton = (Button) findViewById(R.id.login_btLogin);
        edtUserName = (EditText) findViewById(R.id.login_edtUsername);
        edtPassword = (EditText) findViewById(R.id.login_edtPassword);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(LoginActivity.this, InformationActivity.class);
                if (edtUserName.getText().toString().equals("trungpt") && edtPassword.getText().toString().equals("123456"))
                {
                    i.putExtra("username", edtUserName.getText().toString());
                    i.putExtra("pass", edtPassword.getText());
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Username or password incorrect",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
