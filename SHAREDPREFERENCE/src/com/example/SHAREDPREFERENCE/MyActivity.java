package com.example.SHAREDPREFERENCE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MyActivity extends Activity implements View.OnClickListener
{
    /**
     * Called when the activity is first created.
     */
    private CheckBox checkBox;
    private EditText editText;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);
        editText = (EditText) findViewById(R.id.editText1);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
        loadSavedPreferences();

    }
    public void loadSavedPreferences()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value",false);
        String name = sharedPreferences.getString("storedName","YourName");
        editText.setText(name);
        if (checkBoxValue)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }
    }
    public void savePreferences(String key, boolean value)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        savePreferences("CheckBox_Value", checkBox.isChecked());
        if (checkBox.isChecked())
        savePreferences("storedName", editText.getText().toString());
        finish();
    }
}
