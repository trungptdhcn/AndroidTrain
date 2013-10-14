package com.example.OnlineDio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 14/10/2013
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class FavoriteFragment extends Fragment
{
    ListView lisView;
    private ImageButton imageButton;
    public static Fragment newInstance(Context context) {
        FavoriteFragment f = new FavoriteFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        lisView = (ListView)view.findViewById(R.id.lvListSongs);
        ArrayList<Items> listItems = buildData();
        lisView.setAdapter(new ListViewCustomer(getActivity(), listItems));
        return view;
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
