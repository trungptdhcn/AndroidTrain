package com.example.OnlineDio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    ListView lisView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lisView = (ListView) findViewById(R.id.lvListSongs);
        ArrayList<Items> listItems = buildData();
        lisView.setAdapter(new ListViewCustomer(this, listItems));

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
