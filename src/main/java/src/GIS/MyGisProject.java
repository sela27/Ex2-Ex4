package src.GIS;

import java.util.LinkedList;

public class MyGisProject extends LinkedList<GIS_layer> implements GIS_project
{
    private Meta_data Mdata;

    public MyGisProject()
    {
        this.Mdata = new MyMetaData(System.currentTimeMillis());
    }

    @Override
    public Meta_data get_Meta_data()
    {
        return this.Mdata;
    }
}
