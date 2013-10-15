package com.example.OnlineDio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 15/10/2013
 * Time: 08:56
 * To change this template use File | Settings | File Templates.
 */
public class LauchActivity extends Activity
{
    private Button lauch_btloginNormal;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lauch);
        lauch_btloginNormal = (Button)findViewById(R.id.lauch_btloginNormal);
        lauch_btloginNormal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(LauchActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}