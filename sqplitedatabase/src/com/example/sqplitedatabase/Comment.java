package com.example.sqplitedatabase;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 22/10/2013
 * Time: 08:07
 * To change this template use File | Settings | File Templates.
 */
public class Comment
{
    private Long id;
    private String comment;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
