package com.example.OnlineDio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.OnlineDio.content.ContentFragment;
import com.example.OnlineDio.home.Items;
import com.example.OnlineDio.home.ListViewCustomerAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 14/10/2013
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class HomeFragment extends Fragment
{
    ListView lisView;
    private ImageButton home_ibOption;
    private LinearLayout layoutDrawer;


    public static Fragment newInstance(Context context)
    {
        HomeFragment f = new HomeFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home, container, false);
        lisView = (ListView) view.findViewById(R.id.lvListSongs);
        home_ibOption = (ImageButton) view.findViewById(R.id.ibOption);
        layoutDrawer = (LinearLayout) getActivity().findViewById(R.id.left_drawer);
        ArrayList<Items> listItems = buildData();
        lisView.setAdapter(new ListViewCustomerAdapter(getActivity(), listItems));
        lisView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id)
            {
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.navigation_main_FrameLayout, new ContentFragment());
                tx.addToBackStack(null);
                tx.commit();
            }
        });
        final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.navigation_drawer_layout);
        home_ibOption.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawer.openDrawer(layoutDrawer);
            }
        });

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
