package com.example.OnlineDio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity
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
        login_btDone = (Button)findViewById(R.id.login_btDone);
        login_btBack  = (Button)findViewById(R.id.login_btBack);
//        login_edtEmail = (EditText)findViewById(R.id.login_edtEmail);
//        login_edtPass = (EditText)findViewById(R.id.login_edtPass);

    }
    public void validateAccount()
    {

    }
}
