package com.example.OnlineDio;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/17/13
 * Time: 8:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileFragment extends Fragment
{
    private EditText dpBirthday;
    private EditText etCountry;
    private int year;
    private int month;
    private int day;
    private Spinner spListCountry;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile2,
                container, false);
        dpBirthday = (EditText) view.findViewById(R.id.profile_dpBirthday);
        spListCountry = (Spinner) view.findViewById(R.id.profile_spListCountry);
        etCountry = (EditText) view.findViewById(R.id.profile_etCountry);

        etCountry.setOnClickListener(clickedCountry);
        dpBirthday.setOnClickListener(setBirthdayDate);
        spListCountry.setOnItemSelectedListener(itemSelect);

        return view;
    }

    private View.OnClickListener clickedCountry = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            spListCountry.performClick();
        }
    };
    private View.OnClickListener setBirthdayDate = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            onCreateDialog().show();
        }
    };
    private AdapterView.OnItemSelectedListener itemSelect = new AdapterView.OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            etCountry.setText(spListCountry.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener()
    {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay)
        {

            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;

            dpBirthday.setText(new StringBuilder().append("").append(selectedDay)
                    .append(":").append(selectedMonth + 1).append(":").append(selectedYear)
                    .append(" "));
        }

    };

    protected Dialog onCreateDialog()
    {
        final Calendar c = Calendar.getInstance();
        Log.d("Date", c.get(Calendar.YEAR) + "");
        return new DatePickerDialog(getActivity(), datePickerListener, 1990, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }
}
