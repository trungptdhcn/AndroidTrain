package com.example.OnlineDio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.OnlineDio.R;
import com.example.OnlineDio.model.SongDTO;
import com.example.OnlineDio.util.ListViewCustomerAdapter;

import java.util.ArrayList;

public class HomeActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    ListView lisView;
    private ImageButton home_ibOption;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lisView = (ListView) findViewById(R.id.lvListSongs);
        ArrayList<SongDTO> listItems = buildData();
        lisView.setAdapter(new ListViewCustomerAdapter(this, listItems));
        ImageButton home_ibOption = (ImageButton) findViewById(R.id.ibOption);
        home_ibOption.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HomeActivity.this, LauchActivity.class);
                startActivity(i);
            }
        });

    }

    private ArrayList<SongDTO> buildData()
    {
        ArrayList<SongDTO> listResult = new ArrayList<SongDTO>();
        SongDTO item = new SongDTO("Sound Of Silence", "Mr. Bean", "Liked: 100", "Comment: 9", "s days ago");
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
