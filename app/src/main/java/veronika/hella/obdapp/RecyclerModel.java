package veronika.hella.obdapp;

public class RecyclerModel
{
    private String id;

    private String title;

    private String body;

    private String userId;

    public RecyclerModel(String userId,String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", title = "+title+", body = "+body+", userId = "+userId+"]";
    }
}