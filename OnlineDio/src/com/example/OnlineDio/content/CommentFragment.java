package com.example.OnlineDio.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.OnlineDio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 17/10/2013
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class CommentFragment extends Fragment
{
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.comment_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.comment_lvComment);
        List<CommentDTO> listComment = new ArrayList<CommentDTO>();
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        listComment.add(new CommentDTO(" ", " ", " "));
        ListCommentAdapter adapter = new ListCommentAdapter(getActivity(), R.layout.comment_row_custom, listComment);
        listView.setAdapter(adapter);
        return view;
    }
}
