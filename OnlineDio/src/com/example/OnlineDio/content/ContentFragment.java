package com.example.OnlineDio.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
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
    private static final String FLAG_DETAIL = "Detail";
    private static final String FLAG_THUMBNAIL = "Thumbnail";
    private static final String FLAG_COMMENT = "Comment";
    private RadioButton content_rbThumbnail;
    private RadioButton content_rbDetail;
    private RadioButton content_rbComment;
    private Button content_btnPlay;
    private FrameLayout content_frame_layout;
    boolean check_Detail = false;
    boolean check_Thumbnail = true;
    boolean check_Comment = false;
    private String flag;
    boolean check_play = true;

    public static Fragment newInstance(Context context)
    {
        Fragment f = new ContentFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_layout, container, false);
        content_rbComment = (RadioButton) view.findViewById(R.id.content_rbComment);
        content_rbDetail = (RadioButton) view.findViewById(R.id.content_rbDetail);
        content_btnPlay = (Button) view.findViewById(R.id.content_btnPlay);
        content_rbThumbnail = (RadioButton) view.findViewById(R.id.content_rbThumbnail);
        content_rbComment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                CommentFragment commentFragment = new CommentFragment();
                tx.replace(R.id.content_frame_layout, commentFragment);
                tx.commit();
            }
        });
        content_rbDetail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                DetailFragment detailFragment = new DetailFragment();
                tx.replace(R.id.content_frame_layout, detailFragment);
                tx.commit();
            }
        });
        content_rbThumbnail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                ThumbnailFragment thumbnailFragment = new ThumbnailFragment();
                tx.replace(R.id.content_frame_layout, thumbnailFragment);
                tx.commit();
            }
        });
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        ThumbnailFragment thumbnailFragment = new ThumbnailFragment();
        tx.replace(R.id.content_frame_layout, thumbnailFragment);
        tx.commit();
        content_btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (check_play == true)
                {
                    content_btnPlay.setBackground(getResources().getDrawable(R.drawable.content_buttonplay));
                    check_play = false;
                }
                else
                {
                    content_btnPlay.setBackground(getResources().getDrawable(R.drawable.content_buttonpause));
                    check_play = true;
                }
            }
        });

//        content_btThumbnail = (Button)view.findViewById(R.id.content_btThumbnail);
//        content_btThumbnail.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                FragmentTransaction tx = getFragmentManager().beginTransaction();
//                ThumbnailFragment thumbnailFragment = new ThumbnailFragment();
//                tx.replace(R.id.content_frame_layout,thumbnailFragment);
//                tx.commit();
//            }
//        });
        return view;
    }

}
