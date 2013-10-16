package com.example.OnlineDio.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.OnlineDio.R;
import com.example.OnlineDio.nevigation.NavigationActivity;

import java.util.ArrayList;

public class HomeActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    ListView lisView;
    private ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lisView = (ListView) findViewById(R.id.lvListSongs);
        ArrayList<Items> listItems = buildData();
        lisView.setAdapter(new ListViewCustomerAdapter(this, listItems));
        ImageButton imageButton = (ImageButton) findViewById(R.id.ibBack);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HomeActivity.this, NavigationActivity.class);
                startActivity(i);
            }
        });

    }

    private ArrayList<Items> buildData()
    {
        ArrayList<Items> listResult = new ArrayList<Items>();
        Items item = new Items("Sound Of Silence", "Mr. Bean", "Liked: 100", "Comment: 9", "s days ago");
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        listResult.add(item);
        return listResult;
    }
}
