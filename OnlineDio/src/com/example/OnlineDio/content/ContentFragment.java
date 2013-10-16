package com.example.OnlineDio.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.OnlineDio.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 16/10/2013
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class ContentFragment extends Fragment
{
    public static Fragment newInstance(Context context)
    {
        Fragment f = new ContentFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_layout, container, false);
        return view;
    }
}
