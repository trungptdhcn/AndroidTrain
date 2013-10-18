package com.example.OnlineDio;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.OnlineDio.content.CircularImageView;
import com.example.OnlineDio.content.CropOption;
import com.example.OnlineDio.content.CropOptionAdapter;
import com.example.OnlineDio.nevigation.NavigationActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.DialogInterface.OnClickListener;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/17/13
 * Time: 8:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileFragment extends Activity
{
    private EditText dpBirthday;
    private EditText etCountry;
    private int year;
    private int month;
    private int day;
    private Spinner spListCountry;
    private RelativeLayout rlCoverImage;
    private CircularImageView ibProfileIcon;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private ImageButton ibProfileBack;

    private Uri mImageCaptureUri;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;

    private boolean coverOrBackground = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile2);
        final String[] items = new String[]{"Take from camera", "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, items);
        dpBirthday = (EditText) findViewById(R.id.profile_dpBirthday);
        spListCountry = (Spinner) findViewById(R.id.profile_spListCountry);
        etCountry = (EditText) findViewById(R.id.profile_etCountry);
        rlCoverImage = (RelativeLayout) findViewById(R.id.profile_rlCoverImage);
        ibProfileIcon = (CircularImageView) findViewById(R.id.profile_ivAvatar);
        ibProfileBack = (ImageButton) findViewById(R.id.profile_ibBack);

        etCountry.setOnClickListener(clickedCountry);
        dpBirthday.setOnClickListener(setBirthdayDate);
        spListCountry.setOnItemSelectedListener(itemSelect);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image");
        builder.setAdapter(adapter, dialogInterface);
        dialog = builder.create();
        initialCurrentTime();
        rlCoverImage.setOnClickListener(onClickCoverImage);
        ibProfileIcon.setOnClickListener(onClickProfileImage);
        ibProfileBack.setOnClickListener(onClickProfileBackImage);
    }

    private View.OnClickListener onClickProfileBackImage = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            finish();
        }
    };
    private OnClickListener dialogInterface = new OnClickListener()
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            //pick from camera
            if (which == 0)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

                try
                {
                    intent.putExtra("return-data", true);

                    startActivityForResult(intent, PICK_FROM_CAMERA);
                }
                catch (ActivityNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
            else
            { //pick from file
                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
            }
        }

    };
    private View.OnClickListener onClickCoverImage = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            coverOrBackground = true;
            dialog.show();

        }

    };
    private View.OnClickListener onClickProfileImage = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            coverOrBackground = false;
            dialog.show();

        }

    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case PICK_FROM_CAMERA:
                doCrop();

                break;

            case PICK_FROM_FILE:
                mImageCaptureUri = data.getData();

                doCrop();

                break;

            case CROP_FROM_CAMERA:
                Bundle extras = data.getExtras();

                if (extras != null)
                {
                    Bitmap photo = extras.getParcelable("data");
                    if (!coverOrBackground)
                    {
                        ibProfileIcon.setImageBitmap(photo);
                    }
                    else
                    {
                        Drawable d = new BitmapDrawable(getResources(), photo);
                        //rlCoverImage.setBackground(d);
                        rlCoverImage.setBackgroundDrawable(d);
                        coverOrBackground = false;
                    }
                }

                File f = new File(mImageCaptureUri.getPath());

                if (f.exists())
                {
                    f.delete();
                }

                break;

        }
    }

    private void doCrop()
    {
        final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        List<ResolveInfo> list = this.getPackageManager().queryIntentActivities(intent, 0);

        int size = list.size();

        if (size == 0)
        {
            Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();

            return;
        }
        else
        {
            intent.setData(mImageCaptureUri);

            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);

            if (size == 1)
            {
                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);

                i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                startActivityForResult(i, CROP_FROM_CAMERA);
            }
            else
            {
                for (ResolveInfo res : list)
                {
                    final CropOption co = new CropOption();

                    co.title = this.getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
                    co.icon = this.getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
                    co.appIntent = new Intent(intent);

                    co.appIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                    cropOptions.add(co);
                }

                CropOptionAdapter adapter = new CropOptionAdapter(this.getApplicationContext(), cropOptions);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose Crop App");
                builder.setAdapter(adapter, new OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int item)
                    {
                        startActivityForResult(cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
                    }
                });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener()
                {
                    @Override
                    public void onCancel(DialogInterface dialog)
                    {

                        if (mImageCaptureUri != null)
                        {
                            getContentResolver().delete(mImageCaptureUri, null, null);
                            mImageCaptureUri = null;
                        }
                    }
                });

                AlertDialog alert = builder.create();

                alert.show();
            }
        }
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
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }
}
