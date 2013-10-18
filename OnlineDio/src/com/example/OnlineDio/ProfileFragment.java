package com.example.OnlineDio;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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
    private RelativeLayout rlCoverImage;
    private ImageView ibProfileIcon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.profile2,
                container, false);
        dpBirthday = (EditText) view.findViewById(R.id.profile_dpBirthday);
        spListCountry = (Spinner) view.findViewById(R.id.profile_spListCountry);
        etCountry = (EditText) view.findViewById(R.id.profile_etCountry);
        rlCoverImage = (RelativeLayout) view.findViewById(R.id.profile_rlCoverImage);
        ibProfileIcon = (ImageView) view.findViewById(R.id.profile_ivAvatar);

        etCountry.setOnClickListener(clickedCountry);
        dpBirthday.setOnClickListener(setBirthdayDate);
        spListCountry.setOnItemSelectedListener(itemSelect);
        initialCurrentTime();
        rlCoverImage.setOnClickListener(onClickCoverImage);
        ibProfileIcon.setOnClickListener(onClickCoverImage);

        return view;
    }

    private View.OnClickListener onClickCoverImage = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "content://media/internal/images/media"));
            startActivity(intent);*/
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == 1234)
            {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);

                int height = ibProfileIcon.getHeight();
                int width = ibProfileIcon.getWidth();
                ibProfileIcon.setImageURI(selectedImageUri);
                //ibProfileIcon.setLayoutParams(new LinearLayout.LayoutParams(width, height));
            }
        }

    }

    private String getPath(Uri selectedImageUri)
    {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(selectedImageUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void initialCurrentTime()
    {
        final Calendar c = Calendar.getInstance();
        year = 1990;
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
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
        return new DatePickerDialog(getActivity(), datePickerListener, year, month, day);
    }
}
