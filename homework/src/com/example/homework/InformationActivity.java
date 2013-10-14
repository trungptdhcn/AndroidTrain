package com.example.homework;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 09/10/2013
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class InformationActivity extends Activity
{
    private String info_name;
    private TextView info_tvTitle;
    private TextView info_tvAddress;
    private TextView info_tvGender;
    private ListView info_listview_friendList;
    private ArrayAdapter arrayAdapter;
    private CheckBox info_checkBox;
    private TextView info_tvBirthDay;
    private TextView info_tvOnline;
    private Button info_btLogout;
    private ImageView info_image;
    private RadioGroup radioGroup;
    private RadioButton radioButton_male;
    private RadioButton radioButton_female;
    static final int DATE_DIALOG_ID = 999;
    final List<String[]> friendList = new LinkedList<String[]>();
    int[] displayViews = new int[]{android.R.id.text1,
            android.R.id.text2};

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        friendList.add(new String[]{"Name: Peter", "Phone: 123456789"});
        friendList.add(new String[]{"Name: Marry", "Phone: 789456123"});
        friendList.add(new String[]{"Name: Peter", "Phone: 789456123"});
        friendList.add(new String[]{"Name: Marry", "Phone: 789456123"});

        info_tvTitle = (TextView) findViewById(R.id.info_tvTitle);
        info_name = getIntent().getStringExtra("username").toString();
        info_tvTitle.setText("Hi, " + info_name);

        info_tvAddress = (TextView) findViewById(R.id.info_tvAddress);
        info_tvAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialogViewSetAddress();

            }
        });
        info_tvGender = (TextView) findViewById(R.id.info_tvGender);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        info_tvGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                AlertDialogViewSetGender();
                radioGroup.setVisibility(View.VISIBLE);
            }
        });
        radioButton_female = (RadioButton)findViewById(R.id.radioButton_female);
        radioButton_male = (RadioButton)findViewById(R.id.radioButton_male);
        radioButton_female.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                info_tvGender.setText("Gender: Female");
                radioGroup.setVisibility(View.GONE);
            }
        });
        radioButton_male.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                info_tvGender.setText("Gender: Male");
                radioGroup.setVisibility(View.GONE);
            }
        });

        info_listview_friendList = (ListView) findViewById(R.id.info_listview_friendList);
        ArrayList<Map<String, String>> list = buildData();
        String[] from = { "name", "phone" };
        int[] to = { android.R.id.text1, android.R.id.text2 };

        final SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2, from, to);
        info_checkBox = (CheckBox) findViewById(R.id.info_cbListFriend);
        info_checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked())
                {
                    info_listview_friendList.setAdapter(adapter);
                }
                else
                {
                    info_listview_friendList.setAdapter(null);
                }
            }
        });

        info_tvBirthDay = (TextView) findViewById(R.id.info_tvBirthday);
        info_tvBirthDay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setDateBirthDay();
            }
        });
        info_tvOnline = (TextView) findViewById(R.id.info_tvOnline);
        info_tvOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setTimeOnline();
            }
        });
        info_btLogout = (Button)findViewById(R.id.info_btLogout);
        info_btLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(InformationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        info_image = (ImageView)findViewById(R.id.info_imvAvata);
        info_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                i.setType("image/*");


                startActivityForResult(i, 1);
            }
        });

    }
    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(putData("Android", "Mobile"));
        list.add(putData("Windows7", "Windows7"));
        list.add(putData("iPhone", "iPhone"));
        return list;
    }
    private HashMap<String, String> putData(String name, String phone) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("phone", phone);
        return item;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri u = (Uri) data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(u, filePathColumn, null,
                    null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            cursor.close();
            info_image.setImageURI(u);
        }
    }


    private void AlertDialogViewSetAddress()
    {
        final String[] result = {new String()};
        final CharSequence[] items = {"Ha Noi", "Ha Nam", "Ha Tay", "Quang Ninh", "Bac Ninh"};
        final String[] temp = {new String()};


        AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int item)
                    {
                        Toast.makeText(getApplicationContext(), items[item],
                                Toast.LENGTH_SHORT).show();
                        StringBuilder tempStringBuilder = new StringBuilder(items[item].length());
                        tempStringBuilder.append(items[item]);
                        temp[0] = tempStringBuilder.toString();
                    }
                });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                result[0] = temp[0];
                info_tvAddress.setText("Address: " + result[0]);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void AlertDialogViewSetGender()
    {
        final String[] result = {new String()};
        final CharSequence[] items = {"Male", "Female"};
        final String[] temp = {new String()};


        AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int item)
                    {
                        Toast.makeText(getApplicationContext(), items[item],
                                Toast.LENGTH_SHORT).show();
                        StringBuilder tempStringBuilder = new StringBuilder(items[item].length());
                        tempStringBuilder.append(items[item]);
                        temp[0] = tempStringBuilder.toString();
                    }
                });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                result[0] = temp[0];
                info_tvGender.setText("Gender: " + result[0]);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setDateBirthDay()
    {
        int year, month, day;
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        DateSetListener dateSetListener = new DateSetListener();
        DatePickerDialog datedialog = new
                DatePickerDialog(InformationActivity.this, 0,
                dateSetListener, year, month, day);
        datedialog.show();
    }

    class DateSetListener implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear,
                              int dayOfMonth)
        {
            info_tvBirthDay.setText("BirthDay: " + dayOfMonth + "/" + monthOfYear + "/" + year);

        }

    }
    public void setTimeOnline()
    {
        int hour, minute, second;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);
        TimeSetListener timeSetListener = new TimeSetListener();
        TimePickerDialog timeDialog = new
                TimePickerDialog(InformationActivity.this,0,timeSetListener,hour, minute, true);
        timeDialog.show();
    }

    class TimeSetListener implements  TimePickerDialog.OnTimeSetListener
    {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {

            info_tvOnline.setText("Online: "+ hourOfDay+ ":" + minute );
        }
    }
}